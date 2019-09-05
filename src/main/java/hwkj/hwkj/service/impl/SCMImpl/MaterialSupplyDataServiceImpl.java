package hwkj.hwkj.service.impl.SCMImpl;

import hwkj.hwkj.dao.Engineering.MaterialEngineeringDataDao;
import hwkj.hwkj.dao.Quote.QuoteTermDao;
import hwkj.hwkj.dao.SCM.MaterialSupplyDataDao;
import hwkj.hwkj.dao.SCM.VendorCodeDao;
import hwkj.hwkj.dao.User.UserRoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.MaterialSupplyDataService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MaterialSupplyDataServiceImpl implements MaterialSupplyDataService {
    @Autowired
    private MaterialSupplyDataDao materialSupplyDataDao;
    @Autowired
    private MaterialEngineeringDataDao materialEngineeringDataDao;
    @Autowired
    private VendorCodeDao vendorCodeDao;
    @Autowired
    private QuoteTermDao quoteTermDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 新增数据
     * @param materialSupplyData
     * @return
     */
    @Transactional
    @Override
    public boolean insertMaterialSupplyData(MaterialSupplyData materialSupplyData){
        if(materialSupplyDataDao.queryMaterialSupplyDataForExist(materialSupplyData)!=0){
            throw new GlobalException("exist");
        }
        if(materialSupplyDataDao.insertMaterialSupplyData(materialSupplyData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 删除数据
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteMaterialSupplyData(Integer[] Id){
        if(materialSupplyDataDao.deleteMaterialSupplyData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param materialSupplyData
     * @return
     */
    @Transactional
    @Override
    public boolean updateMaterialSupplyData(MaterialSupplyData materialSupplyData) {
        if(materialSupplyDataDao.queryMaterialSupplyDataForAllExist(materialSupplyData)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(materialSupplyData.getMaterial_Number().trim().equals(materialSupplyData.getOld_Material_Number().trim()) &&
            materialSupplyData.getVersion().trim().equals(materialSupplyData.getOld_Version().trim()) &&
            materialSupplyData.getBrand().trim().equals(materialSupplyData.getOld_Brand().trim()) &&
            materialSupplyData.getVendor_Code().trim().equals(materialSupplyData.getOld_Vendor_Code().trim())){
                if(materialSupplyDataDao.updateMaterialSupplyData(materialSupplyData)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(materialSupplyDataDao.queryMaterialSupplyDataForExist(materialSupplyData)!=0){
                    throw new GlobalException("exist");
                }
                if(materialSupplyDataDao.updateMaterialSupplyData(materialSupplyData)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param materialSupplyDataPageModel
     * @param materialSupplyData
     * @return
     */
    @Override
    public void queryMaterialSupplyDataPage(PageModel<MaterialSupplyData> materialSupplyDataPageModel, MaterialSupplyData materialSupplyData) throws Exception{
        List<MaterialSupplyData> list=materialSupplyDataDao.queryMaterialSupplyDataList(materialSupplyDataPageModel, materialSupplyData);
        for(int i=0,length=list.size();i<length;i++){
            if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                list.get(i).setStatus("Y");
            }else {
                list.get(i).setStatus("N");
            }
        }
        materialSupplyDataPageModel.setList(list);
        materialSupplyDataPageModel.setTotal(materialSupplyDataDao.queryMaterialSupplyDataCount(materialSupplyDataPageModel, materialSupplyData));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public MaterialSupplyData queryMaterialSupplyDataById(Integer Id) {
        return materialSupplyDataDao.queryMaterialSupplyDataById(Id);
    }

    /**
     * for download all
     * @param materialSupplyData
     * @return
     */
    @Override
    public List<MaterialSupplyData> queryMaterialSupplyDataForDownLoadAll(MaterialSupplyData materialSupplyData) {
        return materialSupplyDataDao.queryMaterialSupplyDataForDownLoadAll(materialSupplyData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<MaterialSupplyData> queryMaterialSupplyDataForDownLoad(Integer Id[]) {
        return materialSupplyDataDao.queryMaterialSupplyDataForDownLoad(Id);
    }

    /**
     * 上传文件
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean uploadMaterialSupplyData(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        MaterialSupplyData materialSupplyData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(2)==null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(4)==null || sheet.getRow(i).getCell(4).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(5)==null || sheet.getRow(i).getCell(5).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(6)==null || sheet.getRow(i).getCell(6).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(7)==null || sheet.getRow(i).getCell(7).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(8)==null || sheet.getRow(i).getCell(8).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(9)==null || sheet.getRow(i).getCell(9).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(10)==null || sheet.getRow(i).getCell(10).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(11)==null || sheet.getRow(i).getCell(11).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(12)==null || sheet.getRow(i).getCell(12).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(13)==null || sheet.getRow(i).getCell(13).getStringCellValue().trim().length()==0){
                throw new GlobalException("blank:"+(i+1));//代表必填项没填
            }
            String Material_Number=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Version=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            String Brand=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            String Vendor_Code=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            String Deliver_Term=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            String Payment_Term=sheet.getRow(i).getCell(7).getStringCellValue().trim();
            String Receive_Term=sheet.getRow(i).getCell(8).getStringCellValue().trim();
            String Force_Time=sheet.getRow(i).getCell(10).getStringCellValue().trim();
            String Failure_Time=sheet.getRow(i).getCell(11).getStringCellValue().trim();
            String Purchase=sheet.getRow(i).getCell(12).getStringCellValue().trim();
            if(materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumber(Material_Number).size()==0){
                throw new GlobalException("material_Number:"+(i+1));//代表material_Number填写错误
            }
            if(materialEngineeringDataDao.queryMaterialEngineeringDataByVersion(Material_Number,Version)==0){
                throw new GlobalException("version:"+(i+1));//代表version填写错误
            }
            if(materialEngineeringDataDao.queryMaterialEngineeringDataForUploadBrand(Material_Number,Version,Brand)==0){
                throw new GlobalException("brand:"+(i+1));//代表brand填写错误
            }
            if(vendorCodeDao.queryVendorCodeForUploadVendorCode(Brand,Vendor_Code)==0){
                throw new GlobalException("vendor_Code:"+(i+1));//代表vendor_Code填写错误
            }
            if(quoteTermDao.queryQuoteTermForUploadDeliverTerm(Deliver_Term)==0){
                throw new GlobalException("deliver_Term:"+(i+1));//代表deliver_Term填写错误
            }
            if(quoteTermDao.queryQuoteTermForUploadPaymentTerm(Payment_Term)==0){
                throw new GlobalException("payment_Term:"+(i+1));//代表payment_Term填写错误
            }
            if(quoteTermDao.queryQuoteTermForUploadReceiveTerm(Receive_Term)==0){
                throw new GlobalException("receive_Term:"+(i+1));//代表receive_Term填写错误
            }
            if(userRoleDao.queryUserRoleForUploadRole("PC",Purchase)==0){
                throw new GlobalException("purchase:"+(i+1));//代表purchase填写错误
            }
            materialSupplyData=new MaterialSupplyData();
            materialSupplyData.setMaterial_Number(Material_Number);
            materialSupplyData.setVersion(Version);
            materialSupplyData.setBrand(Brand);
            materialSupplyData.setVendor_Code(Vendor_Code);
            materialSupplyData.setMinimum_Lower_Unit_Quantity(Integer.parseInt(sheet.getRow(i).getCell(4).getStringCellValue().trim()));
            materialSupplyData.setPurchasing_Lead_Days(Double.parseDouble(sheet.getRow(i).getCell(5).getStringCellValue()));
            materialSupplyData.setDeliver_Term(Deliver_Term);
            materialSupplyData.setPayment_Term(Payment_Term);
            materialSupplyData.setReceive_Term(Receive_Term);
            materialSupplyData.setSupplier_Warranty_Period_Month(Double.parseDouble(sheet.getRow(i).getCell(9).getStringCellValue()));
            materialSupplyData.setForce_Time(Force_Time);
            materialSupplyData.setFailure_Time(Failure_Time);
            materialSupplyData.setPurchase(Purchase);
            if(sheet.getRow(i).getCell(13)!=null){
                materialSupplyData.setRemark(sheet.getRow(i).getCell(13).getStringCellValue().trim());
            }else {
                materialSupplyData.setRemark("");
            }
            materialSupplyData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            materialSupplyData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(materialSupplyDataDao.queryMaterialSupplyDataForExist(materialSupplyData)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            if(materialSupplyDataDao.insertMaterialSupplyData(materialSupplyData)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }

}
