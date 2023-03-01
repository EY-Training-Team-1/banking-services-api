package services;

import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import repositories.BankRepo;
import repositories.UserRepo;

import java.util.List;
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
    }

    public List<Bank> getAllBank() {
        return (List<Bank>) b.findAll();
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


}
