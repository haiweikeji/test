package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.TitleDataService;
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
public class TitleDataController {
    @Autowired
    private TitleDataService titleDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryTitleData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTitleData(PageModel<TitleData> titleDataPageModel,TitleData titleData){
        Map<String,Object> map =new HashMap<>();
        titleDataService.queryTitleDataPage(titleDataPageModel,titleData);
        map.put("rows",titleDataPageModel.getList());
        map.put("total",titleDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/titleDataAdd.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String titleDataAdd(HttpServletRequest request,TitleData titleData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        titleData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        titleData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!titleDataService.insertTitleData(titleData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/titleDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String titleDataUpdate(HttpServletRequest request,TitleData titleData){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        titleData.setUpdated_By(((User)request.getSession().getAttribute("user")).getName());
        titleData.setUpdate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!titleDataService.updateTitleData(titleData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/titleDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String titleDataRemove(@RequestParam("id[]") Integer id[]){
        if(!titleDataService.deleteTitleData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/titleDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String titleDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="TitleDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\TitleDataModel.xlsx");
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

    @RequestMapping(value ="/titleDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String titleDataDownLoadExcelAll(HttpServletResponse response, TitleData titleData){
        String [] title={"管理职","管理职代码","上一级","状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerContact"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("TitleData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<TitleData> list=titleDataService.queryTitleDataForDownLoadAll(titleData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getManagement());
                row.createCell(1).setCellValue(list.get(j-1).getManagement_Code());
                row.createCell(2).setCellValue(list.get(j-1).getUpper());
                row.createCell(3).setCellValue(list.get(j-1).getStatus());
                row.createCell(4).setCellValue(list.get(j-1).getCreator());
                row.createCell(5).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(6).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(7).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value ="/titleDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String titleDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"管理职","管理职代码","上一级","状态","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerContact"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("TitleData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<TitleData> list=titleDataService.queryTitleDataForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getManagement());
                row.createCell(1).setCellValue(list.get(j-1).getManagement_Code());
                row.createCell(2).setCellValue(list.get(j-1).getUpper());
                row.createCell(3).setCellValue(list.get(j-1).getStatus());
                row.createCell(4).setCellValue(list.get(j-1).getCreator());
                row.createCell(5).setCellValue(list.get(j-1).getCreate_Date());
                row.createCell(6).setCellValue(list.get(j-1).getUpdated_By());
                row.createCell(7).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value = "/titleDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String titleDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!titleDataService.uploadTitleData(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryTitleDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryTitleDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/titleData.do",method = RequestMethod.GET)
    public String titleData(){
        return "titleData";
    }
}
