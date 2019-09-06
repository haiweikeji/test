package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.EmployeePunchData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.EmployeePunchDataService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EmployeePunchDataController {
    @Autowired
    private EmployeePunchDataService employeePunchDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryEmployeePunchData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryEmployeePunchData(PageModel<EmployeePunchData> employeePunchDataPageModel, EmployeePunchData employeePunchData){
        Map<String,Object> map=new HashMap<>();
        employeePunchDataService.queryEmployeePunchDataPage(employeePunchDataPageModel,employeePunchData);
        map.put("rows",employeePunchDataPageModel.getList());
        map.put("total",employeePunchDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/employeePunchDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeePunchDataUpload(@Param("multipartFile") MultipartFile multipartFile){
        //MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        //MultipartFile multipartFile=multipartHttpServletRequest.getFile("file");
        List<EmployeePunchData> list =new ArrayList<>();
        InputStream in=null;
        EmployeePunchData employeePunchData=null;
        XSSFWorkbook xssfWorkbook=null;
        int count=0;
        try{
            in= multipartFile.getInputStream();//获取控件内容的输入流
            xssfWorkbook =new XSSFWorkbook(in);
            XSSFSheet sheet=xssfWorkbook.getSheetAt(2);
            String Attendance_Date=sheet.getRow(3).getCell(5).getStringCellValue();
            String Morning_Work_Time="20"+Attendance_Date.trim().substring(0,8)+" 08:00";
            String Afternoon_Work_Time="20"+Attendance_Date.trim().substring(0,8)+" 17:30";
            for(int i=3,length=sheet.getLastRowNum();i<length;i++){
                if(i+1>length){
                    break;
                }
                count++;
                while (!(sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(sheet.getRow(i+1).getCell(0).getStringCellValue().trim()))){
                    employeePunchData=new EmployeePunchData();
                    employeePunchData.setJobNumber(sheet.getRow(i-count+1).getCell(2).getStringCellValue());
                    employeePunchData.setAttendanceDate(Attendance_Date);
                    employeePunchData.setMorningWorkTime(Morning_Work_Time);
                    employeePunchData.setMorningPunchTime(sheet.getRow(i-count+1).getCell(7).getStringCellValue());
                    if(count==1){
                        employeePunchData.setMorningPunchResult("打卡次数异常");
                        employeePunchData.setLaterAmount(0);
                        employeePunchData.setLaterTime("0");
                        employeePunchData.setAfternoonPunchResult("打卡次数异常");
                        employeePunchData.setEarlyAmount(0);
                        employeePunchData.setEarlyTime("0");
                    }else {
                        if("".equals(sheet.getRow(i-count+1).getCell(7).getStringCellValue().trim())){
                            employeePunchData.setMorningPunchResult("上午未打卡或打卡异常");
                            employeePunchData.setLaterAmount(0);
                            employeePunchData.setLaterTime("0");
                        }else {
                            if(UploadEmployeePunchData.compareTime1(Morning_Work_Time,sheet.getRow(i-count+1).getCell(7).getStringCellValue())){
                                if("高尖科技园".equals(sheet.getRow(i-count+1).getCell(9).getStringCellValue())){
                                    employeePunchData.setMorningPunchResult("上午打卡正常");
                                }else {
                                    employeePunchData.setMorningPunchResult("上午打卡外勤");
                                }
                                employeePunchData.setLaterAmount(0);
                                employeePunchData.setLaterTime("0");
                            }else {
                                if(!UploadEmployeePunchData.compareTime1("20"+Attendance_Date.trim().substring(0,8)+" 12:00",sheet.getRow(i-count+1).getCell(7).getStringCellValue())){
                                    employeePunchData.setMorningPunchResult("上午迟到4小时");
                                    employeePunchData.setLaterAmount(1);
                                    employeePunchData.setLaterTime("4小时");
                                }else {
                                    String Later_Time=UploadEmployeePunchData.getDistanceTime(Morning_Work_Time,sheet.getRow(i-count+1).getCell(7).getStringCellValue());
                                    employeePunchData.setMorningPunchResult("上午迟到"+Later_Time);
                                    employeePunchData.setLaterAmount(1);
                                    employeePunchData.setLaterTime(Later_Time);
                                }
                            }
                        }
                        if("".equals(sheet.getRow(i).getCell(7).getStringCellValue().trim())){
                            employeePunchData.setAfternoonPunchResult("下午未打卡或打卡异常");
                            employeePunchData.setEarlyAmount(0);
                            employeePunchData.setEarlyTime("0");
                        }else{
                            if(UploadEmployeePunchData.compareTime2(Afternoon_Work_Time,sheet.getRow(i).getCell(7).getStringCellValue())){
                                if("高尖科技园".equals(sheet.getRow(i).getCell(9).getStringCellValue())){
                                    employeePunchData.setAfternoonPunchResult("下午打卡正常");
                                }else {
                                    employeePunchData.setAfternoonPunchResult("下午打卡外勤");
                                }
                                employeePunchData.setEarlyAmount(0);
                                employeePunchData.setEarlyTime("0");
                            }else {
                                if(!UploadEmployeePunchData.compareTime2("20"+Attendance_Date.trim().substring(0,8)+" 13:30",sheet.getRow(i).getCell(7).getStringCellValue())){
                                    employeePunchData.setAfternoonPunchResult("下午早退4小时");
                                    employeePunchData.setEarlyAmount(1);
                                    employeePunchData.setEarlyTime("4小时");
                                }else {
                                    String Early_Time=UploadEmployeePunchData.getDistanceTime(Afternoon_Work_Time,sheet.getRow(i).getCell(7).getStringCellValue());
                                    employeePunchData.setAfternoonPunchResult("下午早退了"+Early_Time);
                                    employeePunchData.setEarlyAmount(1);
                                    employeePunchData.setEarlyTime(Early_Time);
                                }
                            }
                        }
                    }
                    employeePunchData.setMorningPunchAddress(sheet.getRow(i-count+1).getCell(9).getStringCellValue());
                    employeePunchData.setMorningPunchEquipment(sheet.getRow(i-count+1).getCell(14).getStringCellValue());
                    employeePunchData.setAfternoonWorkTime(Afternoon_Work_Time);
                    employeePunchData.setAfternoonPunchTime(sheet.getRow(i).getCell(7).getStringCellValue());
                    employeePunchData.setAfternoonPunchAddress(sheet.getRow(i).getCell(9).getStringCellValue());
                    employeePunchData.setAfternoonPunchEquipment(sheet.getRow(i).getCell(14).getStringCellValue());
                    list.add(employeePunchData);
                    count=0;
                    break;
                }
            }
            if(sheet.getRow(sheet.getLastRowNum()-1).getCell(2).getStringCellValue().trim().equals(sheet.getRow(sheet.getLastRowNum()).getCell(2).getStringCellValue().trim())){
                employeePunchData=new EmployeePunchData();
                employeePunchData.setJobNumber(sheet.getRow(sheet.getLastRowNum()-1).getCell(2).getStringCellValue());
                employeePunchData.setAttendanceDate(Attendance_Date);
                employeePunchData.setMorningWorkTime(Morning_Work_Time);
                employeePunchData.setMorningPunchTime(sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue());
                if("".equals(sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue().trim())){
                    employeePunchData.setMorningPunchResult("上午未打卡或打卡异常");
                    employeePunchData.setLaterAmount(0);
                    employeePunchData.setLaterTime("0");
                }else {
                    if(UploadEmployeePunchData.compareTime1(Morning_Work_Time,sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue())){
                        if("高尖科技园".equals(sheet.getRow(sheet.getLastRowNum()-1).getCell(9).getStringCellValue())){
                            employeePunchData.setMorningPunchResult("上午打卡正常");
                        }else {
                            employeePunchData.setMorningPunchResult("上午打卡外勤");
                        }
                        employeePunchData.setLaterAmount(0);
                        employeePunchData.setLaterTime("0");
                    }else {
                        if(!UploadEmployeePunchData.compareTime1("20"+Attendance_Date.trim().substring(0,8)+" 12:00",sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue())){
                            employeePunchData.setMorningPunchResult("上午迟到4小时");
                            employeePunchData.setLaterAmount(1);
                            employeePunchData.setLaterTime("4小时");
                        }else {
                            String Later_Time=UploadEmployeePunchData.getDistanceTime(Morning_Work_Time,sheet.getRow(sheet.getLastRowNum()-1).getCell(7).getStringCellValue());
                            employeePunchData.setMorningPunchResult("上午迟到"+Later_Time);
                            employeePunchData.setLaterAmount(1);
                            employeePunchData.setLaterTime(Later_Time);
                        }
                    }
                }
                if("".equals(sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue().trim())){
                    employeePunchData.setAfternoonPunchResult("下午未打卡或打卡异常");
                    employeePunchData.setEarlyAmount(0);
                    employeePunchData.setEarlyTime("0");
                }else{
                    if(UploadEmployeePunchData.compareTime2(Afternoon_Work_Time,sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue())){
                        if("高尖科技园".equals(sheet.getRow(sheet.getLastRowNum()).getCell(9).getStringCellValue())){
                            employeePunchData.setAfternoonPunchResult("下午打卡正常");
                        }else {
                            employeePunchData.setAfternoonPunchResult("下午打卡外勤");
                        }
                        employeePunchData.setEarlyAmount(0);
                        employeePunchData.setEarlyTime("0");
                    }else {
                        if(!UploadEmployeePunchData.compareTime2("20"+Attendance_Date.trim().substring(0,8)+" 13:30",sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue())){
                            employeePunchData.setAfternoonPunchResult("下午早退4小时");
                            employeePunchData.setEarlyAmount(1);
                            employeePunchData.setEarlyTime("4小时");
                        }else {
                            String Early_Time=UploadEmployeePunchData.getDistanceTime(Afternoon_Work_Time,sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue());
                            employeePunchData.setAfternoonPunchResult("下午早退了"+Early_Time);
                            employeePunchData.setEarlyAmount(1);
                            employeePunchData.setEarlyTime(Early_Time);
                        }
                    }
                }
                employeePunchData.setMorningPunchAddress(sheet.getRow(sheet.getLastRowNum()-1).getCell(9).getStringCellValue());
                employeePunchData.setMorningPunchEquipment(sheet.getRow(sheet.getLastRowNum()-1).getCell(14).getStringCellValue());
                employeePunchData.setAfternoonWorkTime(Afternoon_Work_Time);
                employeePunchData.setAfternoonPunchTime(sheet.getRow(sheet.getLastRowNum()).getCell(7).getStringCellValue());
                employeePunchData.setAfternoonPunchAddress(sheet.getRow(sheet.getLastRowNum()).getCell(9).getStringCellValue());
                employeePunchData.setAfternoonPunchEquipment(sheet.getRow(sheet.getLastRowNum()).getCell(14).getStringCellValue());
                list.add(employeePunchData);
            }else {
                for(int k=1;k>=0;k--){
                    employeePunchData=new EmployeePunchData();
                    employeePunchData.setJobNumber(sheet.getRow(sheet.getLastRowNum()-k).getCell(2).getStringCellValue());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (xssfWorkbook!=null){
                try {
                    xssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!employeePunchDataService.uploadEmployeePunchData(list)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/employeePunchDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeePunchDataDownLoadExcelAll(HttpServletResponse response, EmployeePunchData employeePunchData){
        String [] title={"姓名","部门","工号","职位","考勤日期","上午上班时间","上班打卡时间","上班打卡结果","上班打卡地址","上班打卡设备","下午上班时间",
                "下班打卡时间","下班打卡结果","下班打卡地址","下班打卡设备","迟到次数","迟到时间","早退次数","早退时间"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeePunchData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeePunchData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeePunchData> list=employeePunchDataService.queryEmployeePunchDataForDownLoadAll(employeePunchData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getName());
                row.createCell(1).setCellValue(list.get(j-1).getDept());
                row.createCell(2).setCellValue(list.get(j-1).getJobNumber());
                row.createCell(3).setCellValue(list.get(j-1).getStation());
                row.createCell(4).setCellValue(list.get(j-1).getAttendanceDate());
                row.createCell(5).setCellValue(list.get(j-1).getMorningWorkTime());
                row.createCell(6).setCellValue(list.get(j-1).getMorningPunchTime());
                row.createCell(7).setCellValue(list.get(j-1).getMorningPunchResult());
                row.createCell(8).setCellValue(list.get(j-1).getMorningPunchAddress());
                row.createCell(9).setCellValue(list.get(j-1).getMorningPunchEquipment());
                row.createCell(10).setCellValue(list.get(j-1).getAfternoonWorkTime());
                row.createCell(11).setCellValue(list.get(j-1).getAfternoonPunchTime());
                row.createCell(12).setCellValue(list.get(j-1).getAfternoonPunchResult());
                row.createCell(13).setCellValue(list.get(j-1).getAfternoonPunchAddress());
                row.createCell(14).setCellValue(list.get(j-1).getAfternoonPunchEquipment());
                if(list.get(j-1).getLaterAmount()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getLaterAmount());
                }
                row.createCell(16).setCellValue(list.get(j-1).getLaterTime());
                if(list.get(j-1).getEarlyAmount()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getEarlyAmount());
                }
                row.createCell(18).setCellValue(list.get(j-1).getEarlyTime());
            }
            outputStream=response.getOutputStream();
            xssfWorkbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
            return "{\"error\":\"文件下载失败,请稍后重试!\"}";
        }
        finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(xssfWorkbook!=null){
                try {
                    xssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "{\"success\":\"文件下载成功!\"}";
    }

    @RequestMapping(value ="/employeePunchDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeePunchDataDownLoadExcel(HttpServletResponse response, @RequestParam("Job_Number") String Job_Number[]){
        String [] title={"姓名","部门","工号","职位","考勤日期","上午上班时间","上班打卡时间","上班打卡结果","上班打卡地址","上班打卡设备","下午上班时间",
                "下班打卡时间","下班打卡结果","下班打卡地址","下班打卡设备","迟到次数","迟到时间","早退次数","早退时间"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeePunchData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeePunchData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeePunchData> list=employeePunchDataService.queryEmployeePunchDataForDownLoad(Job_Number);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getName());
                row.createCell(1).setCellValue(list.get(j-1).getDept());
                row.createCell(2).setCellValue(list.get(j-1).getJobNumber());
                row.createCell(3).setCellValue(list.get(j-1).getStation());
                row.createCell(4).setCellValue(list.get(j-1).getAttendanceDate());
                row.createCell(5).setCellValue(list.get(j-1).getMorningWorkTime());
                row.createCell(6).setCellValue(list.get(j-1).getMorningPunchTime());
                row.createCell(7).setCellValue(list.get(j-1).getMorningPunchResult());
                row.createCell(8).setCellValue(list.get(j-1).getMorningPunchAddress());
                row.createCell(9).setCellValue(list.get(j-1).getMorningPunchEquipment());
                row.createCell(10).setCellValue(list.get(j-1).getAfternoonWorkTime());
                row.createCell(11).setCellValue(list.get(j-1).getAfternoonPunchTime());
                row.createCell(12).setCellValue(list.get(j-1).getAfternoonPunchResult());
                row.createCell(13).setCellValue(list.get(j-1).getAfternoonPunchAddress());
                row.createCell(14).setCellValue(list.get(j-1).getAfternoonPunchEquipment());
                if(list.get(j-1).getLaterAmount()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getLaterAmount());
                }
                row.createCell(16).setCellValue(list.get(j-1).getLaterTime());
                if(list.get(j-1).getEarlyAmount()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getEarlyAmount());
                }
                row.createCell(18).setCellValue(list.get(j-1).getEarlyTime());
            }
            outputStream=response.getOutputStream();
            xssfWorkbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
            return "{\"error\":\"文件下载失败,请稍后重试!\"}";
        }
        finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(xssfWorkbook!=null){
                try {
                    xssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "{\"success\":\"文件下载成功!\"}";
    }

    @RequestMapping(value ="/queryEmployeePunchDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryEmployeePunchDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/employeePunchData.do",method = RequestMethod.GET)
    public String employeePunchData(){
        return "employeePunchData";
    }
}
