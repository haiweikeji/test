package hwkj.hwkj.entity.HR;

import javax.persistence.*;

@Table(name = "hwkj_employee_configuration")
public class EmployeeConfiguration {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 工作编号
     */
    @Column(name = "Job_Number")
    private String jobNumber;

    /**
     * 菜单栏
     */
    @Column(name = "Menu_Bar")
    private String menuBar;

    /**
     * org日期
     */
    @Column(name = "Org_Date")
    private String orgDate;

    /**
     * 标题日期
     */
    @Column(name = "Title_Date")
    private String titleDate;

    /**
     * 员工个人资料
     */
    @Column(name = "Employee_Personal_Data")
    private String employeePersonalData;

    /**
     * 员工部门日期
     */
    @Column(name = "Employee_Department_Date")
    private String employeeDepartmentDate;

    /**
     * 员工资格日期
     */
    @Column(name = "Employee_Qualification_Date")
    private String employeeQualificationDate;

    /**
     * 员工绩效
     */
    @Column(name = "Employee_Performance")
    private String employeePerformance;

    /**
     * 员工日期搜索
     */
    @Column(name = "Employee_Date_Search")
    private String employeeDateSearch;

    /**
     * 获取主键
     *
     * @return Id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取工作编号
     *
     * @return Job_Number - 工作编号
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * 设置工作编号
     *
     * @param jobNumber 工作编号
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * 获取菜单栏
     *
     * @return Menu_Bar - 菜单栏
     */
    public String getMenuBar() {
        return menuBar;
    }

    /**
     * 设置菜单栏
     *
     * @param menuBar 菜单栏
     */
    public void setMenuBar(String menuBar) {
        this.menuBar = menuBar;
    }

    /**
     * 获取org日期
     *
     * @return Org_Date - org日期
     */
    public String getOrgDate() {
        return orgDate;
    }

    /**
     * 设置org日期
     *
     * @param orgDate org日期
     */
    public void setOrgDate(String orgDate) {
        this.orgDate = orgDate;
    }

    /**
     * 获取标题日期
     *
     * @return Title_Date - 标题日期
     */
    public String getTitleDate() {
        return titleDate;
    }

    /**
     * 设置标题日期
     *
     * @param titleDate 标题日期
     */
    public void setTitleDate(String titleDate) {
        this.titleDate = titleDate;
    }

    /**
     * 获取员工个人资料
     *
     * @return Employee_Personal_Data - 员工个人资料
     */
    public String getEmployeePersonalData() {
        return employeePersonalData;
    }

    /**
     * 设置员工个人资料
     *
     * @param employeePersonalData 员工个人资料
     */
    public void setEmployeePersonalData(String employeePersonalData) {
        this.employeePersonalData = employeePersonalData;
    }

    /**
     * 获取员工部门日期
     *
     * @return Employee_Department_Date - 员工部门日期
     */
    public String getEmployeeDepartmentDate() {
        return employeeDepartmentDate;
    }

    /**
     * 设置员工部门日期
     *
     * @param employeeDepartmentDate 员工部门日期
     */
    public void setEmployeeDepartmentDate(String employeeDepartmentDate) {
        this.employeeDepartmentDate = employeeDepartmentDate;
    }

    /**
     * 获取员工资格日期
     *
     * @return Employee_Qualification_Date - 员工资格日期
     */
    public String getEmployeeQualificationDate() {
        return employeeQualificationDate;
    }

    /**
     * 设置员工资格日期
     *
     * @param employeeQualificationDate 员工资格日期
     */
    public void setEmployeeQualificationDate(String employeeQualificationDate) {
        this.employeeQualificationDate = employeeQualificationDate;
    }

    /**
     * 获取员工绩效
     *
     * @return Employee_Performance - 员工绩效
     */
    public String getEmployeePerformance() {
        return employeePerformance;
    }

    /**
     * 设置员工绩效
     *
     * @param employeePerformance 员工绩效
     */
    public void setEmployeePerformance(String employeePerformance) {
        this.employeePerformance = employeePerformance;
    }

    /**
     * 获取员工日期搜索
     *
     * @return Employee_Date_Search - 员工日期搜索
     */
    public String getEmployeeDateSearch() {
        return employeeDateSearch;
    }

    /**
     * 设置员工日期搜索
     *
     * @param employeeDateSearch 员工日期搜索
     */
    public void setEmployeeDateSearch(String employeeDateSearch) {
        this.employeeDateSearch = employeeDateSearch;
    }
}