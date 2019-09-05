package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerEquipmentData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerEquipmentDataDao {

    /**
     * 新增数据
     * @param customerEquipmentData
     * @return
     */
    public int insertCustomerEquipmentData(CustomerEquipmentData customerEquipmentData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerEquipmentData(Integer Id[]);

    /**
     * 更新数据
     * @param customerEquipmentData
     * @return
     */
    public int updateCustomerEquipmentData(CustomerEquipmentData customerEquipmentData);

    /**
     * 分页集合List
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     * @return
     */
    public List<CustomerEquipmentData> queryCustomerEquipmentDataList(@Param("customerEquipmentDataPageModel") PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, @Param("customerEquipmentData") CustomerEquipmentData customerEquipmentData);

    /**
     * 分页总数count
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     * @return
     */
    public int queryCustomerEquipmentDataCount(@Param("customerEquipmentDataPageModel") PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, @Param("customerEquipmentData") CustomerEquipmentData customerEquipmentData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerEquipmentData queryCustomerEquipmentDataById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerEquipmentData
     * @return
     */
    public List<CustomerEquipmentData> queryCustomerEquipmentDataForDownLoadAll(@Param("customerEquipmentData") CustomerEquipmentData customerEquipmentData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerEquipmentData> queryCustomerEquipmentDataForDownLoad(Integer Id[]);

    /**
     * by customer_code and factory_address 查询
     * @param Customer_Code
     * @param Factory_Address
     * @return
     */
    public int queryCustomerEquipmentDataByFactoryAddress(@Param("Customer_Code") String Customer_Code,@Param("Factory_Address") String Factory_Address);

    /**
     * 查询维修和保养记录集合
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     * @return
     */
    public List<CustomerEquipmentData> queryMaintenanceAndMaintenanceRecordsDataList(@Param("customerEquipmentDataPageModel") PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, @Param("customerEquipmentData") CustomerEquipmentData customerEquipmentData);

    /**
     * 查询维修和保养记录总数
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     * @return
     */
    public int queryMaintenanceAndMaintenanceRecordsDataCount(@Param("customerEquipmentDataPageModel") PageModel<CustomerEquipmentData> customerEquipmentDataPageModel, @Param("customerEquipmentData") CustomerEquipmentData customerEquipmentData);

    /**
     * download 维修和保养记录
     * @param Id
     * @return
     */
    public List<CustomerEquipmentData> queryMaintenanceAndMaintenanceRecordsDataDownLoad(Integer Id[]);

    /**
     * 检查用户有没有更新
     * @param customerEquipmentData
     * @return
     */
    public int queryCustomerEquipmentDataForAllExist(CustomerEquipmentData customerEquipmentData);

}
