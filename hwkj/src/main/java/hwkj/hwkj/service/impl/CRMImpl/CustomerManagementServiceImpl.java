package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.CRM.CustomerManagementDao;
import hwkj.hwkj.entity.CRM.CustomerManagement;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerManagementService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {
    @Autowired
    private CustomerManagementDao customerManagementDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;

    /**
     * 新增数据
     * @param customerManagement
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerManagement(CustomerManagement customerManagement){
        if(customerManagementDao.queryCustomerManagementForAllExist(customerManagement)!=0){
            throw new GlobalException("exist");
        }
        if(customerManagementDao.insertCustomerManagement(customerManagement)<=0){
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
    public boolean deleteCustomerManagement(Integer Id[]){
        if(customerManagementDao.deleteCustomerManagement(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerManagement
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerManagement(CustomerManagement customerManagement){
        if(customerManagementDao.queryCustomerManagementForAllExist(customerManagement)!=0){
            throw new GlobalException("NoUpdateOrExist");
        }
        if(customerManagementDao.updateCustomerManagement(customerManagement)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerManagementPageModel
     * @param customerManagement
     */
    @Override
    public void queryCustomerManagementPage(PageModel<CustomerManagement> customerManagementPageModel, CustomerManagement customerManagement) {
        customerManagementPageModel.setList(customerManagementDao.queryCustomerManagementList(customerManagementPageModel, customerManagement));
        customerManagementPageModel.setTotal(customerManagementDao.queryCustomerManagementCount(customerManagementPageModel, customerManagement));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerManagement queryCustomerManagementById(Integer Id) {
        return customerManagementDao.queryCustomerManagementById(Id);
    }

    /**
     * for download all
     * @param customerManagement
     * @return
     */
    @Override
    public List<CustomerManagement> queryCustomerManagementForDownLoadAll(CustomerManagement customerManagement) {
        return customerManagementDao.queryCustomerManagementForDownLoadAll(customerManagement);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerManagement> queryCustomerManagementForDownLoad(Integer Id[]) {
        return customerManagementDao.queryCustomerManagementForDownLoad(Id);
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
    public boolean uploadCustomerManagement(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        int year=Calendar.getInstance().get(Calendar.YEAR);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        CustomerManagement customerManagement = null;
        for (int i = 1, length = sheet.getLastRowNum(); i <= length; i++) {
            for (int j = 0; j < 7; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Customer_Code = sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if (customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code) == 0) {
                throw new GlobalException("customer_Code:" + (i + 1));//代表customer_Code填写错误
            }
            String Year_Regex="^[1-9][0-9]{3}$";
            String Year=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(!Year.matches(Year_Regex)){
                throw new GlobalException("year:"+(i+1));
            }
            if(Integer.parseInt(Year)<1970 || Integer.parseInt(Year)>year){
                throw new GlobalException("between_Year:"+(i+1)+","+year);
            }
            String Quarter = sheet.getRow(i).getCell(2).getStringCellValue().trim();
            if(!("第一季度".equals(Quarter) || "第二季度".equals(Quarter) || "第三季度".equals(Quarter) || "第四季度".equals(Quarter))){
                throw new GlobalException("quarter:"+(i+1));
            }
            String Revenue_Regex="^[0-9]+$|^([0-9]+)([.][0-9])$|^([0-9]+)([.][0-9]{2})$";
            String Revenue = sheet.getRow(i).getCell(3).getStringCellValue().trim();
            if(!Revenue.matches(Revenue_Regex)){
                throw new GlobalException("revenue:"+(i+1));
            }
            String Overdue_Amount_Regex="^[0-9]+$|^([0-9]+)([.][0-9])$|^([0-9]+)([.][0-9]{2})$";
            String Overdue_Amount = sheet.getRow(i).getCell(4).getStringCellValue().trim();
            if(!Overdue_Amount.matches(Overdue_Amount_Regex)){
                throw new GlobalException("overdue_Amount:"+(i+1));
            }
            String Score_Regex="^10$|^([0-9])([.][0-9])*$";
            String Score=sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if(!Score.matches(Score_Regex)){
                throw new GlobalException("score:"+(i+1));
            }
            customerManagement=new CustomerManagement();
            customerManagement.setCustomer_Code(Customer_Code);
            customerManagement.setYear(Year);
            customerManagement.setQuarter(Quarter);
            customerManagement.setRevenue(Double.parseDouble(Revenue));
            customerManagement.setOverdue_Amount(Double.parseDouble(Overdue_Amount));
            customerManagement.setScore(Score);
            customerManagement.setImportant_Item(sheet.getRow(i).getCell(6).getStringCellValue().trim());
            customerManagement.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerManagement.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerManagementDao.queryCustomerManagementForAllExist(customerManagement)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            if(customerManagementDao.insertCustomerManagement(customerManagement)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }
}
