package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.CRM.CustomerEquipmentDataDao;
import hwkj.hwkj.dao.CRM.CustomerFactoryAddressDao;
import hwkj.hwkj.dao.User.UserRoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerEquipmentDataService;
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
public class CustomerEquipmentDataServiceImpl implements CustomerEquipmentDataService {
    private static final String Production_Date_Regex="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";
    private static final String SN_Regex="^[a-zA-Z0-9]+$";
    private static final String Quantity_Regex="^[0-9]$|^[1-9][0-9]+$";
    private static final String Operation_Rate_Regex="^100$|^([0-9]|[1-9][0-9])([.][0-9])$|^([0-9]|[1-9][0-9])([.][0-9]{2})*$";
    @Autowired
    private CustomerEquipmentDataDao customerEquipmentDataDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;
    @Autowired
    private CustomerFactoryAddressDao customerFactoryAddressDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 新增数据
     * @param customerEquipmentData
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerEquipmentData(CustomerEquipmentData customerEquipmentData){
        customerEquipmentData.setOperation_Rate(Double.parseDouble(customerEquipmentData.getNew_Operation_Rate().trim()));
        if(customerEquipmentDataDao.queryCustomerEquipmentDataForAllExist(customerEquipmentData)!=0){
            throw new GlobalException("exist");
        }
        if(customerEquipmentDataDao.insertCustomerEquipmentData(customerEquipmentData)<=0){
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
    public boolean deleteCustomerEquipmentData(Integer Id[]){
        if(customerEquipmentDataDao.deleteCustomerEquipmentData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerEquipmentData
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerEquipmentData(CustomerEquipmentData customerEquipmentData){
        customerEquipmentData.setOperation_Rate(Double.parseDouble(customerEquipmentData.getNew_Operation_Rate().trim()));
        if(customerEquipmentDataDao.queryCustomerEquipmentDataForAllExist(customerEquipmentData)!=0){
            throw new GlobalException("NoUpdateOrExist");
        }
        if(customerEquipmentDataDao.updateCustomerEquipmentData(customerEquipmentData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     */
    @Override
    public void queryCustomerEquipmentDataPage(PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, CustomerEquipmentData customerEquipmentData) {
        List<CustomerEquipmentData> list=customerEquipmentDataDao.queryCustomerEquipmentDataList(customerEquipmentDataPageModel,customerEquipmentData);
        for(int i=0,length=list.size();i<length;i++){
            list.get(i).setNew_Operation_Rate(list.get(i).getOperation_Rate()+"%");
        }
        customerEquipmentDataPageModel.setList(list);
        customerEquipmentDataPageModel.setTotal(customerEquipmentDataDao.queryCustomerEquipmentDataCount(customerEquipmentDataPageModel,customerEquipmentData));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerEquipmentData queryCustomerEquipmentDataById(Integer Id) {
        return customerEquipmentDataDao.queryCustomerEquipmentDataById(Id);
    }

    /**
     * for download all
     * @param customerEquipmentData
     * @return
     */
    @Override
    public List<CustomerEquipmentData> queryCustomerEquipmentDataForDownLoadAll(CustomerEquipmentData customerEquipmentData) {
        return customerEquipmentDataDao.queryCustomerEquipmentDataForDownLoadAll(customerEquipmentData);
    }

    /**
     * for dowmload
     * @param Id
     * @return
     */
    @Override
    public List<CustomerEquipmentData> queryCustomerEquipmentDataForDownLoad(Integer Id[]) {
        return customerEquipmentDataDao.queryCustomerEquipmentDataForDownLoad(Id);
    }

    /**
     * 维修和保养记录的分页查询
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     */
    @Override
    public void queryMaintenanceAndMaintenanceRecordsDataPage(PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, CustomerEquipmentData customerEquipmentData) {
        customerEquipmentDataPageModel.setList(customerEquipmentDataDao.queryMaintenanceAndMaintenanceRecordsDataList(customerEquipmentDataPageModel, customerEquipmentData));
        customerEquipmentDataPageModel.setTotal(customerEquipmentDataDao.queryMaintenanceAndMaintenanceRecordsDataCount(customerEquipmentDataPageModel, customerEquipmentData));
    }

    /**
     * download 维修和保养记录
     * @param Id
     * @return
     */
    @Override
    public List<CustomerEquipmentData> queryMaintenanceAndMaintenanceRecordsDataDownLoad(Integer Id[]) {
        return customerEquipmentDataDao.queryMaintenanceAndMaintenanceRecordsDataDownLoad(Id);
    }

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean uploadCustomerEquipmentData(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        CustomerEquipmentData customerEquipmentData = null;
        for (int i = 1, length = sheet.getLastRowNum(); i <= length; i++) {
            for (int j = 0; j < 14; j++) {
                if(10==j || 11==j){
                    continue;
                }
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Customer_Code = sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if (customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code) == 0) {
                throw new GlobalException("customer_Code:" + (i + 1));//代表customer_Code填写错误
            }
            String Factory_Address=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(customerFactoryAddressDao.queryCustomerFactoryAddressForUploadFactoryAddress(Customer_Code,Factory_Address)==0){
                throw new GlobalException("factory_Address:" + (i + 1));//代表factory_Address填写错误
            }
            String Production_Date=sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if(!Production_Date.matches(Production_Date_Regex)){
                throw new GlobalException("production_Date:" + (i + 1));
            }
            String SN=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            if(!SN.matches(SN_Regex)){
                throw new GlobalException("sn:" + (i + 1));
            }
            String Quantity=sheet.getRow(i).getCell(7).getStringCellValue().trim();
            if(!Quantity.matches(Quantity_Regex)){
                throw new GlobalException("quantity:" + (i + 1));
            }
            String Operation_Rate=sheet.getRow(i).getCell(9).getStringCellValue().trim();
            if(!Operation_Rate.matches(Operation_Rate_Regex)){
                throw new GlobalException("operation_Rate:" + (i + 1));
            }
            String BPM=sheet.getRow(i).getCell(12).getStringCellValue().trim();
            if(userRoleDao.queryUserRoleForUploadRole("BPM",BPM)==0){
                throw new GlobalException("bpm:"+(i+1));//代表bpm填写错误
            }
            String Engineer=sheet.getRow(i).getCell(13).getStringCellValue().trim();
            if(userRoleDao.queryUserRoleForUploadRole("ENG",Engineer)==0){
                throw new GlobalException("eng:"+(i+1));//代表eng填写错误
            }
            customerEquipmentData=new CustomerEquipmentData();
            customerEquipmentData.setCustomer_Code(Customer_Code);
            customerEquipmentData.setFactory_Address(Factory_Address);
            customerEquipmentData.setEquipment_Name(sheet.getRow(i).getCell(2).getStringCellValue().trim());
            customerEquipmentData.setBrand(sheet.getRow(i).getCell(3).getStringCellValue().trim());
            customerEquipmentData.setModel_Number(sheet.getRow(i).getCell(4).getStringCellValue().trim());
            customerEquipmentData.setProduction_Date(Production_Date);
            customerEquipmentData.setSN(SN);
            customerEquipmentData.setQuantity(Integer.parseInt(Quantity));
            customerEquipmentData.setProducts(sheet.getRow(i).getCell(8).getStringCellValue().trim());
            customerEquipmentData.setOperation_Rate(Double.parseDouble(Operation_Rate));
            if(sheet.getRow(i).getCell(10)!=null){
                customerEquipmentData.setMaintenance_Record(sheet.getRow(i).getCell(10).getStringCellValue().trim());
            }else {
                customerEquipmentData.setMaintenance_Record("");
            }
            if(sheet.getRow(i).getCell(11)!=null){
                customerEquipmentData.setRepair_Recode(sheet.getRow(i).getCell(11).getStringCellValue().trim());
            }else {
                customerEquipmentData.setRepair_Recode("");
            }
            customerEquipmentData.setBPM(BPM);
            customerEquipmentData.setEngineer(Engineer);
            customerEquipmentData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerEquipmentData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerEquipmentDataDao.queryCustomerEquipmentDataForAllExist(customerEquipmentData)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            if(customerEquipmentDataDao.insertCustomerEquipmentData(customerEquipmentData)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }

}
