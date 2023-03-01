package com.ey.bankingservicesapi.repositories;

import com.ey.bankingservicesapi.models.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BankRepo extends CrudRepository<Bank, Integer> {


}
