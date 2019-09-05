package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerManagement;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.CRM.CustomerManagementService;
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
public class CustomerManagementController {
    @Autowired
    private CustomerManagementService customerManagementService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerManagement.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerManagement(PageModel<CustomerManagement> customerManagementPageModel, CustomerManagement customerManagement){
        Map<String,Object> map=new HashMap<>();
        customerManagementService.queryCustomerManagementPage(customerManagementPageModel, customerManagement);
        map.put("rows",customerManagementPageModel.getList());
        map.put("total",customerManagementPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerManagementAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerManagementAdd(HttpServletRequest request, CustomerManagement customerManagement) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        customerManagement.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerManagement.setCreateDate(new Date());
        if(!customerManagementService.insertCustomerManagement(customerManagement)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerManagementUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerManagementUpdate(HttpServletRequest request,CustomerManagement customerManagement) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        customerManagement.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerManagement.setUpdateDate(new Date());
        if(!customerManagementService.updateCustomerManagement(customerManagement)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerManagementRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerManagementRemove(@RequestParam("id[]") Integer id[]) throws GlobalException {
        if(!customerManagementService.deleteCustomerManagement(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerManagementCustomerCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerManagementCustomerCode(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerManagementDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerManagementDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerManagementModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerManagementModel.xlsx");
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

    @RequestMapping(value ="/customerManagementDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerManagementDownLoadExcelAll(HttpServletResponse response, CustomerManagement customerManagement){
        String [] title={"客户代码","客户中文简称","年","季度","营收贡献(万)","逾期金额(万)",
                "币别","信用等级","评分","重要项次","业务员","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerManagement"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerManagement");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerManagement> list=customerManagementService.queryCustomerManagementForDownLoadAll(customerManagement);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getYear());
                row.createCell(3).setCellValue(list.get(j-1).getQuarter());
                if(list.get(j-1).getRevenue()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getRevenue());
                }
                if(list.get(j-1).getOverdueAmount()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getOverdueAmount());
                }
                row.createCell(6).setCellValue(list.get(j-1).getCurrency());
                row.createCell(7).setCellValue(list.get(j-1).getCreditLevel());
                row.createCell(8).setCellValue(list.get(j-1).getScore());
                row.createCell(9).setCellValue(list.get(j-1).getImportantItem());
                row.createCell(10).setCellValue(list.get(j-1).getBpm());
                row.createCell(11).setCellValue(list.get(j-1).getCreator());
                row.createCell(12).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(13).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(14).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/customerManagementDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerManagementDownLoadExcel(HttpServletResponse response, @RequestParam("id") Integer id[]){
        String [] title={"客户代码","客户中文简称","年","季度","营收贡献(万)","逾期金额(万)",
                "币别","信用等级","评分","重要项次","业务员","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerManagement"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerManagement");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerManagement> list=customerManagementService.queryCustomerManagementForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getYear());
                row.createCell(3).setCellValue(list.get(j-1).getQuarter());
                if(list.get(j-1).getRevenue()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getRevenue());
                }
                if(list.get(j-1).getOverdueAmount()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getOverdueAmount());
                }
                row.createCell(6).setCellValue(list.get(j-1).getCurrency());
                row.createCell(7).setCellValue(list.get(j-1).getCreditLevel());
                row.createCell(8).setCellValue(list.get(j-1).getScore());
                row.createCell(9).setCellValue(list.get(j-1).getImportantItem());
                row.createCell(10).setCellValue(list.get(j-1).getBpm());
                row.createCell(11).setCellValue(list.get(j-1).getCreator());
                row.createCell(12).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(13).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(14).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/customerManagementUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerManagementUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerManagementService.uploadCustomerManagement(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerManagementFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerManagementFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerManagement.do",method = RequestMethod.GET)
    public String customerManagement(){
        return "customerManagement";
    }
}
