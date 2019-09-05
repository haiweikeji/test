package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerBaseData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.Quote.QuoteTermService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.UserRoleService;
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
public class CustomerBaseDataController {
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private QuoteTermService quoteTermService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerBaseData.do",method = RequestMethod.POST)
    public @ResponseBody JSONObject queryCustomerBaseData(PageModel<CustomerBaseData> customerBaseDataPageModel, CustomerBaseData customerBaseData){
        Map<String,Object> map =new HashMap<>();
        customerBaseDataService.queryCustomerBaseDataPage(customerBaseDataPageModel,customerBaseData);
        map.put("rows",customerBaseDataPageModel.getList());
        map.put("total",customerBaseDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerBaseDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerBaseDataAdd(HttpServletRequest request,CustomerBaseData customerBaseData){
        if(((request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerBaseData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerBaseData.setCreateDate(new Date());
        if(!customerBaseDataService.insertCustomerBaseData(customerBaseData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerBaseDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerBaseDataUpdate(HttpServletRequest request,CustomerBaseData customerBaseData){
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            throw new GlobalException("timeOut");
        }
        customerBaseData.setUpdatedBy(user.getName());
        customerBaseData.setUpdateDate(new Date());
        if(!customerBaseDataService.updateCustomerBaseData(customerBaseData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerBaseDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerBaseDataRemove(@RequestParam("id[]") Integer id[]){
        if(!customerBaseDataService.deleteCustomerBaseData(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryQuoteTermThreeTermAndJobNumber.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryQuoteTermThreeTerm(@RequestParam("Role")String BPM){
        Map<String,Object> map =new HashMap<>();
        map.put("deliver_TermList",quoteTermService.queryQuoteTermByDeliver_Term());
        map.put("payment_TermList",quoteTermService.queryQuoteTermByPayment_Term());
        map.put("receive_TermList",quoteTermService.queryQuoteTermByReceive_Term());
        map.put("bPMList",userRoleService.queryUserRoleForRole(BPM));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerBaseDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerBaseDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerBaseDataModel"+time;
        XSSFWorkbook wb;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerBaseDataModel.xlsx");
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

    @RequestMapping(value ="/customerBaseDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerBaseDataDownLoadExcelAll(HttpServletResponse response,CustomerBaseData customerBaseData){
        String [] title={"客户代码","四位首字母","中文全称","中文简称","英文全称","英文简称","集团内客户","国家/地区","城市","行业",
                "公司负责人","注册资金(万)","币别","年营收(万)","行业等级","企业性质","员工人数","客户等级","交易条件","付款条件","验收条件",
                "信用等级","信用额度","办公地址","注册地址","状态","开发日期","业务员","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerBaseData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerBaseData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerBaseData> list=customerBaseDataService.queryCustomerBaseDataForDownLoadAll(customerBaseData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getFourInitials());
                row.createCell(2).setCellValue(list.get(j-1).getChineseFullName());
                row.createCell(3).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(4).setCellValue(list.get(j-1).getEnglishFullName());
                row.createCell(5).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(6).setCellValue(list.get(j-1).getFoxconnGroup());
                row.createCell(7).setCellValue(list.get(j-1).getCountry());
                row.createCell(8).setCellValue(list.get(j-1).getCity());
                row.createCell(9).setCellValue(list.get(j-1).getIndustry());
                row.createCell(10).setCellValue(list.get(j-1).getCompanyOwner());
                if(list.get(j-1).getRegisteredCapital()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getRegisteredCapital());
                }
                row.createCell(12).setCellValue(list.get(j-1).getCurrency());
                if(list.get(j-1).getAnnualRevenue()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getAnnualRevenue());
                }
                row.createCell(14).setCellValue(list.get(j-1).getIndustryRank());
                row.createCell(15).setCellValue(list.get(j-1).getEnterpriseNature());
                if(list.get(j-1).getEmployeeQty()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getEmployeeQty());
                }
                row.createCell(17).setCellValue(list.get(j-1).getCustomerLevel());
                row.createCell(18).setCellValue(list.get(j-1).getDeliverTerm());
                row.createCell(19).setCellValue(list.get(j-1).getPaymentTerm());
                row.createCell(20).setCellValue(list.get(j-1).getReceiveTerm());
                row.createCell(21).setCellValue(list.get(j-1).getCreditLevel());
                if(list.get(j-1).getCreditAmount()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getCreditAmount());
                }
                row.createCell(23).setCellValue(list.get(j-1).getOfficeAddress());
                row.createCell(24).setCellValue(list.get(j-1).getRegisteredAddress());
                row.createCell(25).setCellValue(list.get(j-1).getStatus());
                row.createCell(26).setCellValue(list.get(j-1).getDevelopmentDate());
                row.createCell(27).setCellValue(list.get(j-1).getBpm());
                row.createCell(28).setCellValue(list.get(j-1).getCreator());
                row.createCell(29).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(30).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(31).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/customerBaseDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerBaseDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","四位首字母","中文全称","中文简称","英文全称","英文简称","集团内客户","国家/地区","城市","行业",
                "公司负责人","注册资金(万)","币别","年营收(万)","行业等级","企业性质","员工人数","客户等级","交易条件","付款条件","验收条件",
                "信用等级","信用额度","办公地址","注册地址","状态","开发日期","业务员","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerBaseData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerBaseData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerBaseData> list=customerBaseDataService.queryCustomerBaseDataForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getFourInitials());
                row.createCell(2).setCellValue(list.get(j-1).getChineseFullName());
                row.createCell(3).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(4).setCellValue(list.get(j-1).getEnglishFullName());
                row.createCell(5).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(6).setCellValue(list.get(j-1).getFoxconnGroup());
                row.createCell(7).setCellValue(list.get(j-1).getCountry());
                row.createCell(8).setCellValue(list.get(j-1).getCity());
                row.createCell(9).setCellValue(list.get(j-1).getIndustry());
                row.createCell(10).setCellValue(list.get(j-1).getCompanyOwner());
                if(list.get(j-1).getRegisteredCapital()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getRegisteredCapital());
                }
                row.createCell(12).setCellValue(list.get(j-1).getCurrency());
                if(list.get(j-1).getAnnualRevenue()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getAnnualRevenue());
                }
                row.createCell(14).setCellValue(list.get(j-1).getIndustryRank());
                row.createCell(15).setCellValue(list.get(j-1).getEnterpriseNature());
                if(list.get(j-1).getEmployeeQty()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getEmployeeQty());
                }
                row.createCell(17).setCellValue(list.get(j-1).getCustomerLevel());
                row.createCell(18).setCellValue(list.get(j-1).getDeliverTerm());
                row.createCell(19).setCellValue(list.get(j-1).getPaymentTerm());
                row.createCell(20).setCellValue(list.get(j-1).getReceiveTerm());
                row.createCell(21).setCellValue(list.get(j-1).getCreditLevel());
                if(list.get(j-1).getCreditAmount()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getCreditAmount());
                }
                row.createCell(23).setCellValue(list.get(j-1).getOfficeAddress());
                row.createCell(24).setCellValue(list.get(j-1).getRegisteredAddress());
                row.createCell(25).setCellValue(list.get(j-1).getStatus());
                row.createCell(26).setCellValue(list.get(j-1).getDevelopmentDate());
                row.createCell(27).setCellValue(list.get(j-1).getBpm());
                row.createCell(28).setCellValue(list.get(j-1).getCreator());
                row.createCell(29).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(30).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(31).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/customerBaseDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerBaseDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerBaseDataService.uploadCustomerBaseData(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerBaseDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerBaseDataFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerBaseData.do",method = RequestMethod.GET)
    public String customerBaseData(){
        return "customerBaseData";
    }
}
