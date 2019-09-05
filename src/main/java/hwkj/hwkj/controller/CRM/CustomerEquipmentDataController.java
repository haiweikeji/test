package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerEquipmentData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.CRM.CustomerEquipmentDataService;
import hwkj.hwkj.service.CRM.CustomerFactoryAddressService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.UserRoleService;
import hwkj.hwkj.service.User.UserService;
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
public class CustomerEquipmentDataController {
    @Autowired
    private CustomerEquipmentDataService customerEquipmentDataService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerFactoryAddressService customerFactoryAddressService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerEquipmentData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerEquipmentData(PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, CustomerEquipmentData customerEquipmentData){
        Map<String,Object> map= new HashMap<>();
        customerEquipmentDataService.queryCustomerEquipmentDataPage(customerEquipmentDataPageModel,customerEquipmentData);
        map.put("rows",customerEquipmentDataPageModel.getList());
        map.put("total",customerEquipmentDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerEquipmentDataAdd.do",method =RequestMethod.POST )
    @ResponseBody
    public String customerEquipmentDataAdd(HttpServletRequest request, CustomerEquipmentData customerEquipmentData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerEquipmentData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerEquipmentData.setCreateDate(new Date());
        if(!customerEquipmentDataService.insertCustomerEquipmentData(customerEquipmentData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerEquipmentDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerEquipmentDataUpdate(HttpServletRequest request, CustomerEquipmentData customerEquipmentData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerEquipmentData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerEquipmentData.setUpdateDate(new Date());
        if(!customerEquipmentDataService.updateCustomerEquipmentData(customerEquipmentData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerEquipmentDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerEquipmentDataRemove(@RequestParam("id[]") Integer id[]){
        if(!customerEquipmentDataService.deleteCustomerEquipmentData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerEquipmentDataCustomerCodeAndJobNumber.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerEquipmentDataCustomerCodeAndJobNumber(@RequestParam("Customer_Code") String Customer_Code,@RequestParam("BPM") String BPM,@RequestParam("ENG") String ENG){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        map.put("bPMList",userRoleService.queryUserRoleForRole(BPM));
        map.put("eNGList",userRoleService.queryUserRoleForRole(ENG));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryCustomerEquipmentDataFactoryAddress.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerEquipmentDataFactoryAddress(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map =new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        map.put("factoryAddressList",customerFactoryAddressService.queryCustomerFactoryAddressForFactoryAddressByCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerEquipmentDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerEquipmentDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerEquipmentDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerEquipmentDataModel.xlsx");
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

    @RequestMapping(value ="/customerEquipmentDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerEquipmentDataDownLoadExcelAll(HttpServletResponse response, CustomerEquipmentData customerEquipmentData){
        String [] title={"客户代码","客户中文简称","客户英文简称","国家/地区","城市","工厂地址",
                "设备名称","品牌","型号","出厂日期","出厂编号","数量","产品","稼动率","保养记录","维修记录","业务员",
                "服务工程师","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerEquipmentData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerEquipmentData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerEquipmentData> list=customerEquipmentDataService.queryCustomerEquipmentDataForDownLoadAll(customerEquipmentData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getFactoryAddress());
                row.createCell(6).setCellValue(list.get(j-1).getEquipmentName());
                row.createCell(7).setCellValue(list.get(j-1).getBrand());
                row.createCell(8).setCellValue(list.get(j-1).getModelNumber());
                row.createCell(9).setCellValue(list.get(j-1).getProductionDate());
                row.createCell(10).setCellValue(list.get(j-1).getSn());
                if(list.get(j-1).getQuantity()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getQuantity());
                }
                row.createCell(12).setCellValue(list.get(j-1).getProducts());
                if(list.get(j-1).getOperationRate()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getOperationRate());
                }
                row.createCell(14).setCellValue(list.get(j-1).getMaintenanceRecord());
                row.createCell(15).setCellValue(list.get(j-1).getRepairRecode());
                row.createCell(16).setCellValue(list.get(j-1).getBpm());
                row.createCell(17).setCellValue(list.get(j-1).getEngineer());
                row.createCell(18).setCellValue(list.get(j-1).getCreator());
                row.createCell(18).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(20).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(21).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/customerEquipmentDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerEquipmentDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","客户中文简称","客户英文简称","国家/地区","城市","工厂地址",
                "设备名称","品牌","型号","出厂日期","出厂编号","数量","产品","稼动率","保养记录","维修记录","业务员",
                "服务工程师","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerEquipmentData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerEquipmentData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerEquipmentData> list=customerEquipmentDataService.queryCustomerEquipmentDataForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getFactoryAddress());
                row.createCell(6).setCellValue(list.get(j-1).getEquipmentName());
                row.createCell(7).setCellValue(list.get(j-1).getBrand());
                row.createCell(8).setCellValue(list.get(j-1).getModelNumber());
                row.createCell(9).setCellValue(list.get(j-1).getProductionDate());
                row.createCell(10).setCellValue(list.get(j-1).getSn());
                if(list.get(j-1).getQuantity()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getQuantity());
                }
                row.createCell(12).setCellValue(list.get(j-1).getProducts());
                if(list.get(j-1).getOperationRate()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getOperationRate());
                }
                row.createCell(14).setCellValue(list.get(j-1).getMaintenanceRecord());
                row.createCell(15).setCellValue(list.get(j-1).getRepairRecode());
                row.createCell(16).setCellValue(list.get(j-1).getBpm());
                row.createCell(17).setCellValue(list.get(j-1).getEngineer());
                row.createCell(18).setCellValue(list.get(j-1).getCreator());
                row.createCell(18).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(20).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(21).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/customerEquipmentDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerEquipmentDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerEquipmentDataService.uploadCustomerEquipmentData(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerEquipmentDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerEquipmentDataFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerEquipmentData.do",method = RequestMethod.GET)
    public String customerEquipmentData(){
        return "customerEquipmentData";
    }
}
