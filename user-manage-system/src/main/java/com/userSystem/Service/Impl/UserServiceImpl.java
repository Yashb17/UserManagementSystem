package com.userSystem.Service.Impl;

import com.userSystem.Entity.User;
import com.userSystem.Repository.UserRepository;
import com.userSystem.Service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
        LOGGER.info("@@@@ UserServiceImpl: Inside createUser method for creating a new user with data-> {}", user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        LOGGER.info("@@@@ UserServiceImpl: Inside findUserById method trying to fetch existing user by Id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("@@@@ UserServiceImpl: Inside getAllUsers method to fetch all users");
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        LOGGER.info("@@@@ UserServiceImpl: Inside updateUser method trying to find existing user by Id: {}", user.getId());
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if(Objects.nonNull(existingUser)) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmailId(user.getEmailId());
            User updatedUser = userRepository.save(existingUser);
            LOGGER.info("@@@@ UserServiceImpl: Updated user -> {}", updatedUser);
            return updatedUser;
        }
        else{
            return null;
        }
    }

    @Override
    public Boolean deleteUser(User user) {
        LOGGER.info("@@@@ UserServiceImpl: Inside deleteUser method trying to delete existing user by Id: {}", user.getId());
        User existingUser = findUserById(user.getId()).orElse(null);
        if (Objects.nonNull(existingUser)) {
            LOGGER.info("@@@@ UserServiceImpl: Found user!. Going to delete it");
            userRepository.deleteById(user.getId());
            return Boolean.TRUE;
        }
        else{
            LOGGER.info("@@@@ UserServiceImpl:User Not Found!");
        }
        return Boolean.FALSE;
    }
}
