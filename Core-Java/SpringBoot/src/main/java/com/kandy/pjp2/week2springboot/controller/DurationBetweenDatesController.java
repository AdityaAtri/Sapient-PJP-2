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

import com.kandy.pjp2.week2springboot.bo.DurationBetweenDatesBO;
import com.kandy.pjp2.week2springboot.model.DurationBetweenDates;
import com.kandy.pjp2.week2springboot.model.History;
import com.kandy.pjp2.week2springboot.repository.IDurationBetweenDatesRepository;
import com.kandy.pjp2.week2springboot.repository.IHistoryRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class DurationBetweenDatesController {
	
	@Autowired
	IDurationBetweenDatesRepository durationBetweenDatesRepository;
	
	@Autowired
	IHistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	DurationBetweenDates durationBetweenDates;
	
	@GetMapping("/durationbetweendates/all")
	public List<DurationBetweenDates> getAllDurationBetweenDates() {
		return durationBetweenDatesRepository.findAll();
	}
	
	@GetMapping("/durationbetweendates")
	public String getDurationBetweenDates() {
		return "durationBetweenDates";
	}
	
	@GetMapping("/durationbetweendates/{id}")
	public DurationBetweenDates getDurationBetweenDatesById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return durationBetweenDatesRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/durationbetweendates")
	public String createDayOfWeek(Model model, 
			@RequestParam String givenDate, @RequestParam String finalDate) {
		
		durationBetweenDates.setGivenDate(LocalDate.parse(givenDate));
		durationBetweenDates.setFinalDate(LocalDate.parse(finalDate));
		List<Integer> values = DurationBetweenDatesBO.durationBetweenDates(durationBetweenDates.getGivenDate(),
				durationBetweenDates.getFinalDate());
		durationBetweenDates.setDays(values.get(0));
		durationBetweenDates.setWeeks(values.get(1));
		durationBetweenDates.setMonths(values.get(2));
		
		durationBetweenDatesRepository.save(durationBetweenDates);
		
		
		model.addAttribute("givenDate", durationBetweenDates.getGivenDate());
		model.addAttribute("finalDate", durationBetweenDates.getFinalDate());
		model.addAttribute("months", durationBetweenDates.getMonths());
		model.addAttribute("weeks", durationBetweenDates.getWeeks());
		model.addAttribute("days", durationBetweenDates.getDays());
		
		// History entry
		history.setGivenDate(durationBetweenDates.getGivenDate());
		history.setFinalDate(durationBetweenDates.getFinalDate());
		history.setMenuOption("duration_between_dates");
		history.setRemark(durationBetweenDates.getDays() + " days " 
				+ durationBetweenDates.getWeeks() + " weeks " 
				+ durationBetweenDates.getMonths() + " months");
						
		historyRepository.save(history);
		
		
		return "durationBetweenDates";
	}
	
	@DeleteMapping("/durationbetweendates/{id}")
    public ResponseEntity<?> deleteDayOfWeek(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {

        durationBetweenDatesRepository.deleteById(operationId);

        return ResponseEntity.ok().build();
	}
}
