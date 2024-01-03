package com.dealpricing.entity;

import com.dealpricing.model.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emp_Code;

    private String username;

    private String full_Name;

    private Integer mobile;

    private Integer phone_No;

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "fk_t_id")
    private Technology technology;

    private String designation;

    private String joining_Date;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="fk_a_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_emp_Code", referencedColumnName = "emp_Code")
    private List<Education> education;

    public User (UserDTO userDTO) {
        this.username=userDTO.getUsername();
        this.full_Name = userDTO.getFull_Name();
        this.mobile = userDTO.getMobile();
        this.phone_No= userDTO.getPhone_No();
//        this.technology.setT_id(userDTO.getT_id());
        this.designation=userDTO.getDesignation();
        this.joining_Date=userDTO.getJoining_Date();
        this.address = userDTO.getAddress();
        this.education =userDTO.getEducation();
    }
}
