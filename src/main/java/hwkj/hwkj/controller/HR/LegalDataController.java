package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.LegalData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.LegalDataService;
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
public class LegalDataController {
    @Autowired
    private LegalDataService legalDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryLegalData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryLegalData(PageModel<LegalData> legalDataPageModel, LegalData legalData){
        Map<String,Object> map =new HashMap<>();
        legalDataService.queryLegalDataPage(legalDataPageModel,legalData);
        map.put("rows",legalDataPageModel.getList());
        map.put("total",legalDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/legalDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String legalDataAdd(HttpServletRequest request,LegalData legalData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        legalData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        legalData.setCreateDate(new Date());
        if(!legalDataService.insertLegalData(legalData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/legalDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String legalDataUpdate(HttpServletRequest request,LegalData legalData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        legalData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        legalData.setUpdateDate(new Date());
        if(!legalDataService.updateLegalData(legalData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/legalDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String legalDataRemove(@RequestParam("id[]") Integer id[]){
        if(!legalDataService.deleteLegalData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/optionQuery.do" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject optionQuery(HttpServletRequest request,LegalData legalData){
        Map<String,Object> map =new HashMap<>();
        String Legal_Name2=request.getParameter("legal_Name2");
        legalData.setLegalName(Legal_Name2);
        List<LegalData> list =legalDataService.queryLegalDataByLegalName(legalData);
        map.put("legaldata",list);
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/legalDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String legalDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="LegalDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\LegalDataModel.xlsx");
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

    @RequestMapping(value ="/legalDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String legalDataDownLoadExcelAll(HttpServletResponse response, LegalData legalData){
        String [] title={"法人名称","前两位首字母","公司代码","国家","城市","行业","法人代表",
                "纳税人识别号","工商注册号","注册时间","注册资金(万元)","注册地址","开户银行","银行账号",
                "银行地址","可接受币别","办公地址", "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="LegalData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("LegalData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<LegalData> list=legalDataService.queryLegalDataForDownLoadAll(legalData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getLegalName());
                row.createCell(1).setCellValue(list.get(j-1).getTwoInitials());
                row.createCell(2).setCellValue(list.get(j-1).getCompanyCode());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getIndustry());
                row.createCell(6).setCellValue(list.get(j-1).getLegal());
                row.createCell(7).setCellValue(list.get(j-1).getTaxpayerNumber());
                row.createCell(8).setCellValue(list.get(j-1).getRegistrationNumber());
                row.createCell(9).setCellValue(list.get(j-1).getRegisteredTime());
                if(list.get(j-1).getRegisteredCapital()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getRegisteredCapital());
                }
                row.createCell(11).setCellValue(list.get(j-1).getRegisteredAddress());
                row.createCell(12).setCellValue(list.get(j-1).getBank());
                row.createCell(13).setCellValue(list.get(j-1).getBankAccount());
                row.createCell(14).setCellValue(list.get(j-1).getBankAddress());
                row.createCell(15).setCellValue(list.get(j-1).getCurrency());
                row.createCell(16).setCellValue(list.get(j-1).getOfficeAddress());
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

    @RequestMapping(value ="/legalDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String legalDataDownLoadExcel(HttpServletResponse response, @RequestParam("id") Integer id[]){
        String [] title={"法人名称","前两位首字母","公司代码","国家","城市","行业","法人代表",
                "纳税人识别号","工商注册号","注册时间","注册资金(万元)","注册地址","开户银行","银行账号",
                "银行地址","可接受币别","办公地址", "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="LegalData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("LegalData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<LegalData> list=legalDataService.queryLegalDataForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getLegalName());
                row.createCell(1).setCellValue(list.get(j-1).getTwoInitials());
                row.createCell(2).setCellValue(list.get(j-1).getCompanyCode());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getIndustry());
                row.createCell(6).setCellValue(list.get(j-1).getLegal());
                row.createCell(7).setCellValue(list.get(j-1).getTaxpayerNumber());
                row.createCell(8).setCellValue(list.get(j-1).getRegistrationNumber());
                row.createCell(9).setCellValue(list.get(j-1).getRegisteredTime());
                if(list.get(j-1).getRegisteredCapital()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getRegisteredCapital());
                }
                row.createCell(11).setCellValue(list.get(j-1).getRegisteredAddress());
                row.createCell(12).setCellValue(list.get(j-1).getBank());
                row.createCell(13).setCellValue(list.get(j-1).getBankAccount());
                row.createCell(14).setCellValue(list.get(j-1).getBankAddress());
                row.createCell(15).setCellValue(list.get(j-1).getCurrency());
                row.createCell(16).setCellValue(list.get(j-1).getOfficeAddress());
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

    @RequestMapping(value = "/legalDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String legalDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!legalDataService.uploadLegalData(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryLegalDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryLegalDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/legalData.do",method = RequestMethod.GET)
    public String legalData(){
        return "legalData";
    }
}
