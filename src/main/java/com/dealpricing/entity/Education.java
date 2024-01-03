package com.dealpricing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int e_id;

    private String level;

    private int year;

    private String board;

    private String university;

    private int score;

}
