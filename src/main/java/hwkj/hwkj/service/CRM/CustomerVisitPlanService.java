package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerVisitPlan;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerVisitPlanService {

    /**
     * 新增数据
     * @param customerVisitPlan
     * @return
     */
    public boolean insertCustomerVisitPlan(CustomerVisitPlan customerVisitPlan);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerVisitPlan(Integer Id[]);

    /**
     * 更新数据
     * @param customerVisitPlan
     * @return
     */
    public boolean updateCustomerVisitPlan(CustomerVisitPlan customerVisitPlan);

    /**
     * 分页查询
     * @param customerVisitPlanPageModel
     * @param customerVisitPlan
     * @return
     */
    public void queryCustomerVisitPlanPage(PageModel<CustomerVisitPlan> customerVisitPlanPageModel, CustomerVisitPlan customerVisitPlan);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerVisitPlan queryCustomerVisitPlanById(Integer Id);

    /**
     * for download all
     * @param customerVisitPlan
     * @return
     */
    public List<CustomerVisitPlan> queryCustomerVisitPlanForDownLoadAll(CustomerVisitPlan customerVisitPlan);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerVisitPlan> queryCustomerVisitPlanForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerVisitPlan(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
