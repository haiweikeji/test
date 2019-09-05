package hwkj.hwkj.dao.SCM;

import hwkj.hwkj.entity.SCM.VendorContact;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VendorContactDao {

    /**
     * 新增数据
     * @param vendorContact
     * @return
     */
    public int insertVendorContact(VendorContact vendorContact);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteVendorContact(Integer Id[]);

    /**
     * 更新数据
     * @param vendorContact
     * @return
     */
    public int updateVendorContact(VendorContact vendorContact);

    /**
     * 分页查询List
     * @param vendorContactPageModel
     * @param vendorContact
     * @return
     */
    public List<VendorContact> queryVendorContactList(@Param("vendorContactPageModel")PageModel<VendorContact> vendorContactPageModel,@Param("vendorContact")VendorContact vendorContact);

    /**
     * 分页查询Count
     * @param vendorContactPageModel
     * @param vendorContact
     * @return
     */
    public int queryVendorContactCount(@Param("vendorContactPageModel")PageModel<VendorContact> vendorContactPageModel,@Param("vendorContact")VendorContact vendorContact);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public VendorContact queryVendorContactById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param vendorContact
     * @return
     */
    public List<VendorContact> queryVendorContactForDownLoadAll(@Param("vendorContact")VendorContact vendorContact);

    /**
     * for download
     * @param Id
     * @return
     */
    public VendorContact queryVendorContactForDownLoad(@Param("Id") Integer Id);

    /**
     * check 数据是否已存在
     * @param Vendor_Code
     * @return
     */
    public int queryVendorContactForExist(@Param("Vendor_Code") String Vendor_Code);

    /**
     * 上传文件
     * @param vendorContactList
     * @return
     */
    public int uploadVendorContact(List<VendorContact> vendorContactList);
}
