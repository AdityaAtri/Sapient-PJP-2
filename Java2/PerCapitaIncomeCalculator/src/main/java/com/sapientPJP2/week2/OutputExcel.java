package com.sapientPJP2.week2;
import java.text.DecimalFormat;
import java.util.Vector;

public class OutputExcel {
	
	static Vector<OutDetails> output = new Vector<OutDetails>();
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public static Vector<OutDetails> writeFile(Vector<Details> information) {
		
		int n = information.size(); 
		
		int i = 0;
		while(i!=n) {
			
			Details detail = information.get(i);
			String country = detail.country; 
			//System.out.println(i);
			
			int total = 0;
			double female_income = 0;
			int female_count=0;
			int male_count=0;
			int female = 0;
			int male = 0; 
			double male_income = 0;
		
			//for the female one
			while(i<n && information.get(i).country.equals(country) && information.get(i).gender.equals("F")) {
				double income = information.get(i).avgIncome; 
				if(information.get(i).currency.equals("INR")) income = income/66;
				else if(information.get(i).currency.equals("GBP")) income = income/0.67;
				else if(information.get(i).currency.equals("SGP")) income = income/1.5;
				else if(information.get(i).currency.equals("HKD")) income = income/8; 
				female_income += income; 
				female_count++;
				i++; 
				total+=1; 
				female = 1;
			}
			// System.out.println(i);
			//for the male one 
			while(i<n && information.get(i).country.equals(country) && information.get(i).gender.equals("M")) {
				double income = information.get(i).avgIncome; 
				if(information.get(i).currency.equals("INR")) income = income/66;
				else if(information.get(i).currency.equals("GBP")) income = income/0.67;
				else if(information.get(i).currency.equals("SGP")) income = income/1.5;
				else if(information.get(i).currency.equals("HKD")) income = income/8; 
				male_income += income;
				male_count++;
				i++; 
				total+=1; 
				male = 1; 
			}
			if(female==1) {
				double avgIncome = female_income/female_count; 
				OutDetails dt = new OutDetails(); 
				dt.country = country;
				dt.gender = "F";
				String temp = df2.format(avgIncome); 
				dt.avgIncome = Double.parseDouble(temp);
				output.add(dt);
			}
			if(male==1) {
				double avgIncome = male_income/male_count; 
				OutDetails dt = new OutDetails(); 
				dt.country = country;
				dt.gender = "M";
				String temp = df2.format(avgIncome); 
				dt.avgIncome = Double.parseDouble(temp);
				output.add(dt);
				
			}
		}
		return output;
	}
}
