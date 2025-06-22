package com.java.project.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.project.dto.ExpenseDTO;
import com.java.project.dto.Statistics_DTO;
import com.java.project.entity.Expenses;
import com.java.project.repository.ExpenseRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpensesService{

	@Autowired 
	private ExpenseRepo repo;
	
	public Expenses postExpense(ExpenseDTO expenseDTO) {
		return saveOrUpdateExpenses(new Expenses(),expenseDTO);
	}
	
	public Expenses saveOrUpdateExpenses(Expenses expense, ExpenseDTO expenseDTO) {
		
		expense.setTitle(expenseDTO.getTitle());
		expense.setDate(expenseDTO.getDate());
		expense.setAmount(expenseDTO.getAmount());
		expense.setCategory(expenseDTO.getCategory());
		expense.setDescripton(expenseDTO.getDescription());
		
		return repo.save(expense);
	}
	
	
	public List<Expenses> getAllExpenses(){
		
		return repo.findAll().stream()
				             .sorted(Comparator.comparing(Expenses::getDate).reversed())
				             .collect(Collectors.toList());
	}
	
	public Expenses getExpenseById(Long Id) {
		
		Optional<Expenses> expenses = repo.findById(Id);
		
		if(expenses==null) 
			throw new EntityNotFoundException("No Expense found with given Id: "+Id);

			return expenses.get();
		
	}
	
	public Expenses updateExpense(Long id, ExpenseDTO edto) {
		Optional<Expenses> exp = repo.findById(id);
		if(exp.isPresent()) {
					return saveOrUpdateExpenses(exp.get(),edto);	
		}else {
			throw new EntityNotFoundException("Entity is not found with given Id: "+id);
		}
	}
	
	public void deleteExpense(Long id) {
		Optional<Expenses> expens = repo.findById(id);
		if(expens.isPresent()) {
			repo.deleteById(id);
		}else {
			throw new EntityNotFoundException("Entity is not found with given Id: "+id);
		}
	}
	
	
}