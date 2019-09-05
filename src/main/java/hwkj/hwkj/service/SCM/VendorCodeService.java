package hwkj.hwkj.service.SCM;

import hwkj.hwkj.entity.SCM.VendorCode;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VendorCodeService {

    /**
     * 新增数据
     * @param vendorCode
     * @return
     */
    public boolean insertVendorCode(VendorCode vendorCode);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteVendorCode(Integer Id[]);

    /**
     * 更新数据
     * @param vendorCode
     * @return
     */
    public boolean updateVendorCode(VendorCode vendorCode);

    /**
     * 分页查询
     * @param vendorCodePageModel
     * @param vendorCode
     * @return
     */
    public void queryVendorCodePage(PageModel<VendorCode> vendorCodePageModel,VendorCode vendorCode);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public VendorCode queryVendorCodeById(Integer Id);

    /**
     * for download all
     * @param vendorCode
     * @return
     */
    public List<VendorCode> queryVendorCodeForDownLoadAll(VendorCode vendorCode);

    /**
     * for download
     * @param Id
     * @return
     */
    public VendorCode queryVendorCodeForDownLoad(Integer Id);

    /**
     * 上传文件
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    public boolean uploadVendorCode(MultipartFile multipartFile, HttpServletRequest request) throws Exception;

    /**
     * for 供应商代码
     * @param Vendor_Code
     * @return
     */
    public List<VendorCode> queryVendorCodeForOption(String Vendor_Code);

    /**
     * like语句查询
     * @param Brand
     * @return
     */
    public List<VendorCode> queryVendorCodeForLikeBrandOption(String Brand);

    /**
     * for 供应商标准中文全称
     * @param Vendor_Chinese_Full
     * @return
     */
    public List<VendorCode> queryVendorCodeForVendorChineseFullOption(String Vendor_Chinese_Full);

    /**
     * for 品牌
     * @param Brand
     * @return
     */
    public List<VendorCode> queryVendorCodeForBrandOption(String Brand);

    /**
     * 查询所有代理商的vendor_code
     * @param Vendor_Nature
     * @return
     */
    public List<VendorCode> queryVendorCodeForVendorCodeOption(String Vendor_Nature);

    /**
     * 通过查询品牌来获取供应商代码
     * @param Brand
     * @return
     */
    public List<VendorCode> queryVendorCodeForOptionVendorCode(String Brand);
}
