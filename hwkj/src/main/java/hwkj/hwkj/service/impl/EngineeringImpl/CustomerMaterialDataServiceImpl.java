package hwkj.hwkj.service.impl.EngineeringImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.Engineering.CustomerMaterialDataDao;
import hwkj.hwkj.dao.Engineering.MaterialEngineeringDataDao;
import hwkj.hwkj.entity.Engineering.CustomerMaterialData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.CustomerMaterialDataService;
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
public class CustomerMaterialDataServiceImpl implements CustomerMaterialDataService {
    @Autowired
    private CustomerMaterialDataDao customerMaterialDataDao;
    @Autowired
    private MaterialEngineeringDataDao materialEngineeringDataDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;

    /**
     * 新增数据
     * @param customerMaterialData
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerMaterialData(CustomerMaterialData customerMaterialData){
        if(customerMaterialDataDao.queryCustomerMaterialDataForExist(customerMaterialData)!=0){
            throw new GlobalException("exist");
        }
        if(customerMaterialDataDao.insertCustomerMaterialData(customerMaterialData)<=0){
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
    public boolean deleteCustomerMaterialData(Integer Id[]){
        if(customerMaterialDataDao.deleteCustomerMaterialData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerMaterialData
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerMaterialData(CustomerMaterialData customerMaterialData){
        if(customerMaterialDataDao.queryCustomerMaterialDataForAllExist(customerMaterialData)!=0){
            throw new GlobalException("NoUpdate");//表示没更新
        }else {
            if(customerMaterialData.getCustomer_Abbreviation().trim().equals(customerMaterialData.getOld_Customer_Abbreviation().trim()) &&
                    customerMaterialData.getCustomer_Material_Number().trim().equals(customerMaterialData.getOld_Material_Number().trim()) &&
                    customerMaterialData.getCustomer_Version().trim().equals(customerMaterialData.getOld_Customer_Version().trim()) &&
                    customerMaterialData.getMaterial_Number().trim().equals(customerMaterialData.getOld_Material_Number().trim()) &&
                    customerMaterialData.getVersion().trim().equals(customerMaterialData.getOld_Version().trim()) &&
                    customerMaterialData.getManufacturer_Abbreviation().trim().equals(customerMaterialData.getOld_Manufacturer_Abbreviation().trim()) &&
                    customerMaterialData.getManufacturer_Material_Number().trim().equals(customerMaterialData.getOld_Manufacturer_Material_Number().trim())){
                if(customerMaterialDataDao.updateCustomerMaterialData(customerMaterialData)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(customerMaterialDataDao.queryCustomerMaterialDataForExist(customerMaterialData)!=0){
                    throw new GlobalException("exist");
                }
                if(customerMaterialDataDao.updateCustomerMaterialData(customerMaterialData)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerMaterialDataPageModel
     * @param customerMaterialData
     * @return
     */
    @Override
    public void queryCustomerMaterialDataPage(PageModel<CustomerMaterialData> customerMaterialDataPageModel, CustomerMaterialData customerMaterialData) throws GlobalException,Exception {
        List<CustomerMaterialData> list=customerMaterialDataDao.queryCustomerMaterialDataList(customerMaterialDataPageModel, customerMaterialData);
        for(int i=0,length=list.size();i<length;i++){
            if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getFailure_Time(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                list.get(i).setStatus("Y");
            }else {
                list.get(i).setStatus("N");
            }
        }
        customerMaterialDataPageModel.setList(list);
        customerMaterialDataPageModel.setTotal(customerMaterialDataDao.queryCustomerMaterialDataCount(customerMaterialDataPageModel, customerMaterialData));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerMaterialData queryCustomerMaterialDataById(Integer Id) {
        return customerMaterialDataDao.queryCustomerMaterialDataById(Id);
    }

    /**
     * for download all
     * @param customerMaterialData
     * @return
     */
    @Override
    public List<CustomerMaterialData> queryCustomerMaterialDataForDownLoadAll(CustomerMaterialData customerMaterialData) {
        return customerMaterialDataDao.queryCustomerMaterialDataForDownLoadAll(customerMaterialData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public CustomerMaterialData queryCustomerMaterialDataForDownLoad(Integer Id) {
        return customerMaterialDataDao.queryCustomerMaterialDataForDownLoad(Id);
    }

    /**
     * check customer_material_data 是否存在
     * @param customerMaterialData
     * @return
     */
    @Override
    public int queryCustomerMaterialDataForExist(CustomerMaterialData customerMaterialData) {
        return customerMaterialDataDao.queryCustomerMaterialDataForExist(customerMaterialData);
    }

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean customerMaterialDataUpload(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        CustomerMaterialData customerMaterialData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(4)==null || sheet.getRow(i).getCell(4).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(6)==null || sheet.getRow(i).getCell(6).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(7)==null || sheet.getRow(i).getCell(7).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(8)==null || sheet.getRow(i).getCell(8).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(9)==null || sheet.getRow(i).getCell(9).getStringCellValue().trim().length()==0){
                throw new GlobalException("blank:"+(i+1));//代表必填项没填
            }
            String Material_Number=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            String Version=sheet.getRow(i).getCell(4).getStringCellValue().trim();
            String Manufacturer_Abbreviation=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            String Manufacturer_Material_Number=sheet.getRow(i).getCell(7).getStringCellValue().trim();
            if(materialEngineeringDataDao.queryMaterialEngineeringDataByMaterialNumber(Material_Number).size()==0){
                throw new GlobalException("material_Number:"+(i+1));//代表material_Number填写错误
            }
            if(materialEngineeringDataDao.queryMaterialEngineeringDataByVersion(Material_Number,Version)==0){
                throw new GlobalException("version:"+(i+1));//代表version填写错误
            }
            if(materialEngineeringDataDao.queryMaterialEngineeringDataByManufacturerAbbreviation(Material_Number,Version,Manufacturer_Abbreviation)==0){
                throw new GlobalException("manufacturer_Abbreviation:"+(i+1));//代表manufacturer_Abbreviation填写错误
            }
            if(materialEngineeringDataDao.queryMaterialEngineeringDataByManufacturerMaterialNumber(Material_Number,Version,Manufacturer_Abbreviation,Manufacturer_Material_Number)==0){
                throw new GlobalException("manufacturer_Material_Number:"+(i+1));//代表manufacturer_Material_Number填写错误
            }
            customerMaterialData=new CustomerMaterialData();
            customerMaterialData.setCustomer_Abbreviation(sheet.getRow(i).getCell(0).getStringCellValue());
            customerMaterialData.setCustomer_Material_Number(sheet.getRow(i).getCell(1).getStringCellValue());
            if(sheet.getRow(i).getCell(2)!=null){
                customerMaterialData.setCustomer_Version(sheet.getRow(i).getCell(2).getStringCellValue());
            }else {
                customerMaterialData.setCustomer_Version("");
            }
            customerMaterialData.setMaterial_Number(Material_Number);
            customerMaterialData.setVersion(sheet.getRow(i).getCell(4).getStringCellValue());
            if(sheet.getRow(i).getCell(5)!=null){
                customerMaterialData.setCustomer_Described(sheet.getRow(i).getCell(5).getStringCellValue());
            }else {
                customerMaterialData.setCustomer_Described("");
            }
            customerMaterialData.setManufacturer_Abbreviation(Manufacturer_Abbreviation);
            customerMaterialData.setManufacturer_Material_Number(Manufacturer_Material_Number);
            customerMaterialData.setForce_Time(sheet.getRow(i).getCell(8).getStringCellValue());
            customerMaterialData.setFailure_Time(sheet.getRow(i).getCell(9).getStringCellValue());
            if(sheet.getRow(i).getCell(10)!=null){
                customerMaterialData.setRemark(sheet.getRow(i).getCell(10).getStringCellValue());
            }else {
                customerMaterialData.setRemark("");
            }
            customerMaterialData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerMaterialData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerMaterialDataDao.queryCustomerMaterialDataForExist(customerMaterialData)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            if(customerMaterialDataDao.insertCustomerMaterialData(customerMaterialData)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }
}
