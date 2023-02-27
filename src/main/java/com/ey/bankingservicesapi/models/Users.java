package com.ey.bankingservicesapi.models;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Column(nullable = false)
    private String pass;

    @java.lang.Override
    public java.lang.String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}