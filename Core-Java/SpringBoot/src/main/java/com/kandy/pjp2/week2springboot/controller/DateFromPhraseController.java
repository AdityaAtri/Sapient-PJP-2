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

import com.kandy.pjp2.week2springboot.bo.DateFromPhraseBO;
import com.kandy.pjp2.week2springboot.model.DateFromPhrase;
import com.kandy.pjp2.week2springboot.model.History;
import com.kandy.pjp2.week2springboot.repository.IDateFromPhraseRepository;
import com.kandy.pjp2.week2springboot.repository.IHistoryRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class DateFromPhraseController {
	
	@Autowired
	IDateFromPhraseRepository dateFromPhraseRepository;
	
	@Autowired
	IHistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	DateFromPhrase dateFromPhrase;
	
	@GetMapping("/datefromphrase/all")
	public List<DateFromPhrase> getAllDatesFromPhrase() {
		return dateFromPhraseRepository.findAll();
	}
	
	@GetMapping("/datefromphrase")
	public String getDatesFromPhrase() {
		return "dateFromPhrase";
	}
	
	@GetMapping("/datefromphrase/{id}")
	public DateFromPhrase getDateFromPhraseById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return dateFromPhraseRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/datefromphrase")
	public String createDateFromPhrase(Model model, @RequestParam String phrase) throws IOException {
		dateFromPhrase.setGivenDate(LocalDate.now());
		dateFromPhrase.setPhrase(phrase);
		dateFromPhrase.setFinalDate(DateFromPhraseBO.handleOperation(dateFromPhrase.getGivenDate(), phrase));
		
		dateFromPhraseRepository.save(dateFromPhrase);
		
		model.addAttribute("givenDate", dateFromPhrase.getGivenDate());
		model.addAttribute("finalDate", dateFromPhrase.getFinalDate());
		model.addAttribute("phrase", dateFromPhrase.getPhrase());
		
		
		// History entry
		history.setGivenDate(dateFromPhrase.getGivenDate());
		history.setFinalDate(dateFromPhrase.getFinalDate());
		history.setMenuOption("date_from_phrase");
		history.setRemark("Date From Phrase");
		
		historyRepository.save(history);
		
		return "dateFromPhrase";
	}
	
	@DeleteMapping("/datefromphrase/{id}")
    public ResponseEntity<?> deleteDateFromPhrase(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {
        dateFromPhraseRepository.deleteById(operationId);
        return ResponseEntity.ok().build();
	}

}
