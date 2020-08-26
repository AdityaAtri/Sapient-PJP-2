
package com.sapientPJP2.week2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	// create Integer type linked list
	static Vector<Details> information = new Vector<Details>();
	
	public static Vector<Details> readFile(String FILE_PATH) throws IOException {
		
    	try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(file);
            int numberOfSheets = workbook.getNumberOfSheets();
 
            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
               
            	Sheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.iterator();
                // this is done to avoid cell type row 
                rowIterator.next();
                //iterating over each row
                while (rowIterator.hasNext()) {
 
                    Row row = (Row) rowIterator.next();
                    Details new_row = new Details(); 
                    Cell cell; 
                 
                    // for the city 
                    cell = row.getCell(0);
                    if(cell!=null) new_row.city = cell.getStringCellValue();
                    
                    // for the country 
                    cell = row.getCell(1);
                    if(cell!=null) new_row.country = cell.getStringCellValue();
                    else new_row.country = new_row.city; 
                    
                    // for the gender
                    cell = row.getCell(2);
                    if(cell!=null) new_row.gender = cell.getStringCellValue();
                    
                    // for the currency
                    cell = row.getCell(3);
                    if(cell!=null) new_row.currency = cell.getStringCellValue();
                    
                    // for the income 
                    cell = row.getCell(4);
                    if(cell!=null) new_row.avgIncome = cell.getNumericCellValue();
                    
                    cell = row.getCell(0);
                    if(cell!=null)
                    	information.add(new_row);
                }
            }
            file.close();
            workbook.close();
        } 
    	catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    	// printing the data of excel file into vector 
//    	for(int i=0; i<information.size(); i++) {
//    		Details detail = information.get(i);
//    		System.out.println(detail.city + " "+ detail.country + " " + detail.gender + " " + detail.currency + " "+detail.avgIncome);
//    	}
    	
    	Comparator<Details> comparator = new DetailsComparator();
    	Collections.sort(information,comparator);
    	
//    	for(int i=0; i<information.size(); i++) {
//    		Details detail = information.get(i);
//    		System.out.println(detail.city + " "+ detail.country + " " + detail.gender + " " + detail.currency + " "+detail.avgIncome);
//    	}
		return information;

	}
}