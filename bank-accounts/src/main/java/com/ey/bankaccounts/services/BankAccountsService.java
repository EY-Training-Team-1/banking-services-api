package com.ey.bankaccounts.services;

import com.ey.bankaccounts.models.Bank;
import com.ey.bankaccounts.models.OpenAccountForm;

public interface BankAccountsService {
    Bank openAccount(OpenAccountForm accountForm) throws Exception;
    Boolean closeAccount(Integer accNum);
}
