package com.java.project.income_service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.project.dto.IncomeDTO;
import com.java.project.entity.Income;
import com.java.project.income_repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository repo;
	
	
	public IncomeDTO postIncome(IncomeDTO incomeDto) {
		return saveOrUpdateIncome(new Income(),incomeDto).getIncomeDto();
	}
	public Income saveOrUpdateIncome(Income income,IncomeDTO idto) {
		
		income.setTitle(idto.getTitle());
		income.setAmount(idto.getAmount());
		income.setDate(idto.getDate());
		income.setCategory(idto.getCategory());
		income.setDescription(idto.getDescription());
		
		
		return repo.save(income);
		
	}
	
	public List<IncomeDTO> getAllincomes(){
		return repo.findAll().stream()
				             .sorted(Comparator.comparing(Income::getDate).reversed())
				             .map(Income:: getIncomeDto)
				             .collect(Collectors.toList());
		                      
	}
	@Override
	public IncomeDTO getIncomeById(Long id) {
		Optional<Income> income = repo.findById(id);
		if(income!=null)
		return income.get().getIncomeDto();
		else
			throw new EntityNotFoundException("No record found with given Id");
	}
	
	public IncomeDTO updateIncome(Long id, IncomeDTO incomeDto) {
		if(repo.findById(id) !=null) 
			 return saveOrUpdateIncome(repo.findById(id).get(),incomeDto).getIncomeDto();
		else
			throw new EntityNotFoundException("Invalid Income Details mentioned"+id);
	}
	
	public void deleteById(Long id) {
		if(repo.findById(id)!=null) 
			repo.deleteById(id);
		else
			throw new EntityNotFoundException("No Income found with Id:"+id);
	}
}
