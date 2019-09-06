package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.EmployeePersonalData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.EmployeePersonalDataService;
import hwkj.hwkj.service.User.RoleMenuService;
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
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeePersonalDataController {
    @Autowired
    private EmployeePersonalDataService employeePersonalDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryEmployeePersonalData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryEmployeePersonalData(PageModel<EmployeePersonalData> employeePersonalDataPageModel, EmployeePersonalData employeePersonalData){
        Map<String,Object> map =new HashMap<>();
        employeePersonalDataService.queryEmployeePersonalDataPage(employeePersonalDataPageModel, employeePersonalData);
        map.put("rows",employeePersonalDataPageModel.getList());
        map.put("total",employeePersonalDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }
    @RequestMapping(value = "/employeePersonalDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeePersonalDataAdd(HttpServletRequest request,EmployeePersonalData employeePersonalData,@RequestParam("multipartFile") MultipartFile multipartFile){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(!employeePersonalDataService.insertEmployeePersonalData(request,employeePersonalData,multipartFile)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/employeePersonalDataUpdate.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String employeePersonalDataUpdate(HttpServletRequest request,EmployeePersonalData employeePersonalData,@RequestParam("multipartFile") MultipartFile multipartFile){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(multipartFile!=null && !"".equals(multipartFile.getOriginalFilename())){
            FileOutputStream fileOutputStream=null;
            long Photo_Time=new Date().getTime();
            String Photo_Name=multipartFile.getOriginalFilename();
            String Photo_Suffix_Name=Photo_Name.substring(Photo_Name.lastIndexOf("."));//后缀名
            String New_Photo_Name=employeePersonalData.getChinaName()+Photo_Time+Photo_Suffix_Name;
            try {
                fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/employee_photo/"+New_Photo_Name);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.flush();
                String Photo_Url="/hwkj/upload/employee_photo/"+New_Photo_Name;
                employeePersonalData.setPhoto(Photo_Url);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(employeePersonalData.getLeaveDate()!=null && employeePersonalData.getLeaveDate().trim().length()!=0){
            employeePersonalData.setStatus("N");
        }
        employeePersonalData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        employeePersonalData.setUpdateDate(new Date());
        if(!employeePersonalDataService.updateEmployeePersonalData(employeePersonalData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/employeePersonalDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeePersonalData(@RequestParam("id[]") Integer id[]) throws GlobalException{
        if(!employeePersonalDataService.deleteEmployeePersonalData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/employeePersonalDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeePersonalDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="EmployeePersonalDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream fis =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            fis = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\EmployeePersonalDataModel.xlsx");
            wb = new XSSFWorkbook(fis);
            out = response.getOutputStream();//响应输出流也就是传给客户端
            wb.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"模板下载失败,请稍后重试!\"}";
        }finally{
            try {
                if(fis != null)
                    fis.close();
                if(out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{\"success\":\"模板下载成功!\"}";
    }

    @RequestMapping(value ="/employeePersonalDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeePersonalDataDownLoadExcelAll(HttpServletResponse response,EmployeePersonalData employeePersonalData){
        String [] title={"工号","照片","中文名字","英文名字","性别","年龄","手机号码","微信号码","公司邮箱","私人邮箱",
                "入职日期","在职状态","资位","离职日期","出生日期","身份证号码","身份证住址","现住址","最高学历","专业","毕业院校",
                "毕业时间","语言","籍贯","民族","宗教信仰","婚姻状况","疾病史","家庭成员姓名","关系","紧急联络人","与紧急联络人关系",
                "联络人电话","招聘来源","期数","推荐人","备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeePersonalData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeePersonalData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeePersonalData> list=employeePersonalDataService.queryEmployeePersonalDataForDownLoadAll(employeePersonalData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getJobNumber());
                row.createCell(1).setCellValue(list.get(j-1).getPhoto());
                row.createCell(2).setCellValue(list.get(j-1).getChinaName());
                row.createCell(3).setCellValue(list.get(j-1).getEnglishName());
                row.createCell(4).setCellValue(list.get(j-1).getSex());
                if(list.get(j-1).getAge()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getAge());
                }
                row.createCell(6).setCellValue(list.get(j-1).getPhoneNumber());
                row.createCell(7).setCellValue(list.get(j-1).getWechatNumber());
                row.createCell(8).setCellValue(list.get(j-1).getCompanyMail());
                row.createCell(9).setCellValue(list.get(j-1).getPrivateMail());
                row.createCell(10).setCellValue(list.get(j-1).getEntryDate());
                row.createCell(11).setCellValue(list.get(j-1).getStatus());
                row.createCell(12).setCellValue(list.get(j-1).getEntryPosition());
                row.createCell(13).setCellValue(list.get(j-1).getLeaveDate());
                row.createCell(14).setCellValue(list.get(j-1).getBirthDate());
                row.createCell(15).setCellValue(list.get(j-1).getIdCard());
                row.createCell(16).setCellValue(list.get(j-1).getIdCardAddress());
                row.createCell(17).setCellValue(list.get(j-1).getPresentAddress());
                row.createCell(18).setCellValue(list.get(j-1).getEducation());
                row.createCell(19).setCellValue(list.get(j-1).getMajor());
                row.createCell(20).setCellValue(list.get(j-1).getGraduatedFrom());
                row.createCell(21).setCellValue(list.get(j-1).getGraduatedTime());
                row.createCell(22).setCellValue(list.get(j-1).getLanguage());
                row.createCell(23).setCellValue(list.get(j-1).getNativePlace());
                row.createCell(24).setCellValue(list.get(j-1).getNation());
                row.createCell(25).setCellValue(list.get(j-1).getReligiousBelief());
                row.createCell(26).setCellValue(list.get(j-1).getMaritalStatus());
                row.createCell(27).setCellValue(list.get(j-1).getHistoryDisease());
                row.createCell(28).setCellValue(list.get(j-1).getMemberFamily());
                row.createCell(29).setCellValue(list.get(j-1).getRelationship());
                row.createCell(30).setCellValue(list.get(j-1).getEmergencyContactPerson());
                row.createCell(31).setCellValue(list.get(j-1).getEmergencyContactPersonRelationship());
                row.createCell(32).setCellValue(list.get(j-1).getRelationshipPhone());
                row.createCell(33).setCellValue(list.get(j-1).getRecruitmentSources());
                row.createCell(34).setCellValue(list.get(j-1).getPeriodsNumber());
                row.createCell(35).setCellValue(list.get(j-1).getReferee());
                row.createCell(36).setCellValue(list.get(j-1).getRemarks());
                row.createCell(37).setCellValue(list.get(j-1).getCreator());
                row.createCell(38).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(39).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(40).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/employeePersonalDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeePersonalDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"工号","照片","中文名字","英文名字","性别","年龄","手机号码","微信号码","公司邮箱","私人邮箱",
                "入职日期","在职状态","资位","离职日期","出生日期","身份证号码","身份证住址","现住址","最高学历","专业","毕业院校",
                "毕业时间","语言","籍贯","民族","宗教信仰","婚姻状况","疾病史","家庭成员姓名","关系","紧急联络人","与紧急联络人关系",
                "联络人电话","招聘来源","期数","推荐人","备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeePersonalData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeePersonalData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeePersonalData> list=employeePersonalDataService.queryEmployeePersonalDataForDownLoad(id);
            for (int i=1,length=list.size();i<=length;i++){
                row=sheet.createRow(i);
                row.createCell(0).setCellValue(list.get(i-1).getJobNumber());
                row.createCell(1).setCellValue(list.get(i-1).getPhoto());
                row.createCell(2).setCellValue(list.get(i-1).getChinaName());
                row.createCell(3).setCellValue(list.get(i-1).getEnglishName());
                row.createCell(4).setCellValue(list.get(i-1).getSex());
                if(list.get(i-1).getAge()!=null){
                    row.createCell(5).setCellValue(list.get(i-1).getAge());
                }
                row.createCell(6).setCellValue(list.get(i-1).getPhoneNumber());
                row.createCell(7).setCellValue(list.get(i-1).getWechatNumber());
                row.createCell(8).setCellValue(list.get(i-1).getCompanyMail());
                row.createCell(9).setCellValue(list.get(i-1).getPrivateMail());
                row.createCell(10).setCellValue(list.get(i-1).getEntryDate());
                row.createCell(11).setCellValue(list.get(i-1).getStatus());
                row.createCell(12).setCellValue(list.get(i-1).getEntryPosition());
                row.createCell(13).setCellValue(list.get(i-1).getLeaveDate());
                row.createCell(14).setCellValue(list.get(i-1).getBirthDate());
                row.createCell(15).setCellValue(list.get(i-1).getIdCard());
                row.createCell(16).setCellValue(list.get(i-1).getIdCardAddress());
                row.createCell(17).setCellValue(list.get(i-1).getPresentAddress());
                row.createCell(18).setCellValue(list.get(i-1).getEducation());
                row.createCell(19).setCellValue(list.get(i-1).getMajor());
                row.createCell(20).setCellValue(list.get(i-1).getGraduatedFrom());
                row.createCell(21).setCellValue(list.get(i-1).getGraduatedTime());
                row.createCell(22).setCellValue(list.get(i-1).getLanguage());
                row.createCell(23).setCellValue(list.get(i-1).getNativePlace());
                row.createCell(24).setCellValue(list.get(i-1).getNation());
                row.createCell(25).setCellValue(list.get(i-1).getReligiousBelief());
                row.createCell(26).setCellValue(list.get(i-1).getMaritalStatus());
                row.createCell(27).setCellValue(list.get(i-1).getHistoryDisease());
                row.createCell(28).setCellValue(list.get(i-1).getMemberFamily());
                row.createCell(29).setCellValue(list.get(i-1).getRelationship());
                row.createCell(30).setCellValue(list.get(i-1).getEmergencyContactPerson());
                row.createCell(31).setCellValue(list.get(i-1).getEmergencyContactPersonRelationship());
                row.createCell(32).setCellValue(list.get(i-1).getRelationshipPhone());
                row.createCell(33).setCellValue(list.get(i-1).getRecruitmentSources());
                row.createCell(34).setCellValue(list.get(i-1).getPeriodsNumber());
                row.createCell(35).setCellValue(list.get(i-1).getReferee());
                row.createCell(36).setCellValue(list.get(i-1).getRemarks());
                row.createCell(37).setCellValue(list.get(i-1).getCreator());
                row.createCell(38).setCellValue(list.get(i-1).getCreateDate());
                row.createCell(39).setCellValue(list.get(i-1).getUpdatedBy());
                row.createCell(40).setCellValue(list.get(i-1).getUpdateDate());
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

    @RequestMapping(value ="/employeePersonalDataUpload.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String employeePersonalDataUpload(HttpServletRequest request,@RequestParam("multipartFile") MultipartFile multipartFile)throws GlobalException,Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!employeePersonalDataService.employeePersonalDataUpload(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryEmployeePersonalDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryEmployeePersonalDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/employeePersonalData.do" ,method = RequestMethod.GET)
    public String employeePersonalData(){
        return "employeePersonalData";
    }
}
