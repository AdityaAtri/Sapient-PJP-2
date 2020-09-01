package com.kandy.pjp2.week2springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kandy.pjp2.week2springboot.model.History;
import com.kandy.pjp2.week2springboot.repository.IHistoryRepository;

@Controller
@RequestMapping("/")
public class HistoryController {
	
	@Autowired
	IHistoryRepository historyRepository;
	
	@Autowired
	History history;

	@GetMapping("/history/all")
	public List<History> getAllDayOfWeek() {
		return historyRepository.findAll();
	}
	
	@GetMapping("/history") 
	public String getHistory(Model model) {
		int i = (int) historyRepository.count();
		
		List<History> last100 = new ArrayList<History>();
		
		for(int itr = i; itr>0; itr--) {
			last100.add(historyRepository.getOne( (long)itr));
		}
		
		model.addAttribute("list", last100);
		
		return "history";
	}
}
