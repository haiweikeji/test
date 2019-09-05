package hwkj.hwkj.dao.SCM;

import hwkj.hwkj.entity.SCM.VendorName;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VendorNameDao {

    /**
     * 新增数据
     * @param vendorName
     * @return
     */
    public int insertVendorName(VendorName vendorName);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteVendorName(@Param("Id") Integer Id);

    /**
     * 更新数据
     * @param vendorName
     * @return
     */
    public int updateVendorName(VendorName vendorName);

    /**
     * 分页查询List
     * @param vendorNamePageModel
     * @param vendorName
     * @return
     */
    public List<VendorName> queryVendorNameList(@Param("vendorNamePageModel")PageModel<VendorName> vendorNamePageModel,@Param("vendorName") VendorName vendorName);

    /**
     * 分页查询Count
     * @param vendorNamePageModel
     * @param vendorName
     * @return
     */
    public int queryVendorNameCount(@Param("vendorNamePageModel")PageModel<VendorName> vendorNamePageModel,@Param("vendorName") VendorName vendorName);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public VendorName queryVendorNameById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param vendorName
     * @return
     */
    public List<VendorName> queryVendorNameForDownLoadAll(@Param("vendorName") VendorName vendorName);

    /**
     * for download
     * @param Id
     * @return
     */
    public VendorName queryVendorNameForDownLoad(@Param("Id") Integer Id);

    /**
     * check 实际全称中文写法是否已存在
     * @param vendorName
     * @return
     */
    public int queryVendorNameForExist(VendorName vendorName);

    /**
     * 上传文件
     * @param vendorNameList
     * @return
     */
    public int uploadVendorName(List<VendorName> vendorNameList);

    /**
     * for 供应商中文全称
     * @param Vendor_Chinese_Full
     * @return
     */
    public List<VendorName> queryVendorNameForOption(@Param("Vendor_Chinese_Full") String Vendor_Chinese_Full);

    /**
     * for 品牌
     * @param Brand
     * @return
     */
    public List<VendorName> queryVendorNameForBrandOption(@Param("Brand") String Brand);
}
