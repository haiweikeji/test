package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.EmployeeDepartmentData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface EmployeeDepartmentDataDao {

    /**
     * 新增数据
     * @param employeeDepartmentData
     * @return
     */
    public int insertEmployeeDepartmentData(EmployeeDepartmentData employeeDepartmentData);


    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteEmployeeDepartmentData(Integer Id[]);

    /**
     * 更新数据
     * @param employeeDepartmentData
     * @return
     */
    public int updateEmployeeDepartmentData(EmployeeDepartmentData employeeDepartmentData);

    /**
     * 分页查询集合
     * @param employeeDepartmentDataPageModel
     * @param employeeDepartmentData
     * @return
     */
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataList(@Param("employeeDepartmentDataPageModel")PageModel<EmployeeDepartmentData> employeeDepartmentDataPageModel,@Param("employeeDepartmentData") EmployeeDepartmentData employeeDepartmentData);

    /**
     * 分页查询总数
     * @param employeeDepartmentDataPageModel
     * @param employeeDepartmentData
     * @return
     */
    public int queryEmployeeDepartmentDataCount(@Param("employeeDepartmentDataPageModel")PageModel<EmployeeDepartmentData> employeeDepartmentDataPageModel,@Param("employeeDepartmentData") EmployeeDepartmentData employeeDepartmentData);

    /**
     * for download all
     * @param employeeDepartmentData
     * @return
     */
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataForDownLoadAll(@Param("employeeDepartmentData") EmployeeDepartmentData employeeDepartmentData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataForDownLoad(Integer Id[]);

    /**
     * check 数据是否已存在
     * @param Job_Number
     * @return
     */
    public int queryEmployeeDepartmentDataForExist(@Param("Job_Number") String Job_Number);

    /**
     * 检查用户有没有更新内容
     * @param employeeDepartmentData
     * @return
     */
    public int queryEmployeeDepartmentDataForAllExist(EmployeeDepartmentData employeeDepartmentData);
}
