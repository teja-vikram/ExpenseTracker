package com.java.project.statistics_controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.statistics_services.StatisticsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StatisticsController {
	
	@Autowired
	private final StatisticsService service;

	@GetMapping("/{from}/{to}")
	public ResponseEntity<?> getStatisticalDetails(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate to){
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM--dd--yy");
		try {
		
		//LocalDate fromdate = LocalDate.parse(from,formatter);
		//LocalDate todate = LocalDate.parse(to,formatter);
		return ResponseEntity.ok(service.getDataChart(from, to));
		}catch(DateTimeParseException ex) {
			return ResponseEntity.badRequest().body("Invalid date format. Please use MM-dd--yy (e.g. 12-25-25)");
		}
	}
	
	@GetMapping("/getstats")
	public ResponseEntity<?> getStatistics(){
		return ResponseEntity.ok(service.getStatstics());
	}
}
