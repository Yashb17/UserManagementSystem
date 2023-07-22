package com.userSystem.Service.Impl;

import com.userSystem.Dto.UserDTO;
import com.userSystem.Entity.User;
import com.userSystem.Mapper.UserMapper;
import com.userSystem.Repository.UserRepository;
import com.userSystem.Service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        LOGGER.info("@@@@ UserServiceImpl: Inside createUser method for creating a new user with data-> {}", userDTO);
        User user = userMapper.toUser(userDTO);
        User createdUser = userRepository.save(user);
        UserDTO createdUserDTO = userMapper.toUserDTO(createdUser);
        LOGGER.info("@@@@ UserServiceImpl: Created User -> {}", userMapper.mapForLogging(createdUserDTO));
        return createdUserDTO;
    }

    @Override
    public Optional<UserDTO> findUserDTOById(Long id) {
        LOGGER.info("@@@@ UserServiceImpl: Inside findUserById method trying to fetch existing user by Id: {}", id);
        User user = userRepository.findById(id).orElse(null);
        return Optional.ofNullable(userMapper.toUserDTO(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        LOGGER.info("@@@@ UserServiceImpl: Inside getAllUsers method to fetch all users");
        List<User> user = userRepository.findAll();
        return user.stream().map(e -> userMapper.toUserDTO(e)).toList();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        LOGGER.info("@@@@ UserServiceImpl: Inside updateUser method trying to find existing user by Id: {}", userDTO.getId());
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if(Objects.nonNull(existingUser)) {
            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setEmailId(userDTO.getEmailId());
            User updatedUser = userRepository.save(existingUser);
            UserDTO updatedUserDTO = userMapper.toUserDTO(updatedUser);
            LOGGER.info("@@@@ UserServiceImpl: Updated User -> {}", userMapper.mapForLogging(updatedUserDTO));
            return updatedUserDTO;
        }
        else{
            return null;
        }
    }

    @Override
    public Boolean deleteUser(UserDTO userDTO) {
        LOGGER.info("@@@@ UserServiceImpl: Inside deleteUser method trying to delete existing user by Id: {}", userDTO.getId());
        User existingUser = findUserById(userDTO.getId());
        if (Objects.nonNull(existingUser)) {
            LOGGER.info("@@@@ UserServiceImpl: Found user!. Going to delete it");
            userRepository.deleteById(existingUser.getId());
            return Boolean.TRUE;
        }
        else{
            LOGGER.info("@@@@ UserServiceImpl:User Not Found!");
        }
        return Boolean.FALSE;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
