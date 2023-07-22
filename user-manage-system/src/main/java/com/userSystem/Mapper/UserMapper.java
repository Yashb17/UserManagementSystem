package com.userSystem.Mapper;

import com.userSystem.Dto.UserDTO;
import com.userSystem.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    public UserDTO toUserDTO(User user){
        LOGGER.info("@@@@ UserMapper: mapping from user to userDTO");
        requireNonNull(user);
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailId());
    }

    public User toUser(UserDTO userDTO){
        LOGGER.info("@@@@ UserMapper: mapping from userDTO to user");
        requireNonNull(userDTO);
        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmailId());
    }

    public String mapForLogging(UserDTO userDTO){
        String initialString = "";
        return initialString.concat("Id = ").concat(userDTO.getId().toString()).
                concat(", ").concat("First Name = ").concat(userDTO.getFirstName().concat(", ").
                                concat(", ").concat("Last Name = ").concat(userDTO.getLastName()).
                                        concat(", ").concat("Email Id = ").concat(userDTO.getEmailId()));
    }
}
