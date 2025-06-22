package com.java.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.dto.IncomeDTO;
import com.java.project.entity.Income;
import com.java.project.income_service.IncomeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
	
	@Autowired
	private IncomeService service;
	
	
	@PostMapping("/post")
	public ResponseEntity<?> addIncome(@RequestBody IncomeDTO incomeDto) {
       
		IncomeDTO createdIncome = service.postIncome(incomeDto);
		if(createdIncome != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	} 
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllIncomes(){
		return ResponseEntity.ok(service.getAllincomes());
		
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getIncomeById(@PathVariable Long id){
		
		try {
			return ResponseEntity.ok(service.getIncomeById(id));
		}catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDto ){
		try {
			return ResponseEntity.ok(service.updateIncome(id, incomeDto));
		}catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteIncomeById(@PathVariable Long id){
		try {
			service.deleteById(id);
			return ResponseEntity.ok(null);
		}catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went Wrong");
		}
	}
}
