package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.EmployeePunchData;
import hwkj.hwkj.entity.pagingquery.PageModel;

import java.util.List;

public interface EmployeePunchDataService {

    /**
     * 分页查询
     * @param employeePunchDataPageModel
     * @param employeePunchData
     */
    public void queryEmployeePunchDataPage(PageModel<EmployeePunchData> employeePunchDataPageModel, EmployeePunchData employeePunchData);

    /**
     * for download all
     * @param employeePunchData
     * @return
     */
    public List<EmployeePunchData> queryEmployeePunchDataForDownLoadAll(EmployeePunchData employeePunchData);

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
    public boolean uploadEmployeePunchData(List<EmployeePunchData> employeePunchDataList);
}
