package hwkj.hwkj.service.CRM;

import hwkj.hwkj.entity.CRM.CustomerFactoryAddress;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CustomerFactoryAddressService {

    /**
     * 新增数据
     * @param customerFactoryAddress
     * @return
     */
    public boolean insertCustomerFactoryAddress(CustomerFactoryAddress customerFactoryAddress);

    /**
     * 删除数据
     * @param id
     * @param Customer_Code
     * @param Factory_Address
     * @return
     */
    public boolean deleteCustomerFactoryAddress(Integer id[],String Customer_Code[],String Factory_Address[]);

    /**
     * 更新数据
     * @param customerFactoryAddress
     * @return
     */
    public boolean updateCustomerFactoryAddress(CustomerFactoryAddress customerFactoryAddress);

    /**
     * 分页查询
     * @param customerFactoryAddressPageModel
     * @param customerFactoryAddress
     * @return
     */
    public void queryCustomerFactoryAddressPage(PageModel<CustomerFactoryAddress> customerFactoryAddressPageModel,CustomerFactoryAddress customerFactoryAddress);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerFactoryAddress queryCustomerFactoryAddressById(Integer Id);

    /**
     * for download all
     * @param customerFactoryAddress
     * @return
     */
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForDownLoadAll(CustomerFactoryAddress customerFactoryAddress);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForDownLoad(Integer Id[]);

    /**
     * by Customer_Code查询出factory_address
     * @param Customer_Code
     * @return
     */
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForFactoryAddressByCustomerCode(String Customer_Code);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadCustomerFactoryAddress(HttpServletRequest request, InputStream inputStream)throws GlobalException,Exception;

}
