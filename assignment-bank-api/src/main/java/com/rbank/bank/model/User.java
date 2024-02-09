package com.rbank.bank.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users" , schema = "security_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String username;
    private String password;

    private Boolean verified;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles" , joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "role_Id",referencedColumnName ="id") )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public User( String fullName,String username, String password) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.verified = false;
        this.enabled = true;
    }

}