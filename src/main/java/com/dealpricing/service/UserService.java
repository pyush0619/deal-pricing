package com.dealpricing.service;

import com.dealpricing.entity.Address;
import com.dealpricing.entity.Education;
import com.dealpricing.entity.Technology;
import com.dealpricing.entity.User;
import com.dealpricing.exception.UserNotFoundException;
import com.dealpricing.model.UserDTO;
import com.dealpricing.repository.AddressRepo;
import com.dealpricing.repository.EducationRepo;
import com.dealpricing.repository.TechnologyRepo;
import com.dealpricing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TechnologyRepo technologyRepo;

    @Autowired
    private EducationRepo educationRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private TechnologyService technologyService;

    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long emp_Code) throws UserNotFoundException{
        return userRepo.findById(emp_Code)
                .orElseThrow(()->new UserNotFoundException("No User found by this id "+ emp_Code));
    }

    @Override
    public User addUser(UserDTO userDTO) {
      User user= new User(userDTO);
      user.setUsername(userDTO.getUsername());
      Technology technology= technologyService.getTechnologyById(userDTO.getT_id());
      user.setTechnology(technology);
      return  userRepo.save(user);
    }

    @Override
    public User updateUser(Long emp_Code, UserDTO userdto){
        User user1 = getUserById(emp_Code);

        user1.setUsername(userdto.getUsername());
        user1.setFull_Name(userdto.getFull_Name());
        user1.setMobile(userdto.getMobile());
        user1.setPhone_No(userdto.getPhone_No());
        user1.setDesignation(userdto.getDesignation());
        user1.setJoining_Date(userdto.getJoining_Date());

        Technology technology= technologyService.getTechnologyById(userdto.getT_id());
        user1.setTechnology(technology);

        addressUpdate(emp_Code, userdto);

        educationUpdate(emp_Code, userdto);

        return userRepo.save(user1);
    }
    //@Override
    /*public User updateUser(Long emp_Code, User user) throws UserNotFoundException {
        User user1 = getUserById(emp_Code);

        user1.setUsername(user.getUsername());
        user1.setFull_Name(user.getFull_Name());
        user1.setMobile(user.getMobile());
        user1.setPhone_No(user.getPhone_No());
        user1.setDesignation(user.getDesignation());
        user1.setJoining_Date(user.getJoining_Date());

    *//*    user1.setTechnology(user.getTechnology());
        Optional<Technology> technologyEdit = technologyRepo.findById(user1.getTechnology().getT_id());
        technologyEdit.get().setT_id(user1.getTechnology().getT_id());
        technologyEdit.get().setTechnology_name(user1.getTechnology().getTechnology_name());
*//*
        addressUpdate(emp_Code, user);
        educationUpdate(emp_Code, user);
            return userRepo.save(user1);
    }
*/
    private void addressUpdate(Long employeeCode,UserDTO user)
    {
        User existingUser = getUserById(employeeCode);
        Optional<Address> addressEdit = addressRepo.findById(existingUser.getAddress().getA_id());
        addressEdit.get().setAddress_Type(user.getAddress().getAddress_Type());
        addressEdit.get().setAddress1(user.getAddress().getAddress1());
        addressEdit.get().setAddress2(user.getAddress().getAddress2());
        addressEdit.get().setState(user.getAddress().getState());
        addressEdit.get().setCity(user.getAddress().getCity());
        addressEdit.get().setPinCode(user.getAddress().getPinCode());
        addressEdit.get().setCountry(user.getAddress().getCountry());
    }

    private void educationUpdate(Long employeeCode, UserDTO user)
    {
        User existingUser = getUserById(employeeCode);
        List<Education> educationEditList = educationRepo.findEducationByUser(existingUser.getEmp_Code());
        for(int i =0 ; i<educationEditList.size();i++)
        {
            educationEditList.get(i).setLevel(user.getEducation().get(i).getLevel());
            educationEditList.get(i).setBoard(user.getEducation().get(i).getBoard());
            educationEditList.get(i).setScore(user.getEducation().get(i).getScore());
            educationEditList.get(i).setUniversity(user.getEducation().get(i).getUniversity());
            educationEditList.get(i).setYear(user.getEducation().get(i).getYear());
        }

    }


    @Override
    public void deleteUser(Long emp_Code) throws UserNotFoundException {
        User user = getUserById(emp_Code);
        userRepo.delete(user);
    }

    public User partialUpdateEmployee(Long emp_Code, Map<String, Object> fields)
    {
        Optional<User> existingUser = userRepo.findById(emp_Code);
        if(existingUser.isPresent())
        {
            fields.forEach((key,value) -> {
                Field field= ReflectionUtils.findField(User.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingUser.get(),value);

            });
            return userRepo.save(existingUser.get());
        }
        return null;
    }

}
