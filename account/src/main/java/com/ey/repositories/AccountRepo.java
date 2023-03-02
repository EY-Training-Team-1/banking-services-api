package com.ey.repositories;

import com.ey.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
    void delete(Optional<Account> account);
}