/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    
    /**
     * Convert from {@code java.util.Date} to {@code java.sql.Date}
     * @param date is {@code java.util.Date}
     * @return {@code java.sql.Date}
     */
    public static java.sql.Date convertDateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }
    
    /**
     * 
     * @param hour hour of day
     * @param minute minute of hour
     * @return {@code java.sql.Time}
     * @example input: hour = 12 and minute = 50
     *          output: 12:50
     */
    public static java.sql.Time getTimeSql(int hour, int minute) {
        return getTimeSql(hour+":"+minute);
    }
    
    /**
     * 
     * @param time period of time
     * @return {@code java.sql.Time}
     * @example input: time = "12:59"
     *          output: 12:50
     */
    public static java.sql.Time getTimeSql(String time) {
        return new java.sql.Time(getMilliseconds(time));
    }
    
    /**
     * 
     * @param time period of time
     * @return milliseconds of time
     * @example input: time = "12:50"
     *          output: 1606110623143
     */
    public static long getMilliseconds(String time)  {
        String[] timeArray = time.split(":");
        int hour = Integer.parseInt(timeArray[0]);
        int minute = Integer.parseInt(timeArray[1]);
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        
        return cal.getTimeInMillis();
    }
    
}
