package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.EmployeeDataSearch;
import hwkj.hwkj.entity.pagingquery.PageModel;

import java.util.List;

public interface EmployeeDataSearchService {

    /**
     * 分页查询List
     * @param employeeDataSearchPageModel
     * @param employeeDataSearch
     * @return
     */
    public void queryEmployeeDataSearchPage(PageModel<EmployeeDataSearch> employeeDataSearchPageModel,EmployeeDataSearch employeeDataSearch);

    /**
     * for download all
     * @param employeeDataSearch
     * @return
     */
    public List<EmployeeDataSearch> queryEmployeeDataSearchForDownLoadAll(EmployeeDataSearch employeeDataSearch);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<EmployeeDataSearch> queryEmployeeDataSearchForDownLoad(Integer Id[]);


}
