package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.EmployeePunchData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeePunchDataDao {

    /**
     * 分页集合
     * @param employeePunchDataPageModel
     * @param employeePunchData
     * @return
     */
    public List<EmployeePunchData> queryEmployeePunchDataList(@Param("employeePunchDataPageModel") PageModel<EmployeePunchData> employeePunchDataPageModel, @Param("employeePunchData") EmployeePunchData employeePunchData);

    /**
     * 分页总记录数
     * @param employeePunchDataPageModel
     * @param employeePunchData
     * @return
     */
    public int queryEmployeePunchDataCount(@Param("employeePunchDataPageModel") PageModel<EmployeePunchData> employeePunchDataPageModel,@Param("employeePunchData") EmployeePunchData employeePunchData);

    /**
     * for download all
     * @param employeePunchData
     * @return
     */
    public List<EmployeePunchData> queryEmployeePunchDataForDownLoadAll(@Param("employeePunchData") EmployeePunchData employeePunchData);

    /**
     * for download
     * @param Job_Number
     * @return
     */
    public List<EmployeePunchData> queryEmployeePunchDataForDownLoad(String Job_Number[]);

    /**
     * 上传数据
     * @param employeePunchDataList
     * @return
     */
    public int uploadEmployeePunchData(List<EmployeePunchData> employeePunchDataList);
}
