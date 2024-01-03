package com.dealpricing.controller;

import com.dealpricing.entity.User;
import com.dealpricing.exception.UserNotFoundException;
import com.dealpricing.model.UserDTO;
import com.dealpricing.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    public IUserService userService;

    @GetMapping(value= "/user")
    public ResponseEntity <List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping(value="/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException{
        User user =userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value="/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDTO){
        User savedUser = userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping(value="/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException{
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO user) throws UserNotFoundException{
        User updateUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping(value="/user/{id}")
    public ResponseEntity<User> partialUpdateEmployee(@PathVariable Long id,@Valid @RequestBody Map<String,Object> fields) throws  UserNotFoundException
    {
        User user=userService.getUserById(id);
        if(user!=null)
        {
            User updatedEmp=userService.partialUpdateEmployee(id,fields);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmp);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
