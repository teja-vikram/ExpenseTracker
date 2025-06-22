package com.java.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.project.entity.Expenses;
import com.java.project.entity.Income;

@Repository
public interface ExpenseRepo extends JpaRepository<Expenses,Long>{

	List<Expenses> findByDateBetween(LocalDate startdate, LocalDate fromdate);
	
	@Query("SELECT SUM(e.amount)from Expenses e")
   public  Double sumAllAmount();
	
	Optional<Expenses> findFirstByOrderByDateDesc();
}
