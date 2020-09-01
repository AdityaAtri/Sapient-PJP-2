package com.kandy.pjp2.week2springboot.bo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class DateFromPhraseBO {

	static public LocalDate handleOperation(LocalDate givenDate, String phrase) throws IOException {
		Map<Integer, String> phrases = new HashMap<>();
		phrases.put(1, "today");
		phrases.put(2, "tomorrow");
		phrases.put(3, "day after tomorrow");
		phrases.put(4, "yesterday");
		phrases.put(5, "day before yesterday");
		phrases.put(6, "last week");
		phrases.put(7, "previous week");
		phrases.put(8, "next week");
		phrases.put(9, "next month");
		phrases.put(10, "next year");
		phrases.put(11, "last month");
		phrases.put(12, "last year");
		phrases.put(13, "month after");
		phrases.put(14, "month before");
		phrases.put(15, "weeks from now");
		phrases.put(16, "days from now");
		phrases.put(17, "months from now");
		phrases.put(18, "years from now");
		phrases.put(19, "days earlier");
		phrases.put(20, "weeks earlier");
		phrases.put(21, "months earlier");
		phrases.put(22, "years earlier");

		
 		int n = 0;
 	
 		String cleanPhrase = "";
 		String replaceNumString = "";
	
 		for(String word: phrase.split(" ")) {
 			if(word.matches("^[0-9]*$")) {
 				n = Integer.parseInt(word);
 				replaceNumString = n + " ";
 				break;
 			}
 		}
 			
 		Map<Integer, LocalDate > phraseToDate= new HashMap<>();
 		phraseToDate.put(1, FindADateBO.addDays(givenDate, 0));
 		phraseToDate.put(2, FindADateBO.addDays(givenDate, 1));
 		phraseToDate.put(3, FindADateBO.addDays(givenDate, 2));
 		phraseToDate.put(4, FindADateBO.subDays(givenDate, 1));
 		phraseToDate.put(5, FindADateBO.subDays(givenDate, 2));
 		phraseToDate.put(6, FindADateBO.subWeeks(givenDate, 1));
 		phraseToDate.put(7, FindADateBO.subWeeks(givenDate, 1));
 		phraseToDate.put(8, FindADateBO.addWeeks(givenDate, 1));
 		phraseToDate.put(9, FindADateBO.addMonths(givenDate, 1));
 		phraseToDate.put(10, FindADateBO.addYears(givenDate, 1)); 
 		phraseToDate.put(11, FindADateBO.subMonths(givenDate, 1));
 		phraseToDate.put(12, FindADateBO.subYears(givenDate, 1));
 		phraseToDate.put(13, FindADateBO.addMonths(givenDate, 1));
 		phraseToDate.put(14, FindADateBO.subMonths(givenDate, 1));
 		phraseToDate.put(15, FindADateBO.addWeeks(givenDate, n));
 		phraseToDate.put(16, FindADateBO.addDays(givenDate, n));
 		phraseToDate.put(17, FindADateBO.addMonths(givenDate, n));
 		phraseToDate.put(18, FindADateBO.addYears(givenDate, n)); 
 		phraseToDate.put(19, FindADateBO.subDays(givenDate, n));
 		phraseToDate.put(20, FindADateBO.subWeeks(givenDate, n));
 		phraseToDate.put(21, FindADateBO.subMonths(givenDate, n));
 	 	phraseToDate.put(22, FindADateBO.subYears(givenDate, n));
 			
 		cleanPhrase = phrase.replaceAll(replaceNumString, "");
 		cleanPhrase = cleanPhrase.toLowerCase();
 		
 		LocalDate finalDate = null;
 		boolean isAMatch = false;
 			
 		for (Map.Entry<Integer,String> entry : phrases.entrySet()) {
 			if(entry.getValue().equals(cleanPhrase)) {
 				finalDate = phraseToDate.get(entry.getKey());
 				isAMatch = true;
 				break;
 			}
 		}
		
 		if(isAMatch) {
 			return finalDate;
 		}
 		else {
 			System.out.println("INVALID PHRASE");
 			return LocalDate.MIN;
 		}
	}
}
