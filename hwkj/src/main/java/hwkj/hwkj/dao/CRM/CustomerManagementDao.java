package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerManagement;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerManagementDao {

    /**
     * 新增数据
     * @param customerManagement
     * @return
     */
    public int insertCustomerManagement(CustomerManagement customerManagement);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerManagement(Integer Id[]);

    /**
     * 更新数据
     * @param customerManagement
     * @return
     */
    public int updateCustomerManagement(CustomerManagement customerManagement);

    /**
     * 分页集合List
     * @param customerManagementPageModel
     * @param customerManagement
     * @return
     */
    public List<CustomerManagement> queryCustomerManagementList(@Param("customerManagementPageModel") PageModel<CustomerManagement> customerManagementPageModel, @Param("customerManagement") CustomerManagement customerManagement);

    /**
     * 分页总数count
     * @param customerManagementPageModel
     * @param customerManagement
     * @return
     */
    public int queryCustomerManagementCount(@Param("customerManagementPageModel") PageModel<CustomerManagement> customerManagementPageModel, @Param("customerManagement") CustomerManagement customerManagement);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerManagement queryCustomerManagementById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerManagement
     * @return
     */
    public List<CustomerManagement> queryCustomerManagementForDownLoadAll(@Param("customerManagement") CustomerManagement customerManagement);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerManagement> queryCustomerManagementForDownLoad(Integer Id[]);

    /**
     * 检查用户有没有更新内容
     * @param customerManagement
     * @return
     */
    public int queryCustomerManagementForAllExist(CustomerManagement customerManagement);
}
