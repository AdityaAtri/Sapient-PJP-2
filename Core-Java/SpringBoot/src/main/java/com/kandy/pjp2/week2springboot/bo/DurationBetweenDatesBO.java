package com.kandy.pjp2.week2springboot.bo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class DurationBetweenDatesBO {
	static public List<Integer> durationBetweenDates(LocalDate d1, LocalDate d2) {
		
		List<Integer> values = new ArrayList<>();
		
		int days = Period.between(d1, d2).getDays();
		int weeks = days/7;
		days = days%7;
		int months = Period.between(d1, d2).getMonths();
		values.add(days);
		values.add(weeks);
		values.add(months);
		
		return values;
	}
}
