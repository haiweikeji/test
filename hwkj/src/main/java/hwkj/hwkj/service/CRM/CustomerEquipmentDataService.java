package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerEquipmentData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerEquipmentDataService {

    /**
     * 新增数据
     * @param customerEquipmentData
     * @return
     */
    public boolean insertCustomerEquipmentData(CustomerEquipmentData customerEquipmentData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerEquipmentData(Integer Id[]);

    /**
     * 更新数据
     * @param customerEquipmentData
     * @return
     */
    public boolean updateCustomerEquipmentData(CustomerEquipmentData customerEquipmentData);

    /**
     * 分页查询
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     */
    public void queryCustomerEquipmentDataPage(PageModel<CustomerEquipmentData> customerEquipmentDataPageModel,CustomerEquipmentData customerEquipmentData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerEquipmentData queryCustomerEquipmentDataById(Integer Id);

    /**
     * for download all
     * @param customerEquipmentData
     * @return
     */
    public List<CustomerEquipmentData> queryCustomerEquipmentDataForDownLoadAll(CustomerEquipmentData customerEquipmentData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerEquipmentData> queryCustomerEquipmentDataForDownLoad(Integer Id[]);

    /**
     * 维修和保养记录的分页查询
     * @param customerEquipmentDataPageModel
     * @param customerEquipmentData
     */
    public void queryMaintenanceAndMaintenanceRecordsDataPage(PageModel<CustomerEquipmentData> customerEquipmentDataPageModel,CustomerEquipmentData customerEquipmentData);

    /**
     * download 维修和保养记录
     * @param Id
     * @return
     */
    public List<CustomerEquipmentData> queryMaintenanceAndMaintenanceRecordsDataDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerEquipmentData(HttpServletRequest request, InputStream inputStream)throws GlobalException,Exception;
}
