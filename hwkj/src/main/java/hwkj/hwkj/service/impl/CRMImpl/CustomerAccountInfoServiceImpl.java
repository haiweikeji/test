package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerAccountInfoDao;
import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerAccountInfoService;
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
public class CustomerAccountInfoServiceImpl implements CustomerAccountInfoService {

    private final static String Regex_Credit_Code="^[a-zA-Z0-9]+$";
    private final static String Regex_Taxpayer_Number="^[a-zA-Z0-9]+$";
    private final static String Regex_Registration_Number="^[a-zA-Z0-9]+$";
    private final static String Regex_Registration_Time="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";
    private final static String Regex_Registered_Capital="^[0-9]+$|^([0-9]+)([.][0-9])$|^([0-9]+)([.][0-9]{2})$";
    private final static String Regex_Credit_Amount="^[0-9]+$|^([0-9]+)([.][0-9])$|^([0-9]+)([.][0-9]{2})$";
    private final static String Regex_Bank_Account="^[0-9]{16}$|^[0-9]{19}$";
    private final static String Regex_Force_time="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";
    private final static String Regex_Failure_Time="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";

    @Autowired
    private CustomerAccountInfoDao customerAccountInfoDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;

    /**
     * 新增数
     * @param customerAccountInfo
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerAccountInfo(CustomerAccountInfo customerAccountInfo){
        if(customerAccountInfoDao.queryCustomerAccountInfoForExist(customerAccountInfo.getCustomer_Code())!=0){
            throw new GlobalException("exist");
        }
        if(customerAccountInfoDao.insertCustomerAccountInfo(customerAccountInfo)<=0){
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
    public boolean deleteCustomerAccountInfo(Integer Id[]){
        if(customerAccountInfoDao.deleteCustomerAccountInfo(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerAccountInfo
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerAccountInfo(CustomerAccountInfo customerAccountInfo){
        if(customerAccountInfoDao.queryCustomerAccountInfoForAllExist(customerAccountInfo)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(customerAccountInfo.getCustomer_Code().trim().equals(customerAccountInfo.getOld_Customer_Code().trim())){
                if(customerAccountInfoDao.updateCustomerAccountInfo(customerAccountInfo)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(customerAccountInfoDao.queryCustomerAccountInfoForExist(customerAccountInfo.getCustomer_Code())!=0){
                    throw new GlobalException("exist");
                }
                if(customerAccountInfoDao.updateCustomerAccountInfo(customerAccountInfo)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerAccountInfoPageModel
     * @param customerAccountInfo
     */
    @Override
    public void queryCustomerAccountInfoPage(PageModel<CustomerAccountInfo> customerAccountInfoPageModel, CustomerAccountInfo customerAccountInfo) {
        customerAccountInfoPageModel.setList(customerAccountInfoDao.queryCustomerAccountInfoList(customerAccountInfoPageModel,customerAccountInfo));
        customerAccountInfoPageModel.setTotal(customerAccountInfoDao.queryCustomerAccountInfoCount(customerAccountInfoPageModel,customerAccountInfo));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerAccountInfo queryCustomerAccountInfoById(Integer Id) {
        return customerAccountInfoDao.queryCustomerAccountInfoById(Id);
    }

    /**
     * for download all
     * @param customerAccountInfo
     * @return
     */
    @Override
    public List<CustomerAccountInfo> queryCustomerAccountInfoForDownLoadAll(CustomerAccountInfo customerAccountInfo) {
        return customerAccountInfoDao.queryCustomerAccountInfoForDownLoadAll(customerAccountInfo);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerAccountInfo> queryCustomerAccountInfoForDownLoad(Integer[] Id) {
        return customerAccountInfoDao.queryCustomerAccountInfoForDownLoad(Id);
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
    public boolean uploadCustomerAccountInfo(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        CustomerAccountInfo customerAccountInfo=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            for(int j=0;j<16;j++){
                if(sheet.getRow(i).getCell(j)==null){
                    throw new GlobalException("blank:"+(i+1));
                }
            }
            String Customer_Code=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if(customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code)==0){
                throw new GlobalException("customer_Code:"+(i+1));//代表customer_Code填写错误
            }
            if(customerAccountInfoDao.queryCustomerAccountInfoForExist(Customer_Code)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            String Credit_Code=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(!Credit_Code.matches(Regex_Credit_Code)){
                throw new GlobalException("credit_Code:"+(i+1));
            }
            String Taxpayer_Number=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            if(!Taxpayer_Number.matches(Regex_Taxpayer_Number)){
                throw new GlobalException("taxpayer_Number:"+(i+1));
            }
            String Registration_Number=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            if(!Registration_Number.matches(Regex_Registration_Number)){
                throw new GlobalException("registration_Number:"+(i+1));
            }
            String Registration_Time=sheet.getRow(i).getCell(4).getStringCellValue().trim();
            if(!Registration_Time.matches(Regex_Registration_Time)){
                throw new GlobalException("registration_Time:"+(i+1));
            }
            String Registered_Capital=sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if(!Registered_Capital.matches(Regex_Registered_Capital)){
                throw new GlobalException("registered_Capital:"+(i+1));
            }
            String Credit_Amount=sheet.getRow(i).getCell(8).getStringCellValue().trim();
            if(!Credit_Amount.matches(Regex_Credit_Amount)){
                throw new GlobalException("credit_Amount:"+(i+1));
            }
            String Bank_Account=sheet.getRow(i).getCell(11).getStringCellValue().trim();
            if(!Bank_Account.matches(Regex_Bank_Account)){
                throw new GlobalException("bank_Account:"+(i+1));
            }
            String Force_Time=sheet.getRow(i).getCell(14).getStringCellValue().trim();
            if(!Force_Time.matches(Regex_Force_time)){
                throw new GlobalException("force_Time:"+(i+1));
            }
            String Failure_Time=sheet.getRow(i).getCell(15).getStringCellValue().trim();
            if(!Failure_Time.matches(Regex_Failure_Time)){
                throw new GlobalException("failure_Time:"+(i+1));
            }
            customerAccountInfo=new CustomerAccountInfo();
            customerAccountInfo.setCustomer_Code(Customer_Code);
            customerAccountInfo.setCredit_Code(Credit_Code);
            customerAccountInfo.setTaxpayer_Number(Taxpayer_Number);
            customerAccountInfo.setRegistration_Number(Registration_Number);
            customerAccountInfo.setRegistration_Time(Registration_Time);
            customerAccountInfo.setRegistered_Capital(Double.parseDouble(Registered_Capital));
            customerAccountInfo.setCurrency(sheet.getRow(i).getCell(6).getStringCellValue().trim());
            customerAccountInfo.setCredit_Level(sheet.getRow(i).getCell(7).getStringCellValue().trim());
            customerAccountInfo.setCredit_Amount(Double.parseDouble(Credit_Amount));
            customerAccountInfo.setInvoice_Address(sheet.getRow(i).getCell(9).getStringCellValue().trim());
            customerAccountInfo.setBank(sheet.getRow(i).getCell(10).getStringCellValue().trim());
            customerAccountInfo.setBank_Account(Bank_Account);
            customerAccountInfo.setBank_Address(sheet.getRow(i).getCell(12).getStringCellValue().trim());
            customerAccountInfo.setAcceptable_Currency(sheet.getRow(i).getCell(13).getStringCellValue().trim());
            customerAccountInfo.setForce_Time(Force_Time);
            customerAccountInfo.setFailure_Time(Failure_Time);
            customerAccountInfo.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerAccountInfo.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerAccountInfoDao.insertCustomerAccountInfo(customerAccountInfo)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }

}
