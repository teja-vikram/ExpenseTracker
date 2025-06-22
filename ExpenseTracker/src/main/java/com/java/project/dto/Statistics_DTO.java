package com.java.project.dto;

import com.java.project.entity.Expenses;
import com.java.project.entity.Income;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Statistics_DTO {

	private Double income;
	private Double expense;
	
	private Income latestIncome;
	private Expenses latestExpenses;
	
	private Double balance;
	private Double minIncome;
	private Double maxIncome;
	
	private Double minExpense;
	private Double maxExpense;
}
