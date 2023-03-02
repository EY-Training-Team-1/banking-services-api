package com.ey.bankingservicesapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private int id;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Enumerated(EnumType.STRING)
   // @Column(name = "account_type")
    private AccountType account_type;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "u_id")
    private Users userId;
}
