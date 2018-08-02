package com.thomas.final_project1;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
    User findByPassword(String password);
    User findByEmail(String email);
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    Long countByUsername(String username);
    Long countByPassword(String password);
    Long countByEmail(String email);
    Iterable<User> findAll();
}

