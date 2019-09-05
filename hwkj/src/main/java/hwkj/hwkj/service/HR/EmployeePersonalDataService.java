package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.EmployeePersonalData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;


public interface EmployeePersonalDataService {

    /**
     * 新增数据
     * @param request
     * @param employeePersonalData
     * @param multipartFile
     * @return
     */
    public boolean insertEmployeePersonalData(HttpServletRequest request, EmployeePersonalData employeePersonalData, MultipartFile multipartFile);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteEmployeePersonalData(Integer Id[]);

    /**
     * 更新数据
     * @param employeePersonalData
     * @return
     */
    public boolean updateEmployeePersonalData(EmployeePersonalData employeePersonalData);

    /**
     * 分页查询List
     * @param employeePersonalDataPageModel
     * @param employeePersonalData
     * @return
     */
    public void queryEmployeePersonalDataPage(PageModel<EmployeePersonalData> employeePersonalDataPageModel,EmployeePersonalData employeePersonalData);

    /**
     * for download all
     * @param employeePersonalData
     * @return
     */
    public List<EmployeePersonalData> queryEmployeePersonalDataForDownLoadAll(EmployeePersonalData employeePersonalData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<EmployeePersonalData> queryEmployeePersonalDataForDownLoad(Integer Id[]);

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean employeePersonalDataUpload(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;

}
