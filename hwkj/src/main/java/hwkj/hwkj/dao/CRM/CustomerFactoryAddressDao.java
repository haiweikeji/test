package hwkj.hwkj.dao.CRM;

import hwkj.hwkj.entity.CRM.CustomerFactoryAddress;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerFactoryAddressDao {

    /**
     * 新增数据
     * @param customerFactoryAddress
     * @return
     */
    public int insertCustomerFactoryAddress(CustomerFactoryAddress customerFactoryAddress);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCustomerFactoryAddress(Integer Id[]);

    /**
     * 更新数据
     * @param customerFactoryAddress
     * @return
     */
    public int updateCustomerFactoryAddress(CustomerFactoryAddress customerFactoryAddress);

    /**
     * 分页集合List
     * @param customerFactoryAddressPageModel
     * @param customerFactoryAddress
     * @return
     */
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressList(@Param("customerFactoryAddressPageModel") PageModel<CustomerFactoryAddress> customerFactoryAddressPageModel, @Param("customerFactoryAddress") CustomerFactoryAddress customerFactoryAddress);

    /**
     * 分页总数count
     * @param customerFactoryAddressPageModel
     * @param customerFactoryAddress
     * @return
     */
    public int queryCustomerFactoryAddressCount(@Param("customerFactoryAddressPageModel") PageModel<CustomerFactoryAddress> customerFactoryAddressPageModel, @Param("customerFactoryAddress") CustomerFactoryAddress customerFactoryAddress);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public CustomerFactoryAddress queryCustomerFactoryAddressById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param customerFactoryAddress
     * @return
     */
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForDownLoadAll(@Param("customerFactoryAddress") CustomerFactoryAddress customerFactoryAddress);

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
    public List<CustomerFactoryAddress> queryCustomerFactoryAddressForFactoryAddressByCustomerCode(@Param("Customer_Code") String Customer_Code);

    /**
     * check新增数据和更新是否已存在
     * @param Customer_Code
     * @param Factory_Address
     * @return
     */
    public int queryCustomerFactoryAddressDataExist(@Param("Customer_Code") String Customer_Code,@Param("Factory_Address") String Factory_Address);

    /**
     * 查询最后一条数据
     * @param Customer_Code
     * @return
     */
    public CustomerFactoryAddress queryCustomerFactoryAddressDataLastData(@Param("Customer_Code") String Customer_Code);


    /**
     * 检查上传时工厂地址是否填写正确
     * @param Customer_Code
     * @param Factory_Address
     * @return
     */
    public int queryCustomerFactoryAddressForUploadFactoryAddress(@Param("Customer_Code") String Customer_Code,@Param("Factory_Address") String Factory_Address);
}
