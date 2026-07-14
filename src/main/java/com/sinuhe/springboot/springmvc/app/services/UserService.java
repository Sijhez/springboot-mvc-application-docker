package com.sinuhe.springboot.springmvc.app.services;

import com.sinuhe.springboot.springmvc.app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // los mismos metodos posiblebemnte que el userRepository (CRUD)

    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void removeUser(Long id);



}
