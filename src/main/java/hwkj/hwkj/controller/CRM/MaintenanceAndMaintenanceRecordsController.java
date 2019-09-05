package hwkj.hwkj.controller.CRM;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.CRM.CustomerEquipmentData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.service.CRM.CustomerEquipmentDataService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MaintenanceAndMaintenanceRecordsController {
    @Autowired
    private CustomerEquipmentDataService customerEquipmentDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryMaintenanceAndMaintenanceRecords.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaintenanceAndMaintenanceRecords(PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, CustomerEquipmentData customerEquipmentData){
        Map<String,Object> map= new HashMap<>();
        customerEquipmentDataService.queryMaintenanceAndMaintenanceRecordsDataPage(customerEquipmentDataPageModel,customerEquipmentData);
        map.put("rows",customerEquipmentDataPageModel.getList());
        map.put("total",customerEquipmentDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/maintenanceAndMaintenanceRecords.do",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView maintenanceAndMaintenanceRecords(@RequestParam(value = "cellValue") String cellValue){
        ModelAndView modelAndView=new ModelAndView("maintenanceAndMaintenanceRecords");
        modelAndView.addObject("SN",cellValue);
        return modelAndView;
    }

    @RequestMapping(value ="/maintenanceAndMaintenanceRecordsDataDownLoad.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String maintenanceAndMaintenanceRecordsDataDownLoad(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"客户代码","客户中文简称","客户英文简称","国家/地区","城市","工厂地址",
                "设备名称","品牌","型号","出厂日期","出厂编号","数量","产品","稼动率","保养记录","维修记录","业务员",
                "服务工程师","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MaintenanceAndMaintenanceRecordsData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MaintenanceAndMaintenanceRecordsData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<CustomerEquipmentData> list=customerEquipmentDataService.queryMaintenanceAndMaintenanceRecordsDataDownLoad(id);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getCustomerCode());
                row.createCell(1).setCellValue(list.get(j-1).getChineseAbbreviation());
                row.createCell(2).setCellValue(list.get(j-1).getEnglishAbbreviation());
                row.createCell(3).setCellValue(list.get(j-1).getCountry());
                row.createCell(4).setCellValue(list.get(j-1).getCity());
                row.createCell(5).setCellValue(list.get(j-1).getFactoryAddress());
                row.createCell(6).setCellValue(list.get(j-1).getEquipmentName());
                row.createCell(7).setCellValue(list.get(j-1).getBrand());
                row.createCell(8).setCellValue(list.get(j-1).getModelNumber());
                row.createCell(9).setCellValue(list.get(j-1).getProductionDate());
                row.createCell(10).setCellValue(list.get(j-1).getSn());
                row.createCell(11).setCellValue(list.get(j-1).getQuantity());
                row.createCell(12).setCellValue(list.get(j-1).getProducts());
                row.createCell(13).setCellValue(list.get(j-1).getOperationRate());
                row.createCell(14).setCellValue(list.get(j-1).getMaintenanceRecord());
                row.createCell(15).setCellValue(list.get(j-1).getRepairRecode());
                row.createCell(16).setCellValue(list.get(j-1).getBpm());
                row.createCell(17).setCellValue(list.get(j-1).getEngineer());
                row.createCell(18).setCellValue(list.get(j-1).getCreator());
                row.createCell(18).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(20).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(21).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/queryMaintenanceAndMaintenanceRecordsDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaintenanceAndMaintenanceRecordsDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }
}
