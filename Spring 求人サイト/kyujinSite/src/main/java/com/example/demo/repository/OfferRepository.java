package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Integer>,
JpaSpecificationExecutor<Offer>  {
	List<Offer> findAllByCompanyid(Integer companyid);
	List<Offer> findAllByUserid(Integer userid);
	}
