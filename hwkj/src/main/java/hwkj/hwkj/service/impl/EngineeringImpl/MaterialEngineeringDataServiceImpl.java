package hwkj.hwkj.service.impl.EngineeringImpl;

import hwkj.hwkj.dao.Engineering.CommodityDao;
import hwkj.hwkj.dao.Engineering.MaterialEngineeringDataDao;
import hwkj.hwkj.dao.SCM.VendorCodeDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.MaterialEngineeringDataService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MaterialEngineeringDataServiceImpl implements MaterialEngineeringDataService {
    @Autowired
    private MaterialEngineeringDataDao materialEngineeringDataDao;
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private VendorCodeDao vendorCodeDao;

    /**
     * 新增数据
     * @param materialEngineeringData
     * @return
     */
    @Transactional
    @Override
    public boolean insertMaterialEngineeringData(MaterialEngineeringData materialEngineeringData) throws RuntimeException{
        int eff=materialEngineeringDataDao.insertMaterialEngineeringData(materialEngineeringData);
        if(eff>0){
            return true;
        }
        return false;
    }

    /**
     * 删除数据
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteMaterialEngineeringData(Integer Id) throws RuntimeException{
        int eff=materialEngineeringDataDao.deleteMaterialEngineeringData(Id);
        if(eff>0){
            return true;
        }
        return false;
    }

    /**
     * 更新数据
     * @param materialEngineeringData
     * @return
     */
    @Transactional
    @Override
    public boolean updateMaterialEngineeringData(MaterialEngineeringData materialEngineeringData) throws RuntimeException{
        if(materialEngineeringDataDao.queryMaterialEngineeringDataForAllExist(materialEngineeringData)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(materialEngineeringData.getMaterial_Number().trim().equals(materialEngineeringData.getOld_Material_Number().trim()) &&
            materialEngineeringData.getVersion().trim().equals(materialEngineeringData.getOld_Version().trim()) &&
            materialEngineeringData.getManufacturer_Abbreviation().trim().equals(materialEngineeringData.getOld_Manufacturer_Abbreviation().trim()) &&
            materialEngineeringData.getManufacturer_Material_Number().trim().equals(materialEngineeringData.getOld_Manufacturer_Material_Number().trim())){
                if(materialEngineeringDataDao.queryMaterialEngineeringDataCheckMaterialNumberAndVersion(materialEngineeringData)<=0){
                    throw new GlobalException("material_error");//HW料号+HW版本与品名、物料（服务）类型、规格/尺寸、描述、类别、型号必需相同
                }
                if(materialEngineeringDataDao.updateMaterialEngineeringData(materialEngineeringData)<=0){
                    throw new GlobalException("error");
                }
            }else {
                List<MaterialEngineeringData> MaterialEngineeringDataList=materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumber(materialEngineeringData.getMaterial_Number());
                if(MaterialEngineeringDataList.size()!=0){
                    if(!(materialEngineeringData.getProduct_Name().trim().equals(MaterialEngineeringDataList.get(0).getProduct_Name().trim()) &&
                            materialEngineeringData.getMaterial_Type().trim().equals(MaterialEngineeringDataList.get(0).getMaterial_Type().trim()) &&
                            materialEngineeringData.getSize().trim().equals(MaterialEngineeringDataList.get(0).getSize().trim()) &&
                            materialEngineeringData.getDescribed().trim().equals(MaterialEngineeringDataList.get(0).getDescribed().trim()) &&
                            materialEngineeringData.getCategory().trim().equals(MaterialEngineeringDataList.get(0).getCategory().trim()))){
                        throw new GlobalException("material_Number");
                    }
                    List<MaterialEngineeringData> list1=materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumberAndVersion(materialEngineeringData.getMaterial_Number(),materialEngineeringData.getVersion());
                    if(list1.size()!=0){
                        if(!materialEngineeringData.getModel_Number().trim().equals(list1.get(0).getModel_Number().trim())){
                            throw new GlobalException("model_Number");
                        }
                    }
                }
                if(materialEngineeringDataDao.queryMaterialEngineeringDataFourDataExist(materialEngineeringData)!=0){
                    throw new GlobalException("exist");
                }
                if(materialEngineeringDataDao.updateMaterialEngineeringData(materialEngineeringData)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 上传图纸,规格书,图片
     * @param materialEngineeringData
     * @return
     */
    @Override
    public boolean materialEngineeringDataFileUpload(MaterialEngineeringData materialEngineeringData) {
        if(materialEngineeringDataDao.materialEngineeringDataFileUpload(materialEngineeringData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param materialEngineeringDataPageModel
     * @param materialEngineeringData
     */
    @Override
    public void queryMaterialEngineeringDataPage(PageModel<MaterialEngineeringData> materialEngineeringDataPageModel, MaterialEngineeringData materialEngineeringData) throws Exception{
        List<MaterialEngineeringData> list=materialEngineeringDataDao.queryMaterialEngineeringDataList(materialEngineeringDataPageModel, materialEngineeringData);
        for(int i=0,length=list.size();i<length;i++){
            if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                list.get(i).setStatus("Y");
            }else {
                list.get(i).setStatus("N");
            }
        }
        materialEngineeringDataPageModel.setList(list);
        materialEngineeringDataPageModel.setTotal(materialEngineeringDataDao.queryMaterialEngineeringDataCount(materialEngineeringDataPageModel, materialEngineeringData));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public MaterialEngineeringData queryMaterialEngineeringDataById(Integer Id) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataById(Id);
    }

    /**
     * for download all
     * @param materialEngineeringData
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForDownLoadAll(MaterialEngineeringData materialEngineeringData) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataForDownLoadAll(materialEngineeringData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForDownLoad(Integer Id[]) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataForDownLoad(Id);
    }

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     */
    @Transactional
    @Override
    public boolean materialEngineeringDataUpload(HttpServletRequest request, InputStream inputStream) throws Exception{
        //List<MaterialEngineeringData> materialEngineeringDataList1= Collections.synchronizedList(new ArrayList<>());
        //List<MaterialEngineeringData> materialEngineeringDataList=new ArrayList<>();
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        MaterialEngineeringData materialEngineeringData=null;
        /*synchronized (materialEngineeringDataList1){
            Iterator<MaterialEngineeringData> iterator=materialEngineeringDataList1.iterator();
            while (iterator.hasNext()){
                MaterialEngineeringData materialEngineeringData1=iterator.next();
            }
        }*/
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(2)==null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(4)==null || sheet.getRow(i).getCell(4).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(6)==null || sheet.getRow(i).getCell(6).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(7)==null || sheet.getRow(i).getCell(7).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(8)==null || sheet.getRow(i).getCell(8).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(9)==null || sheet.getRow(i).getCell(9).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(10)==null || sheet.getRow(i).getCell(10).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(11)==null || sheet.getRow(i).getCell(11).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(12)==null || sheet.getRow(i).getCell(12).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(15)==null || sheet.getRow(i).getCell(15).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(17)==null || sheet.getRow(i).getCell(17).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(20)==null || sheet.getRow(i).getCell(20).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(21)==null || sheet.getRow(i).getCell(21).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(22)==null || sheet.getRow(i).getCell(22).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(23)==null || sheet.getRow(i).getCell(23).getStringCellValue().trim().length()==0){
                throw new GlobalException("blank:"+(i+1));//代表必填项没填
            }
            List<Commodity> commodityList=commodityDao.queryCommodityByProductName(sheet.getRow(i).getCell(2).getStringCellValue().trim());
            if(commodityList.size()==0){
                throw new GlobalException("product_name:"+(i+1));//品名填写错误
            }
            if(!("产品".equals(sheet.getRow(i).getCell(3).getStringCellValue().trim())||"服务".equals(sheet.getRow(i).getCell(3).getStringCellValue().trim())||
                    "软体".equals(sheet.getRow(i).getCell(3).getStringCellValue().trim()))){
                throw new GlobalException("material_Type:"+(i+1));//生命周期状态填写错误
            }
            String Brand=sheet.getRow(i).getCell(8).getStringCellValue().trim();
            List<VendorCode> vendorCodeList=vendorCodeDao.queryVendorCodeForLikeBrandOption(Brand);
            if(vendorCodeList.size()==0){
                throw new  GlobalException("brand:"+(i+1));//品牌填写错误
            }
            if(!("研发中".equals(sheet.getRow(i).getCell(20).getStringCellValue().trim())||"导入中".equals(sheet.getRow(i).getCell(20).getStringCellValue().trim())||
                    "试产中".equals(sheet.getRow(i).getCell(20).getStringCellValue().trim())||"量产中".equals(sheet.getRow(i).getCell(20).getStringCellValue().trim())||
                    "EOL".equals(sheet.getRow(i).getCell(20).getStringCellValue().trim()))){
                throw new GlobalException("life_cycle_state:"+(i+1));//生命周期状态填写错误
            }
            String Material_Number=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Version=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            String Vendor_Chinese_Abbreviation=vendorCodeList.get(0).getVendor_Chinese_Abbreviation();
            String City=vendorCodeList.get(0).getCity();
            String Product_Name=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            String Material_Type=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            String Size=sheet.getRow(i).getCell(4).getStringCellValue().trim();
            String Described=sheet.getRow(i).getCell(7).getStringCellValue().trim();
            String Category=commodityList.get(0).getCategory().trim();
            String Model_Number=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            List<MaterialEngineeringData> materialEngineeringDataList=materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumber(Material_Number);
            if(materialEngineeringDataList.size()!=0){
                if(!(Product_Name.equals(materialEngineeringDataList.get(0).getProduct_Name().trim()) &&
                        Material_Type.equals(materialEngineeringDataList.get(0).getMaterial_Type().trim()) &&
                        Size.equals(materialEngineeringDataList.get(0).getSize().trim()) &&
                        Described.equals(materialEngineeringDataList.get(0).getDescribed().trim()) &&
                        Category.equals(materialEngineeringDataList.get(0).getCategory().trim()))){
                    throw new GlobalException("material_Number:"+(i+1));
                }
                List<MaterialEngineeringData> materialEngineeringDataList1=materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumberAndVersion(Material_Number,Version);
                if(materialEngineeringDataList1.size()!=0){
                    if(!Model_Number.equals(materialEngineeringDataList1.get(0).getModel_Number().trim())){
                        throw new GlobalException("model_Number:"+(i+1));
                    }
                }
            }
            materialEngineeringData=new MaterialEngineeringData();
            materialEngineeringData.setMaterial_Number(Material_Number);
            materialEngineeringData.setVersion(Version);
            materialEngineeringData.setProduct_Name(Product_Name);
            materialEngineeringData.setMaterial_Type(Material_Type);
            materialEngineeringData.setSize(Size);
            if(sheet.getRow(i).getCell(5)!=null){
                materialEngineeringData.setTexture_Material(sheet.getRow(i).getCell(5).getStringCellValue());
            }else {
                materialEngineeringData.setTexture_Material("");
            }
            materialEngineeringData.setModel_Number(Model_Number);
            materialEngineeringData.setDescribed(Described);
            materialEngineeringData.setBrand(Brand);
            materialEngineeringData.setManufacturer_Abbreviation(Vendor_Chinese_Abbreviation);
            materialEngineeringData.setManufacturer_Material_Number(sheet.getRow(i).getCell(10).getStringCellValue());
            materialEngineeringData.setCountry_Origin(City);
            materialEngineeringData.setQuantity_Unit(sheet.getRow(i).getCell(12).getStringCellValue());
            if(sheet.getRow(i).getCell(13)!=null){
                materialEngineeringData.setNet_Weight(Double.parseDouble(sheet.getRow(i).getCell(13).getStringCellValue()));
            }
            if(sheet.getRow(i).getCell(14)!=null){
                materialEngineeringData.setGross_Weight(Double.parseDouble(sheet.getRow(i).getCell(14).getStringCellValue()));
            }
            materialEngineeringData.setWeight_Unit(sheet.getRow(i).getCell(15).getStringCellValue());
            if(sheet.getRow(i).getCell(16)!=null){
                materialEngineeringData.setVolume(Double.parseDouble(sheet.getRow(i).getCell(16).getStringCellValue()));
            }
            materialEngineeringData.setPackaging(sheet.getRow(i).getCell(17).getStringCellValue());
            if(sheet.getRow(i).getCell(18)!=null){
                materialEngineeringData.setMinimum_Packing_Capacity(Integer.parseInt(sheet.getRow(i).getCell(18).getStringCellValue()));
            }
            if(sheet.getRow(i).getCell(19)!=null){
                materialEngineeringData.setStandard_Packing_Method(sheet.getRow(i).getCell(19).getStringCellValue());
            }else {
                materialEngineeringData.setStandard_Packing_Method("");
            }
            materialEngineeringData.setLife_Cycle_State(sheet.getRow(i).getCell(20).getStringCellValue());
            materialEngineeringData.setPackage_Quantity(Integer.parseInt(sheet.getRow(i).getCell(21).getStringCellValue()));
            materialEngineeringData.setForce_Time(sheet.getRow(i).getCell(22).getStringCellValue());
            materialEngineeringData.setFailure_Time(sheet.getRow(i).getCell(23).getStringCellValue());
            if(sheet.getRow(i).getCell(24)!=null){
                materialEngineeringData.setRemark(sheet.getRow(i).getCell(24).getStringCellValue());
            }else {
                materialEngineeringData.setRemark("");
            }
            materialEngineeringData.setCategory(Category);
            materialEngineeringData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            materialEngineeringData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(materialEngineeringDataDao.queryMaterialEngineeringDataForExist(materialEngineeringData)>0){
                throw new GlobalException("exist:"+(i+1));
            }
            int eff=materialEngineeringDataDao.insertMaterialEngineeringData(materialEngineeringData);
            if(eff<=0){
                throw new GlobalException("error");
            }
        }
        return true;
    }

    /**
     *下拉框Material_Number
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataOptionMaterialNumber() {
        return materialEngineeringDataDao.queryMaterialEngineeringDataOptionMaterialNumber();
    }

    /**
     *下拉框Version
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataOptionVersion(String Material_Number ) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataOptionVersion(Material_Number);
    }

    /**
     *下拉框Manufacturer_Abbreviation
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataOptionManufacturerAbbreviation(String Material_Number,String Version ) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataOptionManufacturerAbbreviation(Material_Number, Version);
    }

    /**
     *下拉框Manufacturer_Material_Number
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataOptionManufacturerMaterialNumber(String Material_Number,String Version,String Manufacturer_Material_Number) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataOptionManufacturerMaterialNumber(Material_Number, Version, Manufacturer_Material_Number);
    }

    /**
     * check material_engineering_data 是否存在
     * @param materialEngineeringData
     * @return
     */
    @Override
    public int queryMaterialEngineeringDataFourDataExist(MaterialEngineeringData materialEngineeringData) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataFourDataExist(materialEngineeringData);
    }

    /**
     * check material_number 是否存在
     * @param Material_Number
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataByMaterialNumber(String Material_Number) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumber(Material_Number);
    }

    /**
     * 通过查询HW料号和HW版本获取型号
     * @param Material_Number
     * @param Version
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataByMaterialNumberAndVersion(String Material_Number, String Version) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumberAndVersion(Material_Number, Version);
    }

    /**
     * 通过查询HW料号和HW版本获取品牌
     *
     * @param Material_Number
     * @param Version
     * @return
     */
    @Override
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForOptionBrand(String Material_Number, String Version) {
        return materialEngineeringDataDao.queryMaterialEngineeringDataForOptionBrand(Material_Number, Version);
    }
}
