package repositories;

import com.ey.bankingservicesapi.models.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankRepo extends CrudRepository<Bank, Integer> {
    List<Bank> FindById(int id);
    List<Bank> FindByType(String type);
}
