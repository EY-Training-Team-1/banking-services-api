package com.ey.bankingservicesapi.repositories;

import com.ey.bankingservicesapi.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {




}
