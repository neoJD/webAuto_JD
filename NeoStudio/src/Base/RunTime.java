package Base.NeoStudio.src.Base;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RunTime {

    public static String startTime;
    public static String endTime;
    
    public static Date time_start;
    public static Date time_end;
    public static long start;
    public static long end;
    public static long time_elapsed;
    public static long elapsed_m;
    public static int elapsed_s;

    public static void setStartTime(){
        time_start = Calendar.getInstance().getTime();
        start = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
        startTime = format.format(time_start);
    }

    public static void setEndTime(){
        time_end = Calendar.getInstance().getTime();
        end = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
        endTime = format.format(time_end);
    }

    public static void elapsedTime(){
        time_elapsed = (long) ((end-start)/1000.0);
    }
}
