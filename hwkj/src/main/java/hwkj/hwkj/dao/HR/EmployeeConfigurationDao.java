package hwkj.hwkj.dao.HR;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeConfigurationDao {


    /**
     * 根据员工工号查询
     * @param Job_Number
     * @return
     */
    public Integer selectEmployeeConfigurationByJobNumber(@Param("Job_Number") String Job_Number);

    /**
     * 插入数据
     * @param Job_Number
     * @param Menu_Bar
     * @return
     */
    public Integer insertEmployeeConfiguration(@Param("Job_Number") String Job_Number,@Param("Menu_Bar") String Menu_Bar);
}
