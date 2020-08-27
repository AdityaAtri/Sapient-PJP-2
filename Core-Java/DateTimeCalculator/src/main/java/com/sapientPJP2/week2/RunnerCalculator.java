package com.sapientPJP2.week2;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class RunnerCalculator {
	
	static int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static class date  
    { 
        int d, m, y; 
        public date(int d, int m, int y) 
        { 
            this.d = d; 
            this.m = m;
            this.y = y; 
        }  
    }; 

    
	public static void main(String[] args) throws ParseException, IOException {
	PrintWriter writer = new PrintWriter("History.txt");
	writer.print("");
	writer.close();
	while(true) {
		
		System.out.println("Option 1 - Add, Subtract between two dates and express the output in days, dates, weeks, months");
		System.out.println("Option 2 - Add, Subtract 'n' Days, Months, Weeks to the given date and derive the final date");
		System.out.println("Option 3 - Determine the Day of the Week for a given date");
		System.out.println("Option 4 - Determine the Week number for a given a date");
		System.out.println("Option 5 - Adding two dates");
		System.out.println("Option 6 - See the History of all operations performed");
		System.out.println("Option 7 - Wants to enter the NLP phrase ?");
		System.out.println("Option 8 - Close the file");
		System.out.println("Enter the option you want to choose :");
		System.out.println("================================================================================================");
		
		
		Scanner scan = new Scanner(System.in);
		int inputOption = scan.nextInt();
		
		if(inputOption == 1) {
			
			System.out.print("Enter the first date - lower date  - DD-MM-YYYY format ");
			String date1 = scan.next();
			System.out.print("Enter the second date - higher date - DD-MM-YYYY format ");
			String date2 = scan.next();
			
			int day1 = Integer.parseInt(date1.substring(0,2));
			int month1 = Integer.parseInt(date1.substring(3,5));
			int year1 = Integer.parseInt(date1.substring(6,10));
			
			int day2 = Integer.parseInt(date2.substring(0,2));
			int month2 = Integer.parseInt(date2.substring(3,5));
			int year2 = Integer.parseInt(date2.substring(6,10));
			
			date dt1 = new date(day1,month1,year1);
			date dt2 = new date(day2,month2,year2);
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String givenDate1 = Integer.toString(dt1.y)+"-"+Integer.toString(dt1.m)+"-"+Integer.toString(dt1.d);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(sdf.parse(givenDate1));
			
			String givenDate2 = Integer.toString(dt2.y)+"-"+Integer.toString(dt2.m)+"-"+Integer.toString(dt2.d);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(sdf.parse(givenDate2));
			
			Date d1 = cal1.getTime();
			Date d2 = cal2.getTime(); 
			
			int numDays = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)); 
			int numWeeks = numDays/7; 
			int numMonths = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
			
			
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("Input Option 1 : Date1 : "+date1 + " Date2 : "+date2 + "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("Difference between two dates in terms of days : "+ numDays + "\n");
			    fw.write("Difference between two dates in terms of months :"+ numMonths + " Months "+ "\n");
			    fw.write("Difference between two dates in terms of weeks :"+ numWeeks + " Weeks "+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 2) {
			System.out.println("Enter the date - DD-MM-YYYY format ");
			String date = scan.next();
			
			int day = Integer.parseInt(date.substring(0,2));
			int month = Integer.parseInt(date.substring(3,5));
			int year = Integer.parseInt(date.substring(6,10));
			
			date dt = new date(day,month,year);
			
			System.out.println("Enter the value of N ");
			int n = scan.nextInt();
			System.out.println("N is days or months or years ? ");
			String input = scan.next();
			System.out.println("Is add or subtract ?");
			String operation = scan.next();
			
			String givenDate = Integer.toString(dt.y)+"-"+Integer.toString(dt.m)+"-"+Integer.toString(dt.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			String newDate = null;
			
			if(operation.equals("add")) {
				if(input.equals("years")) {
					cal.add(Calendar.YEAR,n); 
					newDate = sdf.format(cal.getTime()); 
					System.out.println("New Date after adding " + n + " years : "+newDate);
				}
				else if(input.equals("months")) {
					cal.add(Calendar.MONTH,n); 
					newDate = sdf.format(cal.getTime());  
					System.out.println("New Date after adding " + n + " months : "+newDate);
				}
				else if(input.equals("days")){
					cal.add(Calendar.DATE,n); 
					newDate = sdf.format(cal.getTime());
					System.out.println("New Date after adding " + n + " days : "+newDate);
				}
				try
				{
				    String filename= "history.txt";
				    FileWriter fw = new FileWriter(filename,true);
				    fw.write("Input Option 2 : Date : "+date + " Add N : "+ n+ "\n" );
				    fw.write("Output :"+ "\n");
				    if(input.equals("year"))
				    	fw.write("New Date after adding " + n + " years : " + newDate + "\n");
				    else if(input.equals("months"))
				    	fw.write("New Date after adding " + n + " months : " + newDate + "\n");
				    else if(input.equals("days"))
				    	fw.write("New Date after adding " + n + " days : " + newDate + "\n");
				    fw.write("================================================================================================"+ "\n");
				    fw.close();
				}
				catch(IOException msg)
				{
				    System.err.println("IOException: " + msg.getMessage());
				}
			}
			else if(operation.equals("subtract")){
				if(input.equals("years")) {
					cal.add(Calendar.YEAR,-1*n); 
					newDate = sdf.format(cal.getTime()); 
					System.out.println("New Date after subtracting " + n + " years : "+newDate);
				}
				else if(input.equals("months")) {
					cal.add(Calendar.MONTH,-1*n); 
					newDate = sdf.format(cal.getTime());  
					System.out.println("New Date after subtracting " + n + " months : "+newDate);
				}
				else if(input.equals("days")){
					cal.add(Calendar.DATE,-1*n); 
					newDate = sdf.format(cal.getTime());
					System.out.println("New Date after subtracting " + n + " days : "+newDate);
				}
				try
				{
				    String filename= "history.txt";
				    FileWriter fw = new FileWriter(filename,true);
				    fw.write("Input Option 2 : Date : "+date + " Subtract N : "+ n + "\n");
				    fw.write("Output :"+ "\n");
				    if(input.equals("year"))
				    	fw.write("New Date after subtracting " + n + " years : "+newDate+ "\n");
				    else if(input.equals("months"))
				    	fw.write("New Date after subtracting " + n + " months : "+newDate+ "\n");
				    else if(input.equals("days"))
				    	fw.write("New Date after subtracting " + n + " days : "+newDate+ "\n");
				    fw.write("================================================================================================"+ "\n");
				    fw.close();
				}
				catch(IOException msg)
				{
				    System.err.println("IOException: " + msg.getMessage());
				}
			}
		}
		else if(inputOption == 3) {
			System.out.println("Enter the date : DD-MM-YYYY format ");
			String date = scan.next();
			
			int day = Integer.parseInt(date.substring(0,2));
			int month = Integer.parseInt(date.substring(3,5));
			int year = Integer.parseInt(date.substring(6,10));
			
			date dt = new date(day,month,year);
			String givenDate = Integer.toString(dt.y)+"-"+Integer.toString(dt.m)+"-"+Integer.toString(dt.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			System.out.println("Day of the week for the given date : " + cal.get(Calendar.DAY_OF_WEEK));
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("Input Option 3 : Date : "+date+ "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("Day of the week for the given date : " + cal.get(Calendar.DAY_OF_WEEK)+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 4) {
			System.out.println("Enter the date : DD-MM-YYYY format ");
			String date = scan.next();
			
			int day = Integer.parseInt(date.substring(0,2));
			int month = Integer.parseInt(date.substring(3,5));
			int year = Integer.parseInt(date.substring(6,10));
			
			date dt = new date(day,month,year);
			String givenDate = Integer.toString(dt.y)+"-"+Integer.toString(dt.m)+"-"+Integer.toString(dt.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			System.out.println("Week number for the given date : " + cal.get(Calendar.WEEK_OF_YEAR));
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("Input Option 4 : Date : "+date+ "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("Week number for the given date : " + cal.get(Calendar.WEEK_OF_YEAR)+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 5) { 
			System.out.print("Enter the first date - DD-MM-YYYY format ");
			String date1 = scan.next();
			System.out.print("Enter the second date - DD-MM-YYYY format ");
			String date2 = scan.next();
			
			int day1 = Integer.parseInt(date1.substring(0,2));
			int month1 = Integer.parseInt(date1.substring(3,5));
			int year1 = Integer.parseInt(date1.substring(6,10));
			
			int day2 = Integer.parseInt(date2.substring(0,2));
			int month2 = Integer.parseInt(date2.substring(3,5));
			int year2 = Integer.parseInt(date2.substring(6,10));
			
			date dt1 = new date(day1,month1,year1);
			date dt2 = new date(day2,month2,year2);
			
			String givenDate = Integer.toString(dt1.y)+"-"+Integer.toString(dt1.m)+"-"+Integer.toString(dt1.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			
			cal.add(Calendar.DATE, dt2.d);
			cal.add(Calendar.MONTH, dt2.m);
			cal.add(Calendar.YEAR, dt2.y); 
			
			String newDate = sdf.format(cal.getTime());
			System.out.println("New date after adding Two dates : "+ newDate);	
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("Input Option 5 : Date1 : "+ date1 + " Date2 : "+date2+ "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("New date after adding Two dates : "+ newDate+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 6) {
			 BufferedReader br = new BufferedReader(new FileReader("history.txt"));
			 String line;
			 while ((line = br.readLine()) != null) {
			   System.out.println(line);
			 }
			 br.close();
		}
		else if(inputOption == 7) {
			
			System.out.println("Enter the NLP phrase :");
			Scanner sc = new Scanner(System.in);
	        String phrase = sc.nextLine();
			String filename= "history.txt";
		    FileWriter fw = new FileWriter(filename,true);
			
			LocalDate currDate = LocalDate.now();
			fw.write("Input Option 7 : NLP Phrase conversion : "+ phrase + " \n");
			
			if(phrase.equals("today")) {
				System.out.println(currDate);
				fw.write("Output :" + currDate + "\n");
			}		
			else if(phrase.equals("tomorrow")) {
				System.out.println(currDate.plusDays(1));
				fw.write("Output :" + currDate.plusDays(1) + "\n");
			}
			else if(phrase.equals("day after tomorrow")) {
				System.out.println(currDate.plusDays(2));
				fw.write("Output :" + currDate.plusDays(2) + "\n");
			}
			else if(phrase.equals("yesterday")) {
				System.out.println(currDate.minusDays(1));
				fw.write("Output :" + currDate.minusDays(1) + "\n");
			}
			else if(phrase.equals("day before yesterday")) {
				System.out.println(currDate.minusDays(2));
				fw.write("Output :" + currDate.minusDays(2) + "\n");
			}
			else if(phrase.equals("last week")) {
				System.out.println(currDate.minusWeeks(1));
				fw.write("Output :" + currDate.minusWeeks(1) + "\n");
			}
			else if(phrase.equals("previous week")) {
				System.out.println(currDate.minusWeeks(1));
				fw.write("Output :" + currDate.minusWeeks(1) + "\n");
			}
			
			else if(phrase.equals("next week")) {
				System.out.println(currDate.plusWeeks(1));
				fw.write("Output :" + currDate.plusWeeks(1) + "\n");
			}
			else if(phrase.equals("next month")) {
				System.out.println(currDate.plusMonths(1));
				fw.write("Output :" + currDate.plusMonths(1) + "\n");
			}
			else if(phrase.equals("next year")) {
				System.out.println(currDate.plusYears(1));
				fw.write("Output :" + currDate.plusYears(1) + "\n");
			}
			else if(phrase.equals("last month")){
				System.out.println(currDate.minusMonths(1));
				fw.write("Output :" + currDate.minusMonths(1) + "\n");
			}
			else if(phrase.equals("last year")) {
				System.out.println(currDate.minusYears(1));
				fw.write("Output :" + currDate.minusYears(1) + "\n");
			}
			else if(phrase.equals("month after")) {
				System.out.println(currDate.plusMonths(1));
				fw.write("Output :" + currDate.plusMonths(1) + "\n");
			}
			else if(phrase.equals("month before")) {
				System.out.println(currDate.minusMonths(1));
				fw.write("Output :" + currDate.minusMonths(1) + "\n");
			}
			else if(phrase.equals("weeks from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusWeeks(n));
				fw.write("Output :" + currDate.plusWeeks(n) + "\n");
			}
			else if(phrase.equals("days from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusDays(n));
				fw.write("Output :" + currDate.plusDays(n) + "\n");
			}
			else if(phrase.equals("months from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusMonths(n));
				fw.write("Output :" + currDate.plusMonths(n) + "\n");
			}
			else if(phrase.equals("years from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusYears(n));
				fw.write("Output :" + currDate.plusYears(n) + "\n");
			}
			else if(phrase.equals("days earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusDays(n));
				fw.write("Output :" + currDate.minusDays(n) + "\n");
			}
			else if(phrase.equals("weeks earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusWeeks(n));
				fw.write("Output :" + currDate.minusWeeks(n) + "\n");
			}
			else if(phrase.equals("months earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusMonths(n));
				fw.write("Output :" + currDate.minusMonths(n) + "\n");
				
			}
			else if(phrase.equals("years earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusYears(n));
				fw.write("Output :" + currDate.minusYears(n) + "\n");
			}
			fw.write("================================================================================================"+ "\n");
			fw.close();
			
		}
		else if(inputOption == 8) {
			System.out.println("Closing the Calculator");
			System.exit(1);
		}
	}
  }
	

}
