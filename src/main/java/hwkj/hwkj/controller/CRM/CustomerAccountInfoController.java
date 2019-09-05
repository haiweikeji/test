package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerAccountInfo;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerAccountInfoService;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
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
public class CustomerAccountInfoController {
    @Autowired
    private CustomerAccountInfoService customerAccountInfoService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerAccountInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerAccountInfo(PageModel<CustomerAccountInfo> customerAccountInfoPageModel, CustomerAccountInfo customerAccountInfo){
        Map<String,Object> map=new HashMap<>();
        customerAccountInfoService.queryCustomerAccountInfoPage(customerAccountInfoPageModel,customerAccountInfo);
        map.put("rows",customerAccountInfoPageModel.getList());
        map.put("total",customerAccountInfoPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerAccountInfoAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerAccountInfoAdd(HttpServletRequest request,CustomerAccountInfo customerAccountInfo){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerAccountInfo.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerAccountInfo.setCreateDate(new Date());
        if(!customerAccountInfoService.insertCustomerAccountInfo(customerAccountInfo)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerAccountInfoUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerAccountInfoUpdate(HttpServletRequest request, CustomerAccountInfo customerAccountInfo){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerAccountInfo.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerAccountInfo.setUpdateDate(new Date());
        if(!customerAccountInfoService.updateCustomerAccountInfo(customerAccountInfo)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerAccountInfoRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerAccountInfoRemove(@RequestParam("id[]") Integer id[]){
        if (!customerAccountInfoService.deleteCustomerAccountInfo(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerAccountInfoNotExistCustomerCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerAccountInfoNotExistCustomerCode(){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataNotExistCustomerCodeForCustomerAccountInfo());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryCustomerAccountInfoAllCustomerCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerAccountInfoAllCustomerCode(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerAccountInfoDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerAccountInfoDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerAccountInfoModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerAccountInfoModel.xlsx");
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

    @RequestMapping(value ="/customerAccountInfoDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerAccountInfoDownLoadExcelAll(HttpServletResponse response, CustomerAccountInfo customerAccountInfo){
        String [] title={"客户代码","中文简称","社会信用代码","纳税人识别号","工商注册号","注册时间","注册资金(万)",
                "币别","信用等级","信用额度(万)","发票地址","开户银行","银行账号","银行地址","可接受币别","生效时间","失效时间"
                ,"建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerAccountInfo"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerAccountInfo");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerAccountInfo> list=customerAccountInfoService.queryCustomerAccountInfoForDownLoadAll(customerAccountInfo);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getCreditCode());
                row.createCell(3).setCellValue(list.get(j-1).getTaxpayerNumber());
                row.createCell(4).setCellValue(list.get(j-1).getRegistrationNumber());
                row.createCell(5).setCellValue(list.get(j-1).getRegistrationTime());
                if(list.get(j-1).getRegisteredCapital()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getRegisteredCapital());
                }
                row.createCell(7).setCellValue(list.get(j-1).getCurrency());
                row.createCell(8).setCellValue(list.get(j-1).getCreditLevel());
                if(list.get(j-1).getCreditAmount()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getCreditAmount());
                }
                row.createCell(10).setCellValue(list.get(j-1).getInvoiceAddress());
                row.createCell(11).setCellValue(list.get(j-1).getBank());
                row.createCell(12).setCellValue(list.get(j-1).getBankAccount());
                row.createCell(13).setCellValue(list.get(j-1).getBankAddress());
                row.createCell(14).setCellValue(list.get(j-1).getAcceptableCurrency());
                row.createCell(15).setCellValue(list.get(j-1).getForceTime());
                row.createCell(16).setCellValue(list.get(j-1).getFailureTime());
                row.createCell(17).setCellValue(list.get(j-1).getCreator());
                row.createCell(18).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(19).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(20).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/customerAccountInfoDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerAccountInfoDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","中文简称","社会信用代码","纳税人识别号","工商注册号","注册时间","注册资金(万)",
                "币别","信用等级","信用额度(万)","发票地址","开户银行","银行账号","银行地址","可接受币别","生效时间","失效时间"
                ,"建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerAccountInfo"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerAccountInfo");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerAccountInfo> list=customerAccountInfoService.queryCustomerAccountInfoForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getCreditCode());
                row.createCell(3).setCellValue(list.get(j-1).getTaxpayerNumber());
                row.createCell(4).setCellValue(list.get(j-1).getRegistrationNumber());
                row.createCell(5).setCellValue(list.get(j-1).getRegistrationTime());
                if(list.get(j-1).getRegisteredCapital()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getRegisteredCapital());
                }
                row.createCell(7).setCellValue(list.get(j-1).getCurrency());
                row.createCell(8).setCellValue(list.get(j-1).getCreditLevel());
                if(list.get(j-1).getCreditAmount()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getCreditAmount());
                }
                row.createCell(10).setCellValue(list.get(j-1).getInvoiceAddress());
                row.createCell(11).setCellValue(list.get(j-1).getBank());
                row.createCell(12).setCellValue(list.get(j-1).getBankAccount());
                row.createCell(13).setCellValue(list.get(j-1).getBankAddress());
                row.createCell(14).setCellValue(list.get(j-1).getAcceptableCurrency());
                row.createCell(15).setCellValue(list.get(j-1).getForceTime());
                row.createCell(16).setCellValue(list.get(j-1).getFailureTime());
                row.createCell(17).setCellValue(list.get(j-1).getCreator());
                row.createCell(18).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(19).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(20).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/customerAccountInfoUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerAccountInfoUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerAccountInfoService.uploadCustomerAccountInfo(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerAccountInfoFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerAccountInfoFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerAccountInfo.do",method = RequestMethod.GET)
    public String customerAccountInfo(){
        return "customerAccountInfo";
    }
}
