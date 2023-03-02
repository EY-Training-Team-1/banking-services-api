package com.ey.bankaccounts.repositories;

import com.ey.bankaccounts.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepo extends CrudRepository<Users, Integer> {

     Optional<Users> findById(Integer userId);
}
