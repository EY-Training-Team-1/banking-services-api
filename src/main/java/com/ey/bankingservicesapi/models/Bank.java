package com.ey.bankingservicesapi.models;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "bank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Bank {

    @Id //makes this a Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id", updatable = false)
    private int id;

    //The status of the account EX: active or not active
    @Column(nullable = false)
    private String status;

    //The account type EX: Checking or Savings
    @Column(nullable = false)
    private String type;


    //The account balance
    @Column(columnDefinition = "default 100")
    private int balance;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "linked",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id"))
    private List<Users> users;


    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", users=" + users +
                '}';
    }
}