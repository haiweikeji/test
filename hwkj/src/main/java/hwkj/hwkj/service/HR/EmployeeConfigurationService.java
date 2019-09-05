package hwkj.hwkj.service.HR;


public interface EmployeeConfigurationService {

    /**
     * 根据员工工号查询
     * @param Job_Number
     * @return
     */
    public Integer selectEmployeeConfigurationByJobNumber(String Job_Number);

    /**
     * 插入数据
     * @param Job_Number
     * @param Menu_Bar
     * @return
     */
    public boolean insertEmployeeConfiguration(String Job_Number,String Menu_Bar);

}
