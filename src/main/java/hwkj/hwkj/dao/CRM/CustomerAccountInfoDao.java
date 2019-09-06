package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerAccountInfo;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.sqlserver.InsertSelectiveMapper;

import java.util.List;

@Mapper
public interface CustomerAccountInfoDao extends tk.mybatis.mapper.common.Mapper<CustomerAccountInfo>, InsertSelectiveMapper<CustomerAccountInfo> {

    /**
     * 新增数据
     *
     * @param customerAccountInfo
     * @return
     */
    public int insertCustomerAccountInfo(CustomerAccountInfo customerAccountInfo);

    /**
     * 删除数据
     *
     * @param Id
     * @return
     */
    public int deleteCustomerAccountInfo(Integer Id[]);

    /**
     * 更新数据
     *
     * @param customerAccountInfo
     * @return
     */
    public int updateCustomerAccountInfo(CustomerAccountInfo customerAccountInfo);

    /**
     * 分页集合List
     *
     * @param customerAccountInfoPageModel
     * @param customerAccountInfo
     * @return
     */
    public List<CustomerAccountInfo> queryCustomerAccountInfoList(@Param("customerAccountInfoPageModel") PageModel<CustomerAccountInfo> customerAccountInfoPageModel, @Param("customerAccountInfo") CustomerAccountInfo customerAccountInfo);

    /**
     * 分页总数count
     *
     * @param customerAccountInfoPageModel
     * @param customerAccountInfo
     * @return
     */
    public int queryCustomerAccountInfoCount(@Param("customerAccountInfoPageModel") PageModel<CustomerAccountInfo> customerAccountInfoPageModel, @Param("customerAccountInfo") CustomerAccountInfo customerAccountInfo);

    /**
     * by Id 查询
     *
     * @param Id
     * @return
     */
    public CustomerAccountInfo queryCustomerAccountInfoById(@Param("Id") Integer Id);

    /**
     * for download all
     *
     * @param customerAccountInfo
     * @return
     */
    public List<CustomerAccountInfo> queryCustomerAccountInfoForDownLoadAll(@Param("customerAccountInfo") CustomerAccountInfo customerAccountInfo);

    /**
     * for download
     *
     * @param Id
     * @return
     */
    public List<CustomerAccountInfo> queryCustomerAccountInfoForDownLoad(Integer Id[]);

    /**
     * 检查customer_code是否已经存在
     *
     * @param Customer_Code
     * @return
     */
    public int queryCustomerAccountInfoForExist(@Param("Customer_Code") String Customer_Code);

    /**
     * 检查用户有没有更新内容
     *
     * @param customerAccountInfo
     * @return
     */
    public int queryCustomerAccountInfoForAllExist(CustomerAccountInfo customerAccountInfo);
}
