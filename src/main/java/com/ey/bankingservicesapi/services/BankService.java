package com.ey.bankingservicesapi.services;

import com.ey.bankingservicesapi.models.BankForm;
import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ey.bankingservicesapi.repositories.BankRepo;
import com.ey.bankingservicesapi.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    UserRepo u;
    @Autowired
    BankRepo b;


    public Bank addBank(Bank bank) {
        return b.save(bank);
    }


    public Bank getBank(int id) {
       return b.findById(id).get();
        //Optional<Bank> actorOptional = b.findById(id);
        //return actorOptional.orElseGet(Bank::new);
    }

    public List<Bank> getAllBanks() {

        List<Bank> banks = new ArrayList<Bank>();
        banks = (List<Bank>) b.findAll();

        return banks;
    }

    public boolean deleteBank(int id) {
        try {
            b.deleteById(id);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Bank convertToBank(BankForm form) {

        Bank bank = new Bank();

        bank.setId(form.getId());
        bank.setStatus(form.getStatus());
        bank.setType(form.getType());
        if(form.getBalance()<=0){
            bank.setBalance(100);
        }else{
            bank.setBalance(form.getBalance());
        }


        if(form.getUsers() == null || form.getUsers().size() == 0) {
            bank.setUsers(new ArrayList<>());
        } else {
            List<Users> allUsers = (List<Users>) u.findAll();
            List<Users> list = new ArrayList<>();
            for (Users x : allUsers) {
                if (form.getUsers().contains((x.getId()))) {
                    list.add(x);
                }
            }
            bank.setUsers(list);
        }

        return bank;
    }


    public BankForm convertToBankForm(Bank bank) {

        BankForm form = new BankForm();

        form.setId(bank.getId());
        form.setStatus(bank.getStatus());
        form.setType(bank.getType());
        form.setBalance(bank.getBalance());

        if(bank.getUsers() != null) {
            List<String> list = new ArrayList<>();
            for (Users m : bank.getUsers()) {
                String s = "/Users/" + m.getId();
                list.add(s);
            }
            form.setUsers(list);
        } else {
            form.setUsers(new ArrayList<>());
        }

        return form;


    }


}
