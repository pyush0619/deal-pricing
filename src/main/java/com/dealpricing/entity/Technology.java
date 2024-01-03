package com.dealpricing.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Technology")
public class Technology {

    @Id
    @GeneratedValue()
    private int t_id;

    private String technology_name;
}
