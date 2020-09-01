package com.kandy.pjp2.week2springboot.bo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.kandy.pjp2.week2springboot.model.FindADate;


public class FindADateBO {
	static public LocalDate addDays(LocalDate givenDate, int n) {
		return givenDate.plusDays(n);
	}
	
	static public LocalDate addWeeks(LocalDate givenDate, int n) {
		return givenDate.plusWeeks(n);
	}

	static public LocalDate addMonths(LocalDate givenDate, int n) {
		return givenDate.plusMonths(n);
	}
	
	static public LocalDate subDays(LocalDate givenDate, int n) {
		return givenDate.minusDays(n);
	}
	
	static public LocalDate subWeeks(LocalDate givenDate, int n) {
		return givenDate.minusWeeks(n);
	}

	static public LocalDate subMonths(LocalDate givenDate, int n) {
		return givenDate.minusMonths(n);
	}
	
	static public LocalDate addYears(LocalDate givenDate, int n) {
		return givenDate.plusYears(n);
	}
	
	static public LocalDate subYears(LocalDate givenDate, int n) {
		return givenDate.minusYears(n);
	}
	
	static public void handleOperation(FindADate findADate, int choice) throws IOException {
		if(choice == 1) {
			Map<Integer, LocalDate> findDateMapAdd = new HashMap<>();
			findDateMapAdd.put(1, addDays(findADate.getGivenDate(), findADate.getQuantity()));
			findDateMapAdd.put(2, addWeeks(findADate.getGivenDate(), findADate.getQuantity()));
			findDateMapAdd.put(3, addMonths(findADate.getGivenDate(), findADate.getQuantity()));
			
			if(findADate.getUnit().toLowerCase().equals("days")) {
				findADate.setFinalDate(findDateMapAdd.get(1));
			} else if(findADate.getUnit().toLowerCase().equals("weeks")) {
				findADate.setFinalDate(findDateMapAdd.get(2));
			} else if(findADate.getUnit().toLowerCase().equals("months")) {
				findADate.setFinalDate(findDateMapAdd.get(3));
			}
			
			return;
				
		} else {
			
			Map<Integer, LocalDate> findDateMapSub = new HashMap<>();
			findDateMapSub.put(1, subDays(findADate.getGivenDate(), findADate.getQuantity()));
			findDateMapSub.put(2, subWeeks(findADate.getGivenDate(), findADate.getQuantity()));
			findDateMapSub.put(3, subMonths(findADate.getGivenDate(), findADate.getQuantity()));
			
			if(findADate.getUnit().toLowerCase().equals("days")) {
				findADate.setFinalDate(findDateMapSub.get(1));
			} else if(findADate.getUnit().toLowerCase().equals("weeks")) {
				findADate.setFinalDate(findDateMapSub.get(2));
			} else if(findADate.getUnit().toLowerCase().equals("months")) {
				findADate.setFinalDate(findDateMapSub.get(3));
			}
			
			return;	
		} 
	} 
}
