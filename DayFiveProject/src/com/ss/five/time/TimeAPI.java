package com.ss.five.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;

public class TimeAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		int year = 2017;
		int month = 11;
		
		//Write an example that, for a given year, reports the length of each month within that year.
		YearMonth monthOfYear = YearMonth.of(year, month);
        System.out.printf(month  + " of " +  year + " has " + monthOfYear.lengthOfMonth() + " days.");
        
        
        //Write an example that, for a given month of the current year, lists all of the Mondays in that month.      
        monthOfYear = YearMonth.of(Year.now().getValue(), month);
        
        LocalDate date = Year.now().atMonth(month).atDay(1);
        
        while(date.getMonth() == monthOfYear.getMonth()) {
        	date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        	System.out.println(date);
        }
        
        
        //Write an example that tests whether a given date occurs on Friday the 13th.
        year = 2019;
        month = 9;
        int day = 13;
        date = Year.of(year).atMonth(month).atDay(day);
        if(date.getDayOfMonth() == 13 && date.getDayOfWeek().getValue() ==5){
        	System.out.println("Date is Friday the 13th");
        }
        else {
        	System.out.println("Day in month: "+ date.getDayOfMonth() + "\nDay of week: "+date.getDayOfWeek().getValue() + "\nDate is not Friday the 13th");
        }
        
	}

}
