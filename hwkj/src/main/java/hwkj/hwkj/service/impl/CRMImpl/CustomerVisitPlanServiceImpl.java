package hwkj.hwkj.service.impl.CRMImpl;

import hwkj.hwkj.dao.CRM.CustomerBaseDataDao;
import hwkj.hwkj.dao.CRM.CustomerContactDao;
import hwkj.hwkj.dao.CRM.CustomerVisitPlanDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.CRM.CustomerVisitPlanService;
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
public class CustomerVisitPlanServiceImpl implements CustomerVisitPlanService {
    private static final String Time_Regex="^(([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01]))[ ]((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
    @Autowired
    private CustomerVisitPlanDao customerVisitPlanDao;
    @Autowired
    private CustomerBaseDataDao customerBaseDataDao;
    @Autowired
    private CustomerContactDao customerContactDao;

    /**
     * 新增数据
     * @param customerVisitPlan
     * @return
     */
    @Transactional
    @Override
    public boolean insertCustomerVisitPlan(CustomerVisitPlan customerVisitPlan){
        if(customerContactDao.queryCustomerContactForUploadContactChineseName(customerVisitPlan.getCustomer_Code().trim(),customerVisitPlan.getContact_Chinese_Name().trim())==0){
            throw new GlobalException("contact_Chinese_Name");
        }
        if(customerVisitPlanDao.queryCustomerVisitPlanForExist(customerVisitPlan)!=0){
            throw new GlobalException("exist");
        }
        CustomerVisitPlan customerVisitPlanAdd=customerVisitPlanDao.queryCustomerVisitPlanLastData(customerVisitPlan.getCustomer_Code());
        if(customerVisitPlanAdd==null){
            customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"000001");
        }
        else {
            int count=Integer.parseInt(customerVisitPlanAdd.getVisit_Id().substring(8));
            if(count>0 && count<9){
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"00000"+(count+1));
            }
            else if(count>=9 && count<99){
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"0000"+(count+1));
            }
            else if(count>=99 && count<999){
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"000"+(count+1));
            }
            else if(count>=999 && count<9999){
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"00"+(count+1));
            }
            else if(count>=9999 && count<99999){
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"0"+(count+1));
            }
            else {
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+(count+1));
            }
        }
        customerVisitPlan.setStatus("Y");
        if(customerVisitPlanDao.insertCustomerVisitPlan(customerVisitPlan)<=0){
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
    public boolean deleteCustomerVisitPlan(Integer Id[]){
        if(customerVisitPlanDao.deleteCustomerVisitPlan(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param customerVisitPlan
     * @return
     */
    @Transactional
    @Override
    public boolean updateCustomerVisitPlan(CustomerVisitPlan customerVisitPlan){
        if(customerVisitPlanDao.queryCustomerVisitPlanForExist(customerVisitPlan)!=0){
            throw new GlobalException("NoUpdateOrExist");
        }
        if(customerVisitPlanDao.updateCustomerVisitPlan(customerVisitPlan)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param customerVisitPlanPageModel
     * @param customerVisitPlan
     */
    @Override
    public void queryCustomerVisitPlanPage(PageModel<CustomerVisitPlan> customerVisitPlanPageModel, CustomerVisitPlan customerVisitPlan) {
        customerVisitPlanPageModel.setList(customerVisitPlanDao.queryCustomerVisitPlanList(customerVisitPlanPageModel, customerVisitPlan));
        customerVisitPlanPageModel.setTotal(customerVisitPlanDao.queryCustomerVisitPlanCount(customerVisitPlanPageModel, customerVisitPlan));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public CustomerVisitPlan queryCustomerVisitPlanById(Integer Id) {
        return customerVisitPlanDao.queryCustomerVisitPlanById(Id);
    }

    /**
     * for download all
     * @param customerVisitPlan
     * @return
     */
    @Override
    public List<CustomerVisitPlan> queryCustomerVisitPlanForDownLoadAll(CustomerVisitPlan customerVisitPlan) {
        return customerVisitPlanDao.queryCustomerVisitPlanForDownLoadAll(customerVisitPlan);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<CustomerVisitPlan> queryCustomerVisitPlanForDownLoad(Integer Id[]) {
        return customerVisitPlanDao.queryCustomerVisitPlanForDownLoad(Id);
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
    public boolean uploadCustomerVisitPlan(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        CustomerVisitPlan customerVisitPlan = null;
        for (int i = 1, length = sheet.getLastRowNum(); i <= length; i++) {
            for (int j = 0; j < 13; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Customer_Code = sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if (customerBaseDataDao.queryCustomerBaseDataForUploadCustomerCode(Customer_Code) == 0) {
                throw new GlobalException("customer_Code:" + (i + 1));//代表customer_Code填写错误
            }
            String Plan_Date_From=sheet.getRow(i).getCell(1).getStringCellValue();
            if(!Plan_Date_From.matches(Time_Regex)){
                throw new GlobalException("plan_Date_From:" + (i + 1));
            }
            String Plan_Date_To=sheet.getRow(i).getCell(2).getStringCellValue();
            if(!Plan_Date_To.matches(Time_Regex)){
                throw new GlobalException("plan_Date_To:" + (i + 1));
            }
            String Actual_Start_Time=sheet.getRow(i).getCell(3).getStringCellValue();
            if(!Actual_Start_Time.matches(Time_Regex)){
                throw new GlobalException("actual_Start_Time:" + (i + 1));
            }
            String Actual_End_Time=sheet.getRow(i).getCell(4).getStringCellValue();
            if(!Actual_End_Time.matches(Time_Regex)){
                throw new GlobalException("actual_End_Time:" + (i + 1));
            }
            String Contact_Chinese_Name =sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if(customerContactDao.queryCustomerContactForUploadContactChineseName(Customer_Code,Contact_Chinese_Name)==0){
                throw new GlobalException("contact_Chinese_Name:"+(i+1));
            }
            String Contact_English_Name =sheet.getRow(i).getCell(6).getStringCellValue().trim();
            if(customerContactDao.queryCustomerContactForUploadContactEnglishName(Customer_Code,Contact_Chinese_Name,Contact_English_Name)==0){
                throw new GlobalException("contact_English_Name:"+(i+1));
            }
            String Title =sheet.getRow(i).getCell(7).getStringCellValue().trim();
            if(customerContactDao.queryCustomerContactForUploadTitle(Customer_Code,Contact_Chinese_Name,Contact_English_Name,Title)==0){
                throw new GlobalException("title:"+(i+1));
            }
            String Dept =sheet.getRow(i).getCell(8).getStringCellValue().trim();
            if(customerContactDao.queryCustomerContactForUploadDept(Customer_Code,Contact_Chinese_Name,Contact_English_Name,Title,Dept)==0){
                throw new GlobalException("dept:"+(i+1));
            }
            String BPM =sheet.getRow(i).getCell(12).getStringCellValue().trim();
            if(customerContactDao.queryCustomerContactForUploadBPM(Customer_Code,Contact_Chinese_Name,Contact_English_Name,Title,Dept,BPM)==0){
                throw new GlobalException("bpm:"+(i+1));
            }
            customerVisitPlan=new CustomerVisitPlan();
            customerVisitPlan.setCustomer_Code(Customer_Code);
            customerVisitPlan.setPlan_Date_From(Plan_Date_From);
            customerVisitPlan.setPlan_Date_To(Plan_Date_To);
            customerVisitPlan.setActual_Start_Time(Actual_Start_Time);
            customerVisitPlan.setActual_End_Time(Actual_End_Time);
            customerVisitPlan.setContact_Chinese_Name(Contact_Chinese_Name);
            customerVisitPlan.setContact_English_Name(Contact_English_Name);
            customerVisitPlan.setTitle(Title);
            customerVisitPlan.setDept(Dept);
            customerVisitPlan.setVisit_Item(sheet.getRow(i).getCell(9).getStringCellValue().trim());
            customerVisitPlan.setVisit_Purpose(sheet.getRow(i).getCell(10).getStringCellValue().trim());
            customerVisitPlan.setVisit_Result(sheet.getRow(i).getCell(11).getStringCellValue().trim());
            customerVisitPlan.setBPM(BPM);
            customerVisitPlan.setStatus("Y");
            customerVisitPlan.setCreator(((User)request.getSession().getAttribute("user")).getName());
            customerVisitPlan.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(customerVisitPlanDao.queryCustomerVisitPlanForExist(customerVisitPlan)!=0){
                throw new GlobalException("exist:"+(i+1));
            }
            CustomerVisitPlan customerVisitPlanAdd=customerVisitPlanDao.queryCustomerVisitPlanLastData(Customer_Code);
            if(customerVisitPlanAdd==null){
                customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"000001");
            }
            else {
                int count=Integer.parseInt(customerVisitPlanAdd.getVisit_Id().substring(8));
                if(count>0 && count<9){
                    customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"00000"+(count+1));
                }
                else if(count>=9 && count<99){
                    customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"0000"+(count+1));
                }
                else if(count>=99 && count<999){
                    customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"000"+(count+1));
                }
                else if(count>=999 && count<9999){
                    customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"00"+(count+1));
                }
                else if(count>=9999 && count<99999){
                    customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+"0"+(count+1));
                }
                else {
                    customerVisitPlan.setVisit_Id("V"+customerVisitPlan.getCustomer_Code()+(count+1));
                }
            }
            if(customerVisitPlanDao.insertCustomerVisitPlan(customerVisitPlan)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }

}
