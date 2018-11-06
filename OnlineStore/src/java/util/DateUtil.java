package util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    
    public static String returnSimpleDateFormat(GregorianCalendar g)
    {
        String date = "";
        date+=g.get(GregorianCalendar.DAY_OF_MONTH)+"-";
        date+=(g.get(GregorianCalendar.MONTH)+1)+"-";
        date+=g.get(GregorianCalendar.YEAR);
        
        return date;
    }
    public static String returnDateFormatForDB(GregorianCalendar g)
    {
        String date = "";
        date+=g.get(GregorianCalendar.YEAR)+"-";
        date+=g.get(GregorianCalendar.MONTH)+"-";
        date+=g.get(GregorianCalendar.DAY_OF_MONTH);

        return date;
    }
    public static GregorianCalendar returnGregorianDate(String s)
    {
        String[] date = s.split("-");
        GregorianCalendar gDate = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        
        return gDate;
    }
    
    public static int checkIfLate(GregorianCalendar returnDate)
    {
        GregorianCalendar currentDate = new GregorianCalendar();
        int late = (int)getDateDiff(returnDate,currentDate,TimeUnit.DAYS);
        return late>0?late:0;
    }
    
    private static long getDateDiff(GregorianCalendar date1, GregorianCalendar date2, TimeUnit timeUnit)
    {
        long diffInMillies = date2.getTimeInMillis()- date1.getTimeInMillis();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
