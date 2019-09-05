package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.EmployeePersonalData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeePersonalDataDao {

    /**
     * 新增数据
     * @param employeePersonalData
     * @return
     */
    public int insertEmployeePersonalData(EmployeePersonalData employeePersonalData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteEmployeePersonalData(Integer Id[]);

    /**
     * 更新数据
     * @param employeePersonalData
     * @return
     */
    public int updateEmployeePersonalData(EmployeePersonalData employeePersonalData);

    /**
     * 分页查询List
     * @param employeePersonalDataPageModel
     * @param employeePersonalData
     * @return
     */
    public List<EmployeePersonalData> queryEmployeePersonalDataList(@Param("employeePersonalDataPageModel") PageModel<EmployeePersonalData> employeePersonalDataPageModel,@Param("employeePersonalData") EmployeePersonalData employeePersonalData);

    /**
     * 分页查询count
     * @param employeePersonalDataPageModel
     * @param employeePersonalData
     * @return
     */
    public int queryEmployeePersonalDataCount(@Param("employeePersonalDataPageModel") PageModel<EmployeePersonalData> employeePersonalDataPageModel,@Param("employeePersonalData") EmployeePersonalData employeePersonalData);

    /**
     * for download all
     * @param employeePersonalData
     * @return
     */
    public List<EmployeePersonalData> queryEmployeePersonalDataForDownLoadAll(@Param("employeePersonalData") EmployeePersonalData employeePersonalData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<EmployeePersonalData> queryEmployeePersonalDataForDownLoad(Integer Id[]);

    /**
     * 根据Id_Card查询获取count
     * @param Id_Card
     * @return
     */
    public int queryEmployeePersonalDataForExist(@Param("Id_Card") String Id_Card);

    /**
     * 查询最后一条记录
     * @return
     */
    public EmployeePersonalData queryEmployeePersonalDataLastData();

    /**
     * 查询employee_personal_data表有而employee_department_data表没有的job_number
     * @param Status
     * @return
     */
    public List<EmployeePersonalData> queryEmployeePersonalDataNotExistJobNumberForEmployeeDepartmentData(@Param("Status") String Status);

    /**
     * 检查上传时工号是否正确
     * @param Job_Number
     * @param Status
     * @return
     */
    public int queryEmployeePersonalDataForUploadJobNumber(@Param("Job_Number") String Job_Number,@Param("Status") String Status);
}
