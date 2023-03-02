package com.ey.bankingservicesapi.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class BankForm {

    private int id;

    private AccountStatus status;

    private AccountType type;

    private double balance;

    private List<String> users;

    @Override
    public String toString() {
        return "BankForm{" +
                "id=" + id +
                ", status=" + status +
                ", type=" + type +
                ", balance=" + balance +
                ", users=" + users +
                '}';
    }
}
