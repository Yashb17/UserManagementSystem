package com.userSystem.Controller;

import com.userSystem.Entity.User;
import com.userSystem.Service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/User/")
public class UserController {
    @Autowired
    private IUserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    //Post Request - createuser  http://localhost:8080/api/User/createUser
    @PostMapping("createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        LOGGER.info("@@@@ UserController: Received create user Request with User: {}", user);
        User userSaved = userService.createUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    //Get Request - findById  http://localhost:8080/api/User/findUser?id=1
    @GetMapping("/findUser")
    public User findUserById(@RequestParam Long id){
        LOGGER.info("@@@@ UserController: Received find user Request with id: {}", id);
        return userService.findUserById(id).orElse(null);
    }

    //Get Request - getAllUsers  http://localhost:8080/api/User/getAllUsers
    @GetMapping("/getAllUsers")
    public List<User> findUserById(){
        LOGGER.info("@@@@ UserController: Received get All users request");
        return userService.getAllUsers();
    }

    //Post Request - updateUser  http://localhost:8080/api/User/updateUser
    @PostMapping("updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        LOGGER.info("@@@@ UserController: Received update user Request with User: {}", user);
        User userSaved = userService.updateUser(user);
        return userSaved != null ? new ResponseEntity<>(userSaved, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //Delete Request - deleteUser  http://localhost:8080/api/User/deleteUser
    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        LOGGER.info("@@@@ UserController: Received delete user Request for User: {}", user);
        Boolean userDeleted = userService.deleteUser(user);
        return userDeleted != Boolean.FALSE ? new ResponseEntity<>("User deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Ohh No,User doesn't exist in database!", HttpStatus.OK);
    }
}
