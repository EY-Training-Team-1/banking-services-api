package com.ey.bankingservicesapi.services;

import com.ey.bankingservicesapi.models.*;
import com.ey.bankingservicesapi.repositories.BankRepo;
import com.ey.bankingservicesapi.repositories.TransactionsRepo;
import com.ey.bankingservicesapi.repositories.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@Transactional
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepo transactionsRepo;
    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public Transactions makeWithDrawl(Transactions transactions) throws Exception {
        //check available balance
        //if sufficient go ahead
        BigDecimal balance = transactions.getAccountNum().getBalance();
        if(balance.compareTo(transactions.getAmount()) < 0 || !transactions.getAccountNum().getStatus().equals(AccountStatus.active)){
            log.error("Exception occurred while making WithDrawl");
            throw new Exception();
        }
        transactions.getAccountNum().setBalance(balance.subtract(transactions.getAmount()));
        bankRepo.save(transactions.getAccountNum());
        Transactions save = transactionsRepo.save(transactions);
        log.info("Transaction Successful made WithDrawl:"+ save.toString());
        return save;
    }

    @Override
    public Transactions makeTransfer(Transactions transactions) throws Exception {
        //check available balance
        //if sufficient go ahead
        List<Bank> bankAccounts = bankRepo.findAllByUserId(transactions.getUserId().getId());
        if(bankAccounts.size() < 1){
            log.error("Exception occurred while making Transfer b/w accounts, atleast two accounts needed");
            throw new Exception();
        }
        List<Bank> list = bankAccounts.stream().filter(x -> x.getId() == transactions.getAccountNum().getId()).collect(Collectors.toList());
        List<Bank> list2 = bankAccounts.stream().filter(x -> x.getId() == transactions.getTransferToAcc()).collect(Collectors.toList());
        if(list == null || list2 == null || list.size() != 1 || list2.size() != 1){
            log.error("Exception occurred while making Transfer b/w accounts, not valid accounts");
            throw new Exception();
        }
        BigDecimal balance = transactions.getAccountNum().getBalance();
        Optional<Bank> byId = bankRepo.findById(transactions.getTransferToAcc());
        Bank bank = byId.orElseThrow();
        if(balance.compareTo(transactions.getAmount()) < 0 || !transactions.getAccountNum().getStatus().equals(AccountStatus.active) || !bank.getStatus().equals(AccountStatus.active)){
            log.error("Exception occurred while making Transfer b/w accounts, insufficient balance");
            throw new Exception();
        }
        transactions.getAccountNum().setBalance(balance.subtract(transactions.getAmount()));
        bank.setBalance(bank.getBalance().add(transactions.getAmount()));
        bankRepo.save(bank);
        bankRepo.save(transactions.getAccountNum());
        Transactions save = transactionsRepo.save(transactions);
        log.info("Transaction Successful made Transfer b/w accounts:"+ save.toString());
        return save;
    }

    @Override
    public Transactions convertToTransactions(TransactionsForm tForm) {
        Optional<Users> optionalUsers = usersRepo.findById(tForm.getUserId());
        Users users = optionalUsers.orElseThrow();
        Optional<Bank> byId = bankRepo.findById(tForm.getAccountNum());
        Bank bank = byId.orElseThrow();
        Transactions build = Transactions.builder()
                .amount(tForm.getAmount())
                .userId(users)
                .operation(tForm.getOperation())
                .accountNum(bank)
                .build();
        return build;
    }

    @Override
    public Transactions convertToTransactions(TransferForm tForm) {
        Optional<Users> optionalUsers = usersRepo.findById(tForm.getUserId());
        Users users = optionalUsers.orElseThrow();
        Optional<Bank> byId = bankRepo.findById(tForm.getAccountNum());
        Bank bank = byId.orElseThrow();
        Transactions build = Transactions.builder()
                .amount(tForm.getAmount())
                .userId(users)
                .operation(tForm.getOperation())
                .accountNum(bank)
                .transferToAcc(tForm.getTransferToAcc())
                .build();
        return build;
    }

    @Override
    public Transactions makeDeposit(Transactions transactions) throws Exception {
        if(!transactions.getAccountNum().getStatus().equals(AccountStatus.active)){
            log.error("Exception occurred while making Deposit, account not active");
            throw new Exception();
        }
        BigDecimal balance = transactions.getAccountNum().getBalance();
        transactions.getAccountNum().setBalance(balance.add(transactions.getAmount()));
        bankRepo.save(transactions.getAccountNum());
        Transactions save = transactionsRepo.save(transactions);
        log.info("Transaction Successful made Deposit:"+ save.toString());
        return save;
    }
}
