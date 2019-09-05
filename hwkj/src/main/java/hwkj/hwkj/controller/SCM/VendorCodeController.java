package hwkj.hwkj.controller.SCM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.VendorCodeService;
import hwkj.hwkj.service.SCM.VendorNameService;
import hwkj.hwkj.service.User.RoleMenuService;
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
import java.util.*;

@Controller
public class VendorCodeController {
    @Autowired
    private VendorCodeService vendorCodeService;
    @Autowired
    private VendorNameService vendorNameService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryVendorCode.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorCode(PageModel<VendorCode> vendorCodePageModel,VendorCode vendorCode){
        Map<String,Object> map=new HashMap<>();
        vendorCodeService.queryVendorCodePage(vendorCodePageModel, vendorCode);
        map.put("rows",vendorCodePageModel.getList());
        map.put("total",vendorCodePageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorCodeAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorCodeAdd(HttpServletRequest request,VendorCode vendorCode){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        vendorCode.setStatus("Y");
        vendorCode.setCreator(((User)request.getSession().getAttribute("user")).getName());
        vendorCode.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!vendorCodeService.insertVendorCode(vendorCode)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/vendorCodeUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorCodeUpdate(HttpServletRequest request,VendorCode vendorCode){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        vendorCode.setUpdated_By(((User)request.getSession().getAttribute("user")).getName());
        vendorCode.setUpdate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!vendorCodeService.updateVendorCode(vendorCode)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/vendorCodeRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorCodeRemove(@RequestParam("id[]") Integer id[]){
        if(!vendorCodeService.deleteVendorCode(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryVendorCodeForOption.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorCodeForOption(){
        Map<String,Object> map=new HashMap<>();
        map.put("job_NumberList",userService.queryJobNumber(""));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorCodeForDownLoadExcelAll.do",method = RequestMethod.GET)
    @ResponseBody
    public String vendorCodeForDownLoadExcelAll(HttpServletResponse response, VendorCode vendorCode){
        String [] title={"供应商标准中文全称","供应商中文简称","供应商英文全称","供应商英文简称",
                "供应商性质","供应商代码","国家/地区","城市","品牌","发票地址","行业","供应商品种类",
                "公司负责人","供应商等级","官网地址","开户银行","银行帐号","银行地址","接受的币别",
                "年营利(万)","行业等级","企业性质","员工人数","统一社会信用代码","纳税人识别号","工商注册号",
                "注册时间","注册资金(万)","信用等级","信用额度(万)","币别","采购工程师","有效状态",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="VendorCode"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("VendorCode");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<VendorCode> list=vendorCodeService.queryVendorCodeForDownLoadAll(vendorCode);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getVendor_Chinese_Full());
                if(list.get(j-1).getVendor_Chinese_Abbreviation()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getVendor_Chinese_Abbreviation());
                }
                if(list.get(j-1).getVendor_English_Full()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getVendor_English_Full());
                }
                if(list.get(j-1).getVendor_English_Abbreviation()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getVendor_English_Abbreviation());
                }
                if(list.get(j-1).getVendor_Nature()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getVendor_Nature());
                }
                row.createCell(5).setCellValue(list.get(j-1).getVendor_Code());
                row.createCell(6).setCellValue(list.get(j-1).getCountry_Area());
                if(list.get(j-1).getCity()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getCity());
                }
                row.createCell(8).setCellValue(list.get(j-1).getBrand());
                if(list.get(j-1).getInvoice_Address()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getInvoice_Address());
                }
                if(list.get(j-1).getIndustry()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getIndustry());
                }
                if(list.get(j-1).getSupplier_Category()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getSupplier_Category());
                }
                if(list.get(j-1).getCompany_Owner()!=null){
                    row.createCell(12).setCellValue(list.get(j-1).getCompany_Owner());
                }
                if(list.get(j-1).getVendor_Level()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getVendor_Level());
                }
                if(list.get(j-1).getWebsite_Address()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getWebsite_Address());
                }
                if(list.get(j-1).getBank()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getBank());
                }
                if(list.get(j-1).getBank_Account()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getBank_Account());
                }
                if(list.get(j-1).getBank_Address()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getBank_Address());
                }
                if(list.get(j-1).getAcceptable_Currency()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getAcceptable_Currency());
                }
                if(list.get(j-1).getAnnual_Revenue()!=null){
                    row.createCell(19).setCellValue(list.get(j-1).getAnnual_Revenue());
                }
                if(list.get(j-1).getIndustry_Rank()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getIndustry_Rank());
                }
                if(list.get(j-1).getEnterprise_Nature()!=null){
                    row.createCell(21).setCellValue(list.get(j-1).getEnterprise_Nature());
                }
                if(list.get(j-1).getEmployee_Qty()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getEmployee_Qty());
                }
                if(list.get(j-1).getCredit_Code()!=null){
                    row.createCell(23).setCellValue(list.get(j-1).getCredit_Code());
                }
                if(list.get(j-1).getTaxpayer_Number()!=null){
                    row.createCell(24).setCellValue(list.get(j-1).getTaxpayer_Number());
                }
                if(list.get(j-1).getRegistration_Number()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getRegistration_Number());
                }
                if(list.get(j-1).getRegistration_Time()!=null){
                    row.createCell(26).setCellValue(list.get(j-1).getRegistration_Time());
                }
                if(list.get(j-1).getRegistered_Capital()!=null){
                    row.createCell(27).setCellValue(list.get(j-1).getRegistered_Capital());
                }
                if(list.get(j-1).getCredit_Level()!=null){
                    row.createCell(28).setCellValue(list.get(j-1).getCredit_Level());
                }
                if(list.get(j-1).getCredit_Amount()!=null){
                    row.createCell(29).setCellValue(list.get(j-1).getCredit_Amount());
                }
                if(list.get(j-1).getCurrency()!=null){
                    row.createCell(30).setCellValue(list.get(j-1).getCurrency());
                }
                if(list.get(j-1).getPurchase()!=null){
                    row.createCell(31).setCellValue(list.get(j-1).getPurchase());
                }
                row.createCell(32).setCellValue(list.get(j-1).getStatus());
                row.createCell(33).setCellValue(list.get(j-1).getCreator());
                row.createCell(34).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(35).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(36).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value = "/vendorCodeForDownLoadExcel.do",method = RequestMethod.GET)
    @ResponseBody
    public String vendorCodeForDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"供应商标准中文全称","供应商中文简称","供应商英文全称","供应商英文简称",
                "供应商性质","供应商代码","国家/地区","城市","品牌","发票地址","行业","供应商品种类",
                "公司负责人","供应商等级","官网地址","开户银行","银行帐号","银行地址","接受的币别",
                "年营利(万)","行业等级","企业性质","员工人数","统一社会信用代码","纳税人识别号","工商注册号",
                "注册时间","注册资金(万)","信用等级","信用额度(万)","币别","采购工程师","有效状态",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="VendorCode"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("VendorCode");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<VendorCode> list=new ArrayList<>();
            for(int i=0,length=id.length;i<length;i++){
                VendorCode vendorCode=vendorCodeService.queryVendorCodeForDownLoad(id[i]);
                list.add(vendorCode);
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getVendor_Chinese_Full());
                if(list.get(j-1).getVendor_Chinese_Abbreviation()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getVendor_Chinese_Abbreviation());
                }
                if(list.get(j-1).getVendor_English_Full()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getVendor_English_Full());
                }
                if(list.get(j-1).getVendor_English_Abbreviation()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getVendor_English_Abbreviation());
                }
                if(list.get(j-1).getVendor_Nature()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getVendor_Nature());
                }
                row.createCell(5).setCellValue(list.get(j-1).getVendor_Code());
                row.createCell(6).setCellValue(list.get(j-1).getCountry_Area());
                if(list.get(j-1).getCity()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getCity());
                }
                row.createCell(8).setCellValue(list.get(j-1).getBrand());
                if(list.get(j-1).getInvoice_Address()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getInvoice_Address());
                }
                if(list.get(j-1).getIndustry()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getIndustry());
                }
                if(list.get(j-1).getSupplier_Category()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getSupplier_Category());
                }
                if(list.get(j-1).getCompany_Owner()!=null){
                    row.createCell(12).setCellValue(list.get(j-1).getCompany_Owner());
                }
                if(list.get(j-1).getVendor_Level()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getVendor_Level());
                }
                if(list.get(j-1).getWebsite_Address()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getWebsite_Address());
                }
                if(list.get(j-1).getBank()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getBank());
                }
                if(list.get(j-1).getBank_Account()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getBank_Account());
                }
                if(list.get(j-1).getBank_Address()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getBank_Address());
                }
                if(list.get(j-1).getAcceptable_Currency()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getAcceptable_Currency());
                }
                if(list.get(j-1).getAnnual_Revenue()!=null){
                    row.createCell(19).setCellValue(list.get(j-1).getAnnual_Revenue());
                }
                if(list.get(j-1).getIndustry_Rank()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getIndustry_Rank());
                }
                if(list.get(j-1).getEnterprise_Nature()!=null){
                    row.createCell(21).setCellValue(list.get(j-1).getEnterprise_Nature());
                }
                if(list.get(j-1).getEmployee_Qty()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getEmployee_Qty());
                }
                if(list.get(j-1).getCredit_Code()!=null){
                    row.createCell(23).setCellValue(list.get(j-1).getCredit_Code());
                }
                if(list.get(j-1).getTaxpayer_Number()!=null){
                    row.createCell(24).setCellValue(list.get(j-1).getTaxpayer_Number());
                }
                if(list.get(j-1).getRegistration_Number()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getRegistration_Number());
                }
                if(list.get(j-1).getRegistration_Time()!=null){
                    row.createCell(26).setCellValue(list.get(j-1).getRegistration_Time());
                }
                if(list.get(j-1).getRegistered_Capital()!=null){
                    row.createCell(27).setCellValue(list.get(j-1).getRegistered_Capital());
                }
                if(list.get(j-1).getCredit_Level()!=null){
                    row.createCell(28).setCellValue(list.get(j-1).getCredit_Level());
                }
                if(list.get(j-1).getCredit_Amount()!=null){
                    row.createCell(29).setCellValue(list.get(j-1).getCredit_Amount());
                }
                if(list.get(j-1).getCurrency()!=null){
                    row.createCell(30).setCellValue(list.get(j-1).getCurrency());
                }
                if(list.get(j-1).getPurchase()!=null){
                    row.createCell(31).setCellValue(list.get(j-1).getPurchase());
                }
                row.createCell(32).setCellValue(list.get(j-1).getStatus());
                row.createCell(33).setCellValue(list.get(j-1).getCreator());
                row.createCell(34).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(35).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(36).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value ="/vendorCodeDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String vendorCodeDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="VendorCodeModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\VendorCodeModel.xlsx");
            wb = new XSSFWorkbook(is);
            out = response.getOutputStream();//响应输出流也就是传给客户端
            wb.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("error");
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

    @RequestMapping(value = "/vendorCodeUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorCodeUpload(@RequestParam("multipartFile")MultipartFile multipartFile,HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(!vendorCodeService.uploadVendorCode(multipartFile, request)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryVendorCodeFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorCodeFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorCode.do",method = RequestMethod.GET)
    public String vendorCode(){
        return "vendorCode";
    }
}
