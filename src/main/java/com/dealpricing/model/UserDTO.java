package com.dealpricing.model;

import com.dealpricing.entity.Address;
import com.dealpricing.entity.Education;
import com.dealpricing.entity.Technology;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

//    @NotEmpty(message = "Username cannot be missing or empty")
//    @Size(min=3, message="First name must not be less than 2 characters")
    private String username;

//    @Size(min=2, message="First name must not be less than 2 characters")
    private String full_Name;

//    @NotEmpty(message = "Mobile cannot be missing or empty")
//    @Size(min=10,message="First name must not be less than 2 characters")
    private Integer mobile;

    private Integer phone_No;

    private String designation;

    private int  t_id;

    private String joining_Date;

//    @NotEmpty(message = "Address1 cannot be missing or empty")
    private Address address;

    private List<Education> education;


}


