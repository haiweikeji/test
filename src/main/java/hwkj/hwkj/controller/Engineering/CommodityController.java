package hwkj.hwkj.controller.Engineering;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.Engineering.Commodity;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.CommodityService;
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
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCommodity.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCommodity(PageModel<Commodity> commodityPageModel, Commodity commodity){
        Map<String,Object> map=new HashMap<>();
        commodityService.queryCommodityPage(commodityPageModel,commodity);
        map.put("rows",commodityPageModel.getList());
        map.put("total",commodityPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/commodityAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String commodityAdd(HttpServletRequest request, Commodity commodity) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        commodity.setCreator(((User)request.getSession().getAttribute("user")).getName());
        commodity.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!commodityService.insertCommodity(commodity)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/commodityUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String commodityUpdate(HttpServletRequest request, Commodity commodity) throws GlobalException {
        if(((User)request.getSession().getAttribute("user"))==null){
            throw new GlobalException("timeOut");
        }
        commodity.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        commodity.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!commodityService.updateCommodity(commodity)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/commodityRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String commodityRemove(@RequestParam("id[]") Integer id[]) throws GlobalException {
        if(!commodityService.deleteCommodity(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/commodityDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String commodityDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CommodityModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CommodityModel.xlsx");
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

    @RequestMapping(value ="/commodityDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String commodityDownLoadExcelAll(HttpServletResponse response, Commodity commodity){
        String [] title={"品名","类别","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="Commodity"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("Commodity");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<Commodity> list=commodityService.queryCommodityForDownLoadAll(commodity);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getProductName());
                row.createCell(1).setCellValue(list.get(j-1).getCategory());
                row.createCell(2).setCellValue(list.get(j-1).getCreator());
                row.createCell(3).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(4).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(5).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value ="/commodityDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String commodityDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"品名","类别","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="Commodity"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("Commodity");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<Commodity> list=commodityService.queryCommodityForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getProductName());
                row.createCell(1).setCellValue(list.get(j-1).getCategory());
                row.createCell(2).setCellValue(list.get(j-1).getCreator());
                row.createCell(3).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(4).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(5).setCellValue(list.get(j-1).getUpdate_Date());
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

    @RequestMapping(value = "/commodityUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String commodityUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!commodityService.uploadCommodity(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCommodityFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCommodityFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/commodity.do",method = RequestMethod.GET)
    public String commodity(){
        return "commodity";
    }
}
