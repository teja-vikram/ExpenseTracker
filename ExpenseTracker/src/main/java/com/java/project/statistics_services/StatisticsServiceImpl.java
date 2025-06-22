package com.java.project.statistics_services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.dto.GraphDTO;
import com.java.project.dto.Statistics_DTO;
import com.java.project.entity.Expenses;
import com.java.project.entity.Income;
import com.java.project.income_repository.IncomeRepository;
import com.java.project.repository.ExpenseRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
	private final IncomeRepository incomeRepo;
	@Autowired
	private final ExpenseRepo expenseRepo;
	
	public GraphDTO getDataChart(LocalDate from, LocalDate to) {
		
		GraphDTO graphDto = new GraphDTO();
		graphDto.setExpenseList(expenseRepo.findByDateBetween(from, to));
		graphDto.setIncomeList(incomeRepo.findByDateBetween(from, to));
		
		return graphDto;
	}
	
	public Statistics_DTO getStatstics(){
		Double totalIncome = incomeRepo.sumAllAmount();
		Double totalExpenses =  expenseRepo.sumAllAmount();
		
		
		Optional<Income> optionalIncome = incomeRepo.findFirstByOrderByDateDesc();
		Optional<Expenses> optionalExpense = expenseRepo.findFirstByOrderByDateDesc();
		
		
			
		
		Statistics_DTO stat_dto = new Statistics_DTO();
		stat_dto.setIncome(totalIncome);
		stat_dto.setExpense(totalExpenses);
		
		if(optionalIncome.isPresent())
			stat_dto.setLatestIncome(optionalIncome.get());
		
		if(optionalExpense.isPresent())
			stat_dto.setLatestExpenses(optionalExpense.get());
		
		stat_dto.setBalance(totalIncome-totalExpenses);
		
		List<Income> incomeList = incomeRepo.findAll();
		List<Expenses> expenseList = expenseRepo.findAll();
		
		OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
		
		OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();
		
		OptionalDouble minExpense = expenseList.stream().mapToDouble(Expenses::getAmount).min();
		OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expenses::getAmount).max();
		
		stat_dto.setMaxIncome(maxIncome.isPresent()? maxIncome.getAsDouble() : null);
		stat_dto.setMinIncome(minIncome.isPresent()? minIncome.getAsDouble():null);
		stat_dto.setMaxExpense(maxExpense.isPresent()? maxExpense.getAsDouble():null);
		stat_dto.setMinExpense(minExpense.isPresent()? minExpense.getAsDouble():null);
		return stat_dto;
		
}
	
	
}
