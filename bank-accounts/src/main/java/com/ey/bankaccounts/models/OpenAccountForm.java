package com.ey.bankaccounts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountForm {
    private Integer userId;
    private AccountType accountType;
    private BigDecimal initialDeposit;

}
