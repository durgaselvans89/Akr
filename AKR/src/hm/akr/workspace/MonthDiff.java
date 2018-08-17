package hm.akr.workspace;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;  
public class MonthDiff {  
    public static void main(String[] args) {  
        
    	Date date2 = new Date();
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");			
		try {
			Date date1 = dateFormat.parse("12-08-2010");
		
		
            int m1 = date1.getYear() * 12 + date1.getMonth();
            int m2 = date2.getYear() * 12 + date2.getMonth();
            System.out.println(m2 - m1);
           // System.out.println(date1.getYear()*12);
            //System.out.println(m2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

 }
}
