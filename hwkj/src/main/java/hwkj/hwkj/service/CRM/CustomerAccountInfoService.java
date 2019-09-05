package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerAccountInfo;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerAccountInfoService {

    /**
     * 新增数据
     * @param customerAccountInfo
     * @return
     */
    public boolean insertCustomerAccountInfo(CustomerAccountInfo customerAccountInfo);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerAccountInfo(Integer Id[]);

    /**
     * 更新数据
     * @param customerAccountInfo
     * @return
     */
    public boolean updateCustomerAccountInfo(CustomerAccountInfo customerAccountInfo);

    /**
     * 分页查询
     * @param customerAccountInfoPageModel
     * @param customerAccountInfo
     */
    public void queryCustomerAccountInfoPage(PageModel<CustomerAccountInfo> customerAccountInfoPageModel,CustomerAccountInfo customerAccountInfo);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerAccountInfo queryCustomerAccountInfoById(Integer Id);

    /**
     * for download all
     * @param customerAccountInfo
     * @return
     */
    public List<CustomerAccountInfo> queryCustomerAccountInfoForDownLoadAll(CustomerAccountInfo customerAccountInfo);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerAccountInfo> queryCustomerAccountInfoForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerAccountInfo(HttpServletRequest request, InputStream inputStream)throws GlobalException,Exception;

}
