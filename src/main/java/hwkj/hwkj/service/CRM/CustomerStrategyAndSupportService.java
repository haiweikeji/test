package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerStrategyAndSupport;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerStrategyAndSupportService {

    /**
     * 新增数据
     * @param customerStrategyAndSupport
     * @return
     */
    public boolean insertCustomerStrategyAndSupport(CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerStrategyAndSupport(Integer Id[]);

    /**
     * 更新数据
     * @param customerStrategyAndSupport
     * @return
     */
    public boolean updateCustomerStrategyAndSupport(CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * 分页查询
     * @param customerStrategyAndSupportPageModel
     * @param customerStrategyAndSupport
     * @return
     */
    public void queryCustomerStrategyAndSupportPage(PageModel<CustomerStrategyAndSupport> customerStrategyAndSupportPageModel,CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerStrategyAndSupport queryCustomerStrategyAndSupportById(Integer Id);

    /**
     * for download all
     * @param customerStrategyAndSupport
     * @return
     */
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportForDownLoadAll(CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerStrategyAndSupport(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
