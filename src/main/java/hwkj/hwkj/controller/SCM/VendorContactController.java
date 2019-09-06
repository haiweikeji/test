package hwkj.hwkj.controller.SCM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.SCM.VendorContact;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.VendorCodeService;
import hwkj.hwkj.service.SCM.VendorContactService;
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
import java.util.*;

@Controller
public class VendorContactController {
    @Autowired
    private VendorContactService vendorContactService;
    @Autowired
    private VendorCodeService vendorCodeService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryVendorContact.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorContact(PageModel<VendorContact> vendorContactPageModel, VendorContact vendorContact){
        Map<String,Object> map=new HashMap<>();
        vendorContactService.queryVendorContactPage(vendorContactPageModel, vendorContact);
        map.put("rows",vendorContactPageModel.getList());
        map.put("total",vendorContactPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorContactAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorContactAdd(HttpServletRequest request,VendorContact vendorContact){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        vendorContact.setStatus("Y");
        vendorContact.setCreator(((User)request.getSession().getAttribute("user")).getName());
        vendorContact.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!vendorContactService.insertVendorContact(vendorContact)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/vendorContactUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorContactUpdate(HttpServletRequest request,VendorContact vendorContact,@RequestParam("Old_Vendor_Code[]") String Old_Vendor_Code[]){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        vendorContact.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        vendorContact.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(!vendorContactService.updateVendorContact(vendorContact,Old_Vendor_Code[0])){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "vendorContactRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorContactRemove(@RequestParam("id[]") Integer id[]){
        if(!vendorContactService.deleteVendorContact(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryVendorContactForOption.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorContactForOption(@RequestParam("Vendor_Code") String Vendor_Code,@RequestParam("BPM") String BPM){
        Map<String,Object> map=new HashMap<>();
        map.put("vendor_CodeList",vendorCodeService.queryVendorCodeForOption(Vendor_Code));
        map.put("bPMList",userRoleService.queryUserRoleForRole(BPM));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorContactForDownLoadExcelAll.do",method = RequestMethod.GET)
    @ResponseBody
    public String vendorContactForDownLoadExcelAll(HttpServletResponse response, VendorContact vendorContact){
        String [] title={"供应商代码","供应商中文简称","国家/地区","城市",
                "联系人中文名字","联系人英文名字","性别","职级","部门","权限","语言",
                "公司电话","手机","公司邮箱","私人邮箱","微信","有效状态","采购工程师",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="VendorContact"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("VendorContact");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<VendorContact> list=vendorContactService.queryVendorContactForDownLoadAll(vendorContact);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getVendorCode());
                if(list.get(j-1).getVendorChineseAbbreviation()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getVendorChineseAbbreviation());
                }
                if(list.get(j-1).getCountryArea()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getCountryArea());
                }
                if(list.get(j-1).getCity()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getCity());
                }
                if(list.get(j-1).getContactChineseName()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getContactChineseName());
                }
                if(list.get(j-1).getContactEnglishName()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getContactEnglishName());
                }
                if(list.get(j-1).getSex()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getSex());
                }
                if(list.get(j-1).getTitle()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getTitle());
                }
                if(list.get(j-1).getDept()!=null){
                    row.createCell(8).setCellValue(list.get(j-1).getDept());
                }
                if(list.get(j-1).getAuthority()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getAuthority());
                }
                if(list.get(j-1).getLanguage()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getLanguage());
                }
                if(list.get(j-1).getCompanyTelephone()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getCompanyTelephone());
                }
                if(list.get(j-1).getPhoneNumber()!=null){
                    row.createCell(12).setCellValue(list.get(j-1).getPhoneNumber());
                }
                if(list.get(j-1).getCompanyMail()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getCompanyMail());
                }
                if(list.get(j-1).getPrivateMail()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getPrivateMail());
                }
                if(list.get(j-1).getWechatNumber()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getWechatNumber());
                }
                if(list.get(j-1).getStatus()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getStatus());
                }
                if(list.get(j-1).getPurchase()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getPurchase());
                }
                row.createCell(18).setCellValue(list.get(j-1).getCreator());
                row.createCell(19).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(20).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(21).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/vendorContactForDownLoadExcel.do",method = RequestMethod.GET)
    @ResponseBody
    public String vendorContactForDownLoadExcel(HttpServletResponse response, @RequestParam("id") Integer id[]){
        String [] title={"供应商代码","供应商中文简称","国家/地区","城市",
                "联系人中文名字","联系人英文名字","性别","职级","部门","权限","语言",
                "公司电话","手机","公司邮箱","私人邮箱","微信","有效状态","采购工程师",
                "建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="VendorContact"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("VendorContact");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<VendorContact> list=new ArrayList<>();
            for(int i=0,length=id.length;i<length;i++){
                VendorContact vendorContact=vendorContactService.queryVendorContactForDownLoad(id[i]);
                list.add(vendorContact);
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getVendorCode());
                if(list.get(j-1).getVendorChineseAbbreviation()!=null){
                    row.createCell(1).setCellValue(list.get(j-1).getVendorChineseAbbreviation());
                }
                if(list.get(j-1).getCountryArea()!=null){
                    row.createCell(2).setCellValue(list.get(j-1).getCountryArea());
                }
                if(list.get(j-1).getCity()!=null){
                    row.createCell(3).setCellValue(list.get(j-1).getCity());
                }
                if(list.get(j-1).getContactChineseName()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getContactChineseName());
                }
                if(list.get(j-1).getContactEnglishName()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getContactEnglishName());
                }
                if(list.get(j-1).getSex()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getSex());
                }
                if(list.get(j-1).getTitle()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getTitle());
                }
                if(list.get(j-1).getDept()!=null){
                    row.createCell(8).setCellValue(list.get(j-1).getDept());
                }
                if(list.get(j-1).getAuthority()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getAuthority());
                }
                if(list.get(j-1).getLanguage()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getLanguage());
                }
                if(list.get(j-1).getCompanyTelephone()!=null){
                    row.createCell(11).setCellValue(list.get(j-1).getCompanyTelephone());
                }
                if(list.get(j-1).getPhoneNumber()!=null){
                    row.createCell(12).setCellValue(list.get(j-1).getPhoneNumber());
                }
                if(list.get(j-1).getCompanyMail()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getCompanyMail());
                }
                if(list.get(j-1).getPrivateMail()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getPrivateMail());
                }
                if(list.get(j-1).getWechatNumber()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getWechatNumber());
                }
                if(list.get(j-1).getStatus()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getStatus());
                }
                if(list.get(j-1).getPurchase()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getPurchase());
                }
                row.createCell(18).setCellValue(list.get(j-1).getCreator());
                row.createCell(19).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(20).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(21).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/vendorContactDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String vendorContactDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="VendorContactModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\VendorContactModel.xlsx");
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

    @RequestMapping(value = "/vendorContactUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String vendorContactUpload(@RequestParam("multipartFile") MultipartFile multipartFile,HttpServletRequest request)throws Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(!vendorContactService.uploadVendorContact(multipartFile,request)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryVendorContactFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryVendorContactFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/vendorContact.do",method = RequestMethod.GET)
    public String vendorContact(){
        return "vendorContact";
    }
}
