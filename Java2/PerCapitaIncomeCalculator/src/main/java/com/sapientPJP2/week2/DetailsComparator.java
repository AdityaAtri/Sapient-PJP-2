package com.sapientPJP2.week2;

import java.util.Comparator;

public class DetailsComparator implements Comparator<Details> {
	
	public int compare(Details o1, Details o2) {
		
		if( o1.country.compareTo(o2.country) == 0 ) {
			
			if(o1.gender.compareTo(o2.gender)== 0) {
				return 0; 
			}
			else return o1.gender.compareTo(o2.gender); 
		}
		else return o1.country.compareTo(o2.country); 
			
	}

}
