package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerStrategyAndSupport;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.CRM.CustomerStrategyAndSupportService;
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
public class CustomerStrategyAndSupportController {
    @Autowired
    private CustomerStrategyAndSupportService customerStrategyAndSupportService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerStrategyAndSupport.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerStrategyAndSupport(PageModel<CustomerStrategyAndSupport> customerStrategyAndSupportPageModel, CustomerStrategyAndSupport customerStrategyAndSupport){
        Map<String,Object> map=new HashMap<>();
        customerStrategyAndSupportService.queryCustomerStrategyAndSupportPage(customerStrategyAndSupportPageModel,customerStrategyAndSupport);
        map.put("rows",customerStrategyAndSupportPageModel.getList());
        map.put("total",customerStrategyAndSupportPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerStrategyAndSupportAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerStrategyAndSupportAdd(HttpServletRequest request,CustomerStrategyAndSupport customerStrategyAndSupport) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        customerStrategyAndSupport.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerStrategyAndSupport.setCreateDate(new Date());
        if(!customerStrategyAndSupportService.insertCustomerStrategyAndSupport(customerStrategyAndSupport)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerStrategyAndSupportUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerStrategyAndSupportUpdate(HttpServletRequest request,CustomerStrategyAndSupport customerStrategyAndSupport) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        customerStrategyAndSupport.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerStrategyAndSupport.setUpdateDate(new Date());
        if(!customerStrategyAndSupportService.updateCustomerStrategyAndSupport(customerStrategyAndSupport)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerStrategyAndSupportRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerStrategyAndSupportRemove(@RequestParam("id[]") Integer id[]){
        if(!customerStrategyAndSupportService.deleteCustomerStrategyAndSupport(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerStrategyAndSupportCustomerCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerStrategyAndSupportCustomerCode(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerStrategyAndSupportDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerStrategyAndSupportDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerStrategyAndSupportModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerStrategyAndSupportModel.xlsx");
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

    @RequestMapping(value ="/customerStrategyAndSupportDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerStrategyAndSupportDownLoadExcelAll(HttpServletResponse response, CustomerStrategyAndSupport customerStrategyAndSupport){
        String [] title={"客户代码","客户中文简称","客户英文简称","开始日期","结束日期","前三大客户","前三大产品",
                "年产量","占客户年营收比率","商业战略","支援需求", "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerStrategyAndSupport"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerStrategyAndSupport");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerStrategyAndSupport> list=customerStrategyAndSupportService.queryCustomerStrategyAndSupportForDownLoadAll(customerStrategyAndSupport);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getDateFrom());
                row.createCell(4).setCellValue(list.get(j-1).getDateTo());
                row.createCell(5).setCellValue(list.get(j-1).getTopThreeCustomer());
                row.createCell(6).setCellValue(list.get(j-1).getTopThreeProduct());
                row.createCell(7).setCellValue(list.get(j-1).getAnnualOutput());
                if(list.get(j-1).getAnnualRevenue()!=null){
                    row.createCell(8).setCellValue(list.get(j-1).getAnnualRevenue());
                }
                row.createCell(9).setCellValue(list.get(j-1).getBusinessStrategy());
                row.createCell(10).setCellValue(list.get(j-1).getSupportNeed());
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

    @RequestMapping(value ="/customerStrategyAndSupportDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerStrategyAndSupportDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","客户中文简称","客户英文简称","开始日期","结束日期","前三大客户","前三大产品",
                "年产量","占客户年营收比率","商业战略","支援需求", "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerStrategyAndSupport"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerStrategyAndSupport");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerStrategyAndSupport> list=customerStrategyAndSupportService.queryCustomerStrategyAndSupportForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getDateFrom());
                row.createCell(4).setCellValue(list.get(j-1).getDateTo());
                row.createCell(5).setCellValue(list.get(j-1).getTopThreeCustomer());
                row.createCell(6).setCellValue(list.get(j-1).getTopThreeProduct());
                row.createCell(7).setCellValue(list.get(j-1).getAnnualOutput());
                if(list.get(j-1).getAnnualRevenue()!=null){
                    row.createCell(8).setCellValue(list.get(j-1).getAnnualRevenue());
                }
                row.createCell(9).setCellValue(list.get(j-1).getBusinessStrategy());
                row.createCell(10).setCellValue(list.get(j-1).getSupportNeed());
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

    @RequestMapping(value = "/customerStrategyAndSupportUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerStrategyAndSupportUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerStrategyAndSupportService.uploadCustomerStrategyAndSupport(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerStrategyAndSupportFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerStrategyAndSupportFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerStrategyAndSupport.do",method = RequestMethod.GET)
    public String customerStrategyAndSupport(){
        return "customerStrategyAndSupport";
    }
}
