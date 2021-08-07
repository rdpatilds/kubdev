package com.drools.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private DateFormat format;
	public DateUtil() {
		format = new SimpleDateFormat("dd/MM/yyyy");
	}
	public int compare(String date1, String date2) {
		Date source = null;
		Date target = null;
		try {
			source = format.parse(date1);
			target = format.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return source.compareTo(target);
		
	}
	
	public int compare(Account account, String date2) {
		Date source = null;
		Date target = null;
		try {
			source = format.parse(account.getRecentInteraction());
			target = format.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return source.compareTo(target);
	}
	
	public boolean compareTo(Account account, String date2) {
		Date source = null;
		Date target = null;
		try {
			source = format.parse(account.getRecentInteraction());
			target = format.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return source.after(target);
	}
	
	public static void main(String[] args) {
		DateUtil util = new DateUtil();
		String date1 = "27/07/2021";
		String date2 = "10/1/2010";
		System.out.println(util.compare(date1, date2));
	}

}
