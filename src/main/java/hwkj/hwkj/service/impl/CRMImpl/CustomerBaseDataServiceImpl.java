package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.Quote.QuoteTermDao;
import hwkj.hwkj.dao.User.UserRoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerBaseDataService;
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
public class CustomerBaseDataServiceImpl implements CustomerBaseDataService {
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;
    @Autowired
    private QuoteTermDao quoteTermDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 新增数据
     * @param customerBaseData
     * @return
     * @throws RuntimeException
     */
    @Transactional
    @Override
    public boolean insertCustomerBaseData(CustomerBaseData customerBaseData){
        String Customer_Code="";
        String Four_Initials=customerBaseData.getFour_Initials().trim().toUpperCase();
        if(Four_Initials.length()==0){
            Four_Initials=Four_Initials+"AAAA";
        }
        else if(Four_Initials.length()==1){
            Four_Initials=Four_Initials+"AAA";
        }
        else if(Four_Initials.length()==2){
            Four_Initials=Four_Initials+"AA";
        }
        else if(Four_Initials.length()==3){
            Four_Initials=Four_Initials+"A";
        }
        else {
            Four_Initials=Four_Initials.substring(0,4);
        }
        customerBaseData.setFour_Initials(Four_Initials);
        if(customerBaseDataDao.queryCustomerBaseDataForExist(customerBaseData)!=0){
            throw new GlobalException("exist");
        }
        CustomerBaseData customerBaseDataAdd=customerBaseDataDao.queryCustomerBaseDataLastData(Four_Initials);
        if(customerBaseDataAdd==null){
            Customer_Code=Four_Initials+"001";
        }
        else {
            int count=Integer.parseInt(customerBaseDataAdd.getCustomer_Code().substring(4));
            if(count>0 && count<9){
                Customer_Code=Four_Initials+"00"+(count+1);
            }
            else if(count>=9 && count<99){
                Customer_Code=Four_Initials+"0"+(count+1);
            }
            else {
                Customer_Code=Four_Initials+(count+1);
            }
        }
        customerBaseData.setCustomer_Code(Customer_Code);
        if(customerBaseDataDao.insertCustomerBaseData(customerBaseData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 删除数据
     * @param Id
     * @return
     * @throws RuntimeException
     */
    @Transactional
    @Override
    public boolean deleteCustomerBaseData(Integer Id[]){
        if(customerBaseDataDao.deleteCustomerBaseData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerBaseData
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerBaseData(CustomerBaseData customerBaseData){
        if(customerBaseData.getStatus()!=null && customerBaseData.getStatus().trim().length()!=0){
            if("待开发".equals(customerBaseData.getStatus().trim())){
                customerBaseData.setDevelopment_Date(null);
            }
        }
        if(customerBaseDataDao.updateCustomerBaseData(customerBaseData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerBaseDataPageModel
     * @param customerBaseData
     */
    @Override
    public void queryCustomerBaseDataPage(PageModel<CustomerBaseData> customerBaseDataPageModel, CustomerBaseData customerBaseData) {
        customerBaseDataPageModel.setList(customerBaseDataDao.queryCustomerBaseDataList(customerBaseDataPageModel,customerBaseData));
        customerBaseDataPageModel.setTotal(customerBaseDataDao.queryCustomerBaseDataCount(customerBaseDataPageModel,customerBaseData));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerBaseData queryCustomerBaseDataById(Integer Id) {
        return customerBaseDataDao.queryCustomerBaseDataById(Id);
    }

    /**
     * 查询CustomerBaseData 所有Customer_Code
     * @return
     */
    @Override
    public List<CustomerBaseData> queryCustomerBaseDataAllCustomerCode(String Customer_Code) {
        return customerBaseDataDao.queryCustomerBaseDataAllCustomerCode(Customer_Code);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerBaseData> queryCustomerBaseDataForDownLoad(Integer Id[]) {
        return customerBaseDataDao.queryCustomerBaseDataForDownLoad(Id);
    }

    /**
     * for download all
     * @param customerBaseData
     * @return
     */
    @Override
    public List<CustomerBaseData> queryCustomerBaseDataForDownLoadAll(CustomerBaseData customerBaseData) {
        return customerBaseDataDao.queryCustomerBaseDataForDownLoadAll(customerBaseData);
    }

    /**
     * 上传文件Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean uploadCustomerBaseData(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        CustomerBaseData customerBaseData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++ ){
            for(int j=0;j<23;j++){
                if(sheet.getRow(i).getCell(j)==null){
                    throw new GlobalException("blank:"+(i+1));
                }
            }
            String Four_Initials=sheet.getRow(i).getCell(0).getStringCellValue().trim().toUpperCase();
            if(Four_Initials.length()==0){
                Four_Initials=Four_Initials+"AAAA";
            }
            else if(Four_Initials.length()==1){
                Four_Initials=Four_Initials+"AAA";
            }
            else if(Four_Initials.length()==2){
                Four_Initials=Four_Initials+"AA";
            }
            else if(Four_Initials.length()==3){
                Four_Initials=Four_Initials+"A";
            }
            else {
                Four_Initials=Four_Initials.substring(0,4);
            }
            String Chinese_Full_Name=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            String Country=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            String City=sheet.getRow(i).getCell(7).getStringCellValue().trim();
            customerBaseData=new CustomerBaseData();
            customerBaseData.setFour_Initials(Four_Initials);
            customerBaseData.setChinese_Full_Name(Chinese_Full_Name);
            customerBaseData.setCountry(Country);
            customerBaseData.setCity(City);
            if(customerBaseDataDao.queryCustomerBaseDataForExist(customerBaseData)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            String Deliver_Term=sheet.getRow(i).getCell(15).getStringCellValue().trim();
            String Payment_Term=sheet.getRow(i).getCell(16).getStringCellValue().trim();
            String Receive_Term=sheet.getRow(i).getCell(17).getStringCellValue().trim();
            if(quoteTermDao.queryQuoteTermForUploadDeliverTerm(Deliver_Term)==0){
                throw new GlobalException("deliver_Term:"+(i+1));//代表deliver_Term填写错误
            }
            if(quoteTermDao.queryQuoteTermForUploadPaymentTerm(Payment_Term)==0){
                throw new GlobalException("payment_Term:"+(i+1));//代表payment_Term填写错误
            }
            if(quoteTermDao.queryQuoteTermForUploadReceiveTerm(Receive_Term)==0){
                throw new GlobalException("receive_Term:"+(i+1));//代表receive_Term填写错误
            }
            String Foxconn_Group=sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if(!("是".equals(Foxconn_Group) || "否".equals(Four_Initials))){
                throw new GlobalException("foxconn_Group:"+(i+1));
            }
            String Enterprise_Nature=sheet.getRow(i).getCell(12).getStringCellValue().trim();
            if(!("外资欧美".equals(Enterprise_Nature) || "非外资欧美".equals(Enterprise_Nature) || "台资企业".equals(Enterprise_Nature))){
                throw new GlobalException("enterprise_Nature:"+(i+1));
            }
            String BPM=sheet.getRow(i).getCell(22).getStringCellValue().trim();
            if(userRoleDao.queryUserRoleForUploadRole("BPM",BPM)==0){
                throw new GlobalException("bpm:"+(i+1));//代表bpm填写错误
            }
            String Status=sheet.getRow(i).getCell(20).getStringCellValue().trim();
            if(!("待开发".equals(Status) || "开发中".equals(Status) || "交易中".equals(Status) || "暂停交易".equals(Status) || "停止交易".equals(Status))){
                throw new GlobalException("status:"+(i+1));
            }
            if(!("待开发".equals(Status))){
                customerBaseData.setDevelopment_Date(sheet.getRow(i).getCell(21).getStringCellValue().trim());
            }
            customerBaseData.setChinese_Abbreviation(sheet.getRow(i).getCell(2).getStringCellValue().trim());
            customerBaseData.setEnglish_Full_Name(sheet.getRow(i).getCell(3).getStringCellValue().trim());
            customerBaseData.setEnglish_Abbreviation(sheet.getRow(i).getCell(4).getStringCellValue().trim());
            customerBaseData.setIndustry(sheet.getRow(i).getCell(8).getStringCellValue().trim());
            customerBaseData.setCompany_Owner(sheet.getRow(i).getCell(9).getStringCellValue().trim());
            customerBaseData.setAnnual_Revenue(Double.parseDouble(sheet.getRow(i).getCell(10).getStringCellValue()));
            customerBaseData.setIndustry_Rank(sheet.getRow(i).getCell(11).getStringCellValue().trim());
            customerBaseData.setEmployee_Qty(Integer.parseInt(sheet.getRow(i).getCell(13).getStringCellValue()));
            customerBaseData.setCustomer_Level(sheet.getRow(i).getCell(14).getStringCellValue().trim());
            customerBaseData.setOffice_Address(sheet.getRow(i).getCell(18).getStringCellValue().trim());
            customerBaseData.setRegistered_Address(sheet.getRow(i).getCell(19).getStringCellValue().trim());
            customerBaseData.setDeliver_Term(Deliver_Term);
            customerBaseData.setPayment_Term(Payment_Term);
            customerBaseData.setReceive_Term(Receive_Term);
            customerBaseData.setFoxconn_Group(Foxconn_Group);
            customerBaseData.setEnterprise_Nature(Enterprise_Nature);
            customerBaseData.setBPM(BPM);
            customerBaseData.setStatus(Status);
            CustomerBaseData customerBaseDataAdd=customerBaseDataDao.queryCustomerBaseDataLastData(Four_Initials);
            String Customer_Code="";
            if(customerBaseDataAdd==null){
                Customer_Code=Four_Initials+"001";
            }
            else {
                int count=Integer.parseInt(customerBaseDataAdd.getCustomer_Code().substring(4));
                if(count>0 && count<9){
                    Customer_Code=Four_Initials+"00"+(count+1);
                }
                else if(count>=9 && count<99){
                    Customer_Code=Four_Initials+"0"+(count+1);
                }
                else {
                    Customer_Code=Four_Initials+(count+1);
                }
            }
            customerBaseData.setCustomer_Code(Customer_Code);
            customerBaseData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerBaseData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerBaseDataDao.insertCustomerBaseData(customerBaseData)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }

    /**
     * 下拉框Chinese_Abbreviation
     * @return
     */
    @Override
    public List<CustomerBaseData> queryCustomerBaseDataOptionChineseAbbreviation() {
        return customerBaseDataDao.queryCustomerBaseDataOptionChineseAbbreviation();
    }

    /**
     * 查询customer_base_data表中有而customer_account_info表中没有的Customer_Code
     * @return
     */
    @Override
    public List<CustomerBaseData> queryCustomerBaseDataNotExistCustomerCodeForCustomerAccountInfo() {
        return customerBaseDataDao.queryCustomerBaseDataNotExistCustomerCodeForCustomerAccountInfo();
    }

    /**
     * 查询customer_base_data表中有而customer_contact表中没有的Customer_Code
     * @return
     */
    @Override
    public List<CustomerBaseData> queryCustomerBaseDataNotExistCustomerCodeForCustomerContact() {
        return customerBaseDataDao.queryCustomerBaseDataNotExistCustomerCodeForCustomerContact();
    }

}
