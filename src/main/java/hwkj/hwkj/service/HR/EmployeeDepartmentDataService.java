package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.EmployeeDepartmentData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface EmployeeDepartmentDataService {

    /**
     * 新增数据
     * @param employeeDepartmentData
     * @return
     */
    public boolean insertEmployeeDepartmentData(EmployeeDepartmentData employeeDepartmentData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteEmployeeDepartmentData(Integer Id[]);

    /**
     * 更新数据
     * @param employeeDepartmentData
     * @return
     */
    public boolean updateEmployeeDepartmentData(EmployeeDepartmentData employeeDepartmentData);

    /**
     * 分页查询
     * @param employeeDepartmentDataPageModel
     * @param employeeDepartmentData
     * @return
     */
    public void queryEmployeeDepartmentDataPage(PageModel<EmployeeDepartmentData> employeeDepartmentDataPageModel, EmployeeDepartmentData employeeDepartmentData)throws Exception;

    /**
     * for download all
     * @param employeeDepartmentData
     * @return
     */
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataForDownLoadAll(EmployeeDepartmentData employeeDepartmentData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataForDownLoad(Integer Id[]);

    /**
     * 根据组织代码查询org_data table 根据管理职查询title_data table
     * @param Org_Code
     * @return
     */
    public Map<String,Object> queryEmployeeDepartmentDataForOption(String Org_Code);

    /**
     * 下拉框选择组织代码
     * @param Org_Code
     * @return
     */
    public Map<String,Object> queryEmployeeDepartmentDataOptionOrgCode(String Org_Code);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws Exception
     * @throws GlobalException
     */
    public boolean uploadEmployeeDepartmentData(HttpServletRequest request, InputStream inputStream) throws Exception, GlobalException;
}
