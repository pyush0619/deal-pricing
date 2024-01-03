package com.dealpricing.service;

import com.dealpricing.entity.User;
import com.dealpricing.exception.UserNotFoundException;
import com.dealpricing.model.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface IUserService {
    public List<User> getAllUsers();

    public User getUserById(Long emp_Code);


    public User addUser(UserDTO user);


    public User updateUser(Long emp_Code, UserDTO userdto);

    public void deleteUser(Long emp_Code);

    public User partialUpdateEmployee(Long emp_Code, Map<String, Object> fields);


}
