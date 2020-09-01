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

import com.kandy.pjp2.week2springboot.bo.WeekNumberBO;
import com.kandy.pjp2.week2springboot.model.History;
import com.kandy.pjp2.week2springboot.model.WeekNumber;
import com.kandy.pjp2.week2springboot.repository.IHistoryRepository;
import com.kandy.pjp2.week2springboot.repository.IWeekNumberRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class WeekNumberController {
	@Autowired
	IWeekNumberRepository weekNumberRepository;
	
	@Autowired
	IHistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	WeekNumber weekNumber;
	
	@GetMapping("/weeknumber/all")
	public List<WeekNumber> getAllDayOfWeek() {
		return weekNumberRepository.findAll();
	}
	
	@GetMapping("/weeknumber")
	public String getDayOfWeek() {
		return "weekNumber";
	}
	
	@GetMapping("/weeknumber/{id}")
	public WeekNumber getWeekNumberById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return weekNumberRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/weeknumber")
	public String creatWeekNumber(Model model, @RequestParam String givenDate) {
		weekNumber.setGivenDate(LocalDate.parse(givenDate));
		weekNumber.setWeekNumber(WeekNumberBO.weekNumber(weekNumber.getGivenDate()));
		
		model.addAttribute("givenDate", weekNumber.getGivenDate());
		model.addAttribute("weekNumber", weekNumber.getWeekNumber());
		
		weekNumberRepository.save(weekNumber);
		
		//History entry
		history.setGivenDate(weekNumber.getGivenDate());
		history.setFinalDate(weekNumber.getGivenDate());
		history.setMenuOption("week_number");
		history.setRemark( Integer.toString(weekNumber.getWeekNumber()));
						
		historyRepository.save(history);
		
		return "weekNumber";
	}
	
	@DeleteMapping("/weeknumber/{id}")
    public ResponseEntity<?> deleteWeekNumber(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {
        weekNumberRepository.deleteById(operationId);

        return ResponseEntity.ok().build();
	}
}
