package hwkj.hwkj.controller.SCM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.MaterialEngineeringDataService;
import hwkj.hwkj.service.Quote.QuoteTermService;
import hwkj.hwkj.service.SCM.MaterialSupplyDataService;
import hwkj.hwkj.service.SCM.VendorCodeService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.UserRoleService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
public class MaterialSupplyDataController {
    @Autowired
    private MaterialSupplyDataService materialSupplyDataService;
    @Autowired
    private MaterialEngineeringDataService materialEngineeringDataService;
    @Autowired
    private VendorCodeService vendorCodeService;
    @Autowired
    private QuoteTermService quoteTermService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryMaterialSupplyData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialSupplyData(PageModel<MaterialSupplyData> materialSupplyDataPageModel, MaterialSupplyData materialSupplyData) throws Exception{
        Map<String,Object> map=new HashMap<>();
        materialSupplyDataService.queryMaterialSupplyDataPage(materialSupplyDataPageModel,materialSupplyData);
        map.put("rows",materialSupplyDataPageModel.getList());
        map.put("total",materialSupplyDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/materialSupplyDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialSupplyDataAdd(HttpServletRequest request,MaterialSupplyData materialSupplyData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        materialSupplyData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        materialSupplyData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!materialSupplyDataService.insertMaterialSupplyData(materialSupplyData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/materialSupplyDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialSupplyDataUpdate(HttpServletRequest request,MaterialSupplyData materialSupplyData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        materialSupplyData.setUpdated_By(((User)request.getSession().getAttribute("user")).getName());
        materialSupplyData.setUpdate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!materialSupplyDataService.updateMaterialSupplyData(materialSupplyData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/materialSupplyDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialSupplyDataRemove(@RequestParam("id[]") Integer id[]){
        if(!materialSupplyDataService.deleteMaterialSupplyData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryMaterialSupplyDataForOption.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialSupplyDataForOption(@RequestParam("Role") String Role){
        Map<String ,Object> map=new HashMap<>();
        map.put("materialNumberList",materialEngineeringDataService.queryMaterialEngineeringDataOptionMaterialNumber());
        //map.put("brandList",vendorCodeService.queryVendorCodeForBrandOption(""));
        //map.put("vendorCodeList",vendorCodeService.queryVendorCodeForVendorCodeOption("代理商"));
        map.put("deliverTermList",quoteTermService.queryQuoteTermByDeliver_Term());
        map.put("paymentTermList",quoteTermService.queryQuoteTermByPayment_Term());
        map.put("receiveTermList",quoteTermService.queryQuoteTermByReceive_Term());
        map.put("roleList",userRoleService.queryUserRoleForRole(Role));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialSupplyDataOptionVersion.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialSupplyDataOptionVersion(@RequestParam("Material_Number") String Material_Number){
        Map<String ,Object> map=new HashMap<>();
        map.put("versionList",materialEngineeringDataService.queryMaterialEngineeringDataOptionVersion(Material_Number));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialSupplyDataOptionBrand.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialSupplyDataOptionBrand(@RequestParam("Material_Number") String Material_Number,@RequestParam("Version") String Version){
        Map<String ,Object> map=new HashMap<>();
        map.put("brandList",materialEngineeringDataService.queryMaterialEngineeringDataForOptionBrand(Material_Number,Version));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialSupplyDataOptionVendorCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialSupplyDataOptionVendorCode(@RequestParam("Brand") String Brand){
        Map<String ,Object> map=new HashMap<>();
        map.put("vendorCodeList",vendorCodeService.queryVendorCodeForOptionVendorCode(Brand));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/materialSupplyDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String materialSupplyDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="MaterialSupplyDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\MaterialSupplyDataModel.xlsx");
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

    @RequestMapping(value = "/materialSupplyDataDownLoadExcelAll.do",method = RequestMethod.GET)
    @ResponseBody
    public String materialSupplyDataDownLoadExcelAll(HttpServletResponse response, MaterialSupplyData materialSupplyData){
        String [] title={"HW料号","HW版本","品名","品牌",
                "型号","规格/大小","制造商简称","制造商料号","供应商中文简称","供应商代码","物料类型","类别",
                "原产地","数量单位","最小订单量","最小包装量","采购前置期(天)","交易条件","付款条件",
                "验收条件","供应商保固期(月)","生效时间","失效时间","采购工程师","备注","有效状态",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MaterialSupplyData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MaterialSupplyData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<MaterialSupplyData> list=materialSupplyDataService.queryMaterialSupplyDataForDownLoadAll(materialSupplyData);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setStatus("Y");
                }else {
                    list.get(i).setStatus("N");
                }
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getMaterial_Number());
                if(list.get(j-1).getVersion()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getVersion());
                }
                if(list.get(j-1).getProduct_Name()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getProduct_Name());
                }
                if(list.get(j-1).getBrand()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getBrand());
                }
                if(list.get(j-1).getModel_Number()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getModel_Number());
                }
                if(list.get(j-1).getSize()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getSize());
                }
                if(list.get(j-1).getManufacturer_Abbreviation()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getManufacturer_Abbreviation());
                }
                if(list.get(j-1).getManufacturer_Material_Number()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getManufacturer_Material_Number());
                }
                row.createCell(8).setCellValue(list.get(j-1).getVendor_Chinese_Abbreviation());
                if(list.get(j-1).getVendor_Code()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getVendor_Code());
                }
                if(list.get(j-1).getMaterial_Type()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getMaterial_Type());
                }
                if(list.get(j-1).getCategory()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getCategory());
                }
                if(list.get(j-1).getCountry_Origin()!=null){
                    row.createCell(12).setCellValue(list.get(j-1).getCountry_Origin());
                }
                if(list.get(j-1).getQuantity_Unit()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getQuantity_Unit());
                }
                if(list.get(j-1).getMinimum_Lower_Unit_Quantity()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getMinimum_Lower_Unit_Quantity());
                }
                if(list.get(j-1).getMinimum_Packing_Capacity()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getMinimum_Packing_Capacity());
                }
                if(list.get(j-1).getPurchasing_Lead_Days()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getPurchasing_Lead_Days());
                }
                if(list.get(j-1).getDeliver_Term()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getDeliver_Term());
                }
                if(list.get(j-1).getPayment_Term()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getPayment_Term());
                }
                if(list.get(j-1).getReceive_Term()!=null){
                    row.createCell(19).setCellValue(list.get(j-1).getReceive_Term());
                }
                if(list.get(j-1).getSupplier_Warranty_Period_Month()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getSupplier_Warranty_Period_Month());
                }
                if(list.get(j-1).getForce_Time()!=null){
                    row.createCell(21).setCellValue(list.get(j-1).getForce_Time());
                }
                if(list.get(j-1).getFailure_Time()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getFailure_Time());
                }
                if(list.get(j-1).getPurchase()!=null){
                    row.createCell(23).setCellValue(list.get(j-1).getPurchase());
                }
                if(list.get(j-1).getRemark()!=null){
                    row.createCell(24).setCellValue(list.get(j-1).getRemark());
                }
                if(list.get(j-1).getStatus()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getStatus());
                }
                //row.createCell(32).setCellValue(list.get(j-1).getStatus());
                row.createCell(26).setCellValue(list.get(j-1).getCreator());
                row.createCell(27).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(28).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(29).setCellValue(list.get(j-1).getUpdate_Date());
            }
            outputStream=response.getOutputStream();
            xssfWorkbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException("error");
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

    @RequestMapping(value = "/materialSupplyDataDownLoadExcel.do",method = RequestMethod.GET)
    @ResponseBody
    public String materialSupplyDataDownLoadExcel(HttpServletResponse response, @RequestParam("id") Integer id[]){
        String [] title={"HW料号","HW版本","品名","品牌",
                "型号","规格/大小","制造商简称","制造商料号","供应商中文简称","供应商代码","物料类型","类别",
                "原产地","数量单位","最小订单量","最小包装量","采购前置期(天)","交易条件","付款条件",
                "验收条件","供应商保固期(月)","生效时间","失效时间","采购工程师","备注","有效状态",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MaterialSupplyData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MaterialSupplyData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<MaterialSupplyData> list=materialSupplyDataService.queryMaterialSupplyDataForDownLoad(id);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setStatus("Y");
                }else {
                    list.get(i).setStatus("N");
                }
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getMaterial_Number());
                if(list.get(j-1).getVersion()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getVersion());
                }
                if(list.get(j-1).getProduct_Name()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getProduct_Name());
                }
                if(list.get(j-1).getBrand()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getBrand());
                }
                if(list.get(j-1).getModel_Number()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getModel_Number());
                }
                if(list.get(j-1).getSize()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getSize());
                }
                if(list.get(j-1).getManufacturer_Abbreviation()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getManufacturer_Abbreviation());
                }
                if(list.get(j-1).getManufacturer_Material_Number()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getManufacturer_Material_Number());
                }
                row.createCell(8).setCellValue(list.get(j-1).getVendor_Chinese_Abbreviation());
                if(list.get(j-1).getVendor_Code()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getVendor_Code());
                }
                if(list.get(j-1).getMaterial_Type()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getMaterial_Type());
                }
                if(list.get(j-1).getCategory()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getCategory());
                }
                if(list.get(j-1).getCountry_Origin()!=null){
                    row.createCell(12).setCellValue(list.get(j-1).getCountry_Origin());
                }
                if(list.get(j-1).getQuantity_Unit()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getQuantity_Unit());
                }
                if(list.get(j-1).getMinimum_Lower_Unit_Quantity()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getMinimum_Lower_Unit_Quantity());
                }
                if(list.get(j-1).getMinimum_Packing_Capacity()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getMinimum_Packing_Capacity());
                }
                if(list.get(j-1).getPurchasing_Lead_Days()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getPurchasing_Lead_Days());
                }
                if(list.get(j-1).getDeliver_Term()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getDeliver_Term());
                }
                if(list.get(j-1).getPayment_Term()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getPayment_Term());
                }
                if(list.get(j-1).getReceive_Term()!=null){
                    row.createCell(19).setCellValue(list.get(j-1).getReceive_Term());
                }
                if(list.get(j-1).getSupplier_Warranty_Period_Month()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getSupplier_Warranty_Period_Month());
                }
                if(list.get(j-1).getForce_Time()!=null){
                    row.createCell(21).setCellValue(list.get(j-1).getForce_Time());
                }
                if(list.get(j-1).getFailure_Time()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getFailure_Time());
                }
                if(list.get(j-1).getPurchase()!=null){
                    row.createCell(23).setCellValue(list.get(j-1).getPurchase());
                }
                if(list.get(j-1).getRemark()!=null){
                    row.createCell(24).setCellValue(list.get(j-1).getRemark());
                }
                if(list.get(j-1).getStatus()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getStatus());
                }
                //row.createCell(32).setCellValue(list.get(j-1).getStatus());
                row.createCell(26).setCellValue(list.get(j-1).getCreator());
                row.createCell(27).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(28).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(29).setCellValue(list.get(j-1).getUpdate_Date());
            }
            outputStream=response.getOutputStream();
            xssfWorkbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException("error");
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

    @RequestMapping(value = "/materialSupplyDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialSupplyDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!materialSupplyDataService.uploadMaterialSupplyData(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryMaterialSupplyDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialSupplyDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/materialSupplyData.do",method = RequestMethod.GET)
    public String materialSupplyData(){
        return "materialSupplyData";
    }
}
