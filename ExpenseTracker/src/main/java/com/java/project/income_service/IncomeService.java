package com.java.project.income_service;

import java.util.List;

import com.java.project.dto.IncomeDTO;
import com.java.project.entity.Income;

public interface IncomeService {

	public IncomeDTO postIncome(IncomeDTO incomeDto);
	public List<IncomeDTO> getAllincomes();
	public IncomeDTO getIncomeById(Long id);
	public IncomeDTO updateIncome(Long id, IncomeDTO incomeDto);
	public void deleteById(Long id);
}
