package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.EmployeeDataSearch;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeDataSearchDao {

    /**
     * 分页查询List
     * @param employeeDataSearchPageModel
     * @param employeeDataSearch
     * @return
     */
    public List<EmployeeDataSearch> queryEmployeeDataSearchList(@Param("employeeDataSearchPageModel")PageModel<EmployeeDataSearch> employeeDataSearchPageModel, @Param("employeeDataSearch") EmployeeDataSearch employeeDataSearch);

    /**
     * 分页查询count
     * @param employeeDataSearchPageModel
     * @param employeeDataSearch
     * @return
     */
    public int queryEmployeeDataSearchCount(@Param("employeeDataSearchPageModel")PageModel<EmployeeDataSearch> employeeDataSearchPageModel, @Param("employeeDataSearch") EmployeeDataSearch employeeDataSearch);

    /**
     * for download all
     * @param employeeDataSearch
     * @return
     */
    public List<EmployeeDataSearch> queryEmployeeDataSearchForDownLoadAll(@Param("employeeDataSearch") EmployeeDataSearch employeeDataSearch);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<EmployeeDataSearch> queryEmployeeDataSearchForDownLoad(Integer Id[]);

}
