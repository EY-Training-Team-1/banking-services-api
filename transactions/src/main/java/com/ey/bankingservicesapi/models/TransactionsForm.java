package com.ey.bankingservicesapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsForm {
    private int userId;
    private BigDecimal amount;
    private Operation operation;
    private int accountNum;
}
