package hwkj.hwkj.service.SCM;

import hwkj.hwkj.entity.pagingquery.PageModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VendorContactService {

    /**
     * 新增数据
     * @param vendorContact
     * @return
     */
    public boolean insertVendorContact(VendorContact vendorContact);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteVendorContact(Integer Id[]);

    /**
     * 更新数据
     * @param vendorContact
     * @param Old_Vendor_Code
     * @return
     */
    public boolean updateVendorContact(VendorContact vendorContact,String Old_Vendor_Code);

    /**
     * 分页查询
     * @param vendorContactPageModel
     * @param vendorContact
     * @return
     */
    public void queryVendorContactPage(PageModel<VendorContact> vendorContactPageModel,VendorContact vendorContact);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public VendorContact queryVendorContactById(Integer Id);

    /**
     * for download all
     * @param vendorContact
     * @return
     */
    public List<VendorContact> queryVendorContactForDownLoadAll(VendorContact vendorContact);

    /**
     * for download
     * @param Id
     * @return
     */
    public VendorContact queryVendorContactForDownLoad(Integer Id);

    /**
     * 文件上传
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    public boolean uploadVendorContact(MultipartFile multipartFile, HttpServletRequest request) throws Exception;
}
