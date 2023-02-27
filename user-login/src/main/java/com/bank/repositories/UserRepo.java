package main.java.com.bank.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByName(String name);

}