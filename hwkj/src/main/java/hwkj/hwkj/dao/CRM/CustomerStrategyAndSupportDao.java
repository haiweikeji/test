package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerStrategyAndSupport;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerStrategyAndSupportDao {

    /**
     * 新增数据
     * @param customerStrategyAndSupport
     * @return
     */
    public int insertCustomerStrategyAndSupport(CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerStrategyAndSupport(Integer Id[]);

    /**
     * 更新数据
     * @param customerStrategyAndSupport
     * @return
     */
    public int updateCustomerStrategyAndSupport(CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * 分页集合List
     * @param customerStrategyAndSupportPageModel
     * @param customerStrategyAndSupport
     * @return
     */
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportList(@Param("customerStrategyAndSupportPageModel") PageModel<CustomerStrategyAndSupport> customerStrategyAndSupportPageModel, @Param("customerStrategyAndSupport") CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * 分页总数count
     * @param customerStrategyAndSupportPageModel
     * @param customerStrategyAndSupport
     * @return
     */
    public int queryCustomerStrategyAndSupportCount(@Param("customerStrategyAndSupportPageModel") PageModel<CustomerStrategyAndSupport> customerStrategyAndSupportPageModel, @Param("customerStrategyAndSupport") CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerStrategyAndSupport queryCustomerStrategyAndSupportById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerStrategyAndSupport
     * @return
     */
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportForDownLoadAll(@Param("customerStrategyAndSupport") CustomerStrategyAndSupport customerStrategyAndSupport);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerStrategyAndSupport> queryCustomerStrategyAndSupportForDownLoad(Integer Id[]);

    /**
     * 检查用户有没有更新内容
     * @param customerStrategyAndSupport
     * @return
     */
    public int queryCustomerStrategyAndSupportForAllExist(CustomerStrategyAndSupport customerStrategyAndSupport);
}
