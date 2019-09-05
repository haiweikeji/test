package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.EmployeePunchDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.service.HR.EmployeePunchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeePunchDataServiceImpl implements EmployeePunchDataService {

    @Autowired
    private EmployeePunchDataDao employeePunchDataDao;
    /**
     * 分页查询
     * @param employeePunchDataPageModel
     * @param employeePunchData
     */
    @Override
    public void queryEmployeePunchDataPage(PageModel<EmployeePunchData> employeePunchDataPageModel, EmployeePunchData employeePunchData) {
        employeePunchDataPageModel.setList(employeePunchDataDao.queryEmployeePunchDataList(employeePunchDataPageModel,employeePunchData));
        employeePunchDataPageModel.setTotal(employeePunchDataDao.queryEmployeePunchDataCount(employeePunchDataPageModel,employeePunchData));
    }

    /**
     * for download all
     * @param employeePunchData
     * @return
     */
    @Override
    public List<EmployeePunchData> queryEmployeePunchDataForDownLoadAll(EmployeePunchData employeePunchData) {
        return employeePunchDataDao.queryEmployeePunchDataForDownLoadAll(employeePunchData);
    }

    /**
     * for download
     * @param Job_Number
     * @return
     */
    @Override
    public List<EmployeePunchData> queryEmployeePunchDataForDownLoad(String Job_Number[]) {
        return employeePunchDataDao.queryEmployeePunchDataForDownLoad(Job_Number);
    }

    /**
     * 上传数据
     * @param employeePunchDataList
     * @return
     * @throws RuntimeException
     */
    @Transactional
    @Override
    public boolean uploadEmployeePunchData(List<EmployeePunchData> employeePunchDataList) throws RuntimeException{
        int eff=employeePunchDataDao.uploadEmployeePunchData(employeePunchDataList);
        if(eff>0){
            return true;
        }
        return false;
    }
}
