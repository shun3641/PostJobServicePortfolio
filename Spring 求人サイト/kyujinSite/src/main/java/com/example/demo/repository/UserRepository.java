package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>,
JpaSpecificationExecutor<User>  {
  Optional<User> findByEmail(String email);
  Optional<User> findById(Integer id);
}
