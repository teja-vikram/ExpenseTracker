package com.java.project.entity;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.project.dto.IncomeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Integer amount;
	private LocalDate date;
	private String category;
	private String description;
	
	
	
	public IncomeDTO getIncomeDto() {
		IncomeDTO incomeDto = new IncomeDTO();
		incomeDto.setId(id);
		incomeDto.setTitle(title);
		incomeDto.setAmount(amount);
		incomeDto.setDate(date);
		incomeDto.setCategory(category);
		incomeDto.setDescription(description);
		
		return incomeDto;
	}

}
