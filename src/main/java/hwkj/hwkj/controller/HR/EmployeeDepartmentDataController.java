package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.EmployeeDepartmentData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.EmployeeDepartmentDataService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeDepartmentDataController {

    @Autowired
    private EmployeeDepartmentDataService employeeDepartmentDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/selectEmployeeDepartmentData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectEmployeeDepartmentData (PageModel<EmployeeDepartmentData> employeeDepartmentDataPageModel, EmployeeDepartmentData employeeDepartmentData) throws Exception{
        Map<String,Object> map=new HashMap<>();
        employeeDepartmentDataService.queryEmployeeDepartmentDataPage(employeeDepartmentDataPageModel, employeeDepartmentData);
        map.put("rows",employeeDepartmentDataPageModel.getList());
        map.put("total",employeeDepartmentDataPageModel.getTotal());
        return  (JSONObject)JSONObject.toJSON(map);

    }

    @RequestMapping(value = "/employeeDepartmentDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeeDepartmentDataAdd(HttpServletRequest request,EmployeeDepartmentData employeeDepartmentData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        employeeDepartmentData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        employeeDepartmentData.setCreateDate(new Date());
        if(!employeeDepartmentDataService.insertEmployeeDepartmentData(employeeDepartmentData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/employeeDepartmentDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeeDepartmentDataUpdate(HttpServletRequest request,EmployeeDepartmentData employeeDepartmentData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        employeeDepartmentData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        employeeDepartmentData.setUpdateDate(new Date());
        if(!employeeDepartmentDataService.updateEmployeeDepartmentData(employeeDepartmentData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/employeeDepartmentDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeeDepartmentDataRemove(@RequestParam("id[]") Integer id[]){
        if(!employeeDepartmentDataService.deleteEmployeeDepartmentData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryEmployeeDepartmentDataForOption.do" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryEmployeeDepartmentDataForOption(@RequestParam("Org_Code") String Org_Code){
        Map<String,Object> map=employeeDepartmentDataService.queryEmployeeDepartmentDataForOption(Org_Code);
        return map;
    }

    @RequestMapping(value ="/queryEmployeeDepartmentDataOptionOrgCode.do" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryEmployeeDepartmentDataOptionOrgCode(@RequestParam("Org_Code") String Org_Code){
        Map<String,Object> map=employeeDepartmentDataService.queryEmployeeDepartmentDataOptionOrgCode(Org_Code);
        return map;
    }

    @RequestMapping(value ="/employeeDepartmentDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeeDepartmentDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="EmployeeDepartmentDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\EmployeeDepartmentDataModel.xlsx");
            wb = new XSSFWorkbook(is);
            out = response.getOutputStream();//响应输出流也就是传给客户端
            wb.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"模板下载失败,请稍后重试!\"}";
        }finally{
            try {
                if(is!= null)
                    is.close();
                if(out != null)
                    out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "{\"success\":\"模板下载成功!\"}";
    }

    @RequestMapping(value ="/employeeDepartmentDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeeDepartmentDataDownLoadExcelAll(HttpServletResponse response,EmployeeDepartmentData employeeDepartmentData){
        String [] title={"工号","中文名字","英文名字","性别","年龄","管理职","资位","职系","岗位","手机号码","微信号码","公司邮箱","入职时间","公司名称",
                "事业群名称","事业处","区域","部门名称","课别","组别","组织代码","任职类型","任职开始时间","任职结束时间","调动原因",
                "职位有效状态","职位状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeeDepartmentData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeeDepartmentData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeeDepartmentData> list=employeeDepartmentDataService.queryEmployeeDepartmentDataForDownLoadAll(employeeDepartmentData);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getEndService(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setEffectivePosition("Y");
                }
                else {
                    list.get(i).setEffectivePosition("N");
                }
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getJobNumber());
                row.createCell(1).setCellValue(list.get(j-1).getChinaName());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishName());
                row.createCell(3).setCellValue(list.get(j-1).getSex());
                if(list.get(j-1).getAge()!=0){
                    row.createCell(4).setCellValue(list.get(j-1).getAge());
                }
                row.createCell(5).setCellValue(list.get(j-1).getManagement());
                row.createCell(6).setCellValue(list.get(j-1).getEntryPosition());
                row.createCell(7).setCellValue(list.get(j-1).getJobFamily());
                row.createCell(8).setCellValue(list.get(j-1).getStation());
                row.createCell(9).setCellValue(list.get(j-1).getPhoneNumber());
                row.createCell(10).setCellValue(list.get(j-1).getWeChatNumber());
                row.createCell(11).setCellValue(list.get(j-1).getCompanyMail());
                row.createCell(12).setCellValue(list.get(j-1).getEntryDate());
                row.createCell(13).setCellValue(list.get(j-1).getLegalName());
                row.createCell(14).setCellValue(list.get(j-1).getBg());
                row.createCell(15).setCellValue(list.get(j-1).getBu());
                row.createCell(16).setCellValue(list.get(j-1).getRegion());
                row.createCell(17).setCellValue(list.get(j-1).getDept());
                row.createCell(18).setCellValue(list.get(j-1).getKe());
                row.createCell(19).setCellValue(list.get(j-1).getZu());
                row.createCell(20).setCellValue(list.get(j-1).getOrgCode());
                row.createCell(21).setCellValue(list.get(j-1).getJobType());
                row.createCell(22).setCellValue(list.get(j-1).getStartService());
                row.createCell(23).setCellValue(list.get(j-1).getEndService());
                row.createCell(24).setCellValue(list.get(j-1).getMoveReason());
                row.createCell(25).setCellValue(list.get(j-1).getEffectivePosition());
                row.createCell(26).setCellValue(list.get(j-1).getStatus());
                row.createCell(27).setCellValue(list.get(j-1).getCreator());
                row.createCell(28).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(29).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(30).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/employeeDepartmentDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String employeeDepartmentDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"工号","中文名字","英文名字","性别","年龄","管理职","资位","职系","岗位","手机号码","微信号码","公司邮箱","入职时间","公司名称",
                "事业群名称","事业处","区域","部门名称","课别","组别","组织代码","任职类型","任职开始时间","任职结束时间","调动原因",
                "职位有效状态","职位状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="EmployeeDepartmentData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("EmployeeDepartmentData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<EmployeeDepartmentData> list=employeeDepartmentDataService.queryEmployeeDepartmentDataForDownLoad(id);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getEndService(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setEffectivePosition("Y");
                }
                else {
                    list.get(i).setEffectivePosition("N");
                }
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getJobNumber());
                row.createCell(1).setCellValue(list.get(j-1).getChinaName());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishName());
                row.createCell(3).setCellValue(list.get(j-1).getSex());
                if(list.get(j-1).getAge()!=0){
                    row.createCell(4).setCellValue(list.get(j-1).getAge());
                }
                row.createCell(5).setCellValue(list.get(j-1).getManagement());
                row.createCell(6).setCellValue(list.get(j-1).getEntryPosition());
                row.createCell(7).setCellValue(list.get(j-1).getJobFamily());
                row.createCell(8).setCellValue(list.get(j-1).getStation());
                row.createCell(9).setCellValue(list.get(j-1).getPhoneNumber());
                row.createCell(10).setCellValue(list.get(j-1).getWeChatNumber());
                row.createCell(11).setCellValue(list.get(j-1).getCompanyMail());
                row.createCell(12).setCellValue(list.get(j-1).getEntryDate());
                row.createCell(13).setCellValue(list.get(j-1).getLegalName());
                row.createCell(14).setCellValue(list.get(j-1).getBg());
                row.createCell(15).setCellValue(list.get(j-1).getBu());
                row.createCell(16).setCellValue(list.get(j-1).getRegion());
                row.createCell(17).setCellValue(list.get(j-1).getDept());
                row.createCell(18).setCellValue(list.get(j-1).getKe());
                row.createCell(19).setCellValue(list.get(j-1).getZu());
                row.createCell(20).setCellValue(list.get(j-1).getOrgCode());
                row.createCell(21).setCellValue(list.get(j-1).getJobType());
                row.createCell(22).setCellValue(list.get(j-1).getStartService());
                row.createCell(23).setCellValue(list.get(j-1).getEndService());
                row.createCell(24).setCellValue(list.get(j-1).getMoveReason());
                row.createCell(25).setCellValue(list.get(j-1).getEffectivePosition());
                row.createCell(26).setCellValue(list.get(j-1).getStatus());
                row.createCell(27).setCellValue(list.get(j-1).getCreator());
                row.createCell(28).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(29).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(30).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/employeeDepartmentDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String employeeDepartmentDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!employeeDepartmentDataService.uploadEmployeeDepartmentData(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryEmployeeDepartmentDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryEmployeeDepartmentDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/employeeDepartmentData.do" ,method = RequestMethod.GET)
    public String employeeDepartmentData(){
        return "employeeDepartmentData";
    }
}
