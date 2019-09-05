package hwkj.hwkj.controller.Engineering;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.Engineering.MaterialEngineeringData;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.SCM.VendorCode;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.CommodityService;
import hwkj.hwkj.service.Engineering.MaterialEngineeringDataService;
import hwkj.hwkj.service.SCM.VendorCodeService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
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
public class MaterialEngineeringDataController {
    @Autowired
    private MaterialEngineeringDataService materialEngineeringDataService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private VendorCodeService vendorCodeService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryMaterialEngineeringData.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringData(PageModel<MaterialEngineeringData> materialEngineeringDataPageModel, MaterialEngineeringData materialEngineeringData)throws Exception{
        Map<String,Object> map=new HashMap<>();
        materialEngineeringDataService.queryMaterialEngineeringDataPage(materialEngineeringDataPageModel, materialEngineeringData);
        map.put("rows",materialEngineeringDataPageModel.getList());
        map.put("total",materialEngineeringDataPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/materialEngineeringDataAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialEngineeringDataAdd(@RequestParam("photo_Files") MultipartFile []photo_Files,@RequestParam("drawing_Files") MultipartFile []drawing_Files,@RequestParam("specification_Files") MultipartFile []specification_Files,
                                             HttpServletRequest request,MaterialEngineeringData materialEngineeringData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(materialEngineeringDataService.queryMaterialEngineeringDataFourDataExist(materialEngineeringData)>0){
            throw new GlobalException("exist");
        }
        List<MaterialEngineeringData> list=materialEngineeringDataService.queryMaterialEngineeringDataByMaterialNumber(materialEngineeringData.getMaterialNumber());
        if(list.size()!=0){
            if(!(materialEngineeringData.getProductName().trim().equals(list.get(0).getProductName().trim()) &&
            materialEngineeringData.getMaterialType().trim().equals(list.get(0).getMaterialType().trim()) &&
            materialEngineeringData.getSize().trim().equals(list.get(0).getSize().trim()) &&
            materialEngineeringData.getDescribed().trim().equals(list.get(0).getDescribed().trim()) &&
            materialEngineeringData.getCategory().trim().equals(list.get(0).getCategory().trim()))){
                throw new GlobalException("material_Number");
            }
            List<MaterialEngineeringData> list1=materialEngineeringDataService.queryMaterialEngineeringDataByMaterialNumberAndVersion(materialEngineeringData.getMaterialNumber(),materialEngineeringData.getVersion());
            if(!materialEngineeringData.getModelNumber().trim().equals(list1.get(0).getModelNumber().trim())){
                throw new GlobalException("model_Number");
            }
        }
        FileOutputStream fileOutputStream=null;
        Date date=null;
        String drawing_Url="",specification_Url="",photo_Url="";
        //MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        try{
            if(drawing_Files!=null && !"".equals(drawing_Files[0].getOriginalFilename()) && drawing_Files.length>0){
                for(int i=0,length=drawing_Files.length;i<length;i++){
                    MultipartFile drawing_File=drawing_Files[i];
                    date=new Date();
                    Long drawing_Time=date.getTime();
                    String drawing_File_Name=drawing_File.getOriginalFilename();//源文件名
                    String drawing_Suffix_Name=drawing_File_Name.substring(drawing_File_Name.lastIndexOf("."));//后缀名
                    String new_Drawing_File_Name="D"+materialEngineeringData.getMaterialNumber()+materialEngineeringData.getVersion()+drawing_Time+drawing_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/drawing/"+new_Drawing_File_Name);
                    fileOutputStream.write(drawing_File.getBytes());
                    drawing_Url+="/hwkj/upload/drawing/"+new_Drawing_File_Name+",";
                    //materialEngineeringData.setDrawing("/hwkj/upload/drawing/"+new_Drawing_File_Name);
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
                    String new_Specification_File_Name="S"+materialEngineeringData.getMaterialNumber()+materialEngineeringData.getVersion()+specification_Time+specification_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/specification/"+new_Specification_File_Name);
                    fileOutputStream.write(specification_File.getBytes());
                    specification_Url+="/hwkj/upload/specification/"+new_Specification_File_Name+",";
                    //materialEngineeringData.setSpecification("/hwkj/upload/specification/"+new_Specification_File_Name);
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
                    String new_Photo_File_Name="P"+materialEngineeringData.getMaterialNumber()+materialEngineeringData.getVersion()+photo_Time+photo_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/image/"+new_Photo_File_Name);
                    fileOutputStream.write(photo_File.getBytes());
                    photo_Url+="/hwkj/upload/image/"+new_Photo_File_Name+",";
                    //materialEngineeringData.setPhoto("/hwkj/upload/image/"+new_Photo_File_Name);
                }
                photo_Url=photo_Url.substring(0,photo_Url.length()-1);
            }
            //System.out.println("fileName:"+multipartFile.getOriginalFilename());
            /*Iterator<String> iterator=multipartHttpServletRequest.getFileNames();
            while (iterator.hasNext()){
                MultipartFile multipartFile=multipartHttpServletRequest.getFile(iterator.next());
                if(multipartFile!=null){
                    System.out.println(multipartFile.getOriginalFilename()+",");
                    fileOutputStream=new FileOutputStream("F:\\ideaproject\\upload\\image\\"+multipartFile.getOriginalFilename());
                    fileOutputStream.write(multipartFile.getBytes());
                }
            }*/
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!"".equals(drawing_Url)){
            materialEngineeringData.setDrawing(drawing_Url);
        }
        if(!"".equals(specification_Url)){
            materialEngineeringData.setSpecification(specification_Url);
        }
        if(!"".equals(photo_Url)){
            materialEngineeringData.setPhoto(photo_Url);
        }
        materialEngineeringData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        materialEngineeringData.setCreateDate(new Date());
        if(!materialEngineeringDataService.insertMaterialEngineeringData(materialEngineeringData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/materialEngineeringDataUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialEngineeringDataUpdate(HttpServletRequest request,MaterialEngineeringData materialEngineeringData) throws GlobalException {
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        materialEngineeringData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        materialEngineeringData.setUpdateDate(new Date());
        if(!materialEngineeringDataService.updateMaterialEngineeringData(materialEngineeringData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/materialEngineeringDataRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialEngineeringDataRemove(@RequestParam("id[]") Integer id[]) throws GlobalException {
        for(int i=0,length=id.length;i<length;i++){
            if(!materialEngineeringDataService.deleteMaterialEngineeringData(id[i])){
                throw new GlobalException("error");
            }
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/queryMaterialEngineeringDataForBrandAndProductName.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataForBrandAndProductName(@RequestParam("Brand") String Brand,@RequestParam("Product_Name") String Product_Name){
        Map<String,Object> map=new HashMap<>();
        List<VendorCode> newList=new ArrayList<>();
        VendorCode vendorCode=null;
        List<VendorCode> list=vendorCodeService.queryVendorCodeForBrandOption(Brand);
        for(int i=0,length=list.size();i<length;i++){
            String stringBrand=list.get(i).getBrand();
            if(stringBrand.contains(",")){
                String [] brand=list.get(i).getBrand().split(",");
                for(int j=0;j<brand.length;j++){
                    vendorCode=new VendorCode();
                    vendorCode.setBrand(brand[j]);
                    newList.add(vendorCode);
                }
            }else {
                newList.add(list.get(i));
            }
        }
        //map.put("materialEngineeringDataList",materialEngineeringDataService.queryMaterialEngineeringDataForOptionMaterialNumber(Material_Number));
        map.put("vendorCodeList",newList);
        map.put("commodityList",commodityService.queryCommodityByProductName(Product_Name));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialEngineeringDataForBrand.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataForBrand(@RequestParam("Brand") String Brand){
        Map<String,Object> map=new HashMap<>();
        map.put("vendorCodeList",vendorCodeService.queryVendorCodeForLikeBrandOption(Brand));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/queryMaterialEngineeringDataForProductName.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataForProductName(@RequestParam("Product_Name") String Product_Name){
        Map<String,Object> map=new HashMap<>();
        map.put("commodityList",commodityService.queryCommodityByProductName(Product_Name));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/materialEngineeringDataDownLoadExcelAll.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String materialEngineeringDataDownLoadExcelAll(HttpServletResponse response, MaterialEngineeringData materialEngineeringData){
        String [] title={"料号","版本","品名","物料类型","类别","规则/尺寸","材质","型号",
                "描述","品牌","制造商简称","制造商料号","原厂地","图纸","规格书","图片","数量单位","净重",
                "毛重","重量单位","材积cm3","包装材料","最小包装量","标准包装方式","生命周期状态",
                "包装数量","生效时间","失效时间","有效状态","备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MaterialEngineeringData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MaterialEngineeringData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<MaterialEngineeringData> list=materialEngineeringDataService.queryMaterialEngineeringDataForDownLoadAll(materialEngineeringData);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailureTime(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setStatus("Y");
                }else {
                    list.get(i).setStatus("N");
                }
            }
            for (int j=1,length=list.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(list.get(j-1).getMaterialNumber());
                row.createCell(1).setCellValue(list.get(j-1).getVersion());
                row.createCell(2).setCellValue(list.get(j-1).getProductName());
                row.createCell(3).setCellValue(list.get(j-1).getMaterialType());
                row.createCell(4).setCellValue(list.get(j-1).getCategory());
                row.createCell(5).setCellValue(list.get(j-1).getSize());
                row.createCell(6).setCellValue(list.get(j-1).getTextureMaterial());
                row.createCell(7).setCellValue(list.get(j-1).getModelNumber());
                row.createCell(8).setCellValue(list.get(j-1).getDescribed());
                row.createCell(9).setCellValue(list.get(j-1).getBrand());
                row.createCell(10).setCellValue(list.get(j-1).getManufacturerAbbreviation());
                row.createCell(11).setCellValue(list.get(j-1).getManufacturerMaterialNumber());
                row.createCell(12).setCellValue(list.get(j-1).getCountryOrigin());
                row.createCell(13).setCellValue(list.get(j-1).getDrawing());
                row.createCell(14).setCellValue(list.get(j-1).getSpecification());
                row.createCell(15).setCellValue(list.get(j-1).getPhoto());
                row.createCell(16).setCellValue(list.get(j-1).getQuantityUnit());
                if(list.get(j-1).getNetWeight()!=null){
                    row.createCell(17).setCellValue(list.get(j-1).getNetWeight());
                }
                if(list.get(j-1).getGrossWeight()!=null){
                    row.createCell(18).setCellValue(list.get(j-1).getGrossWeight());
                }
                row.createCell(19).setCellValue(list.get(j-1).getWeightUnit());
                if(list.get(j-1).getVolume()!=null){
                    row.createCell(20).setCellValue(list.get(j-1).getVolume());
                }
                row.createCell(21).setCellValue(list.get(j-1).getPackaging());
                if(list.get(j-1).getMinimumPackingCapacity()!=null){
                    row.createCell(22).setCellValue(list.get(j-1).getMinimumPackingCapacity());
                }
                row.createCell(23).setCellValue(list.get(j-1).getStandardPackingMethod());
                row.createCell(24).setCellValue(list.get(j-1).getLifeCycleState());
                if(list.get(j-1).getPackageQuantity()!=null){
                    row.createCell(25).setCellValue(list.get(j-1).getPackageQuantity());
                }
                row.createCell(26).setCellValue(list.get(j-1).getForceTime());
                row.createCell(27).setCellValue(list.get(j-1).getFailureTime());
                row.createCell(28).setCellValue(list.get(j-1).getStatus());
                row.createCell(29).setCellValue(list.get(j-1).getRemark());
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

    @RequestMapping(value ="/materialEngineeringDataDownLoadExcel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String materialEngineeringDataDownLoadExcel(HttpServletResponse response,@RequestParam("id") Integer id[]){
        String [] title={"料号","版本","品名","物料类型","类别","规则/尺寸","材质","型号",
                "描述","品牌","制造商简称","制造商料号","原厂地","图纸","规格书","图片","数量单位","净重",
                "毛重","重量单位","材积cm3","包装材料","最小包装量","标准包装方式","生命周期状态",
                "包装数量","生效时间","失效时间","有效状态","备注","建立人","建立日期","修改人","修改日期"};
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String fileName="MaterialEngineeringData"+time;
        XSSFWorkbook xssfWorkbook=null;
        OutputStream outputStream=null;
        Cell cell=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"iso8859-1")+".xlsx");
            xssfWorkbook=new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet("MaterialEngineeringData");
            XSSFRow row=sheet.createRow(0);
            for(int i=0,length=title.length;i<length;i++){
                cell= row.createCell(i);
                cell.setCellValue(title[i]);
            }
            List<MaterialEngineeringData> newList=new ArrayList<>();
            List<MaterialEngineeringData> list=materialEngineeringDataService.queryMaterialEngineeringDataForDownLoad(id);
            for(int i=0,length=list.size();i<length;i++){
                if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailureTime(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                    list.get(i).setStatus("Y");
                }else {
                    list.get(i).setStatus("N");
                }
                newList.add(list.get(i));
            }
            for (int j=1,length=newList.size();j<=length;j++){
                row=sheet.createRow(j);
                row.createCell(0).setCellValue(newList.get(j-1).getMaterialNumber());
                row.createCell(1).setCellValue(newList.get(j-1).getVersion());
                row.createCell(2).setCellValue(newList.get(j-1).getProductName());
                row.createCell(3).setCellValue(newList.get(j-1).getMaterialType());
                row.createCell(4).setCellValue(newList.get(j-1).getCategory());
                row.createCell(5).setCellValue(newList.get(j-1).getSize());
                row.createCell(6).setCellValue(newList.get(j-1).getTextureMaterial());
                row.createCell(7).setCellValue(newList.get(j-1).getModelNumber());
                row.createCell(8).setCellValue(newList.get(j-1).getDescribed());
                row.createCell(9).setCellValue(newList.get(j-1).getBrand());
                row.createCell(10).setCellValue(newList.get(j-1).getManufacturerAbbreviation());
                row.createCell(11).setCellValue(newList.get(j-1).getManufacturerMaterialNumber());
                row.createCell(12).setCellValue(newList.get(j-1).getCountryOrigin());
                row.createCell(13).setCellValue(newList.get(j-1).getDrawing());
                row.createCell(14).setCellValue(newList.get(j-1).getSpecification());
                row.createCell(15).setCellValue(newList.get(j-1).getPhoto());
                row.createCell(16).setCellValue(newList.get(j-1).getQuantityUnit());
                if(newList.get(j-1).getNetWeight()!=null){
                    row.createCell(17).setCellValue(newList.get(j-1).getNetWeight());
                }
                if(newList.get(j-1).getGrossWeight()!=null){
                    row.createCell(18).setCellValue(newList.get(j-1).getGrossWeight());
                }
                row.createCell(19).setCellValue(newList.get(j-1).getWeightUnit());
                if(newList.get(j-1).getVolume()!=null){
                    row.createCell(20).setCellValue(newList.get(j-1).getVolume());
                }
                row.createCell(21).setCellValue(newList.get(j-1).getPackaging());
                if(newList.get(j-1).getMinimumPackingCapacity()!=null){
                    row.createCell(22).setCellValue(newList.get(j-1).getMinimumPackingCapacity());
                }
                row.createCell(23).setCellValue(newList.get(j-1).getStandardPackingMethod());
                row.createCell(24).setCellValue(newList.get(j-1).getLifeCycleState());
                if(newList.get(j-1).getPackageQuantity()!=null){
                    row.createCell(25).setCellValue(newList.get(j-1).getPackageQuantity());
                }
                row.createCell(26).setCellValue(newList.get(j-1).getForceTime());
                row.createCell(27).setCellValue(newList.get(j-1).getFailureTime());
                row.createCell(28).setCellValue(newList.get(j-1).getStatus());
                row.createCell(29).setCellValue(newList.get(j-1).getRemark());
                row.createCell(30).setCellValue(newList.get(j-1).getCreator());
                row.createCell(31).setCellValue(newList.get(j-1).getCreateDate());
                row.createCell(32).setCellValue(newList.get(j-1).getUpdatedBy());
                row.createCell(33).setCellValue(newList.get(j-1).getUpdateDate());
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

    @RequestMapping(value ="/materialEngineeringDataDownLoadModel.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String materialEngineeringDataDownLoadModel(HttpServletResponse response){
        String time=new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String modelName="MaterialEngineeringDataModel"+time;
        XSSFWorkbook wb = null;
        InputStream is =null;
        OutputStream out=null;
        try {
            response.setContentType("application/vnd.ms-excel; charset=UTF-8");//设置头文件
            response.setHeader("content-disposition", "attachment;filename="+ new String(modelName.getBytes("GBK"),"iso8859-1")+".xlsx");
            is = new FileInputStream("F:\\ideaproject\\hwkj\\target\\classes\\excelmodel\\MaterialEngineeringDataModel.xlsx");
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

    @RequestMapping(value = "/materialEngineeringDataUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialEngineeringDataUpload(@RequestParam("multipartFile") MultipartFile multipartFile,HttpServletRequest request) throws GlobalException,Exception{
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        InputStream inputStream=multipartFile.getInputStream();
        if(!materialEngineeringDataService.materialEngineeringDataUpload(request,inputStream)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/materialEngineeringDataFileUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public String materialEngineeringDataFileUpload(@RequestParam("photo_Files") MultipartFile []photo_Files,@RequestParam("drawing_Files") MultipartFile []drawing_Files,@RequestParam("specification_Files") MultipartFile []specification_Files,
                                                    HttpServletRequest request,MaterialEngineeringData materialEngineeringData) throws GlobalException {
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
                    String new_Drawing_File_Name="D"+materialEngineeringData.getMaterialNumber()+materialEngineeringData.getVersion()+drawing_Time+drawing_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/drawing/"+new_Drawing_File_Name);
                    fileOutputStream.write(drawing_File.getBytes());
                    drawing_Url+="/hwkj/upload/drawing/"+new_Drawing_File_Name+",";
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
                    String new_Specification_File_Name="S"+materialEngineeringData.getMaterialNumber()+materialEngineeringData.getVersion()+specification_Time+specification_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/specification/"+new_Specification_File_Name);
                    fileOutputStream.write(specification_File.getBytes());
                    specification_Url+="/hwkj/upload/specification/"+new_Specification_File_Name+",";
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
                    String new_Photo_File_Name="P"+materialEngineeringData.getMaterialNumber()+materialEngineeringData.getVersion()+photo_Time+photo_Suffix_Name;//重命名
                    fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/image/"+new_Photo_File_Name);
                    fileOutputStream.write(photo_File.getBytes());
                    photo_Url+="/hwkj/upload/image/"+new_Photo_File_Name+",";
                }
                photo_Url=photo_Url.substring(0,photo_Url.length()-1);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!"".equals(drawing_Url)){
            materialEngineeringData.setDrawing(drawing_Url);
        }
        if(!"".equals(specification_Url)){
            materialEngineeringData.setSpecification(specification_Url);
        }
        if(!"".equals(photo_Url)){
            materialEngineeringData.setPhoto(photo_Url);
        }
        materialEngineeringData.setUpdatedBy(((User)request.getSession().getAttribute("user")).getName());
        materialEngineeringData.setUpdateDate(new Date());
        if(!materialEngineeringDataService.materialEngineeringDataFileUpload(materialEngineeringData)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryMaterialEngineeringDataFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMaterialEngineeringDataFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/materialEngineeringData.do",method = RequestMethod.GET)
    public String materialEngineeringData(){
        return "materialEngineeringData";
    }
}
