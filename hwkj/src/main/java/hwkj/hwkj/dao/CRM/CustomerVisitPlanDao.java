package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerVisitPlan;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerVisitPlanDao {

    /**
     * 新增数据
     * @param customerVisitPlan
     * @return
     */
    public int insertCustomerVisitPlan(CustomerVisitPlan customerVisitPlan);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerVisitPlan(Integer Id[]);

    /**
     * 更新数据
     * @param customerVisitPlan
     * @return
     */
    public int updateCustomerVisitPlan(CustomerVisitPlan customerVisitPlan);

    /**
     * 分页集合List
     * @param customerVisitPlanPageModel
     * @param customerVisitPlan
     * @return
     */
    public List<CustomerVisitPlan> queryCustomerVisitPlanList(@Param("customerVisitPlanPageModel") PageModel<CustomerVisitPlan> customerVisitPlanPageModel, @Param("customerVisitPlan") CustomerVisitPlan customerVisitPlan);

    /**
     * 分页总数count
     * @param customerVisitPlanPageModel
     * @param customerVisitPlan
     * @return
     */
    public int queryCustomerVisitPlanCount(@Param("customerVisitPlanPageModel") PageModel<CustomerVisitPlan> customerVisitPlanPageModel, @Param("customerVisitPlan") CustomerVisitPlan customerVisitPlan);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerVisitPlan queryCustomerVisitPlanById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerVisitPlan
     * @return
     */
    public List<CustomerVisitPlan> queryCustomerVisitPlanForDownLoadAll(@Param("customerVisitPlan") CustomerVisitPlan customerVisitPlan);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerVisitPlan> queryCustomerVisitPlanForDownLoad(Integer Id[]);

    /**
     * 查询最后一条数据
     * @param Customer_Code
     * @return
     */
    public CustomerVisitPlan queryCustomerVisitPlanLastData(@Param("Customer_Code") String Customer_Code);

    /**
     * 检查新增信息是否已存在
     * @param customerVisitPlan
     * @return
     */
    public int queryCustomerVisitPlanForExist(CustomerVisitPlan customerVisitPlan);

}
