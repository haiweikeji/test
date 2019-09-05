package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerVisitPlan;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.CRM.CustomerContactService;
import hwkj.hwkj.service.CRM.CustomerVisitPlanService;
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
public class CustomerVisitPlanController {
    @Autowired
    private CustomerVisitPlanService customerVisitPlanService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private CustomerContactService customerContactService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerVisitPlan.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerVisitPlan(PageModel<CustomerVisitPlan> customerVisitPlanPageModel, CustomerVisitPlan customerVisitPlan){
        Map<String,Object> map=new HashMap<>();
        customerVisitPlanService.queryCustomerVisitPlanPage(customerVisitPlanPageModel, customerVisitPlan);
        map.put("rows",customerVisitPlanPageModel.getList());
        map.put("total",customerVisitPlanPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerVisitPlanAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerVisitPlanAdd(HttpServletRequest request, CustomerVisitPlan customerVisitPlan) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        customerVisitPlan.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerVisitPlan.setCreateDate(new Date());
        if(!customerVisitPlanService.insertCustomerVisitPlan(customerVisitPlan)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerVisitPlanUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerVisitPlanUpdate(HttpServletRequest request,CustomerVisitPlan customerVisitPlan) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        customerVisitPlan.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerVisitPlan.setUpdateDate(new Date());
        if(!customerVisitPlanService.updateCustomerVisitPlan(customerVisitPlan)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerVisitPlanRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerVisitPlanRemove(@RequestParam("id[]") Integer id[]) throws GlobalException {
        if(!customerVisitPlanService.deleteCustomerVisitPlan(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerVisitPlanCustomerCodeAndContact.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerVisitPlanCustomerCodeAndContact(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        map.put("contactList",customerContactService.queryCustomerContactByCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryCustomerVisitPlanCustomerCodeAndContactChineseName.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerVisitPlanCustomerCodeAndContactChineseName(@RequestParam("Customer_Code") String Customer_Code,@RequestParam("Contact_Chinese_Name") String Contact_Chinese_Name){
        Map<String,Object> map =new HashMap<>();
        map.put("contactChineseNameList",customerContactService.queryCustomerContactByCustomerCodeAndContactChineseName(Customer_Code,Contact_Chinese_Name));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerVisitPlanDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerVisitPlanDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerVisitPlanModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerVisitPlanModel.xlsx");
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

    @RequestMapping(value ="/customerVisitPlanDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerVisitPlanDownLoadExcelAll(HttpServletResponse response, CustomerVisitPlan customerVisitPlan){
        String [] title={"拜访编码","客户代码","客户中文简称","计划开始时间","计划结束时间","实际开始时间","实际结束时间",
                "联系人中文名字","联系人英文名字","职级","部门","拜访事项","拜访目的","拜访结果","状态","业务员",
                "建立人","建立日期","修改人","修改日期","审核人","审核日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerVisitPlan"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerVisitPlan");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerVisitPlan> list=customerVisitPlanService.queryCustomerVisitPlanForDownLoadAll(customerVisitPlan);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getVisitId());
                row.createCell(1).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(2).setCellValue(list.get(j-1).getChinaeseAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getPlanDateFrom());
                row.createCell(4).setCellValue(list.get(j-1).getPlanDateTo());
                row.createCell(5).setCellValue(list.get(j-1).getActualStartTime());
                row.createCell(6).setCellValue(list.get(j-1).getActualEndTime());
                row.createCell(7).setCellValue(list.get(j-1).getContactChineseName());
                row.createCell(8).setCellValue(list.get(j-1).getContactEnglishName());
                row.createCell(9).setCellValue(list.get(j-1).getTitle());
                row.createCell(10).setCellValue(list.get(j-1).getDept());
                row.createCell(11).setCellValue(list.get(j-1).getVisitItem());
                row.createCell(12).setCellValue(list.get(j-1).getVisitPurpose());
                row.createCell(13).setCellValue(list.get(j-1).getVisitResult());
                row.createCell(14).setCellValue(list.get(j-1).getStatus());
                row.createCell(15).setCellValue(list.get(j-1).getBpm());
                row.createCell(16).setCellValue(list.get(j-1).getCreator());
                row.createCell(17).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(18).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(19).setCellValue(list.get(j-1).getUpdateDate());
                row.createCell(20).setCellValue(list.get(j-1).getApprovedBy());
                row.createCell(21).setCellValue(list.get(j-1).getApprovedDate());
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

    @RequestMapping(value ="/customerVisitPlanDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerVisitPlanDownLoadExcel(HttpServletResponse response, @RequestParam("id") Integer id[]){
        String [] title={"拜访编码","客户代码","客户中文简称","计划开始时间","计划结束时间","实际开始时间","实际结束时间",
                "联系人中文名字","联系人英文名字","职级","部门","拜访事项","拜访目的","拜访结果","状态","业务员",
                "建立人","建立日期","修改人","修改日期","审核人","审核日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerVisitPlan"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerVisitPlan");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerVisitPlan> list=customerVisitPlanService.queryCustomerVisitPlanForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getVisitId());
                row.createCell(1).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(2).setCellValue(list.get(j-1).getChinaeseAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getPlanDateFrom());
                row.createCell(4).setCellValue(list.get(j-1).getPlanDateTo());
                row.createCell(5).setCellValue(list.get(j-1).getActualStartTime());
                row.createCell(6).setCellValue(list.get(j-1).getActualEndTime());
                row.createCell(7).setCellValue(list.get(j-1).getContactChineseName());
                row.createCell(8).setCellValue(list.get(j-1).getContactEnglishName());
                row.createCell(9).setCellValue(list.get(j-1).getTitle());
                row.createCell(10).setCellValue(list.get(j-1).getDept());
                row.createCell(11).setCellValue(list.get(j-1).getVisitItem());
                row.createCell(12).setCellValue(list.get(j-1).getVisitPurpose());
                row.createCell(13).setCellValue(list.get(j-1).getVisitResult());
                row.createCell(14).setCellValue(list.get(j-1).getStatus());
                row.createCell(15).setCellValue(list.get(j-1).getBpm());
                row.createCell(16).setCellValue(list.get(j-1).getCreator());
                row.createCell(17).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(18).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(19).setCellValue(list.get(j-1).getUpdateDate());
                row.createCell(20).setCellValue(list.get(j-1).getApprovedBy());
                row.createCell(21).setCellValue(list.get(j-1).getApprovedDate());
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

    @RequestMapping(value = "/customerVisitPlanUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerVisitPlanUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerVisitPlanService.uploadCustomerVisitPlan(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerVisitPlanFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerVisitPlanFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerVisitPlan.do",method = RequestMethod.GET)
    public String customerVisitPlan(){
        return "customerVisitPlan";
    }
}
