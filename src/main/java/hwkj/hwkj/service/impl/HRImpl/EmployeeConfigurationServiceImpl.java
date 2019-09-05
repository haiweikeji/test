package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.EmployeeConfigurationDao;
import hwkj.hwkj.service.HR.EmployeeConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConfigurationServiceImpl implements EmployeeConfigurationService {
    @Autowired
    private EmployeeConfigurationDao EmployeeConfigurationService;

    /**
     * 根据员工工号查询
     * @param Job_Number
     * @return
     */
    @Override
    public Integer selectEmployeeConfigurationByJobNumber(String Job_Number) {
        return EmployeeConfigurationService.selectEmployeeConfigurationByJobNumber(Job_Number);
    }

    /**
     * 插入数据
     * @param Job_Number
     * @param Menu_Bar
     * @return
     * @throws RuntimeException
     */
    @Override
    public boolean insertEmployeeConfiguration(String Job_Number, String Menu_Bar) throws RuntimeException{
        int eff=EmployeeConfigurationService.insertEmployeeConfiguration(Job_Number, Menu_Bar);
        if(eff>0){
            return true;
        }
        return false;
    }
}
