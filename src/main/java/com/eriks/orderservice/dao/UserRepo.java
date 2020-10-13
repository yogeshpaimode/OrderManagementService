package com.eriks.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eriks.orderservice.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}
