package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.EmployeeDataSearch;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.service.HR.EmployeeDataSearchService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeDataSearchController {
    @Autowired
    private EmployeeDataSearchService employeeDataSearchService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/selectEmployeeDataSearch.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectEmployeeDataSearch(PageModel<EmployeeDataSearch> employeeDataSearchPageModel,EmployeeDataSearch employeeDataSearch){
        Map<String,Object> map =new HashMap<>();
        employeeDataSearchService.queryEmployeeDataSearchPage(employeeDataSearchPageModel, employeeDataSearch);
        map.put("rows",employeeDataSearchPageModel.getList());
        map.put("total",employeeDataSearchPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/employeeDataSearchDownLoadExcelAll.do",method = RequestMethod.GET)
    @ResponseBody
    public String employeeDataSearchDownLoadExcelAll(HttpServletResponse response,EmployeeDataSearch employeeDataSearch){
        String [] title={"工号","中文名字","英文名字","性别","年龄","管理职","资位","手机号码","微信号码","公司邮箱","私人邮箱","公司名称",
                "事业群名称","事业处","区域","部门名称","课别","组别","入职日期","出生日期","身份证号码","身份证住址","现住址","最高学历","专业","毕业院校",
                "毕业时间","语言","籍贯","民族","宗教信仰","婚姻状况","疾病史","紧急联络人","与紧急联络人关系","紧急联系人电话",
                "职位状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeeDataSearch"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeeDataSearch");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeeDataSearch> list=employeeDataSearchService.queryEmployeeDataSearchForDownLoadAll(employeeDataSearch);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getJob_Number());
                row.createCell(1).setCellValue(list.get(j-1).getChina_Name());
                row.createCell(2).setCellValue(list.get(j-1).getEnglish_Name());
                row.createCell(3).setCellValue(list.get(j-1).getSex());
                if(list.get(j-1).getAge()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getAge());
                }
                row.createCell(5).setCellValue(list.get(j-1).getManagement());
                row.createCell(6).setCellValue(list.get(j-1).getEntry_Position());
                row.createCell(7).setCellValue(list.get(j-1).getPhone_Number());
                row.createCell(8).setCellValue(list.get(j-1).getWeChat_Number());
                row.createCell(9).setCellValue(list.get(j-1).getCompany_Mail());
                row.createCell(10).setCellValue(list.get(j-1).getPrivate_Mail());
                row.createCell(11).setCellValue(list.get(j-1).getLegal_Name());
                row.createCell(12).setCellValue(list.get(j-1).getBG());
                row.createCell(13).setCellValue(list.get(j-1).getBU());
                row.createCell(14).setCellValue(list.get(j-1).getRegion());
                row.createCell(15).setCellValue(list.get(j-1).getDept());
                row.createCell(16).setCellValue(list.get(j-1).getKe());
                row.createCell(17).setCellValue(list.get(j-1).getZu());
                row.createCell(18).setCellValue(list.get(j-1).getEntry_Date());
                row.createCell(19).setCellValue(list.get(j-1).getBirth_Date());
                row.createCell(20).setCellValue(list.get(j-1).getId_Card());
                row.createCell(21).setCellValue(list.get(j-1).getId_Card_Address());
                row.createCell(22).setCellValue(list.get(j-1).getPresent_Address());
                row.createCell(23).setCellValue(list.get(j-1).getEducation());
                row.createCell(24).setCellValue(list.get(j-1).getMajor());
                row.createCell(25).setCellValue(list.get(j-1).getGraduated_From());
                row.createCell(26).setCellValue(list.get(j-1).getGraduated_From());
                row.createCell(27).setCellValue(list.get(j-1).getLanguage());
                row.createCell(28).setCellValue(list.get(j-1).getNative_Place());
                row.createCell(29).setCellValue(list.get(j-1).getNation());
                row.createCell(30).setCellValue(list.get(j-1).getReligious_Belief());
                row.createCell(31).setCellValue(list.get(j-1).getMarital_Status());
                row.createCell(32).setCellValue(list.get(j-1).getHistory_Disease());
                row.createCell(33).setCellValue(list.get(j-1).getEmergency_Contact_Person());
                row.createCell(34).setCellValue(list.get(j-1).getEmergency_Contact_Person_Relationship());
                row.createCell(35).setCellValue(list.get(j-1).getRelationship_Phone());
                row.createCell(36).setCellValue(list.get(j-1).getStatus());
                row.createCell(37).setCellValue(list.get(j-1).getCreator());
                row.createCell(38).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(39).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(40).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value = "/employeeDataSearchDownLoadExcel.do",method = RequestMethod.GET)
    @ResponseBody
    public String employeeDataSearchDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"工号","中文名字","英文名字","性别","年龄","管理职","资位","手机号码","微信号码","公司邮箱","私人邮箱","公司名称",
                "事业群名称","事业处","区域","部门名称","课别","组别","入职日期","出生日期","身份证号码","身份证住址","现住址","最高学历","专业","毕业院校",
                "毕业时间","语言","籍贯","民族","宗教信仰","婚姻状况","疾病史","紧急联络人","与紧急联络人关系","紧急联系人电话",
                "职位状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeeDataSearch"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeeDataSearch");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeeDataSearch> list=employeeDataSearchService.queryEmployeeDataSearchForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getJob_Number());
                row.createCell(1).setCellValue(list.get(j-1).getChina_Name());
                row.createCell(2).setCellValue(list.get(j-1).getEnglish_Name());
                row.createCell(3).setCellValue(list.get(j-1).getSex());
                if(list.get(j-1).getAge()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getAge());
                }
                row.createCell(5).setCellValue(list.get(j-1).getManagement());
                row.createCell(6).setCellValue(list.get(j-1).getEntry_Position());
                row.createCell(7).setCellValue(list.get(j-1).getPhone_Number());
                row.createCell(8).setCellValue(list.get(j-1).getWeChat_Number());
                row.createCell(9).setCellValue(list.get(j-1).getCompany_Mail());
                row.createCell(10).setCellValue(list.get(j-1).getPrivate_Mail());
                row.createCell(11).setCellValue(list.get(j-1).getLegal_Name());
                row.createCell(12).setCellValue(list.get(j-1).getBG());
                row.createCell(13).setCellValue(list.get(j-1).getBU());
                row.createCell(14).setCellValue(list.get(j-1).getRegion());
                row.createCell(15).setCellValue(list.get(j-1).getDept());
                row.createCell(16).setCellValue(list.get(j-1).getKe());
                row.createCell(17).setCellValue(list.get(j-1).getZu());
                row.createCell(18).setCellValue(list.get(j-1).getEntry_Date());
                row.createCell(19).setCellValue(list.get(j-1).getBirth_Date());
                row.createCell(20).setCellValue(list.get(j-1).getId_Card());
                row.createCell(21).setCellValue(list.get(j-1).getId_Card_Address());
                row.createCell(22).setCellValue(list.get(j-1).getPresent_Address());
                row.createCell(23).setCellValue(list.get(j-1).getEducation());
                row.createCell(24).setCellValue(list.get(j-1).getMajor());
                row.createCell(25).setCellValue(list.get(j-1).getGraduated_From());
                row.createCell(26).setCellValue(list.get(j-1).getGraduated_From());
                row.createCell(27).setCellValue(list.get(j-1).getLanguage());
                row.createCell(28).setCellValue(list.get(j-1).getNative_Place());
                row.createCell(29).setCellValue(list.get(j-1).getNation());
                row.createCell(30).setCellValue(list.get(j-1).getReligious_Belief());
                row.createCell(31).setCellValue(list.get(j-1).getMarital_Status());
                row.createCell(32).setCellValue(list.get(j-1).getHistory_Disease());
                row.createCell(33).setCellValue(list.get(j-1).getEmergency_Contact_Person());
                row.createCell(34).setCellValue(list.get(j-1).getEmergency_Contact_Person_Relationship());
                row.createCell(35).setCellValue(list.get(j-1).getRelationship_Phone());
                row.createCell(36).setCellValue(list.get(j-1).getStatus());
                row.createCell(37).setCellValue(list.get(j-1).getCreator());
                row.createCell(38).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(39).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(40).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value ="/queryEmployeeDataSearchFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryEmployeeDataSearchFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/employeeDataSearch.do" ,method = RequestMethod.GET)
    public String employeeDataSearch(){
        return "employeeDataSearch";
    }
}
