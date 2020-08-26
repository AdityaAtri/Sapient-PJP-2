package com.sapientPJP2.week2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProcessFile {
	
		public static void ReadnProcess() throws FileNotFoundException {
		// user id  - client id - security id - Transaction date - Transaction type - Priority flag
		
		ArrayList<TransactionDetails> transactions = new ArrayList<TransactionDetails>();
		HashMap<String, Integer> map = new HashMap<String, Integer>(); 
		
		Scanner scan = new Scanner(new File("fee-processing-input.txt"));
		int count = 0; 
	    while(scan.hasNextLine()){
	        String line = scan.nextLine();
	        String[] transactionDetail = line.split("\\|");
            TransactionDetails transaction = new TransactionDetails(transactionDetail[0], transactionDetail[1],
                    transactionDetail[2], transactionDetail[3], transactionDetail[4], transactionDetail[5]);
            // System.out.println(transaction.uID + " " + transaction.securityID + " " + transaction.date);
            String key_formed = transaction.clientID + " " + transaction.securityID + " " + transaction.date + " "+transaction.type ; 
            
            if((transaction.type.equals("buy") || transaction.type.equals("sell"))) {
            	if(map.containsKey(key_formed)==false) map.put(key_formed,1); 
                else map.put(key_formed, map.get(key_formed) + 1);
            	count++;
            }
            
            transactions.add(transaction);
	    }
	    // System.out.println(count);
	    
//	    Iterator<Entry<String, Integer>> entries = map.entrySet().iterator();
//	    while (entries.hasNext()) {
//	        Entry<String, Integer> entry = entries.next();
//	        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//	    }
	    String filename = "fee-processing-output.txt";
	    try { 
            BufferedWriter out = new BufferedWriter(new FileWriter(filename)); 
            out.write(""); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("File not opened" + e); 
        } 
	    
	    for(int i=0; i<transactions.size(); i++) {
	    	TransactionDetails transaction = transactions.get(i);
	    	String key_formed = transaction.clientID + " " + transaction.securityID + " " + transaction.date+ " "+transaction.type; 
	    	//System.out.println(key_formed);
	    	if(map.containsKey(key_formed)==false || ( map.containsKey(key_formed)==true && map.get(key_formed) <= 1)) {
	    			
	    		if(transaction.priority.equals("yes")) transaction.processingFees = "900";
	    		else if(transaction.priority.equals("no") && (transaction.type.equals("sell") || transaction.type.equals("withdraw")))
	    				transaction.processingFees = "100";
	    		else if(transaction.priority.equals("no") && (transaction.type.equals("buy") || transaction.type.equals("deposit")))
	    				transaction.processingFees = "50";
	    	}
	    	else transaction.processingFees = "10";
	    	
	    	String uid = transaction.uID;
	    	String clientid = transaction.clientID;
	    	String securityid = transaction.securityID;
	    	String date = transaction.date;
	    	String type = transaction.type;
	    	String priority = transaction.priority;
	    	String processingfees = transaction.processingFees; 
	    	String row = uid+"|"+clientid+"|"+securityid+"|"+date+"|"+type+"|"+priority+"|"+processingfees + "\n"; 
	    	
	    	try {  
	            BufferedWriter out = new BufferedWriter(new FileWriter(filename, true)); 
	            out.write(row); 
	            out.close(); 
	        } 
	        catch (IOException e) { 
	            System.out.println("exception occoured" + e); 
	        } 
	    	
	    }
	    System.out.println("Success - Appended the Fees to 'fee-processing-output.txt' file. ");
    }
}
