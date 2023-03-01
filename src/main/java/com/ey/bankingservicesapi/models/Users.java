package com.ey.bankingservicesapi.models;


import lombok.extern.slf4j.Slf4j;
import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name= "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Users {

    @Id //makes this a Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", updatable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String pass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "linked",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id"))
    private List<Bank> banks;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email +
                '}';
    }
}