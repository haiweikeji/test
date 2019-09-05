package hwkj.hwkj.dao.Engineering;

import hwkj.hwkj.entity.Engineering.CustomerMaterialData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMaterialDataDao {

    /**
     * 新增数据
     * @param customerMaterialData
     * @return
     */
    public int insertCustomerMaterialData(CustomerMaterialData customerMaterialData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerMaterialData(Integer Id[]);

    /**
     * 更新数据
     * @param customerMaterialData
     * @return
     */
    public int updateCustomerMaterialData(CustomerMaterialData customerMaterialData);

    /**
     * 分页集合List
     * @param customerMaterialDataPageModel
     * @param customerMaterialData
     * @return
     */
    public List<CustomerMaterialData> queryCustomerMaterialDataList(@Param("customerMaterialDataPageModel") PageModel<CustomerMaterialData> customerMaterialDataPageModel, @Param("customerMaterialData") CustomerMaterialData customerMaterialData);

    /**
     * 分页总数count
     * @param customerMaterialDataPageModel
     * @param customerMaterialData
     * @return
     */
    public int queryCustomerMaterialDataCount(@Param("customerMaterialDataPageModel") PageModel<CustomerMaterialData> customerMaterialDataPageModel, @Param("customerMaterialData") CustomerMaterialData customerMaterialData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerMaterialData queryCustomerMaterialDataById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerMaterialData
     * @return
     */
    public List<CustomerMaterialData> queryCustomerMaterialDataForDownLoadAll(@Param("customerMaterialData") CustomerMaterialData customerMaterialData);

    /**
     * for download
     * @param Id
     * @return
     */
    public CustomerMaterialData queryCustomerMaterialDataForDownLoad(@Param("Id") Integer Id);

    /**
     * check customer_material_data 是否存在
     * @param customerMaterialData
     * @return
     */
    public int queryCustomerMaterialDataForExist(CustomerMaterialData customerMaterialData);

    /**
     * 去check所以字段是否有相同数据，为了去检查有没有更新
     * @param customerMaterialData
     * @return
     */
    public int queryCustomerMaterialDataForAllExist(CustomerMaterialData customerMaterialData);
}
