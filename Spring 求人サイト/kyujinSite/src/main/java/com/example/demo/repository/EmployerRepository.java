package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
	Optional<Employer> findByEmail(String email);
}
