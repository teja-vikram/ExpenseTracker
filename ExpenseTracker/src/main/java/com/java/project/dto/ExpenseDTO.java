package com.java.project.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseDTO {

	
	private Long id;
	private String title;
	private String description;
	private String category;
	private LocalDate date;
	private int amount;
}
