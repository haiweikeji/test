package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerBaseData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerBaseDataDao {

    /**
     * 新增数据
     * @param customerBaseData
     * @return
     */
    public int insertCustomerBaseData(CustomerBaseData customerBaseData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerBaseData(Integer Id[]);

    /**
     * 更新数据
     * @param customerBaseData
     * @return
     */
    public int updateCustomerBaseData(CustomerBaseData customerBaseData);

    /**
     * 分页查询集合
     * @param customerBaseDataPageModel
     * @param customerBaseData
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataList(@Param("customerBaseDataPageModel") PageModel<CustomerBaseData> customerBaseDataPageModel,@Param("customerBaseData") CustomerBaseData customerBaseData);

    /**
     * 分页查询count总数
     * @param customerBaseDataPageModel
     * @param customerBaseData
     * @return
     */
    public int queryCustomerBaseDataCount(@Param("customerBaseDataPageModel") PageModel<CustomerBaseData> customerBaseDataPageModel,@Param("customerBaseData") CustomerBaseData customerBaseData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerBaseData queryCustomerBaseDataById(@Param("Id") Integer Id);

    /**
     * 判断新增数据是否存在
     * @param customerBaseData
     * @return
     */
    public int queryCustomerBaseDataForCustomerCode(CustomerBaseData customerBaseData);

    /**
     * 递增生成Customer_Code
     * @param customerBaseData
     * @return
     */
    public int queryCustomerBaseDataGenerateCustomerCode(CustomerBaseData customerBaseData);

    /**
     * 查询CustomerBaseData 所有Customer_Code
     * @param Customer_Code
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataAllCustomerCode(@Param("Customer_Code") String Customer_Code);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataForDownLoad(Integer Id[]);

    /**
     * for download all
     * @param customerBaseData
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataForDownLoadAll(@Param("customerBaseData") CustomerBaseData customerBaseData);

    /**
     * 检查新增数据是否已存在
     * @param customerBaseData
     * @return
     */
    public int queryCustomerBaseDataForExist(CustomerBaseData customerBaseData);

    /**
     * check 客户中文简称是否是合法
     * @param Chinese_Abbreviation
     * @return
     */
    public int queryCustomerBaseDataForChineseAbbreviationExist(@Param("Chinese_Abbreviation") String Chinese_Abbreviation);

    /**
     * 查询最后一条数据
     * @param Four_Initials
     * @return
     */
    public CustomerBaseData queryCustomerBaseDataLastData(@Param("Four_Initials") String Four_Initials);

    /**
     * 下拉框Chinese_Abbreviation
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataOptionChineseAbbreviation();

    /**
     * 查询customer_base_data表中有而customer_account_info表中没有的Customer_Code
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataNotExistCustomerCodeForCustomerAccountInfo();

    /**
     * 查询customer_base_data表中有而customer_contact表中没有的Customer_Code
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataNotExistCustomerCodeForCustomerContact();

    /**
     * 检查上传时客户代码是否填写正确
     * @param Customer_Code
     * @return
     */
    public int queryCustomerBaseDataForUploadCustomerCode(@Param("Customer_Code") String Customer_Code);
}

