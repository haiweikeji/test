package hwkj.hwkj.controller.Engineering;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.Engineering.MachineData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.MachineDataService;
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
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MachineDataController {
    @Autowired
    private MachineDataService machineDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryMachineData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMachineData(PageModel<MachineData> machineDataPageModel, MachineData machineData){
        Map<String,Object> map=new HashMap<>();
        machineDataService.queryMachineDataPage(machineDataPageModel, machineData);
        map.put("rows",machineDataPageModel.getList());
        map.put("total",machineDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/machineDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String machineDataAdd(@RequestParam("photo_Files") MultipartFile[]photo_Files, @RequestParam("drawing_Files") MultipartFile []drawing_Files, @RequestParam("specification_Files") MultipartFile []specification_Files,
                                 HttpServletRequest request, MachineData machineData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(machineDataService.queryMachineDataForExist(machineData)>0){
            throw new GlobalException("exist");
        }
        FileOutputStream fileOutputStream=null;
        Date date=null;
        String drawing_Url="",specification_Url="",photo_Url="";
        try{
            if(drawing_Files!=null && !"".equals(drawing_Files[0].getOriginalFilename()) && drawing_Files.length>0){
                for(int i=0,length=drawing_Files.length;i<length;i++){
                    MultipartFile drawing_File=drawing_Files[i];
                    date=new Date();
                    Long drawing_Time=date.getTime();
                    String drawing_File_Name=drawing_File.getOriginalFilename();//源文件名
                    String drawing_Suffix_Name=drawing_File_Name.substring(drawing_File_Name.lastIndexOf("."));//后缀名
                    String new_Drawing_File_Name="D"+machineData.getBrand()+machineData.getModelNumber()+drawing_Time+drawing_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/machine_data/drawing/"+new_Drawing_File_Name);
                    fileOutputStream.write(drawing_File.getBytes());
                    drawing_Url+="/hwkj/upload/machine_data/drawing/"+new_Drawing_File_Name+",";
                }
                drawing_Url=drawing_Url.substring(0,drawing_Url.length()-1);
            }
            if(specification_Files!=null && !"".equals(specification_Files[0].getOriginalFilename()) && specification_Files.length>0){
                for(int i=0,length=specification_Files.length;i<length;i++){
                    MultipartFile specification_File=specification_Files[i];
                    date=new Date();
                    long specification_Time=date.getTime();
                    String specification_File_Name=specification_File.getOriginalFilename();//源文件名
                    String specification_Suffix_Name=specification_File_Name.substring(specification_File_Name.lastIndexOf("."));//后缀名
                    String new_Specification_File_Name="S"+machineData.getBrand()+machineData.getModelNumber()+specification_Time+specification_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/machine_data/specification/"+new_Specification_File_Name);
                    fileOutputStream.write(specification_File.getBytes());
                    specification_Url+="/hwkj/upload/machine_data/specification/"+new_Specification_File_Name+",";
                }
                specification_Url=specification_Url.substring(0,specification_Url.length()-1);
            }
            if(photo_Files != null && !"".equals(photo_Files[0].getOriginalFilename()) && photo_Files.length > 0){
                for(int i=0,length=photo_Files.length;i<length;i++){
                    MultipartFile photo_File=photo_Files[i];
                    date=new Date();
                    long photo_Time=date.getTime();
                    String photo_File_Name=photo_File.getOriginalFilename();//源文件名
                    String photo_Suffix_Name=photo_File_Name.substring(photo_File_Name.lastIndexOf("."));//后缀名
                    String new_Photo_File_Name="P"+machineData.getBrand()+machineData.getModelNumber()+photo_Time+photo_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/machine_data/image/"+new_Photo_File_Name);
                    fileOutputStream.write(photo_File.getBytes());
                    photo_Url+="/hwkj/upload/machine_data/image/"+new_Photo_File_Name+",";
                }
                photo_Url=photo_Url.substring(0,photo_Url.length()-1);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!"".equals(drawing_Url)){
            machineData.setDrawing(drawing_Url);
        }
        if(!"".equals(specification_Url)){
            machineData.setSpecification(specification_Url);
        }
        if(!"".equals(photo_Url)){
            machineData.setPhoto(photo_Url);
        }
        machineData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        machineData.setCreateDate(new Date());
        if(!machineDataService.insertMachineData(machineData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/machineDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String machineDataUpdate(HttpServletRequest request,MachineData machineData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(machineDataService.queryMachineDataForExist(machineData)>0){
            throw new GlobalException("exist");
        }
        machineData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        machineData.setUpdateDate(new Date());
        if(!machineDataService.updateMachineData(machineData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/machineDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String machineDataRemove(@RequestParam("id[]") Integer id[]) throws GlobalException {
        for(int i=0,length=id.length;i<length;i++){
            if(!machineDataService.deleteMachineData(id[i])){
                throw new GlobalException("error");
            }
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/machineDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String machineDataDownLoadExcelAll(HttpServletResponse response, MachineData machineData){
        String [] title={"设备名称","设备类型","品牌","型号","规格","原产地","描述","机床精度等级",
                "最大加工尺寸mm","最小加工尺寸mm","轨道行程","适用加工的产品","设备优点简述","刀库种类","主轴锥孔类型","速度类型","速度单位","最高运行速度",
                "最大压力(吨)","用气种类","额定气压(Pa)","单位耗气量升","额定电压(V)","额定电流(A)","额定功率(W)",
                "设备外形尺寸(cm)","设备重量(kg)","制造商简称","最早出厂时间","生命周期状态","图纸","规格书","图片",
                "备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MachineData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MachineData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<MachineData> list=machineDataService.queryMachineDataForDownLoadAll(machineData);
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getMachineName());
                row.createCell(1).setCellValue(list.get(j-1).getMachineType());
                row.createCell(2).setCellValue(list.get(j-1).getBrand());
                row.createCell(3).setCellValue(list.get(j-1).getModelNumber());
                if(list.get(j-1).getSize()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getSize());
                }
                if(list.get(j-1).getCountryOrigin()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getCountryOrigin());
                }
                if(list.get(j-1).getDescribed()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getDescribed());
                }
                if(list.get(j-1).getMachineToolAccuracyGrade()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getMachineToolAccuracyGrade());
                }
                if(list.get(j-1).getMaxMachiningSize()!=null){
                    row.createCell(8).setCellValue(list.get(j-1).getMaxMachiningSize());
                }
                if(list.get(j-1).getMinMachiningSize()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getMinMachiningSize());
                }
                if(list.get(j-1).getTrackTravel()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getTrackTravel());
                }
                row.createCell(11).setCellValue(list.get(j-1).getMachiningProduct());
                row.createCell(12).setCellValue(list.get(j-1).getEquipmentAdvantages());
                if(list.get(j-1).getToolMagazineType()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getToolMagazineType());
                }
                if(list.get(j-1).getSpindleConeHoleType()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getSpindleConeHoleType());
                }
                if(list.get(j-1).getSpeedType()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getSpeedType());
                }
                if(list.get(j-1).getSpeedUnit()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getSpeedUnit());
                }
                if(list.get(j-1).getMaxSpeed()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getMaxSpeed());
                }
                if(list.get(j-1).getMaxPressure()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getMaxPressure());
                }
                if(list.get(j-1).getAirType()!=null){
                    row.createCell(19).setCellValue(list.get(j-1).getAirType());
                }
                if(list.get(j-1).getRatedPressure()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getRatedPressure());
                }
                if(list.get(j-1).getUnitGasConsumption()!=null){
                    row.createCell(21).setCellValue(list.get(j-1).getUnitGasConsumption());
                }
                if(list.get(j-1).getRatedVoltage()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getRatedVoltage());
                }
                if(list.get(j-1).getCurrentRating()!=null){
                    row.createCell(23).setCellValue(list.get(j-1).getCurrentRating());
                }
                if(list.get(j-1).getPowerRating()!=null){
                    row.createCell(24).setCellValue(list.get(j-1).getPowerRating());
                }
                if(list.get(j-1).getContourSize()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getContourSize());
                }
                if(list.get(j-1).getWeight()!=null){
                    row.createCell(26).setCellValue(list.get(j-1).getWeight());
                }
                if(list.get(j-1).getManufacturerAbbreviation()!=null){
                    row.createCell(27).setCellValue(list.get(j-1).getManufacturerAbbreviation());
                }
                if(list.get(j-1).getFactoryTime()!=null){
                    row.createCell(28).setCellValue(list.get(j-1).getFactoryTime());
                }
                if(list.get(j-1).getLifeCycleState()!=null){
                    row.createCell(29).setCellValue(list.get(j-1).getLifeCycleState());
                }
                if(list.get(j-1).getDrawing()!=null){
                    row.createCell(30).setCellValue(list.get(j-1).getDrawing());
                }
                if(list.get(j-1).getSpecification()!=null){
                    row.createCell(31).setCellValue(list.get(j-1).getSpecification());
                }
                if(list.get(j-1).getPhoto()!=null){
                    row.createCell(32).setCellValue(list.get(j-1).getPhoto());
                }
                if(list.get(j-1).getRemark()!=null){
                    row.createCell(33).setCellValue(list.get(j-1).getRemark());
                }
                row.createCell(34).setCellValue(list.get(j-1).getCreator());
                row.createCell(35).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(36).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(37).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/machineDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String machineDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"设备名称","设备类型","品牌","型号","规格","原产地","描述","机床精度等级",
                "最大加工尺寸mm","最小加工尺寸mm","轨道行程","适用加工的产品","设备优点简述","刀库种类","主轴锥孔类型","速度类型","速度单位","最高运行速度",
                "最大压力(吨)","用气种类","额定气压(Pa)","单位耗气量升","额定电压(V)","额定电流(A)","额定功率(W)",
                "设备外形尺寸(cm)","设备重量(kg)","制造商简称","最早出厂时间","生命周期状态","图纸","规格书","图片",
                "备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MachineData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MachineData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<MachineData> list=new ArrayList<>();
            for(int i=0,length=id.length;i<length;i++){
                MachineData machineData=machineDataService.queryMachineDataForDownLoad(id[i]);
                list.add(machineData);
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getMachineName());
                row.createCell(1).setCellValue(list.get(j-1).getMachineType());
                row.createCell(2).setCellValue(list.get(j-1).getBrand());
                row.createCell(3).setCellValue(list.get(j-1).getModelNumber());
                if(list.get(j-1).getSize()!=null){
                    row.createCell(4).setCellValue(list.get(j-1).getSize());
                }
                if(list.get(j-1).getCountryOrigin()!=null){
                    row.createCell(5).setCellValue(list.get(j-1).getCountryOrigin());
                }
                if(list.get(j-1).getDescribed()!=null){
                    row.createCell(6).setCellValue(list.get(j-1).getDescribed());
                }
                if(list.get(j-1).getMachineToolAccuracyGrade()!=null){
                    row.createCell(7).setCellValue(list.get(j-1).getMachineToolAccuracyGrade());
                }
                if(list.get(j-1).getMaxMachiningSize()!=null){
                    row.createCell(8).setCellValue(list.get(j-1).getMaxMachiningSize());
                }
                if(list.get(j-1).getMinMachiningSize()!=null){
                    row.createCell(9).setCellValue(list.get(j-1).getMinMachiningSize());
                }
                if(list.get(j-1).getTrackTravel()!=null){
                    row.createCell(10).setCellValue(list.get(j-1).getTrackTravel());
                }
                row.createCell(11).setCellValue(list.get(j-1).getMachiningProduct());
                row.createCell(12).setCellValue(list.get(j-1).getEquipmentAdvantages());
                if(list.get(j-1).getToolMagazineType()!=null){
                    row.createCell(13).setCellValue(list.get(j-1).getToolMagazineType());
                }
                if(list.get(j-1).getSpindleConeHoleType()!=null){
                    row.createCell(14).setCellValue(list.get(j-1).getSpindleConeHoleType());
                }
                if(list.get(j-1).getSpeedType()!=null){
                    row.createCell(15).setCellValue(list.get(j-1).getSpeedType());
                }
                if(list.get(j-1).getSpeedUnit()!=null){
                    row.createCell(16).setCellValue(list.get(j-1).getSpeedUnit());
                }
                if(list.get(j-1).getMaxSpeed()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getMaxSpeed());
                }
                if(list.get(j-1).getMaxPressure()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getMaxPressure());
                }
                if(list.get(j-1).getAirType()!=null){
                    row.createCell(19).setCellValue(list.get(j-1).getAirType());
                }
                if(list.get(j-1).getRatedPressure()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getRatedPressure());
                }
                if(list.get(j-1).getUnitGasConsumption()!=null){
                    row.createCell(21).setCellValue(list.get(j-1).getUnitGasConsumption());
                }
                if(list.get(j-1).getRatedVoltage()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getRatedVoltage());
                }
                if(list.get(j-1).getCurrentRating()!=null){
                    row.createCell(23).setCellValue(list.get(j-1).getCurrentRating());
                }
                if(list.get(j-1).getPowerRating()!=null){
                    row.createCell(24).setCellValue(list.get(j-1).getPowerRating());
                }
                if(list.get(j-1).getContourSize()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getContourSize());
                }
                if(list.get(j-1).getWeight()!=null){
                    row.createCell(26).setCellValue(list.get(j-1).getWeight());
                }
                if(list.get(j-1).getManufacturerAbbreviation()!=null){
                    row.createCell(27).setCellValue(list.get(j-1).getManufacturerAbbreviation());
                }
                if(list.get(j-1).getFactoryTime()!=null){
                    row.createCell(28).setCellValue(list.get(j-1).getFactoryTime());
                }
                if(list.get(j-1).getLifeCycleState()!=null){
                    row.createCell(29).setCellValue(list.get(j-1).getLifeCycleState());
                }
                if(list.get(j-1).getDrawing()!=null){
                    row.createCell(30).setCellValue(list.get(j-1).getDrawing());
                }
                if(list.get(j-1).getSpecification()!=null){
                    row.createCell(31).setCellValue(list.get(j-1).getSpecification());
                }
                if(list.get(j-1).getPhoto()!=null){
                    row.createCell(32).setCellValue(list.get(j-1).getPhoto());
                }
                if(list.get(j-1).getRemark()!=null){
                    row.createCell(33).setCellValue(list.get(j-1).getRemark());
                }
                row.createCell(34).setCellValue(list.get(j-1).getCreator());
                row.createCell(35).setCellValue(list.get(j-1).getCreateDate());
                row.createCell(36).setCellValue(list.get(j-1).getUpdatedBy());
                row.createCell(37).setCellValue(list.get(j-1).getUpdateDate());
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

    @RequestMapping(value = "/machineDataDownLoadModel.do",method = RequestMethod.GET)
    @ResponseBody
    public String machineDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="MachineDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\MachineDataModel.xlsx");
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

    @RequestMapping(value = "/machineDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String machineDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) throws GlobalException,Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!machineDataService.machineDataUpload(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/machineDataFileUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String machineDataFileUpload(@RequestParam("photo_Files") MultipartFile []photo_Files,@RequestParam("drawing_Files") MultipartFile []drawing_Files,@RequestParam("specification_Files") MultipartFile []specification_Files,
                                                    HttpServletRequest request,MachineData machineData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        FileOutputStream fileOutputStream=null;
        Date date=null;
        String drawing_Url="",specification_Url="",photo_Url="";
        try{
            if(drawing_Files!=null && !"".equals(drawing_Files[0].getOriginalFilename()) && drawing_Files.length>0){
                for(int i=0,length=drawing_Files.length;i<length;i++){
                    MultipartFile drawing_File=drawing_Files[i];
                    date=new Date();
                    Long drawing_Time=date.getTime();
                    String drawing_File_Name=drawing_File.getOriginalFilename();//源文件名
                    String drawing_Suffix_Name=drawing_File_Name.substring(drawing_File_Name.lastIndexOf("."));//后缀名
                    String new_Drawing_File_Name="D"+machineData.getBrand()+machineData.getModelNumber()+drawing_Time+drawing_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/machine_data/drawing/"+new_Drawing_File_Name);
                    fileOutputStream.write(drawing_File.getBytes());
                    drawing_Url+="/hwkj/upload/machine_data/drawing/"+new_Drawing_File_Name+",";
                }
                drawing_Url=drawing_Url.substring(0,drawing_Url.length()-1);
            }
            if(specification_Files!=null && !"".equals(specification_Files[0].getOriginalFilename()) && specification_Files.length>0){
                for(int i=0,length=specification_Files.length;i<length;i++){
                    MultipartFile specification_File=specification_Files[i];
                    date=new Date();
                    long specification_Time=date.getTime();
                    String specification_File_Name=specification_File.getOriginalFilename();//源文件名
                    String specification_Suffix_Name=specification_File_Name.substring(specification_File_Name.lastIndexOf("."));//后缀名
                    String new_Specification_File_Name="S"+machineData.getBrand()+machineData.getModelNumber()+specification_Time+specification_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/machine_data/specification/"+new_Specification_File_Name);
                    fileOutputStream.write(specification_File.getBytes());
                    specification_Url+="/hwkj/upload/machine_data/specification/"+new_Specification_File_Name+",";
                }
                specification_Url=specification_Url.substring(0,specification_Url.length()-1);
            }
            if(photo_Files != null && !"".equals(photo_Files[0].getOriginalFilename()) && photo_Files.length > 0){
                for(int i=0,length=photo_Files.length;i<length;i++){
                    MultipartFile photo_File=photo_Files[i];
                    date=new Date();
                    long photo_Time=date.getTime();
                    String photo_File_Name=photo_File.getOriginalFilename();//源文件名
                    String photo_Suffix_Name=photo_File_Name.substring(photo_File_Name.lastIndexOf("."));//后缀名
                    String new_Photo_File_Name="P"+machineData.getBrand()+machineData.getModelNumber()+photo_Time+photo_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/machine_data/image/"+new_Photo_File_Name);
                    fileOutputStream.write(photo_File.getBytes());
                    photo_Url+="/hwkj/upload/machine_data/image/"+new_Photo_File_Name+",";
                }
                photo_Url=photo_Url.substring(0,photo_Url.length()-1);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!"".equals(drawing_Url)){
            machineData.setDrawing(drawing_Url);
        }
        if(!"".equals(specification_Url)){
            machineData.setSpecification(specification_Url);
        }
        if(!"".equals(photo_Url)){
            machineData.setPhoto(photo_Url);
        }
        machineData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        machineData.setUpdateDate(new Date());
        if(!machineDataService.updateMachineData(machineData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryMachineDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMachineDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/machineData.do",method = RequestMethod.GET)
    public String machineData(){
        return "machineData";
    }
}
