package com.ey.bankaccounts.services;

import com.ey.bankaccounts.models.AccountStatus;
import com.ey.bankaccounts.models.Bank;
import com.ey.bankaccounts.models.OpenAccountForm;
import com.ey.bankaccounts.models.Users;
import com.ey.bankaccounts.repositories.BankRepo;
import com.ey.bankaccounts.repositories.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BankAccountsServiceImpl implements BankAccountsService{
    private UsersRepo usersRepo;
    private BankRepo bankRepo;

    @Autowired
    public BankAccountsServiceImpl(BankRepo bankRepo, UsersRepo usersRepo) {
    this.bankRepo = bankRepo;
    this.usersRepo = usersRepo;
    }

    @Override
    public Bank openAccount(OpenAccountForm accountForm) throws Exception {
        Optional<Users> user = usersRepo.findById(accountForm.getUserId());
        Users users = user.orElseThrow();
        if(accountForm.getInitialDeposit().compareTo(BigDecimal.valueOf(100.00)) < 0){
            log.debug("Exception occurred, Minimum deposit of $100 required to open account");
            throw new Exception("Minimum deposit of $100 required to open account");
        }
        Bank b = new Bank();
        b.setStatus(AccountStatus.active.active);
        b.setBalance(accountForm.getInitialDeposit());
        b.setAccount_type(accountForm.getAccountType());
        b.setUserId(users);
        Bank save = bankRepo.save(b);
        log.info("Account creation Successful:"+ save.toString());
        return save;
    }

    @Override
    public Boolean closeAccount(Integer accNum) {
        Optional<Bank> account = bankRepo.findById(accNum);
        if(account.isEmpty())
            return false;
        Bank bank = account.get();
        bank.setStatus(AccountStatus.closed);
        Bank save = bankRepo.save(bank);
        log.info("Account closed Successfully:"+ save.toString());
        return true;
    }
}
