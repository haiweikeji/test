package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.CRM.CustomerContactDao;
import hwkj.hwkj.dao.User.UserRoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerContactService;
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
public class CustomerContactServiceImpl implements CustomerContactService {
    @Autowired
    private CustomerContactDao customerContactDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 新增数据
     * @param customerContact
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerContact(CustomerContact customerContact){
        if(customerContactDao.queryCustomerContactForExist(customerContact.getCustomer_Code(),customerContact.getContact_Chinese_Name())!=0){
            throw new GlobalException("exist");
        }
        if(customerContact.getCompany_Telephone()!=null && customerContact.getCompany_Telephone().trim().length()!=0){
            String Company_Telephone_Regex="^[0-9]+([+-][0-9]+)+$";
            if(!customerContact.getCompany_Telephone().trim().matches(Company_Telephone_Regex)){
                throw new GlobalException("company_Telephone");
            }
        }
        if(customerContact.getPhone_Number()!=null && customerContact.getPhone_Number().trim().length()!=0){
            String Phone_Number_Regex="^[0-9]+$";
            if(!customerContact.getPhone_Number().trim().matches(Phone_Number_Regex)){
                throw new GlobalException("phone_Number");
            }
        }
        if(customerContact.getId_Card()!=null && customerContact.getId_Card().trim().length()!=0){
            String Id_Card_Regex="^([0-9]{17})([0-9]|[X]|[x])$";
            if(!customerContact.getId_Card().trim().matches(Id_Card_Regex)){
                throw new GlobalException("id_Card");
            }
            customerContact.setBirth_Date(customerContact.getId_Card().trim().substring(6,14));
        }else {
            customerContact.setBirth_Date("");
        }
        if(customerContact.getPassport_Number()!=null && customerContact.getPassport_Number().trim().length()!=0){
            String Passport_Number_Regex="^[0-9a-zA-Z]+$";
            if(!customerContact.getPassport_Number().trim().matches(Passport_Number_Regex)){
                throw new GlobalException("passport_Number");
            }
        }
        customerContact.setStatus("Y");
        if(customerContactDao.insertCustomerContact(customerContact)<=0){
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
    public boolean deleteCustomerContact(Integer Id[]){
        if(customerContactDao.deleteCustomerContact(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerContact
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerContact(CustomerContact customerContact){
        if(customerContactDao.queryCustomerContactForAllExist(customerContact)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(customerContact.getCompany_Telephone()!=null && customerContact.getCompany_Telephone().trim().length()!=0){
                String Company_Telephone_Regex="^[0-9]+([+-][0-9]+)+$";
                if(!customerContact.getCompany_Telephone().trim().matches(Company_Telephone_Regex)){
                    throw new GlobalException("company_Telephone");
                }
            }
            if(customerContact.getPhone_Number()!=null && customerContact.getPhone_Number().trim().length()!=0){
                String Phone_Number_Regex="^[0-9]+$";
                if(!customerContact.getPhone_Number().trim().matches(Phone_Number_Regex)){
                    throw new GlobalException("phone_Number");
                }
            }
            if(customerContact.getId_Card()!=null && customerContact.getId_Card().trim().length()!=0){
                String Id_Card_Regex="^([0-9]{17})([0-9]|[X]|[x])$";
                if(!customerContact.getId_Card().trim().matches(Id_Card_Regex)){
                    throw new GlobalException("id_Card");
                }
                customerContact.setBirth_Date(customerContact.getId_Card().substring(6,14));
            }else {
                customerContact.setBirth_Date("");
            }
            if(customerContact.getPassport_Number()!=null && customerContact.getPassport_Number().trim().length()!=0){
                String Passport_Number_Regex="^[0-9a-zA-Z]+$";
                if(!customerContact.getPassport_Number().trim().matches(Passport_Number_Regex)){
                    throw new GlobalException("passport_Number");
                }
            }
            if(customerContact.getCustomer_Code().trim().equals(customerContact.getOld_Customer_Code().trim()) &&customerContact.getContact_Chinese_Name().trim().equals(customerContact.getOld_Contact_Chinese_Name().trim())){
                if(customerContactDao.updateCustomerContact(customerContact)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(customerContactDao.queryCustomerContactForExist(customerContact.getCustomer_Code(),customerContact.getContact_Chinese_Name())!=0){
                    throw new GlobalException("exist");
                }
                if(customerContactDao.updateCustomerContact(customerContact)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerContactPageModel
     * @param customerContact
     */
    @Override
    public void queryCustomerContactPage(PageModel<CustomerContact> customerContactPageModel, CustomerContact customerContact) {
        customerContactPageModel.setList(customerContactDao.queryCustomerContactList(customerContactPageModel,customerContact));
        customerContactPageModel.setTotal(customerContactDao.queryCustomerContactCount(customerContactPageModel,customerContact));
    }

    /**
     * by id查询
     * @param Id
     * @return
     */
    @Override
    public CustomerContact queryCustomerContactById(Integer Id) {
        return customerContactDao.queryCustomerContactById(Id);
    }

    /**
     * for download all
     * @param customerContact
     * @return
     */
    @Override
    public List<CustomerContact> queryCustomerContactForDownLoadAll(CustomerContact customerContact) {
        return customerContactDao.queryCustomerContactForDownLoadAll(customerContact);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerContact> queryCustomerContactForDownLoad(Integer Id[]) {
        return customerContactDao.queryCustomerContactForDownLoad(Id);
    }

    /**
     * by customer_code 查询
     * @param Customer_Code
     * @return
     */
    @Override
    public List<CustomerContact> queryCustomerContactByCustomerCode(String Customer_Code) {
        return customerContactDao.queryCustomerContactByCustomerCode(Customer_Code);
    }

    /**
     * by customer_code and Contact_Chinese_Name 查询
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @return
     */
    @Override
    public List<CustomerContact> queryCustomerContactByCustomerCodeAndContactChineseName(String Customer_Code, String Contact_Chinese_Name) {
        return customerContactDao.queryCustomerContactByCustomerCodeAndContactChineseName(Customer_Code, Contact_Chinese_Name);
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
    public boolean uploadCustomerContact(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        CustomerContact customerContact=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(4)==null || sheet.getRow(i).getCell(4).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(5)==null || sheet.getRow(i).getCell(5).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(6)==null || sheet.getRow(i).getCell(6).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(7)==null || sheet.getRow(i).getCell(7).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(8)==null || sheet.getRow(i).getCell(8).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(9)==null || sheet.getRow(i).getCell(9).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(10)==null || sheet.getRow(i).getCell(10).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(12)==null || sheet.getRow(i).getCell(12).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(22)==null || sheet.getRow(i).getCell(22).getStringCellValue().trim().length()==0){
                throw new GlobalException("blank:"+(i+1));//代表必填项没填
            }
            String Customer_Code=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if(customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code)==0){
                throw new GlobalException("customer_Code:"+(i+1));//代表customer_Code填写错误
            }
            String Contact_Chinese_Name=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(customerContactDao.queryCustomerContactForExist(Customer_Code,Contact_Chinese_Name)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            String Sex=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            if(!("男".equals(Sex) || "女".equals(Sex))){
                throw new GlobalException("sex:"+(i+1));//带表性别填写错误
            }
            String BPM=sheet.getRow(i).getCell(22).getStringCellValue().trim();
            if(userRoleDao.queryUserRoleForUploadRole("BPM",BPM)==0){
                throw new GlobalException("bpm:"+(i+1));//代表bpm填写错误
            }
            customerContact=new CustomerContact();
            String Company_Telephone=sheet.getRow(i).getCell(8).getStringCellValue().trim();
            String Company_Telephone_Regex="^[0-9]+([+-][0-9]+)+$";
            if(!Company_Telephone.matches(Company_Telephone_Regex)){
                throw new GlobalException("company_Telephone:"+(i+1));
            }
            String Phone_Number=sheet.getRow(i).getCell(9).getStringCellValue().trim();
            String Phone_Number_Regex="^[0-9]+$";
            if(!Phone_Number.matches(Phone_Number_Regex)){
                throw new GlobalException("phone_Number:"+(i+1));
            }
            String Company_Mail=sheet.getRow(i).getCell(10).getStringCellValue().trim();
            String Company_Mail_Regex="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            if(!Company_Mail.matches(Company_Mail_Regex)){
                throw new GlobalException("company_Mail:"+(i+1));
            }
            if(sheet.getRow(i).getCell(11)!=null && sheet.getRow(i).getCell(11).getStringCellValue().trim().length()!=0){
                String Private_Mail=sheet.getRow(i).getCell(11).getStringCellValue().trim();
                String Private_Mail_Regex="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                if(!Private_Mail.matches(Private_Mail_Regex)){
                    throw new GlobalException("private_Mail:"+(i+1));
                }
                customerContact.setPrivate_Mail(Private_Mail);
            }else {
                customerContact.setPrivate_Mail("");
            }
            if(sheet.getRow(i).getCell(13)!=null && sheet.getRow(i).getCell(13).getStringCellValue().trim().length()!=0){
                String Id_Card=sheet.getRow(i).getCell(13).getStringCellValue().trim();
                if(Id_Card.length()!=18){
                    throw new GlobalException("id_Card_Length_Error:"+(i+1));
                }
                String Id_Card_Regex="^([0-9]{17})([0-9]|[X]|[x])$";
                if(!Id_Card.matches(Id_Card_Regex)){
                    throw new GlobalException("id_Card:"+(i+1));
                }
                customerContact.setId_Card(Id_Card);
                customerContact.setBirth_Date(Id_Card.trim().substring(6,14));
            }else {
                customerContact.setId_Card("");
                customerContact.setBirth_Date("");
            }
            if(sheet.getRow(i).getCell(14)!=null && sheet.getRow(i).getCell(14).getStringCellValue().trim().length()!=0){
                String Passport_Number=sheet.getRow(i).getCell(14).getStringCellValue().trim();
                String Passport_Number_Regex="^[0-9a-zA-Z]+$";
                if(!Passport_Number.matches(Passport_Number_Regex)){
                    throw new GlobalException("passport_Number:"+(i+1));
                }
                customerContact.setPassport_Number(Passport_Number);
            }else {
                customerContact.setPassport_Number("");
            }
            //String Chinese_Abbreviation=customerBaseDataDao.queryCustomerBaseDataAllCustomerCode(Customer_Code).get(0).getChinese_Abbreviation();
            customerContact.setCustomer_Code(Customer_Code);
            //customerContact.setChinese_Abbreviation(Chinese_Abbreviation);
            customerContact.setContact_Chinese_Name(Contact_Chinese_Name);
            if(sheet.getRow(i).getCell(2)!=null){
                customerContact.setContact_English_Name(sheet.getRow(i).getCell(2).getStringCellValue().trim());
            }else {
                customerContact.setContact_English_Name("");
            }
            customerContact.setSex(Sex);
            customerContact.setTitle(sheet.getRow(i).getCell(4).getStringCellValue().trim());
            customerContact.setDept(sheet.getRow(i).getCell(5).getStringCellValue().trim());
            customerContact.setAuthority(sheet.getRow(i).getCell(6).getStringCellValue().trim());
            customerContact.setLanguage(sheet.getRow(i).getCell(7).getStringCellValue().trim());
            customerContact.setCompany_Telephone(Company_Telephone);
            customerContact.setPhone_Number(Phone_Number);
            customerContact.setCompany_Mail(Company_Mail);
            customerContact.setWeChat_Number(sheet.getRow(i).getCell(12).getStringCellValue().trim());
            if(sheet.getRow(i).getCell(15)!=null){
                customerContact.setNative_Place(sheet.getRow(i).getCell(15).getStringCellValue().trim());
            }else {
                customerContact.setNative_Place("");
            }
            if(sheet.getRow(i).getCell(16)!=null){
                customerContact.setNation(sheet.getRow(i).getCell(16).getStringCellValue().trim());
            }else {
                customerContact.setNation("");
            }
            if(sheet.getRow(i).getCell(17)!=null){
                customerContact.setReligious_Belief(sheet.getRow(i).getCell(17).getStringCellValue().trim());
            }else {
                customerContact.setReligious_Belief("");
            }
            if(sheet.getRow(i).getCell(18)!=null){
                customerContact.setDiet_Taboo(sheet.getRow(i).getCell(18).getStringCellValue().trim());
            }else{
                customerContact.setDiet_Taboo("");
            }
            if(sheet.getRow(i).getCell(19)!=null){
                customerContact.setInterests(sheet.getRow(i).getCell(19).getStringCellValue().trim());
            }else {
                customerContact.setInterests("");
            }
            if(sheet.getRow(i).getCell(20)!=null){
                customerContact.setHabit(sheet.getRow(i).getCell(20).getStringCellValue().trim());
            }else {
                customerContact.setHabit("");
            }
            if(sheet.getRow(i).getCell(21)!=null){
                customerContact.setMember_Family(sheet.getRow(i).getCell(21).getStringCellValue().trim());
            }else {
                customerContact.setMember_Family("");
            }
            customerContact.setBPM(BPM);
            customerContact.setStatus("Y");
            customerContact.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerContact.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerContactDao.insertCustomerContact(customerContact)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }
}
