package com.ey.bankingservicesapi.services;

import com.ey.bankingservicesapi.models.UserForm;
import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.ey.bankingservicesapi.repositories.BankRepo;
import com.ey.bankingservicesapi.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepo u;
    BankRepo b;

    @Autowired
    UserService(UserRepo user, BankRepo bank){
        this.u=user;
        this.b=bank;
    }


    public Users getUser(int id) {
        return u.findById(id).get();
    }

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
         users = (List<Users>) u.findAll();

         return users;
    }

    public Users addUser(Users user){


        return u.save(user);
    }

    public boolean deleteUser(int id) {
        try {
            u.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bank> getBankAccounts(Users user) {

        List<Bank> banks = (List<Bank>) b.findAll();

        return banks.stream()
                .filter(a -> a.getUsers().contains(user))
                .collect(Collectors.toList());

    }


    public Users convertToUser(UserForm form) {

        Users user = new Users();

        user.setId(form.getId());
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPass(form.getPass());


        if(form.getBanks() == null || form.getBanks().size() == 0) {
            user.setBanks(new ArrayList<>());
        } else {
            List<Bank> allBanks = (List<Bank>) b.findAll();
            user.setBanks(allBanks.stream()
                    .filter(x -> form.getBanks().contains((x.getId())))
                    .collect(Collectors.toList()));
        }

        return user;
    }


    public UserForm convertToUserForm(Users user) {

        UserForm form = new UserForm();

        form.setId(user.getId());
        form.setName(user.getName());
        form.setEmail(user.getEmail());

        StringBuilder pass = new StringBuilder();

        for(int i =0; i<user.getPass().length(); i++){
            pass.append("*");
        }

        form.setPass(pass.toString());

        if(user.getBanks() != null) {
            List<String> list = new ArrayList<>();
            for (Bank m : user.getBanks()) {
                String s = "/banks/" + m.getId();
                list.add(s);
            }
            form.setBanks(list);
        } else {
            form.setBanks(new ArrayList<>());
        }

        return form;
    }

}
