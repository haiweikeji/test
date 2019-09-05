package hwkj.hwkj.controller.Engineering;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.Engineering.CustomerMaterialData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.Engineering.CustomerMaterialDataService;
import hwkj.hwkj.service.Engineering.MaterialEngineeringDataService;
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
import java.util.*;

@Controller
public class CustomerMaterialDataController {
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private CustomerMaterialDataService customerMaterialDataService;
    @Autowired
    private MaterialEngineeringDataService materialEngineeringDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerMaterialData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerMaterialData(PageModel<CustomerMaterialData> customerMaterialDataPageModel,CustomerMaterialData customerMaterialData) throws GlobalException,Exception{
        Map<String,Object> map=new HashMap<>();
        customerMaterialDataService.queryCustomerMaterialDataPage(customerMaterialDataPageModel, customerMaterialData);
        map.put("rows",customerMaterialDataPageModel.getList());
        map.put("total",customerMaterialDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerMaterialDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerMaterialDataAdd(HttpServletRequest request,CustomerMaterialData customerMaterialData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        /*if(materialEngineeringDataService.queryMaterialEngineeringDataFourDataExist(customerMaterialData.getMaterial_Number(),customerMaterialData.getVersion(),customerMaterialData.getManufacturer_Abbreviation(),customerMaterialData.getManufacturer_Material_Number())==0){
            throw new GlobalException("unExist");//代表不存在
        }
        if(customerMaterialDataService.queryCustomerMaterialDataForExist(customerMaterialData)>0){
            throw new GlobalException("exist");//代表存在了
        }*/
        customerMaterialData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerMaterialData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!customerMaterialDataService.insertCustomerMaterialData(customerMaterialData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerMaterialDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerMaterialDataUpdate(HttpServletRequest request,CustomerMaterialData customerMaterialData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        /*if(materialEngineeringDataService.queryMaterialEngineeringDataFourDataExist(customerMaterialData.getMaterial_Number(),customerMaterialData.getVersion(),customerMaterialData.getManufacturer_Abbreviation(),customerMaterialData.getManufacturer_Material_Number())==0){
            throw new GlobalException("unExist");//代表不存在
        }
        if(customerMaterialDataService.queryCustomerMaterialDataForExist(customerMaterialData)>0){
            throw new GlobalException("exist");//代表存在了
        }*/
        customerMaterialData.setUpdated_By(((User)request.getSession().getAttribute("user")).getName());
        customerMaterialData.setUpdate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!customerMaterialDataService.updateCustomerMaterialData(customerMaterialData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerMaterialDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerMaterialDataRemove(@RequestParam("id[]") Integer id[]){
        if(!customerMaterialDataService.deleteCustomerMaterialData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerMaterialDataOptionChineseAbbreviationAndMaterialNumber.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerMaterialDataOptionChineseAbbreviationAndMaterialNumber(){
        Map<String,Object> map=new HashMap<>();
        map.put("chineseAbbreviationList",customerBaseDataService.queryCustomerBaseDataOptionChineseAbbreviation());
        map.put("materialNumberList",materialEngineeringDataService.queryMaterialEngineeringDataOptionMaterialNumber());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialEngineeringDataOptionVersion.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataOptionVersion(@RequestParam("Material_Number") String Material_Number){
        Map<String,Object> map=new HashMap<>();
        map.put("versionList",materialEngineeringDataService.queryMaterialEngineeringDataOptionVersion(Material_Number));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialEngineeringDataOptionManufacturerAbbreviation.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataOptionManufacturerAbbreviation(@RequestParam("Material_Number") String Material_Number,@RequestParam("Version") String Version){
        Map<String,Object> map=new HashMap<>();
        map.put("manufacturerAbbreviationList",materialEngineeringDataService.queryMaterialEngineeringDataOptionManufacturerAbbreviation(Material_Number, Version));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialEngineeringDataOptionManufacturerMaterialNumber.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataOptionManufacturerMaterialNumber(@RequestParam("Material_Number") String Material_Number,@RequestParam("Version") String Version,@RequestParam("Manufacturer_Abbreviation") String Manufacturer_Abbreviation){
        Map<String,Object> map=new HashMap<>();
        map.put("manufacturerMaterialNumberList",materialEngineeringDataService.queryMaterialEngineeringDataOptionManufacturerMaterialNumber(Material_Number, Version, Manufacturer_Abbreviation));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerMaterialDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerMaterialDataDownLoadExcelAll(HttpServletResponse response, CustomerMaterialData customerMaterialData){
        String [] title={"客户简称","客户料号","客户版本","HW料号","HW版本","描述","制造商简称","制造商料号",
                "生命周期状态","生效时间","失效时间","有效状态","备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerMaterialData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerMaterialData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerMaterialData> list=customerMaterialDataService.queryCustomerMaterialDataForDownLoadAll(customerMaterialData);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setStatus("Y");
                }else {
                    list.get(i).setStatus("N");
                }
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomer_Abbreviation());
                row.createCell(1).setCellValue(list.get(j-1).getCustomer_Material_Number());
                if(list.get(j-1).getCustomer_Version()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getCustomer_Version());
                }
                row.createCell(3).setCellValue(list.get(j-1).getMaterial_Number());
                if(list.get(j-1).getVersion()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getVersion());
                }
                row.createCell(5).setCellValue(list.get(j-1).getDescribed());
                row.createCell(6).setCellValue(list.get(j-1).getManufacturer_Abbreviation());
                row.createCell(7).setCellValue(list.get(j-1).getManufacturer_Material_Number());
                row.createCell(8).setCellValue(list.get(j-1).getLife_Cycle_State());
                row.createCell(9).setCellValue(list.get(j-1).getForce_Time());
                row.createCell(10).setCellValue(list.get(j-1).getFailure_Time());
                row.createCell(11).setCellValue(list.get(j-1).getStatus());
                row.createCell(12).setCellValue(list.get(j-1).getRemark());
                row.createCell(13).setCellValue(list.get(j-1).getCreator());
                row.createCell(14).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(15).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(16).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value ="/customerMaterialDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerMaterialDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户简称","客户料号","客户版本","HW料号","HW版本","描述","制造商简称","制造商料号",
                "生命周期状态","生效时间","失效时间","有效状态","备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerMaterialData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerMaterialData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerMaterialData> list=new ArrayList<>();
            for(int i=0,length=id.length;i<length;i++){
                CustomerMaterialData customerMaterialData=customerMaterialDataService.queryCustomerMaterialDataForDownLoad(id[i]);
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(customerMaterialData.getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    customerMaterialData.setStatus("Y");
                }else {
                    customerMaterialData.setStatus("N");
                }
                list.add(customerMaterialData);
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomer_Abbreviation());
                row.createCell(1).setCellValue(list.get(j-1).getCustomer_Material_Number());
                if(list.get(j-1).getCustomer_Version()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getCustomer_Version());
                }
                row.createCell(3).setCellValue(list.get(j-1).getMaterial_Number());
                if(list.get(j-1).getVersion()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getVersion());
                }
                row.createCell(5).setCellValue(list.get(j-1).getDescribed());
                row.createCell(6).setCellValue(list.get(j-1).getManufacturer_Abbreviation());
                row.createCell(7).setCellValue(list.get(j-1).getManufacturer_Material_Number());
                row.createCell(8).setCellValue(list.get(j-1).getLife_Cycle_State());
                row.createCell(9).setCellValue(list.get(j-1).getForce_Time());
                row.createCell(10).setCellValue(list.get(j-1).getFailure_Time());
                row.createCell(11).setCellValue(list.get(j-1).getStatus());
                row.createCell(12).setCellValue(list.get(j-1).getRemark());
                row.createCell(13).setCellValue(list.get(j-1).getCreator());
                row.createCell(14).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(15).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(16).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value ="/customerMaterialDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerMaterialDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerMaterialData"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerMaterialDataModel.xlsx");
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

    @RequestMapping(value = "/customerMaterialDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerMaterialDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerMaterialDataService.customerMaterialDataUpload(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerMaterialDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerMaterialDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerMaterialData.do",method = RequestMethod.GET)
    public String customerMaterialData(){
        return "customerMaterialData";
    }
}
