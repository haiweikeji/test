package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerFactoryAddress;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.CRM.CustomerEquipmentDataService;
import hwkj.hwkj.service.CRM.CustomerFactoryAddressService;
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
public class CustomerFactoryAddressController {
    @Autowired
    private CustomerFactoryAddressService customerFactoryAddressService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private CustomerEquipmentDataService customerEquipmentDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerFactoryAddress.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerFactoryAddress(PageModel<CustomerFactoryAddress> customerFactoryAddressPageModel, CustomerFactoryAddress customerFactoryAddress){
        Map<String,Object> map =new HashMap<>();
        customerFactoryAddressService.queryCustomerFactoryAddressPage(customerFactoryAddressPageModel,customerFactoryAddress);
        map.put("rows",customerFactoryAddressPageModel.getList());
        map.put("total",customerFactoryAddressPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerFactoryAddressAdd.do",method =RequestMethod.POST )
    @ResponseBody
    public String customerFactoryAddressAdd(HttpServletRequest request, CustomerFactoryAddress customerFactoryAddress){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerFactoryAddress.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerFactoryAddress.setCreateDate(new Date());
        if(!customerFactoryAddressService.insertCustomerFactoryAddress(customerFactoryAddress)){
            throw new GlobalException("error");//error代表:新增数据失败,请联系管理员或稍后尝试!
        }
        return "{\"message\":\"success\"}";//success代表:新增数据成功
    }

    @RequestMapping(value = "/customerFactoryAddressUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerFactoryAddressUpdate(HttpServletRequest request,CustomerFactoryAddress customerFactoryAddress){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerFactoryAddress.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerFactoryAddress.setUpdateDate(new Date());
        if(!customerFactoryAddressService.updateCustomerFactoryAddress(customerFactoryAddress)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";//success代表:更新数据成功!
    }

    @RequestMapping(value = "/customerFactoryAddressRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerFactoryAddressRemove(@RequestParam("id[]") Integer id[],@RequestParam("Customer_Code[]") String Customer_Code[],@RequestParam("Factory_Address[]") String Factory_Address[]){
        if(!customerFactoryAddressService.deleteCustomerFactoryAddress(id, Customer_Code, Factory_Address)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";//success代表:删除数据成功
    }

    @RequestMapping(value = "/queryCustomerFactoryAddressCustomerCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerFactoryAddressCustomerCode(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerFactoryAddressDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerFactoryAddressDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerFactoryAddressModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerFactoryAddressModel.xlsx");
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

    @RequestMapping(value ="/customerFactoryAddressDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerFactoryAddressDownLoadExcelAll(HttpServletResponse response, CustomerFactoryAddress customerFactoryAddress){
        String [] title={"客户代码","客户中文简称","客户英文简称","国家/地区","城市","工厂地址","地址码",
                          "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerFactoryAddress"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerFactoryAddress");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerFactoryAddress> list=customerFactoryAddressService.queryCustomerFactoryAddressForDownLoadAll(customerFactoryAddress);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getFactoryAddress());
                row.createCell(6).setCellValue(list.get(j-1).getCreator());
                row.createCell(7).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(8).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(9).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/customerFactoryAddressDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerFactoryAddressDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","客户中文简称","客户英文简称","国家/地区","城市","工厂地址","地址码",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerFactoryAddress"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerFactoryAddress");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerFactoryAddress> list=customerFactoryAddressService.queryCustomerFactoryAddressForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getFactoryAddress());
                row.createCell(6).setCellValue(list.get(j-1).getCreator());
                row.createCell(7).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(8).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(9).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/customerFactoryAddressUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerFactoryAddressUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerFactoryAddressService.uploadCustomerFactoryAddress(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerFactoryAddressFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerFactoryAddressFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerFactoryAddress.do",method = RequestMethod.GET)
    public String customerFactoryAddress(){
        return "customerFactoryAddress";
    }
}
