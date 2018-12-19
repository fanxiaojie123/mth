package cn.mth.mthuserprovider.DateUtil;


import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Component
public class TimeUtil {
    /**
     * 获取一天中的最后时间也就是每天的YYYY:MM:DD：23:59:59
     *
     *
     * @return
     */
    public static Date getendTime(){
        Calendar calendar = new GregorianCalendar();
        //一天的结束时间 yyyy:MM:dd 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date dayEnd = calendar.getTime();
        return dayEnd;
    }

    /**
     * 获取一天中的开始时间也就是每天的YYYY:MM:DD：00:00:00
     * @return
     */
    public static Date getStartTime(){
        Calendar calendar = new GregorianCalendar();
        //一天的结束时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date dayStart = calendar.getTime();
        return dayStart;
    }

    /**
     * 获取明天的开始时间
     * @return
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getStartTime());
        cal.add(Calendar.DAY_OF_MONTH, +1);
        return cal.getTime();

    }

    /**
     * 获取明天的结束时间
     * @return
     */

    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getendTime());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();

    }


    /**
     * 获取当前时间
     * @return
     */
    public String getSystemTime(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(day);
        return  time;
    }

    /**
     *获取一周前的时间
     * @return
     */
    public Date getWeekTime(){
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)-7);
        Date date=curr.getTime();
        return date;
    }

    /**
     * date格式转换成String格式
     * @param time
     * @return
     */
    public String getTime(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HHmmss ");
        String date1 = formatter.format(time);//格式化数据
        return date1;
    }


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    /**
     * 获取当前得网络时间
     * @return
     */
    public Date getNetWorkTime(){
        String url = "http://time.tianqi.com";
        Date date = null;
        try {
            URL url1 = new URL(url);
            URLConnection conn = url1.openConnection();  //生成连接对象
            conn.connect();  //连接对象网页
             date = new Date(conn.getDate());  //获取对象网址时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * 获取当前时间之前或者之后
     * 的指定时间，只需要修改后面的数字参数即可
     * @return
     */
    public Date getBefor(){
        Date date = new Date();
        Date changTime = new Date(date.getTime() + 120 * 60 * 1000);
        return changTime;
    }


    /**
     * 比较两个date格式的大小
     * @param date
     * @param date1
     * @return
     */
    public int compareDate(Date date,Date date1){
        //相等则返回0，date1大返回1，否则返回-1
        int i = date.compareTo(date1);
        return i;
    }
}
