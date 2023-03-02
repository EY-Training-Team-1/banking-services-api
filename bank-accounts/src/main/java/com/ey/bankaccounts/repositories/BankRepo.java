package com.ey.bankaccounts.repositories;

import com.ey.bankaccounts.models.Bank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepo extends CrudRepository<Bank, Integer> {
    @Query(value = "SELECT * FROM BANK b WHERE b.user_id = ?1", nativeQuery = true)
    List<Bank> findAllByUserId(int userId);
    Optional<Bank> findById(int accountNum);
    Bank save(Bank bank);
}
