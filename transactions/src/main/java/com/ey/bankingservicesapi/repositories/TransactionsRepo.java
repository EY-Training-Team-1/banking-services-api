package com.ey.bankingservicesapi.repositories;

import com.ey.bankingservicesapi.models.Bank;
import com.ey.bankingservicesapi.models.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends CrudRepository<Transactions, Integer> {
    Transactions findByUserId(int userId);
}
