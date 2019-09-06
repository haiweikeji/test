package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.OrgData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.LegalDataService;
import hwkj.hwkj.service.HR.OrgDataService;
import hwkj.hwkj.service.User.RoleMenuService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class OrgDataController {

    @Autowired
    private OrgDataService orgDataService;
    @Autowired
    private LegalDataService legalDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value ="/queryOrgData.do" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryOrgData(PageModel<OrgData> orgDataPageModel, OrgData orgData){
        Map<String,Object> map =new HashMap<>();
        orgDataService.queryOrgDataPage(orgDataPageModel, orgData);
        map.put("rows",orgDataPageModel.getList());
        map.put("total",orgDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/orgDataUpload.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String orgDataUpload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile multipartFile=multipartHttpServletRequest.getFile("multipartFile");
        InputStream in=null;
        String flag ="1";
        //List<OrgData> orgDataList =new ArrayList<>();
        try {
             in= multipartFile.getInputStream();//获取控件内容的输入流
        } catch (IOException e) {
            e.printStackTrace();
            flag="0";
            return flag;
        }
        XSSFWorkbook xssfWorkbook=null;
        OrgData orgData=null;
        try {
            xssfWorkbook =new XSSFWorkbook(in);
            XSSFSheet sheet=xssfWorkbook.getSheetAt(0);
            for (Row row:sheet) {
                if(row.getRowNum()==0){
                    continue;
                }
                if("".equals(row.getCell(2))){
                    continue;
                }
                orgData=new OrgData();
                orgData.setGroupName(row.getCell(0).getStringCellValue());
                orgData.setGroupCode("HW0000000");
                orgData.setLegalName(row.getCell(1).getStringCellValue());
                if(legalDataService.selectLegalDataByLegalName(row.getCell(1).getStringCellValue()).getCompanyCode()==null){
                    flag="2";
                    return flag;
                }
                orgData.setCompanyCode(legalDataService.selectLegalDataByLegalName(row.getCell(1).getStringCellValue()).getCompanyCode());
                orgData.setBg(row.getCell(2).getStringCellValue());
                if(row.getCell(3)!=null){
                    orgData.setBu(row.getCell(3).getStringCellValue());
                }
                orgData.setRegion(row.getCell(4).getStringCellValue());
                orgData.setDept(row.getCell(5).getStringCellValue());
                orgData.setKe(row.getCell(6).getStringCellValue());
                orgData.setZu(row.getCell(7).getStringCellValue());
                orgData.setCostCode(orgDataService.calculateOrgCode(orgData).substring(0,7)+"00");
                orgData.setOrgCode(orgDataService.calculateOrgCode(orgData));
                orgData.setStatus("Y");
                orgData.setCreator(((User)request.getSession().getAttribute("user")).getName());
                orgData.setCreateDate(new Date());
                orgDataService.insertOrgData(orgData);
                //orgDataList.add(orgData);
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag="3";
            return flag;
        }
        finally {
            if (xssfWorkbook!=null){
                try {
                    xssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @RequestMapping(value ="/orgDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String orgDataAdd(HttpServletRequest request,OrgData orgData) {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        orgData.setGroupName("HW");
        orgData.setGroupCode("HW0000000");
        orgData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        orgData.setCreateDate(new Date());
        if(!orgDataService.insertOrgData(orgData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/orgDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String orgDataRemove(@RequestParam("id[]") Integer id[]){
        if(!orgDataService.deleteOrgData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/orgDataUpdate.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String orgDataUpdate(HttpServletRequest request,OrgData orgData) {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        orgData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        orgData.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//DATE-->string
        if(!orgDataService.updateOrgData(orgData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/orgDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String orgDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="OrgDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\OrgDataModel.xlsx");
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

    @RequestMapping(value ="/orgDataDownLoadExcel.do" ,method =RequestMethod.GET)
    @ResponseBody
    public String orgDataDownLoadExcel(HttpServletResponse response, @RequestParam("id")Integer [] id){
        String [] title={"集团名称","集团代码","法人名称","公司代码","事业群名称","事业处","区域","部门名称","课别","组别",
                "费用代码","组织代码","状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="OrgData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("OrgData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<OrgData> list=orgDataService.queryOrgDataForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getGroupName());
                row.createCell(1).setCellValue(list.get(j-1).getGroupCode());
                row.createCell(2).setCellValue(list.get(j-1).getLegalName());
                row.createCell(3).setCellValue(list.get(j-1).getCompanyCode());
                row.createCell(4).setCellValue(list.get(j-1).getBg());
                row.createCell(5).setCellValue(list.get(j-1).getBu());
                row.createCell(6).setCellValue(list.get(j-1).getRegion());
                row.createCell(7).setCellValue(list.get(j-1).getDept());
                row.createCell(8).setCellValue(list.get(j-1).getKe());
                row.createCell(9).setCellValue(list.get(j-1).getZu());
                row.createCell(10).setCellValue(list.get(j-1).getCostCode());
                row.createCell(11).setCellValue(list.get(j-1).getOrgCode());
                row.createCell(12).setCellValue(list.get(j-1).getStatus());
                row.createCell(13).setCellValue(list.get(j-1).getCreator());
                row.createCell(14).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(15).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(16).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/orgDataDownLoadExcelAll.do" ,method =RequestMethod.GET)
    @ResponseBody
    public String orgDataDownLoadExcelAll(HttpServletResponse response,OrgData orgData){
        String [] title={"集团名称","集团代码","法人名称","公司代码","事业群名称","事业处","区域","部门名称","课别","组别",
                "费用代码","组织代码","状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="OrgData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("OrgData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<OrgData> list=orgDataService.queryOrgDataForDownLoadAll(orgData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getGroupName());
                row.createCell(1).setCellValue(list.get(j-1).getGroupCode());
                row.createCell(2).setCellValue(list.get(j-1).getLegalName());
                row.createCell(3).setCellValue(list.get(j-1).getCompanyCode());
                row.createCell(4).setCellValue(list.get(j-1).getBg());
                row.createCell(5).setCellValue(list.get(j-1).getBu());
                row.createCell(6).setCellValue(list.get(j-1).getRegion());
                row.createCell(7).setCellValue(list.get(j-1).getDept());
                row.createCell(8).setCellValue(list.get(j-1).getKe());
                row.createCell(9).setCellValue(list.get(j-1).getZu());
                row.createCell(10).setCellValue(list.get(j-1).getCostCode());
                row.createCell(11).setCellValue(list.get(j-1).getOrgCode());
                row.createCell(12).setCellValue(list.get(j-1).getStatus());
                row.createCell(13).setCellValue(list.get(j-1).getCreator());
                row.createCell(14).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(15).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(16).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/queryOrgDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryOrgDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/calculateOrgCode.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> calculateOrgCode(OrgData orgData){
        String OrgCode=orgDataService.calculateOrgCode(orgData);
        Map<String,String> map=new HashMap();
        map.put("orgCode",OrgCode);
        return map;
    }

    @RequestMapping(value = "/orgData.do",method = RequestMethod.GET)
    public String orgData(){
        return "orgData";
    }
}
