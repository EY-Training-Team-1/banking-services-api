package com.ey.bankingservicesapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferForm {
    private Integer userId;
    private BigDecimal amount;
    private Operation operation;
    private Integer accountNum;
    private Integer transferToAcc;
}
