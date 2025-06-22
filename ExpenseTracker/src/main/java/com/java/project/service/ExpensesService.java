package com.java.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.dto.ExpenseDTO;
import com.java.project.entity.Expenses;
import com.java.project.repository.ExpenseRepo;

public interface ExpensesService {

	public Expenses postExpense(ExpenseDTO expenseDTO);
	public List<Expenses> getAllExpenses();
	public Expenses getExpenseById(Long Id);
	public Expenses updateExpense(Long id, ExpenseDTO edto);
	public void deleteExpense(Long id);
}
