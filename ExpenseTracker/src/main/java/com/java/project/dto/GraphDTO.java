package com.java.project.dto;

import java.util.List;

import com.java.project.entity.Expenses;
import com.java.project.entity.Income;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphDTO {
   
	private List<Expenses> expenseList;
	private List<Income> incomeList;
}
