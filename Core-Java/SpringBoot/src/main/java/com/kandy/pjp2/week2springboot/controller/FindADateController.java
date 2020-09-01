package com.kandy.pjp2.week2springboot.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kandy.pjp2.week2springboot.bo.FindADateBO;
import com.kandy.pjp2.week2springboot.model.DurationBetweenDates;
import com.kandy.pjp2.week2springboot.model.FindADate;
import com.kandy.pjp2.week2springboot.model.History;
import com.kandy.pjp2.week2springboot.repository.IFindADateRepository;
import com.kandy.pjp2.week2springboot.repository.IHistoryRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class FindADateController {
	@Autowired
	IFindADateRepository findADateRepository;
	
	@Autowired
	IHistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	FindADate findADate;
	
	@GetMapping("/findadate/all")
	public List<FindADate> getAllFindADate() {
		return findADateRepository.findAll();
	}
	
	@GetMapping("/findadate")
	public String getFindADate() {
		return "findADate";
	}
	
	@GetMapping("/findadate/{id}")
	public FindADate getFindADateById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return findADateRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/findadate")
	public String createFindADate(Model model, @RequestParam String givenDate,
			@RequestParam String unit, @RequestParam int quantity, @RequestParam int addsub) throws IOException {
		
		findADate.setGivenDate(LocalDate.parse(givenDate));
		findADate.setUnit(unit);
		findADate.setQuantity(quantity);
		FindADateBO.handleOperation(findADate, addsub);
				
		findADateRepository.save(findADate);
		
		model.addAttribute("givenDate", findADate.getGivenDate());
		model.addAttribute("finalDate", findADate.getFinalDate());
		
		// History entry
		String beforeOrAfter = "";
		if(addsub == 1)
			beforeOrAfter = "After";
		else
			beforeOrAfter = "Before";
		
		history.setGivenDate(findADate.getGivenDate());
		history.setFinalDate(findADate.getFinalDate());
		history.setMenuOption("find_a_date");
		history.setRemark(findADate.getQuantity() + " " + findADate.getUnit() + " " + beforeOrAfter);
						
		historyRepository.save(history);
		
		return "findADate";
	}
	
	@DeleteMapping("/findadate/{id}")
    public ResponseEntity<?> deleteFindADate(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {
		
        findADateRepository.deleteById(operationId);

        return ResponseEntity.ok().build();
	}
}
