package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.CRM.CustomerStrategyAndSupportDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerStrategyAndSupportService;
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
public class CustomerStrategyAndSupportServiceImpl implements CustomerStrategyAndSupportService {
    @Autowired
    private CustomerStrategyAndSupportDao customerStrategyAndSupportDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;

    /**
     * 新增数据
     * @param customerStrategyAndSupport
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerStrategyAndSupport(CustomerStrategyAndSupport customerStrategyAndSupport){
        customerStrategyAndSupport.setAnnual_Revenue(Double.parseDouble(customerStrategyAndSupport.getNew_Annual_Revenue().trim()));
        if(customerStrategyAndSupportDao.queryCustomerStrategyAndSupportForAllExist(customerStrategyAndSupport)!=0){
            throw new GlobalException("exist");
        }
        if(customerStrategyAndSupportDao.insertCustomerStrategyAndSupport(customerStrategyAndSupport)<=0){
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
    public boolean deleteCustomerStrategyAndSupport(Integer Id[]){
        if(customerStrategyAndSupportDao.deleteCustomerStrategyAndSupport(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerStrategyAndSupport
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerStrategyAndSupport(CustomerStrategyAndSupport customerStrategyAndSupport){
        customerStrategyAndSupport.setAnnual_Revenue(Double.parseDouble(customerStrategyAndSupport.getNew_Annual_Revenue().trim()));
        if(customerStrategyAndSupportDao.queryCustomerStrategyAndSupportForAllExist(customerStrategyAndSupport)!=0){
            throw new GlobalException("NoUpdateOrExist");
        }
        if(customerStrategyAndSupportDao.updateCustomerStrategyAndSupport(customerStrategyAndSupport)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerStrategyAndSupportPageModel
     * @param customerStrategyAndSupport
     * @return
     */
    @Override
    public void queryCustomerStrategyAndSupportPage(PageModel<CustomerStrategyAndSupport> customerStrategyAndSupportPageModel, CustomerStrategyAndSupport customerStrategyAndSupport) {
        List<CustomerStrategyAndSupport> list=customerStrategyAndSupportDao.queryCustomerStrategyAndSupportList(customerStrategyAndSupportPageModel, customerStrategyAndSupport);
        for(int i=0,length=list.size();i<length;i++){
            list.get(i).setNew_Annual_Revenue(list.get(i).getAnnual_Revenue()+"%");
        }
        customerStrategyAndSupportPageModel.setList(list);
        customerStrategyAndSupportPageModel.setTotal(customerStrategyAndSupportDao.queryCustomerStrategyAndSupportCount(customerStrategyAndSupportPageModel, customerStrategyAndSupport));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerStrategyAndSupport queryCustomerStrategyAndSupportById(Integer Id) {
        return customerStrategyAndSupportDao.queryCustomerStrategyAndSupportById(Id);
    }

    /**
     * for download all
     * @param customerStrategyAndSupport
     * @return
     */
    @Override
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportForDownLoadAll(CustomerStrategyAndSupport customerStrategyAndSupport) {
        return customerStrategyAndSupportDao.queryCustomerStrategyAndSupportForDownLoadAll(customerStrategyAndSupport);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportForDownLoad(Integer Id[]) {
        return customerStrategyAndSupportDao.queryCustomerStrategyAndSupportForDownLoad(Id);
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
    public boolean uploadCustomerStrategyAndSupport(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        CustomerStrategyAndSupport customerStrategyAndSupport = null;
        for (int i = 1, length = sheet.getLastRowNum(); i <= length; i++) {
            for (int j = 0; j < 9; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Customer_Code = sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if (customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code) == 0) {
                throw new GlobalException("customer_Code:" + (i + 1));//代表customer_Code填写错误
            }
            String Annual_Output_Regex = "^[1-9]+[0-9]+([,，][1-9]+[0-9]+)([,，][1-9]+[0-9]+)$";
            String Annual_Output = sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if (!Annual_Output.matches(Annual_Output_Regex)) {
                throw new GlobalException("annual_Output:"+(i + 1));
            }
            String Annual_Revenue_Regex = "^100$|^([0-9]|[1-9][0-9])([.][0-9])$|^([0-9]|[1-9][0-9])([.][0-9]{2})*$";
            String Annual_Revenue = sheet.getRow(i).getCell(6).getStringCellValue().trim();
            if (!Annual_Revenue.matches(Annual_Revenue_Regex)) {
                throw new GlobalException("annual_Revenue:"+(i + 1));
            }
            /*if(Annual_Revenue.length()==1 || Annual_Revenue.length()==2){
                Annual_Revenue=Annual_Revenue+".00%";
            }
            else if(Annual_Revenue.length()==3 && !Annual_Revenue.contains(".")){
                Annual_Revenue=Annual_Revenue+".00%";
            }
            else if(Annual_Revenue.length()==3 && Annual_Revenue.contains(".")){
                Annual_Revenue=Annual_Revenue+"0%";
            }
            else if(Annual_Revenue.length()==4){
                Annual_Revenue=Annual_Revenue+".0%";
            }*/
            //Annual_Revenue = Annual_Revenue + "%";
            customerStrategyAndSupport=new CustomerStrategyAndSupport();
            customerStrategyAndSupport.setCustomer_Code(Customer_Code);
            customerStrategyAndSupport.setDate_From(sheet.getRow(i).getCell(1).getStringCellValue().trim());
            customerStrategyAndSupport.setDate_To(sheet.getRow(i).getCell(2).getStringCellValue().trim());
            customerStrategyAndSupport.setTop_Three_Customer(sheet.getRow(i).getCell(3).getStringCellValue().trim());
            customerStrategyAndSupport.setTop_Three_Product(sheet.getRow(i).getCell(4).getStringCellValue().trim());
            customerStrategyAndSupport.setAnnual_Output(Annual_Output);
            customerStrategyAndSupport.setAnnual_Revenue(Double.parseDouble(Annual_Revenue));
            customerStrategyAndSupport.setBusiness_Strategy(sheet.getRow(i).getCell(7).getStringCellValue().trim());
            customerStrategyAndSupport.setSupport_Need(sheet.getRow(i).getCell(8).getStringCellValue().trim());
            customerStrategyAndSupport.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerStrategyAndSupport.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerStrategyAndSupportDao.queryCustomerStrategyAndSupportForAllExist(customerStrategyAndSupport)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            if(customerStrategyAndSupportDao.insertCustomerStrategyAndSupport(customerStrategyAndSupport)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }
}
