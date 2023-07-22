package com.userSystem.Service;

import com.userSystem.Controller.UserController;
import com.userSystem.Dto.UserDTO;
import com.userSystem.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService{

    UserDTO createUser(UserDTO userDTO);

    Optional<UserDTO> findUserDTOById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO);

    Boolean deleteUser(UserDTO userDTO);
}
