package hwkj.hwkj.controller.Quote;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.Quote.QuoteTerm;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Quote.QuoteTermService;
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
public class QuoteTermController {
    @Autowired
    private QuoteTermService quoteTermService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryQuoteTerm.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryQuoteTerm(PageModel<QuoteTerm> quoteTermPageModel, QuoteTerm quoteTerm){
        Map<String,Object> map =new HashMap<>();
        quoteTermService.queryQuoteTermPage(quoteTermPageModel,quoteTerm);
        map.put("rows",quoteTermPageModel.getList());
        map.put("total",quoteTermPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/quoteTermAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String quoteTermAdd(HttpServletRequest request,QuoteTerm quoteTerm){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        quoteTerm.setCreator(((User)request.getSession().getAttribute("user")).getName());
        quoteTerm.setCreateDate(new Date());
        if(!quoteTermService.insertQuoteTerm(quoteTerm)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/quoteTermUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String quoteTermUpdate(HttpServletRequest request,QuoteTerm quoteTerm){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(quoteTerm.getPercent()!=null && quoteTerm.getPercent().trim().length()!=0){
            quoteTerm.setPercent(quoteTerm.getPercent()+"%");
        }
        quoteTerm.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        quoteTerm.setUpdateDate(new Date());
        if(!quoteTermService.updateQuoteTerm(quoteTerm)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/quoteTermRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String quoteTermRemove(@RequestParam("id[]") Integer id[]){
        if(!quoteTermService.deleteQuoteTerm(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/quoteTermDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String quoteTermDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="QuoteTermModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\QuoteTermModel.xlsx");
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

    @RequestMapping(value ="/QuoteTermDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String QuoteTermDownLoadExcelAll(HttpServletResponse response,QuoteTerm quoteTerm){
        String [] title={"条件类别","条件代码","条款","描述","资金占用天数","百分比","Status",
                "备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="QuoteTerm"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("QuoteTerm");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<QuoteTerm> list=quoteTermService.queryQuoteTermForDownLoadAll(quoteTerm);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getConditionType());
                row.createCell(1).setCellValue(list.get(j-1).getConditionCode());
                row.createCell(2).setCellValue(list.get(j-1).getClause());
                row.createCell(3).setCellValue(list.get(j-1).getDescribed());
                if(list.get(j-1).getDays()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getDays());
                }
                row.createCell(5).setCellValue(list.get(j-1).getPercent());
                row.createCell(6).setCellValue(list.get(j-1).getStatus());
                row.createCell(7).setCellValue(list.get(j-1).getRemark());
                row.createCell(8).setCellValue(list.get(j-1).getCreator());
                row.createCell(9).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(10).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(11).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/QuoteTermDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String QuoteTermDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"条件类别","条件代码","条款","描述","资金占用天数","百分比","Status",
                "备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="QuoteTerm"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("QuoteTerm");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<QuoteTerm> list=quoteTermService.queryQuoteTermForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getConditionType());
                row.createCell(1).setCellValue(list.get(j-1).getConditionCode());
                row.createCell(2).setCellValue(list.get(j-1).getClause());
                row.createCell(3).setCellValue(list.get(j-1).getDescribed());
                if(list.get(j-1).getDays()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getDays());
                }
                row.createCell(5).setCellValue(list.get(j-1).getPercent());
                row.createCell(6).setCellValue(list.get(j-1).getStatus());
                row.createCell(7).setCellValue(list.get(j-1).getRemark());
                row.createCell(8).setCellValue(list.get(j-1).getCreator());
                row.createCell(9).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(10).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(11).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/quoteTermUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String quoteTermUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!quoteTermService.uploadQuoteTerm(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryQuoteTermFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryQuoteTermFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/quoteTerm.do",method = RequestMethod.GET)
    public String quoteTerm(){
        return "quoteTerm";
    }
}
