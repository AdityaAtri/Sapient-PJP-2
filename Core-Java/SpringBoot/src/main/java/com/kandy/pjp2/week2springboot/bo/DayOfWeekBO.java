package com.kandy.pjp2.week2springboot.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayOfWeekBO {
	static public String dayOfWeek(LocalDate d1) {
		return d1.format(DateTimeFormatter.ofPattern("EEEE"));
	}
}
