package date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Day{
    public static String getWeekDayString(String date){ //ex 07/03/2017
        String dayWeek = "---";
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));

            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    dayWeek = "DOM";
                    break;
                case Calendar.MONDAY:
                    dayWeek = "SEG";
                    break;
                case Calendar.TUESDAY:
                    dayWeek = "TER";
                    break;
                case Calendar.WEDNESDAY:
                    dayWeek = "QUA";
                    break;
                case Calendar.THURSDAY:
                    dayWeek = "QUI";
                    break;
                case Calendar.FRIDAY:
                    dayWeek = "SEX";
                    break;
                case Calendar.SATURDAY:
                    dayWeek = "SAB";
    
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayWeek;
    }

    public static int getWeekDayInt(String date){ //ex 07/03/2017
        int dayWeek = -1;
        GregorianCalendar gc = new GregorianCalendar();
        try {

            gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));

            switch (gc.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    dayWeek = 1;
                    break;
                case Calendar.MONDAY:
                    dayWeek = 2;
                    break;
                case Calendar.TUESDAY:
                    dayWeek = 3;
                    break;
                case Calendar.WEDNESDAY:
                    dayWeek = 4;
                    break;
                case Calendar.THURSDAY:
                    dayWeek = 5;
                    break;
                case Calendar.FRIDAY:
                    dayWeek = 6;
                    break;
                case Calendar.SATURDAY:
                    dayWeek = 7;
    
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayWeek;

    }
    
    public static int LastDayMonth(String date)
    {
        String lastDate = new String();

        Calendar c = Calendar.getInstance();
        String[] dateSplit = date.split("/");

        int month = Integer.parseInt(dateSplit[1]);
        
        //Ao colocar o mês corrente era retornado o mês posterior. Por isso o -1
        c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        lastDate = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
        String[] lastDateSplit = lastDate.split("/");

        int lastDay = Integer.parseInt(lastDateSplit[0]);

        return lastDay;
    }

    public static int WeekMonth(String date){

        int weekOfMonth = -1;
        GregorianCalendar gc = new GregorianCalendar();

        try {
            gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
            weekOfMonth = gc.get(Calendar.WEEK_OF_MONTH);
            }

        catch (ParseException e) {
            e.printStackTrace();
        }

        return weekOfMonth;
    }
}