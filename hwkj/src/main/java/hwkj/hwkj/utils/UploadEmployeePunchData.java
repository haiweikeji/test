package hwkj.hwkj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadEmployeePunchData {

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<=time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            //sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour + "小时" + min + "分";
    }

    public static boolean compareTime1(String st1,String st2)throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1=df.parse(st1);
        Date d2=df.parse(st2);
        long time1=d1.getTime();
        long time2=d2.getTime();
        if(time2<=time1){
            return true;
        }
        return false;
    }

    public static boolean compareTime2(String st1,String st2)throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1=df.parse(st1);
        Date d2=df.parse(st2);
        long time1=d1.getTime();
        long time2=d2.getTime();
        if(time2>=time1){
            return true;
        }
        return false;
    }

    /**
     *
     * @param st1 代表失效时间
     * @param st2 代表当前时间
     * @return
     * @throws Exception
     */
    public static boolean materialEngineeringDataCompareTime(String st1,String st2)throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d1=df.parse(st1);
        Date d2=df.parse(st2);
        long time1=d1.getTime();
        long time2=d2.getTime();
        if(time2<=time1){
            return true;
        }
        return false;
    }
}
