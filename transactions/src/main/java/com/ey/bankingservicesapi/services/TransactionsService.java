package com.ey.bankingservicesapi.services;

import com.ey.bankingservicesapi.models.Transactions;
import com.ey.bankingservicesapi.models.TransactionsForm;
import com.ey.bankingservicesapi.models.TransferForm;

public interface TransactionsService {
    Transactions makeWithDrawl(Transactions transactions) throws Exception;
    Transactions convertToTransactions(TransactionsForm tForm);
    Transactions convertToTransactions(TransferForm tForm);
    Transactions makeDeposit(Transactions transactions) throws Exception;
    Transactions makeTransfer(Transactions transactions) throws Exception;
}
