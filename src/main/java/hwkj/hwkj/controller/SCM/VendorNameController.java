package hwkj.hwkj.controller.SCM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.SCM.VendorName;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.VendorCodeService;
import hwkj.hwkj.service.SCM.VendorNameService;
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
import java.util.*;

@Controller
public class VendorNameController {
    @Autowired
    private VendorNameService vendorNameService;
    @Autowired
    private VendorCodeService vendorCodeService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryVendorName.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorName(PageModel<VendorName> vendorNamePageModel, VendorName vendorName){
        Map<String,Object> map=new HashMap<>();
        vendorNameService.queryVendorNamePage(vendorNamePageModel, vendorName);
        map.put("rows",vendorNamePageModel.getList());
        map.put("total",vendorNamePageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorNameAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorNameAdd(HttpServletRequest request,VendorName vendorName){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        vendorName.setStatus("Y");
        vendorName.setCreator(((User)request.getSession().getAttribute("user")).getName());
        vendorName.setCreateDate(new Date());
        if(!vendorNameService.insertVendorName(vendorName)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/vendorNameUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorNameUpdate(HttpServletRequest request,VendorName vendorName,@RequestParam("Old_Actual_Chinese_Full[]") String Old_Actual_Chinese_Full[]){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        vendorName.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        vendorName.setUpdateDate(new Date());
        if(!vendorNameService.updateVendorName(vendorName,Old_Actual_Chinese_Full[0])){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryVendorCodeForVendorChineseFullOption.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorCodeForVendorChineseFullOption(@RequestParam("Vendor_Chinese_Full") String Vendor_Chinese_Full){
        Map<String,Object> map=new HashMap<>();
        map.put("vendorChineseFullList",vendorCodeService.queryVendorCodeForVendorChineseFullOption(Vendor_Chinese_Full));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorNameRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorNameRemove(@RequestParam("id[]") Integer id[]){
        for(int i=0,length=id.length;i<length;i++){
            if(!vendorNameService.deleteVendorName(id[i])){
                throw new GlobalException("error");
            }
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/vendorNameForDownLoadExcelAll.do",method = RequestMethod.GET)
    @ResponseBody
    public String vendorNameForDownLoadExcelAll(HttpServletResponse response, VendorName vendorName){
        String [] title={"标准名称中文全称","标准中文简称","标准名称英文全称","标准英文简称英文",
                "全称实际中文写法","全称实际英文写法","有效状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="VendorName"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("VendorName");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<VendorName> list=vendorNameService.queryVendorNameForDownLoadAll(vendorName);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                if(list.get(j-1).getStandardChineseFull()!=null){
                    row.createCell(0).setCellValue(list.get(j-1).getStandardChineseFull());
                }
                if(list.get(j-1).getStandardChineseAbbreviation()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getStandardChineseAbbreviation());
                }
                if(list.get(j-1).getStandardEnglishFull()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getStandardEnglishFull());
                }
                if(list.get(j-1).getStandardEnglishAbbreviation()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getStandardEnglishAbbreviation());
                }
                if(list.get(j-1).getActualChineseFull()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getActualChineseFull());
                }
                if(list.get(j-1).getActualEnglishFull()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getActualEnglishFull());
                }
                row.createCell(6).setCellValue(list.get(j-1).getStatus());
                row.createCell(7).setCellValue(list.get(j-1).getCreator());
                row.createCell(8).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(9).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(10).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/vendorNameForDownLoadExcel.do",method = RequestMethod.GET)
    @ResponseBody
    public String vendorNameForDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"标准名称中文全称","标准中文简称","标准名称英文全称","标准英文简称英文",
                "全称实际中文写法","全称实际英文写法","有效状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="VendorName"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("VendorName");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<VendorName> list=new ArrayList<>();
            for(int i=0,length=id.length;i<length;i++){
                VendorName vendorName=vendorNameService.queryVendorNameForDownLoad(id[i]);
                list.add(vendorName);
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                if(list.get(j-1).getStandardChineseFull()!=null){
                    row.createCell(0).setCellValue(list.get(j-1).getStandardChineseFull());
                }
                if(list.get(j-1).getStandardChineseAbbreviation()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getStandardChineseAbbreviation());
                }
                if(list.get(j-1).getStandardEnglishFull()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getStandardEnglishFull());
                }
                if(list.get(j-1).getStandardEnglishAbbreviation()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getStandardEnglishAbbreviation());
                }
                if(list.get(j-1).getActualChineseFull()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getActualChineseFull());
                }
                if(list.get(j-1).getActualEnglishFull()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getActualEnglishFull());
                }
                row.createCell(6).setCellValue(list.get(j-1).getStatus());
                row.createCell(7).setCellValue(list.get(j-1).getCreator());
                row.createCell(8).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(9).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(10).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/vendorNameDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String vendorNameDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="VendorNameModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\VendorNameModel.xlsx");
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

    @RequestMapping(value = "/vendorNameUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorNameUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(!vendorNameService.vendorNameUpload(multipartFile,request)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryVendorNameFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorNameFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorName.do",method = RequestMethod.GET)
    public String vendorName(){
        return "vendorName";
    }
}
