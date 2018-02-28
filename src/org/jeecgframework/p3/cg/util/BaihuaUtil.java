package org.jeecgframework.p3.cg.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaihuaUtil {

		public static boolean isadmin(List<String> list){
			boolean isadmin =false;
			for(String s:list){
				if(s.equals("00")){
					isadmin=true;
				}
			}
			return isadmin;
		}
		

public static Long getDaysBetween(Date startDate, Date endDate) {  
    Calendar fromCalendar = Calendar.getInstance();  
    fromCalendar.setTime(startDate);  
    fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
    fromCalendar.set(Calendar.MINUTE, 0);  
    fromCalendar.set(Calendar.SECOND, 0);  
    fromCalendar.set(Calendar.MILLISECOND, 0);  

    Calendar toCalendar = Calendar.getInstance();  
    toCalendar.setTime(endDate);  
    toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
    toCalendar.set(Calendar.MINUTE, 0);  
    toCalendar.set(Calendar.SECOND, 0);  
    toCalendar.set(Calendar.MILLISECOND, 0);  

    return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);  
} 
}
