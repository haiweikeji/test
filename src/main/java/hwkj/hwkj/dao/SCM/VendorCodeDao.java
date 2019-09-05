package hwkj.hwkj.dao.SCM;

import hwkj.hwkj.entity.SCM.VendorCode;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VendorCodeDao {

    /**
     * 新增数据
     * @param vendorCode
     * @return
     */
    public int insertVendorCode(VendorCode vendorCode);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteVendorCode(Integer Id[]);

    /**
     * 更新数据
     * @param vendorCode
     * @return
     */
    public int updateVendorCode(VendorCode vendorCode);

    /**
     * 分页查询List
     * @param vendorCodePageModel
     * @param vendorCode
     * @return
     */
    public List<VendorCode> queryVendorContactList(@Param("vendorCodePageModel") PageModel<VendorCode> vendorCodePageModel, @Param("vendorCode")VendorCode vendorCode);

    /**
     * 分页查询Count
     * @param vendorCodePageModel
     * @param vendorCode
     * @return
     */
    public int queryVendorCodeCount(@Param("vendorCodePageModel")PageModel<VendorCode> vendorCodePageModel,@Param("vendorCode")VendorCode vendorCode);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public VendorCode queryVendorCodeById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param vendorCode
     * @return
     */
    public List<VendorCode> queryVendorCodeForDownLoadAll(@Param("vendorCode")VendorCode vendorCode);

    /**
     * for download
     * @param Id
     * @return
     */
    public VendorCode queryVendorCodeForDownLoad(@Param("Id") Integer Id);

    /**
     * check 数据是否已存在
     * @param vendorCode
     * @return
     */
    public int queryVendorCodeForExist(VendorCode vendorCode);

    /**
     * 查询最后一条数据
     * @param Vendor_Nature
     * @return
     */
    public VendorCode queryVendorCodeLastData(@Param("Vendor_Nature") String Vendor_Nature);

    /**
     * for 供应商代码
     * @param Vendor_Code
     * @return
     */
    public List<VendorCode> queryVendorCodeForOption(@Param("Vendor_Code") String Vendor_Code);

    /**
     * check 填写的供应商标准中文全称是否合法
     * @param Vendor_Chinese_Full
     * @return
     */
    public List<VendorCode> queryVendorCodeForCheck(@Param("Vendor_Chinese_Full") String Vendor_Chinese_Full);

    /**
     * for 供应商标准中文全称
     * @param Vendor_Chinese_Full
     * @return
     */
    public List<VendorCode> queryVendorCodeForVendorChineseFullOption(@Param("Vendor_Chinese_Full") String Vendor_Chinese_Full);

    /**
     * check 品牌是否已存在
     * @param Brand
     * @return
     */
    public int queryVendorCodeForBrandExist(@Param("Brand") String Brand);

    /**
     * for 品牌
     * @param Brand
     * @return
     */
    public List<VendorCode> queryVendorCodeForBrandOption(@Param("Brand") String Brand);

    /**
     * like语句查询
     * @param Brand
     * @return
     */
    public List<VendorCode> queryVendorCodeForLikeBrandOption(@Param("Brand") String Brand);

    /**
     * 查询所有代理商的vendor_code
     * @param Vendor_Nature
     * @return
     */
    public List<VendorCode> queryVendorCodeForVendorCodeOption(@Param("Vendor_Nature") String Vendor_Nature);

    /**
     * 检查上传时品牌是否填写正确
     * @param Brand
     * @return
     */
    public int queryVendorCodeForUploadBrand(@Param("Brand") String Brand);

    /**
     * 检查上传时供应商代码是否填写正确
     * @param Brand
     * @param Vendor_Code
     * @return
     */
    public int queryVendorCodeForUploadVendorCode(@Param("Brand")String Brand ,@Param("Vendor_Code") String Vendor_Code);

    /**
     * 通过查询品牌来获取供应商代码
     * @param Brand
     * @return
     */
    public List<VendorCode> queryVendorCodeForOptionVendorCode(@Param("Brand") String Brand);
}
