package com.kandy.pjp2.week2springboot.controller;

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

import com.kandy.pjp2.week2springboot.bo.DayOfWeekBO;
import com.kandy.pjp2.week2springboot.model.DayOfWeek;
import com.kandy.pjp2.week2springboot.model.History;
import com.kandy.pjp2.week2springboot.repository.IDayOfWeekRepository;
import com.kandy.pjp2.week2springboot.repository.IHistoryRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class DayOfWeekController {
	
	@Autowired
	IDayOfWeekRepository dayOfWeekRepository;
	
	@Autowired
	IHistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	DayOfWeek dayOfWeek;
	
	@GetMapping("/dayofweek/all")
	public List<DayOfWeek> getAllDayOfWeek() {
		return dayOfWeekRepository.findAll();
	}
	
	@GetMapping("/dayofweek")
	public String getDayOfWeek() {
		return "dayofweek";
	}
	
	@GetMapping("/dayofweek/{id}")
	public DayOfWeek getDayOfWeekById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return dayOfWeekRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/dayofweek")
	public String createDayOfWeek(Model model, @RequestParam String givenDate) {
		dayOfWeek.setGivenDate(LocalDate.parse(givenDate));
		dayOfWeek.setDayOfWeek(DayOfWeekBO.dayOfWeek(dayOfWeek.getGivenDate()));
		
		model.addAttribute("givendate", dayOfWeek.getGivenDate());
		model.addAttribute("day", dayOfWeek.getDayOfWeek());
		
		dayOfWeekRepository.save(dayOfWeek);
		
		// History entry
		history.setGivenDate(dayOfWeek.getGivenDate());
		history.setFinalDate(dayOfWeek.getGivenDate());
		history.setMenuOption("day_of_week");
		history.setRemark(dayOfWeek.getDayOfWeek());
				
		historyRepository.save(history);
		
		return "dayofweek";
	}
	
	@DeleteMapping("/dayofweek/{id}")
    public ResponseEntity<?> deleteDayOfWeek(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {

        dayOfWeekRepository.deleteById(operationId);

        return ResponseEntity.ok().build();
	}
}
