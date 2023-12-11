package com.arianit.bakery.controllers;

import com.arianit.bakery.dto.UserDTO;
import com.arianit.bakery.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        return userService.getUserById(userId)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createdUser = UserDTO.fromEntity(userService.createUser(userDTO));
        return new ResponseEntity<>(createdUser,HttpStatus.OK);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<UserDTO> delteUser(@PathVariable Long userId){
        try {
            userService.deleteUserById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatePassword/{userId}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody String password) {
        boolean updated = userService.updatePassword(userId, password);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/updateEmail/{userId}")
    public ResponseEntity<Void> updateEmail(@PathVariable Long userId, @RequestBody String email) {
        boolean updated = userService.updateEmail(userId, email);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
