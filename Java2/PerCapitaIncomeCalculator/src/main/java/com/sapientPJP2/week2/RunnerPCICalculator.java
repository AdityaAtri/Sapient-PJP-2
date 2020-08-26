package com.sapientPJP2.week2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import com.opencsv.CSVWriter;

public class RunnerPCICalculator {
 
     public static void main(String[] args) throws IOException {
    	 
    	Vector<Details> information =  ReadExcel.readFile("/Users/adityaatri/Desktop/input.xlsx");
    	Vector<OutDetails> outputInformation = OutputExcel.writeFile(information); 
    	
//    	System.out.println("INPUT : ");
//    	for(int i=0; i<information.size(); i++) {
//    		Details detail = information.get(i);
//    		System.out.println(detail.city + " "+ detail.country + " " + detail.gender + " " + detail.currency + " "+detail.avgIncome);
//    	}
    	
    	File file = new File("output.csv"); 
    	FileWriter outputfile = new FileWriter(file); 
    	CSVWriter writer = new CSVWriter(outputfile);
    	String[] header = { "Country", "Gender", "Average Income (in USD)" }; 
    	writer.writeNext(header);
    	 
    	
    	for(int i=0; i<outputInformation.size(); i++) {
    		OutDetails detail = outputInformation.get(i);
    		// System.out.println(detail.country + " " + detail.gender + " "+detail.avgIncome);
            String[] data = { detail.country, detail.gender, Double.toString(detail.avgIncome)}; 
            writer.writeNext(data); 
    	}
        writer.close(); 
        System.out.println("Output written to File : output.csv"); 
		
     }
}
