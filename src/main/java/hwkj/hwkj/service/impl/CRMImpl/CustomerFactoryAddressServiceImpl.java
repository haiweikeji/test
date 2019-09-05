package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.CRM.CustomerEquipmentDataDao;
import hwkj.hwkj.dao.CRM.CustomerFactoryAddressDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerFactoryAddressService;
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
public class CustomerFactoryAddressServiceImpl implements CustomerFactoryAddressService {
    @Autowired
    private CustomerFactoryAddressDao customerFactoryAddressDao;
    @Autowired
    private CustomerEquipmentDataDao customerEquipmentDataDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;

    /**
     * 新增数据
     * @param customerFactoryAddress
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerFactoryAddress(CustomerFactoryAddress customerFactoryAddress){
        String Factory_Code="";
        String Customer_Code=customerFactoryAddress.getCustomer_Code();
        String Factory_Address=customerFactoryAddress.getFactory_Address();
        if(customerFactoryAddressDao.queryCustomerFactoryAddressDataExist(Customer_Code,Factory_Address)!=0){
            throw new GlobalException("exist");
        }
        CustomerFactoryAddress customerFactoryAddressAdd=customerFactoryAddressDao.queryCustomerFactoryAddressDataLastData(Customer_Code);
        if(customerFactoryAddressAdd==null){
            Factory_Code=Customer_Code+"01";
        }else{
            int count=Integer.parseInt(customerFactoryAddressAdd.getFactory_Code().substring(7));
            if(count>0 && count<9){
                Factory_Code=Customer_Code+"0"+(count+1);
            }
            else {
                Factory_Code=Customer_Code+(count+1);
            }
        }
        customerFactoryAddress.setFactory_Code(Factory_Code);
        if(customerFactoryAddressDao.insertCustomerFactoryAddress(customerFactoryAddress)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 删除数据
     * @param id
     * @param Customer_Code
     * @param Factory_Address
     * @return
     */
    @Transactional
    @Override
    public boolean deleteCustomerFactoryAddress(Integer[] id, String[] Customer_Code, String[] Factory_Address) {
        for(int i=0,length=id.length;i<length;i++){
            if(customerEquipmentDataDao.queryCustomerEquipmentDataByFactoryAddress(Customer_Code[i],Factory_Address[i])!=0){
                throw new GlobalException("used"+(i+1));
            }
        }
        if(customerFactoryAddressDao.deleteCustomerFactoryAddress(id)!=id.length){
            throw new GlobalException("error");
        }
        return true;
    }


    /**
     * 更新数据
     * @param customerFactoryAddress
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerFactoryAddress(CustomerFactoryAddress customerFactoryAddress){
        String Customer_Code=customerFactoryAddress.getCustomer_Code().trim();
        String Factory_Address=customerFactoryAddress.getFactory_Address().trim();
        String Old_Customer_Code=customerFactoryAddress.getOld_Customer_Code().trim();
        String Old_Factory_Address=customerFactoryAddress.getOld_Factory_Address().trim();
        if(Customer_Code.equals(Old_Customer_Code) && Factory_Address.equals(Old_Factory_Address)){
            throw new GlobalException("NoUpdate");
        }else {
            if(customerEquipmentDataDao.queryCustomerEquipmentDataByFactoryAddress(Old_Customer_Code,Old_Factory_Address)!=0){
                throw new GlobalException("used");
            }
            if(customerFactoryAddressDao.queryCustomerFactoryAddressDataExist(Customer_Code,Factory_Address)!=0){
                throw new GlobalException("exist");
            }
            if(customerFactoryAddressDao.updateCustomerFactoryAddress(customerFactoryAddress)<=0){
                throw new GlobalException("error");
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerFactoryAddressPageModel
     * @param customerFactoryAddress
     */
    @Override
    public void queryCustomerFactoryAddressPage(PageModel<CustomerFactoryAddress> customerFactoryAddressPageModel, CustomerFactoryAddress customerFactoryAddress) {
        customerFactoryAddressPageModel.setList(customerFactoryAddressDao.queryCustomerFactoryAddressList(customerFactoryAddressPageModel,customerFactoryAddress));
        customerFactoryAddressPageModel.setTotal(customerFactoryAddressDao.queryCustomerFactoryAddressCount(customerFactoryAddressPageModel,customerFactoryAddress));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerFactoryAddress queryCustomerFactoryAddressById(Integer Id) {
        return customerFactoryAddressDao.queryCustomerFactoryAddressById(Id);
    }

    /**
     * for download all
     * @param customerFactoryAddress
     * @return
     */
    @Override
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForDownLoadAll(CustomerFactoryAddress customerFactoryAddress) {
        return customerFactoryAddressDao.queryCustomerFactoryAddressForDownLoadAll(customerFactoryAddress);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForDownLoad(Integer Id[]) {
        return customerFactoryAddressDao.queryCustomerFactoryAddressForDownLoad(Id);
    }

    /**
     * by Customer_Code查询出factory_address
     * @param Customer_Code
     * @return
     */
    @Override
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForFactoryAddressByCustomerCode(String Customer_Code) {
        return customerFactoryAddressDao.queryCustomerFactoryAddressForFactoryAddressByCustomerCode(Customer_Code);
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
    public boolean uploadCustomerFactoryAddress(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        CustomerFactoryAddress customerFactoryAddress = null;
        for (int i = 1, length = sheet.getLastRowNum(); i <= length; i++) {
            for (int j = 0;j<2; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Customer_Code = sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if (customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code) == 0) {
                throw new GlobalException("customer_Code:" + (i + 1));//代表customer_Code填写错误
            }
            String Factory_Address = sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(customerFactoryAddressDao.queryCustomerFactoryAddressDataExist(Customer_Code,Factory_Address)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            CustomerFactoryAddress customerFactoryAddressUpload=customerFactoryAddressDao.queryCustomerFactoryAddressDataLastData(Customer_Code);
            String Factory_Code="";
            if(customerFactoryAddressUpload==null){
                Factory_Code=Customer_Code+"01";
            }else{
                int count=Integer.parseInt(customerFactoryAddressUpload.getFactory_Code().substring(7));
                if(count>0 && count<9){
                    Factory_Code=Customer_Code+"0"+(count+1);
                }
                else {
                    Factory_Code=Customer_Code+(count+1);
                }
            }
            customerFactoryAddress=new CustomerFactoryAddress();
            customerFactoryAddress.setCustomer_Code(Customer_Code);
            customerFactoryAddress.setFactory_Address(Factory_Address);
            customerFactoryAddress.setFactory_Code(Factory_Code);
            customerFactoryAddress.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerFactoryAddress.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerFactoryAddressDao.insertCustomerFactoryAddress(customerFactoryAddress)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }
}
