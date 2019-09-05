package hwkj.hwkj;

import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getTime {

    public void getYear(){
        //Calendar calendar =Calendar.getInstance();
        //calendar.get(Calendar.YEAR)
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
    }
    @Test
    @Ignore
    public void test1()throws Exception{
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd");
        Date From=simpleDateFormat.parse("19960125");
        Date To =new Date();
        Long Start=From.getTime();
        Long End=To.getTime();
        Long Year=(End-Start)/(60*60*24*1000*365);
        System.out.println(Year);
    }
    @Test
    public void test2()throws Exception{
        String date1="1987-01-01";

        String date2="2010-01-01";

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");

        Date d1=sdf.parse(date1);

        Date d2=sdf.parse(date2);
        int year=60*60*24*1000;
        long time=d2.getTime()-d1.getTime();
        long daysBetween=(time/year)/365;
        System.out.println("1987-01-01 与 2010-01-01 相隔 "+daysBetween+" 天");
    }

    @Test
    public void test3(){
        for(int i=1000000;i<1999999;i++){
            if(i%198==0 && String.valueOf(i).matches("^[1][3][0-9][0-9][4][5][6]")){
                System.out.println(i);
            }
        }
    }
}
