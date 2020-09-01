package com.kandy.pjp2.week2springboot.bo;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekNumberBO {
	static public int weekNumber(LocalDate d1) {
		return d1.get(WeekFields.of(Locale.getDefault()).weekOfYear());
	}
}
