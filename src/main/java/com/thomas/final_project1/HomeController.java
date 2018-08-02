package com.thomas.final_project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController
{
    @Autowired
    private UserService userService;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        model.addAttribute("user", user);

        if(result.hasErrors())
        {
            return "registration";
        }
        else
        {
            userService.saveUser(user);
            return "redirect:/login";
        }
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/postReply")
    public String postReply(Model model)
    {
        model.addAttribute("post", new Post());
        return "postReply";
    }

    @PostMapping("/process")
    public String loadReplies(@ModelAttribute Post post, Principal principal)
    {
        if(principal.getName().isEmpty())
            return "redirect:/login";

        Set<Post> posts = new HashSet<Post>();
        post.setUser(userService.findByUsername(principal.getName()));
        postRepository.save(post);

        return "redirect:/";
    }

    @RequestMapping("/")
    public String listOfPosts(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "listPosts";
    }

    @RequestMapping("/signOut")
    public String signOut()
    {
        //Remove user from active session
        return "signOut";
    }
}
