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
    @Column(columnDefinition = "default 100")
    private int balance;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;






}