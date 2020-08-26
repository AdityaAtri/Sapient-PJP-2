package com.sapientPJP2.week2;

public class TransactionDetails {
	
	public String uID;
    public String clientID;
    public String securityID;
    public String date;
    public String type;
    public String priority;
    public String processingFees;

    public TransactionDetails(String uID, String clientID, String securityID, String date, String type, String priority) {
        this.uID = uID ;
        this.clientID = clientID;
        this.securityID = securityID;
        this.date = date;
        this.type = type;
        this.priority = priority;
    }
}
