package com.thomas.final_project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
/*
@Component
public class DataLoader implements CommandLineRunner
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings)
    {
        roleRepository.save(new Role("USER"));
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("Jim", "Kaine", "lalaland@gmail.com", "jim1", "kainellwyn", true, null);
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("Kim", "Washington", "google@gmail.com", "kim1", "washington", true, null);
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

    }
}
*/