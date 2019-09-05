package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerBaseData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerBaseDataService {

    /**
     * 新增数据
     * @param customerBaseData
     * @return
     */
    public boolean insertCustomerBaseData(CustomerBaseData customerBaseData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerBaseData(Integer Id[]);

    /**
     * 更新数据
     * @param customerBaseData
     * @return
     */
    public boolean updateCustomerBaseData(CustomerBaseData customerBaseData);

    /**
     * 分页查询
     * @param customerBaseDataPageModel
     * @param customerBaseData
     */
    public void queryCustomerBaseDataPage(PageModel<CustomerBaseData> customerBaseDataPageModel,CustomerBaseData customerBaseData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerBaseData queryCustomerBaseDataById(Integer Id);

    /**
     * 查询CustomerBaseData 所有Customer_Code
     * @param Customer_Code
     * @return
     */
    public List<CustomerBaseData> queryCustomerBaseDataAllCustomerCode(String Customer_Code);

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
    public List<CustomerBaseData> queryCustomerBaseDataForDownLoadAll(CustomerBaseData customerBaseData);

    /**
     * 上传文件Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerBaseData(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;

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

}
