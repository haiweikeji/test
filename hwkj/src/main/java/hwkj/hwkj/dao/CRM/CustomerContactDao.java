package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerContact;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerContactDao {

    /**
     * 新增数据
     * @param customerContact
     * @return
     */
    public int insertCustomerContact(CustomerContact customerContact);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerContact(Integer Id[]);

    /**
     * 更新数据
     * @param customerContact
     * @return
     */
    public int updateCustomerContact(CustomerContact customerContact);

    /**
     * 分页查询集合
     * @param customerContactPageModel
     * @param customerContact
     * @return
     */
    public List<CustomerContact> queryCustomerContactList(@Param("customerContactPageModel") PageModel<CustomerContact> customerContactPageModel,@Param("customerContact") CustomerContact customerContact);

    /**
     * 分页查询总数
     * @param customerContactPageModel
     * @param customerContact
     * @return
     */
    public int queryCustomerContactCount(@Param("customerContactPageModel") PageModel<CustomerContact> customerContactPageModel,@Param("customerContact") CustomerContact customerContact);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public CustomerContact queryCustomerContactById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerContact
     * @return
     */
    public List<CustomerContact> queryCustomerContactForDownLoadAll(@Param("customerContact") CustomerContact customerContact);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerContact> queryCustomerContactForDownLoad(Integer Id[]);

    /**
     * 检查新增数据是否已存在
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @return
     */
    public int queryCustomerContactForExist(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name);

    /**
     * by customer_code 查询
     * @param Customer_Code
     * @return
     */
    public List<CustomerContact> queryCustomerContactByCustomerCode(@Param("Customer_Code") String Customer_Code);

    /**
     * by customer_code and Contact_Chinese_Name 查询
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @return
     */
    public List<CustomerContact> queryCustomerContactByCustomerCodeAndContactChineseName(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name);

    /**
     * 检查用户有没有更新内容
     * @param customerContact
     * @return
     */
    public int queryCustomerContactForAllExist(CustomerContact customerContact);

    /**
     * 检查上传时联系人中文名字是否正确
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @return
     */
    public int queryCustomerContactForUploadContactChineseName(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name);

    /**
     * 检查上传时联系人英文名字是否正确
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @param Contact_English_Name
     * @return
     */
    public int queryCustomerContactForUploadContactEnglishName(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name,
                                                               @Param("Contact_English_Name") String Contact_English_Name);

    /**
     * 查上传时职级是否正确
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @param Contact_English_Name
     * @param Title
     * @return
     */
    public int queryCustomerContactForUploadTitle(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name,
                                                               @Param("Contact_English_Name") String Contact_English_Name,@Param("Title") String Title);

    /**
     * 查上传时部门是否正确
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @param Contact_English_Name
     * @param Title
     * @param Dept
     * @return
     */
    public int queryCustomerContactForUploadDept(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name,
                                                  @Param("Contact_English_Name") String Contact_English_Name,@Param("Title") String Title,@Param("Dept") String Dept);

    /**
     * 查上传时业务员是否正确
     * @param Customer_Code
     * @param Contact_Chinese_Name
     * @param Contact_English_Name
     * @param Title
     * @param Dept
     * @return
     */
    public int queryCustomerContactForUploadBPM(@Param("Customer_Code") String Customer_Code,@Param("Contact_Chinese_Name") String Contact_Chinese_Name,
                                                 @Param("Contact_English_Name") String Contact_English_Name,@Param("Title") String Title,
                                                 @Param("Dept") String Dept,@Param("BPM") String BPM);
}
