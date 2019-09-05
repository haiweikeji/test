package hwkj.hwkj.service.Engineering;

import hwkj.hwkj.entity.Engineering.CustomerMaterialData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerMaterialDataService {

    /**
     * 新增数据
     * @param customerMaterialData
     * @return
     */
    public boolean insertCustomerMaterialData(CustomerMaterialData customerMaterialData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCustomerMaterialData(Integer Id[]);

    /**
     * 更新数据
     * @param customerMaterialData
     * @return
     */
    public boolean updateCustomerMaterialData(CustomerMaterialData customerMaterialData);

    /**
     * 分页查询
     * @param customerMaterialDataPageModel
     * @param customerMaterialData
     * @return
     */
    public void queryCustomerMaterialDataPage(PageModel<CustomerMaterialData> customerMaterialDataPageModel,CustomerMaterialData customerMaterialData) throws Exception;

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerMaterialData queryCustomerMaterialDataById(Integer Id);

    /**
     * for download all
     * @param customerMaterialData
     * @return
     */
    public List<CustomerMaterialData> queryCustomerMaterialDataForDownLoadAll(CustomerMaterialData customerMaterialData);

    /**
     * for download
     * @param Id
     * @return
     */
    public CustomerMaterialData queryCustomerMaterialDataForDownLoad(Integer Id);

    /**
     * check customer_material_data 是否存在
     * @param customerMaterialData
     * @return
     */
    public int queryCustomerMaterialDataForExist(CustomerMaterialData customerMaterialData);

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean customerMaterialDataUpload(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
