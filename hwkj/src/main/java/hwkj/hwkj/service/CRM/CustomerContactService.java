package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerContact;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerContactService {

    /**
     * 新增数据
     * @param customerContact
     * @return
     */
    public boolean insertCustomerContact(CustomerContact customerContact);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerContact(Integer Id[]);

    /**
     * 更新数据
     * @param customerContact
     * @return
     */
    public boolean updateCustomerContact(CustomerContact customerContact);

    /**
     * 分页查询
     * @param customerContactPageModel
     * @param customerContact
     */
    public void queryCustomerContactPage(PageModel<CustomerContact> customerContactPageModel,CustomerContact customerContact);


    /**
     * by id 查询
     * @param Id
     * @return
     */
    public CustomerContact queryCustomerContactById(Integer Id);

    /**
     * for download all
     * @param customerContact
     * @return
     */
    public List<CustomerContact> queryCustomerContactForDownLoadAll(CustomerContact customerContact);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerContact> queryCustomerContactForDownLoad(Integer Id[]);

    /**
     * by customer_code 查询
     * @param Customer_Code
     * @return
     */
    public List<CustomerContact> queryCustomerContactByCustomerCode(String Customer_Code);

    /**
     * by customer_code and Contact_Chinese_Name 查询
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @return
     */
    public List<CustomerContact> queryCustomerContactByCustomerCodeAndContactChineseName(String Customer_Code,String Contact_Chinese_Name);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerContact(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
