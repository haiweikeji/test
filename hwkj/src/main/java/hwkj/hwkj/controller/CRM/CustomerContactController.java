package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerContact;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
import hwkj.hwkj.service.CRM.CustomerContactService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.UserRoleService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerContactController {
    @Autowired
    private CustomerContactService customerContactService;
    @Autowired
    private CustomerBaseDataService customerBaseDataService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryCustomerContact.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerContact(PageModel<CustomerContact> customerContactPageModel, CustomerContact customerContact){
        Map<String,Object> map=new HashMap<>();
        customerContactService.queryCustomerContactPage(customerContactPageModel,customerContact);
        map.put("rows",customerContactPageModel.getList());
        map.put("total",customerContactPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerContactAdd.do",method =RequestMethod.POST )
    @ResponseBody
    public String customerContactAdd(HttpServletRequest request, CustomerContact customerContact){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerContact.setCreator(((User)request.getSession().getAttribute("user")).getName());
        customerContact.setCreateDate(new Date());
        if(!customerContactService.insertCustomerContact(customerContact)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerContactUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerContactUpdate(HttpServletRequest request, CustomerContact customerContact){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        customerContact.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        customerContact.setUpdateDate(new Date());
        if(!customerContactService.updateCustomerContact(customerContact)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/customerContactRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerContactRemove(@RequestParam("id[]") Integer id[]){
        if(!customerContactService.deleteCustomerContact(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryCustomerContactAllCustomerCode.do",method = RequestMethod.POST)
    @ResponseBody
    public  JSONObject queryCustomerContactAllCustomerCode(@RequestParam("Customer_Code") String Customer_Code,@RequestParam("Role") String BPM){
        Map<String,Object> map=new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        map.put("bpmList",userRoleService.queryUserRoleForRole(BPM));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryCustomerContactForOption.do",method = RequestMethod.POST)
    @ResponseBody
    public  JSONObject queryCustomerContactForOption(@RequestParam("Customer_Code") String Customer_Code){
        Map<String,Object> map=new HashMap<>();
        map.put("customerCodeList",customerBaseDataService.queryCustomerBaseDataAllCustomerCode(Customer_Code));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/customerContactDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerContactDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="CustomerContactModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\CustomerContactModel.xlsx");
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

    @RequestMapping(value ="/customerContactDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerContactDownLoadExcelAll(HttpServletResponse response, CustomerContact customerContact){
        String [] title={"客户代码","中文简称","国家/地区","城市","联系人中文名字","联系人英文名字","性别",
                "职级","部门","权限","语言","公司电话","手机号","公司邮箱","私人邮箱","微信号","身份证号",
                "护照号","出生日期","开户银行","银行账号","祖籍","民族","宗教","饮食禁忌","兴趣爱好","习惯",
                "家庭状况","状态","业务员","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerContact"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerContact");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerContact> list=customerContactService.queryCustomerContactForDownLoadAll(customerContact);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getCountry());
                row.createCell(3).setCellValue(list.get(j-1).getCity());
                row.createCell(4).setCellValue(list.get(j-1).getContactChineseName());
                row.createCell(5).setCellValue(list.get(j-1).getContactEnglishName());
                row.createCell(6).setCellValue(list.get(j-1).getSex());
                row.createCell(7).setCellValue(list.get(j-1).getTitle());
                row.createCell(8).setCellValue(list.get(j-1).getDept());
                row.createCell(9).setCellValue(list.get(j-1).getAuthority());
                row.createCell(10).setCellValue(list.get(j-1).getLanguage());
                row.createCell(11).setCellValue(list.get(j-1).getCompanyTelephone());
                row.createCell(12).setCellValue(list.get(j-1).getPhoneNumber());
                row.createCell(13).setCellValue(list.get(j-1).getCompanyMail());
                row.createCell(14).setCellValue(list.get(j-1).getPrivateMail());
                row.createCell(15).setCellValue(list.get(j-1).getWechatNumber());
                row.createCell(16).setCellValue(list.get(j-1).getIdCard());
                row.createCell(17).setCellValue(list.get(j-1).getPassportNumber());
                row.createCell(18).setCellValue(list.get(j-1).getBirthDate());
                row.createCell(19).setCellValue(list.get(j-1).getBank());
                row.createCell(20).setCellValue(list.get(j-1).getBankAccount());
                row.createCell(21).setCellValue(list.get(j-1).getNativePlace());
                row.createCell(22).setCellValue(list.get(j-1).getNation());
                row.createCell(23).setCellValue(list.get(j-1).getReligiousBelief());
                row.createCell(24).setCellValue(list.get(j-1).getDietTaboo());
                row.createCell(25).setCellValue(list.get(j-1).getInterests());
                row.createCell(26).setCellValue(list.get(j-1).getHabit());
                row.createCell(27).setCellValue(list.get(j-1).getMemberFamily());
                row.createCell(28).setCellValue(list.get(j-1).getStatus());
                row.createCell(29).setCellValue(list.get(j-1).getBpm());
                row.createCell(30).setCellValue(list.get(j-1).getCreator());
                row.createCell(31).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(32).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(33).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/customerContactDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String customerContactDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","中文简称","国家/地区","城市","联系人中文名字","联系人英文名字","性别",
                "职级","部门","权限","语言","公司电话","手机号","公司邮箱","私人邮箱","微信号","身份证号",
                "护照号","出生日期","开户银行","银行账号","祖籍","民族","宗教","饮食禁忌","兴趣爱好","习惯",
                "家庭状况","状态","业务员","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="CustomerContact"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("CustomerContact");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerContact> list=customerContactService.queryCustomerContactForDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getCountry());
                row.createCell(3).setCellValue(list.get(j-1).getCity());
                row.createCell(4).setCellValue(list.get(j-1).getContactChineseName());
                row.createCell(5).setCellValue(list.get(j-1).getContactEnglishName());
                row.createCell(6).setCellValue(list.get(j-1).getSex());
                row.createCell(7).setCellValue(list.get(j-1).getTitle());
                row.createCell(8).setCellValue(list.get(j-1).getDept());
                row.createCell(9).setCellValue(list.get(j-1).getAuthority());
                row.createCell(10).setCellValue(list.get(j-1).getLanguage());
                row.createCell(11).setCellValue(list.get(j-1).getCompanyTelephone());
                row.createCell(12).setCellValue(list.get(j-1).getPhoneNumber());
                row.createCell(13).setCellValue(list.get(j-1).getCompanyMail());
                row.createCell(14).setCellValue(list.get(j-1).getPrivateMail());
                row.createCell(15).setCellValue(list.get(j-1).getWechatNumber());
                row.createCell(16).setCellValue(list.get(j-1).getIdCard());
                row.createCell(17).setCellValue(list.get(j-1).getPassportNumber());
                row.createCell(18).setCellValue(list.get(j-1).getBirthDate());
                row.createCell(19).setCellValue(list.get(j-1).getBank());
                row.createCell(20).setCellValue(list.get(j-1).getBankAccount());
                row.createCell(21).setCellValue(list.get(j-1).getNativePlace());
                row.createCell(22).setCellValue(list.get(j-1).getNation());
                row.createCell(23).setCellValue(list.get(j-1).getReligiousBelief());
                row.createCell(24).setCellValue(list.get(j-1).getDietTaboo());
                row.createCell(25).setCellValue(list.get(j-1).getInterests());
                row.createCell(26).setCellValue(list.get(j-1).getHabit());
                row.createCell(27).setCellValue(list.get(j-1).getMemberFamily());
                row.createCell(28).setCellValue(list.get(j-1).getStatus());
                row.createCell(29).setCellValue(list.get(j-1).getBpm());
                row.createCell(30).setCellValue(list.get(j-1).getCreator());
                row.createCell(31).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(32).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(33).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/customerContactUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String customerContactUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!customerContactService.uploadCustomerContact(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryCustomerContactFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCustomerContactFunction(HttpServletRequest request,@RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/customerContact.do",method = RequestMethod.GET)
    public String customerContact(){
        return "customerContact";
    }
}
