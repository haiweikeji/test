package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerManagement;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerManagementService {

    /**
     * 新增数据
     * @param customerManagement
     * @return
     */
    public boolean insertCustomerManagement(CustomerManagement customerManagement);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerManagement(Integer Id[]);

    /**
     * 更新数据
     * @param customerManagement
     * @return
     */
    public boolean updateCustomerManagement(CustomerManagement customerManagement);

    /**
     * 分页查询
     * @param customerManagementPageModel
     * @param customerManagement
     * @return
     */
    public void queryCustomerManagementPage(PageModel<CustomerManagement> customerManagementPageModel, CustomerManagement customerManagement);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerManagement queryCustomerManagementById(Integer Id);

    /**
     * for download all
     * @param customerManagement
     * @return
     */
    public List<CustomerManagement> queryCustomerManagementForDownLoadAll(CustomerManagement customerManagement);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerManagement> queryCustomerManagementForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerManagement(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
