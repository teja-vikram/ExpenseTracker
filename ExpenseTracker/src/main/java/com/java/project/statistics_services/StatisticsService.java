package com.java.project.statistics_services;

import java.time.LocalDate;

import com.java.project.dto.GraphDTO;
import com.java.project.dto.Statistics_DTO;

public interface StatisticsService {

	public GraphDTO getDataChart(LocalDate from, LocalDate to);

	public Statistics_DTO getStatstics();
}
