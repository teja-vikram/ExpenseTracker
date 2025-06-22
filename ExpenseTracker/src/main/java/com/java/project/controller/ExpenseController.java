package com.java.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.dto.ExpenseDTO;
import com.java.project.entity.Expenses;
import com.java.project.service.ExpensesService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ExpenseController {

	@Autowired
	private ExpensesService service;
	
	@PostMapping("/save")
	public ResponseEntity<?> addExpenses(@RequestBody ExpenseDTO expenseDTO) {
		Expenses createExpenses  = service.postExpense(expenseDTO);
		
		if(createExpenses != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createExpenses);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllExpenses(){
		return ResponseEntity.ok(service.getAllExpenses());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getExepenseById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.getExpenseById(id));
		}catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went Wrong");
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expDto){
		
		try {
			return ResponseEntity.ok(service.updateExpense(id, expDto));
		}catch(EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteExp(@PathVariable Long id){
		
		try {
			service.deleteExpense(id);
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
		}catch(EntityNotFoundException exc) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
		}
		
	}
	
}
