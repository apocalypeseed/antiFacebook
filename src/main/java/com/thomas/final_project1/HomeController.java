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
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController
{
    @Autowired
    UserRepository userRepository;

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
            userRepository.save(user);
            return "listPosts";
        }
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/secure")
    public String secure()
    {
        return "secure";
    }

    @RequestMapping("/base")
    public String base() { return "base"; }

    @GetMapping("/postReply")
    public String postReply(Model model)
    {
        model.addAttribute("post", new Post());
        return "postReply";
    }

    @PostMapping("/process")
    public String loadReplies(@ModelAttribute Post post)
    {
        User user = userRepository.findByUsername(post.getUsername());

        //Retrieves specific user from user repository and updates his postings
        //Then puts user back into userRepository
        if(user != null && user.getPassword().equals(post.getPassword()))
        {
            Set<Post> posts = new HashSet<Post>();
            post.setUser(user);
            postRepository.save(post);

            return "redirect:/listPosts";
        }

        return "postReply";
    }

    @RequestMapping("/listPosts")
    public String listOfPosts(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "listPosts";
    }
}
