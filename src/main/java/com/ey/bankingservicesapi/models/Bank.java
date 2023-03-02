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
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    //The account type EX: Checking or Savings
    @Column(name = "account_type", nullable = false)
    private AccountType type;


    //The account balance
    @Column(columnDefinition = "numeric(12,2)")
    private double balance;



    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "banks")
    private List<Users> users;


    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", status=" + status +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }
}