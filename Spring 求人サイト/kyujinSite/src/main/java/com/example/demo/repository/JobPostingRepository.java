package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.example.demo.model.JobPosting;
import com.example.demo.model.JobPosting.EmploymentType;
import com.example.demo.model.JobPosting.LocationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Integer>
{
	JobPosting findByCompanyId(Long id);
	List<JobPosting> findAllByCompanyIdOrderByIdAsc(Integer companyid);
	
	List<JobPosting> findAllByJobType(
	        String jobType);
	
	JobPosting findByIdAndCompanyId(Integer id,Integer companyid);
	@Modifying
	@Query("""
	    DELETE FROM JobPosting j
	    WHERE j.updatedAt <= :validityPeriod
	""")
	int deleteOlderThan(@Param("validityPeriod") LocalDateTime validityPeriod);
	
	 Page<JobPosting> findAllByOrderByIdAsc(
	            Pageable pageable
	    );
	
}
