package hwkj.hwkj;

import hwkj.hwkj.service.HR.OrgDataService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class upLoadTest {
    @Autowired
    private OrgDataService orgDataService;
    @Test
    public void hrUpload(){
        String flag ="1";
        try {
            List<OrgData> list =new ArrayList<OrgData>();
            XSSFWorkbook xssfWorkbook =new XSSFWorkbook(new FileInputStream(new File("G:\\HW\\hw.xlsx")));
            XSSFSheet sheet=xssfWorkbook.getSheetAt(2);
            System.out.println(sheet.getLastRowNum());
            //DecimalFormat df = new DecimalFormat("0");
            //String whatYourWant = df.format(cell.getNumericCellValue());
            int count=0;
            for (int i=3;i<=sheet.getLastRowNum();i++) {
                //if(sheet.getRow(i)==null){
                    //continue;
                //}
                if(i+1>sheet.getLastRowNum()){
                    break;
                }
                count++;
                while (!(sheet.getRow(i).getCell(0).getStringCellValue().equals(sheet.getRow(i+1).getCell(0).getStringCellValue()))){
                    System.out.print(sheet.getRow(i-count+1).getCell(0).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(2).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(5).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(6).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(7).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(8).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(9).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i-count+1).getCell(14).getStringCellValue()+",");

                    System.out.print(sheet.getRow(i).getCell(5).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i).getCell(6).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i).getCell(7).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i).getCell(8).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i).getCell(9).getStringCellValue()+",");
                    System.out.print(sheet.getRow(i).getCell(14).getStringCellValue()+",");
                    if(upLoadTest.compareTime1(sheet.getRow(i-count+1).getCell(6).getStringCellValue(),sheet.getRow(i-count+1).getCell(7).getStringCellValue())){
                        if("高尖科技园".equals(sheet.getRow(i-count+1).getCell(9).getStringCellValue())){
                            System.out.print("上午打卡正常"+",");
                        }else {
                            System.out.print("上午打卡外勤"+",");
                        }
                    }else {
                        String PP=upLoadTest.getDistanceTime(sheet.getRow(i-count+1).getCell(6).getStringCellValue(),sheet.getRow(i-count+1).getCell(7).getStringCellValue());
                        System.out.print("上午迟到了:"+PP);
                    }
                    if(upLoadTest.compareTime2(sheet.getRow(i).getCell(6).getStringCellValue(),sheet.getRow(i).getCell(7).getStringCellValue())){
                        if("高尖科技园".equals(sheet.getRow(i).getCell(9).getStringCellValue())){
                            System.out.print("下午打卡正常"+",");
                        }else {
                            System.out.print("下午打卡外勤"+",");
                        }
                    }else {
                        String LL=upLoadTest.getDistanceTime(sheet.getRow(i).getCell(6).getStringCellValue(),sheet.getRow(i).getCell(7).getStringCellValue());
                        System.out.print("下午早退了:"+LL);
                    }
                    System.out.println();
                    count=0;
                    break;
                }
                //count++;
               // for (int j=0;j<19;j++){
                   // if(sheet.getRow(i).getCell(0).getStringCellValue().equals(sheet.getRow(i+1).getCell(0).getStringCellValue())){
                        //System.out.print("");

                    //if("".equals(sheet.getRow(i).getCell(j).getStringCellValue())){
                        //continue;
                    //}
                    //if(sheet.getRow(i).getCell(j)==null){
                        //continue;
                    //}
                    //else if(sheet.getRow(i).getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC){
                       //System.out.println( df.format(sheet.getRow(i).getCell(j).getNumericCellValue()));
                    //}
                    //else {
                        //sheet.getRow(i).getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                        //System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
                    //}
                //}

            }
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(0).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(2).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(5).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(6).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(8).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(9).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()-1).getCell(14).getStringCellValue()+",");

            System.out.print(sheet.getRow(sheet.getLastRowNum()).getCell(5).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()).getCell(6).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()).getCell(8).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()).getCell(9).getStringCellValue()+",");
            System.out.print(sheet.getRow(sheet.getLastRowNum()).getCell(14).getStringCellValue()+",");
            if(upLoadTest.compareTime1(sheet.getRow(sheet.getLastRowNum()-1).getCell(6).getStringCellValue(),sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue())){
                if("高尖科技园".equals(sheet.getRow(sheet.getLastRowNum()-1).getCell(9).getStringCellValue())){
                    System.out.print("上午打卡正常"+",");
                }else {
                    System.out.print("上午打卡外勤"+",");
                }
            }else {
                String PP=upLoadTest.getDistanceTime(sheet.getRow(sheet.getLastRowNum()-1).getCell(6).getStringCellValue(),sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue());
                System.out.print("上午迟到了:"+PP);
            }
            if(upLoadTest.compareTime2(sheet.getRow(sheet.getLastRowNum()).getCell(6).getStringCellValue(),sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue())){
                if("高尖科技园".equals(sheet.getRow(sheet.getLastRowNum()).getCell(9).getStringCellValue())){
                    System.out.print("下午打卡正常"+",");
                }else {
                    System.out.print("下午打卡外勤"+",");
                }
            }else {
                String LL=upLoadTest.getDistanceTime(sheet.getRow(sheet.getLastRowNum()).getCell(6).getStringCellValue(),sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue());
                System.out.print("下午早退了:"+LL);
            }
        } catch (Exception e) {
            flag="0";
            e.printStackTrace();
        }
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        if("".equals(str2.trim())){
            return "4小时0分";
        }else if("".equals(str1.trim())){
            str1=str2.trim().substring(0,10)+" 08:00";
        }
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
        //System.out.println(day + "天" + hour + "小时" + min + "分" + sec + "秒");
        //System.out.println("迟到了:"+ hour + "小时" + min + "分");
        return hour + "小时" + min + "分";
    }

    public static boolean compareTime1(String st1,String st2)throws Exception{
        if("".equals(st2.trim())){
            return false;
        }else if("".equals(st1.trim())){
            st1=st2.trim().substring(0,10)+" 08:00";
        }
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
        if("".equals(st2.trim())){
            return false;
        }else if("".equals(st1.trim())){
            st1=st2.trim().substring(0,10)+" 08:00";
        }
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

    @Test
    public void compare(){
        upLoadTest.getDistanceTime("2018-12-26 08:00","2018-12-26 07:50");
    }

    @Test
    public void copyExcel() {
        XSSFWorkbook wb = null;
        FileInputStream fis =null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\EmployeePersonalDataModel.xlsx");
            fos = new FileOutputStream("E:\\model.xlsx");
            wb = new XSSFWorkbook(fis);
            wb.write(fos);
            fis.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(fis != null)
                    fis.close();
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test(){
        String s="\"";
        System.out.println(s);
    }
}
