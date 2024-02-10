package com.rbank.bank.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles" , schema = "security_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

}