package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.EmployeeDataSearchDao;
import hwkj.hwkj.entity.HR.EmployeeDataSearch;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.service.HR.EmployeeDataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDataSearchServiceImpl implements EmployeeDataSearchService {
    @Autowired
    private EmployeeDataSearchDao employeeDataSearchDao;

    /**
     * 分页查询List
     *
     * @param employeeDataSearchPageModel
     * @param employeeDataSearch
     * @return
     */
    @Override
    public void queryEmployeeDataSearchPage(PageModel<EmployeeDataSearch> employeeDataSearchPageModel, EmployeeDataSearch employeeDataSearch) {
        employeeDataSearchPageModel.setList(employeeDataSearchDao.queryEmployeeDataSearchList(employeeDataSearchPageModel, employeeDataSearch));
        employeeDataSearchPageModel.setTotal(employeeDataSearchDao.queryEmployeeDataSearchCount(employeeDataSearchPageModel, employeeDataSearch));
    }

    /**
     * for download all
     *
     * @param employeeDataSearch
     * @return
     */
    @Override
    public List<EmployeeDataSearch> queryEmployeeDataSearchForDownLoadAll(EmployeeDataSearch employeeDataSearch) {
        return employeeDataSearchDao.queryEmployeeDataSearchForDownLoadAll(employeeDataSearch);
    }

    /**
     * for download
     *
     * @param Id
     * @return
     */
    @Override
    public List<EmployeeDataSearch> queryEmployeeDataSearchForDownLoad(Integer Id[]) {
        return employeeDataSearchDao.queryEmployeeDataSearchForDownLoad(Id);
    }
}
