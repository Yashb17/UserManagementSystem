package com.userSystem.Service;

import com.userSystem.Controller.UserController;
import com.userSystem.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService{

    User createUser(User user);

    Optional<User> findUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    Boolean deleteUser(User user);
}
