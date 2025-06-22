package com.java.project.income_repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.project.entity.Expenses;
import com.java.project.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

	List<Income> findByDateBetween(LocalDate startdate, LocalDate fromdate);

	@Query("SELECT SUM(i.amount)from Income i")
    public  Double sumAllAmount();
	
	Optional<Income>findFirstByOrderByDateDesc();
}
