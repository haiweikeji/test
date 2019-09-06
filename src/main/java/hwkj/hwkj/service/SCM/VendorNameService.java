package hwkj.hwkj.service.SCM;

import hwkj.hwkj.entity.SCM.VendorName;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VendorNameService {

    /**
     * 新增数据
     * @param vendorName
     * @return
     */
    public boolean insertVendorName(VendorName vendorName);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteVendorName(Integer Id);

    /**
     * 更新数据
     * @param vendorName
     * @param Old_Actual_Chinese_Full
     * @return
     */
    public boolean updateVendorName(VendorName vendorName,String Old_Actual_Chinese_Full);

    /**
     * 分页查询List
     * @param vendorNamePageModel
     * @param vendorName
     * @return
     */
    public void queryVendorNamePage(PageModel<VendorName> vendorNamePageModel, VendorName vendorName);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public VendorName queryVendorNameById(Integer Id);

    /**
     * for download all
     * @param vendorName
     * @return
     */
    public List<VendorName> queryVendorNameForDownLoadAll(VendorName vendorName);

    /**
     * for download
     * @param Id
     * @return
     */
    public VendorName queryVendorNameForDownLoad(Integer Id);

    /**
     * 文件上传
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    public boolean vendorNameUpload(MultipartFile multipartFile, HttpServletRequest request) throws Exception;

    /**
     * for 供应商中文全称
     * @param Vendor_Chinese_Full
     * @return
     */
    public List<VendorName> queryVendorNameForOption(String Vendor_Chinese_Full);

    /**
     * for 品牌
     * @param Brand
     * @return
     */
    public List<VendorName> queryVendorNameForBrandOption(String Brand);
}
