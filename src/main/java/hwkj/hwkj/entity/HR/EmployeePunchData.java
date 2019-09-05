package hwkj.hwkj.entity.HR;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "hwkj_employee_punch_data")
public class EmployeePunchData {
    /**
     * 工号
     */
    @Column(name = "Job_Number")
    private String jobNumber;

    /**
     * 考勤日期
     */
    @Column(name = "Attendance_Date")
    private String attendanceDate;

    /**
     * 早上上班时间
     */
    @Column(name = "Morning_Work_Time")
    private String morningWorkTime;

    /**
     * 早上打卡时间
     */
    @Column(name = "Morning_Punch_Time")
    private String morningPunchTime;

    /**
     * 早上打卡结果
     */
    @Column(name = "Morning_Punch_Result")
    private String morningPunchResult;

    /**
     * 早上打卡地址
     */
    @Column(name = "Morning_Punch_Address")
    private String morningPunchAddress;

    /**
     * 早上打卡设备
     */
    @Column(name = "Morning_Punch_Equipment")
    private String morningPunchEquipment;

    /**
     * 下午上班时间
     */
    @Column(name = "Afternoon_Work_Time")
    private String afternoonWorkTime;

    /**
     * 下午打卡时间
     */
    @Column(name = "Afternoon_Punch_Time")
    private String afternoonPunchTime;

    /**
     * 下午打卡结果
     */
    @Column(name = "Afternoon_Punch_Result")
    private String afternoonPunchResult;

    /**
     * 下午打卡地点
     */
    @Column(name = "Afternoon_Punch_Address")
    private String afternoonPunchAddress;

    /**
     * 下午打卡设备
     */
    @Column(name = "Afternoon_Punch_Equipment")
    private String afternoonPunchEquipment;

    /**
     * 迟到总次数
     */
    @Column(name = "Later_Amount")
    private Integer laterAmount;

    /**
     * 迟到总时间
     */
    @Column(name = "Later_Time")
    private String laterTime;

    /**
     * 早退总次数
     */
    @Column(name = "Early_Amount")
    private Integer earlyAmount;

    /**
     * 早退总时间
     */
    @Column(name = "Early_Time")
    private String earlyTime;

    /**
     * 姓名
     */
    @Column(name = "Name")
    private String name;

    /**
     * 部门
     */
    @Column(name = "Dept")
    private String dept;

    /**
     * 获取工号
     *
     * @return Job_Number - 工号
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * 设置工号
     *
     * @param jobNumber 工号
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * 获取考勤日期
     *
     * @return Attendance_Date - 考勤日期
     */
    public String getAttendanceDate() {
        return attendanceDate;
    }

    /**
     * 设置考勤日期
     *
     * @param attendanceDate 考勤日期
     */
    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    /**
     * 获取早上上班时间
     *
     * @return Morning_Work_Time - 早上上班时间
     */
    public String getMorningWorkTime() {
        return morningWorkTime;
    }

    /**
     * 设置早上上班时间
     *
     * @param morningWorkTime 早上上班时间
     */
    public void setMorningWorkTime(String morningWorkTime) {
        this.morningWorkTime = morningWorkTime;
    }

    /**
     * 获取早上打卡时间
     *
     * @return Morning_Punch_Time - 早上打卡时间
     */
    public String getMorningPunchTime() {
        return morningPunchTime;
    }

    /**
     * 设置早上打卡时间
     *
     * @param morningPunchTime 早上打卡时间
     */
    public void setMorningPunchTime(String morningPunchTime) {
        this.morningPunchTime = morningPunchTime;
    }

    /**
     * 获取早上打卡结果
     *
     * @return Morning_Punch_Result - 早上打卡结果
     */
    public String getMorningPunchResult() {
        return morningPunchResult;
    }

    /**
     * 设置早上打卡结果
     *
     * @param morningPunchResult 早上打卡结果
     */
    public void setMorningPunchResult(String morningPunchResult) {
        this.morningPunchResult = morningPunchResult;
    }

    /**
     * 获取早上打卡地址
     *
     * @return Morning_Punch_Address - 早上打卡地址
     */
    public String getMorningPunchAddress() {
        return morningPunchAddress;
    }

    /**
     * 设置早上打卡地址
     *
     * @param morningPunchAddress 早上打卡地址
     */
    public void setMorningPunchAddress(String morningPunchAddress) {
        this.morningPunchAddress = morningPunchAddress;
    }

    /**
     * 获取早上打卡设备
     *
     * @return Morning_Punch_Equipment - 早上打卡设备
     */
    public String getMorningPunchEquipment() {
        return morningPunchEquipment;
    }

    /**
     * 设置早上打卡设备
     *
     * @param morningPunchEquipment 早上打卡设备
     */
    public void setMorningPunchEquipment(String morningPunchEquipment) {
        this.morningPunchEquipment = morningPunchEquipment;
    }

    /**
     * 获取下午上班时间
     *
     * @return Afternoon_Work_Time - 下午上班时间
     */
    public String getAfternoonWorkTime() {
        return afternoonWorkTime;
    }

    /**
     * 设置下午上班时间
     *
     * @param afternoonWorkTime 下午上班时间
     */
    public void setAfternoonWorkTime(String afternoonWorkTime) {
        this.afternoonWorkTime = afternoonWorkTime;
    }

    /**
     * 获取下午打卡时间
     *
     * @return Afternoon_Punch_Time - 下午打卡时间
     */
    public String getAfternoonPunchTime() {
        return afternoonPunchTime;
    }

    /**
     * 设置下午打卡时间
     *
     * @param afternoonPunchTime 下午打卡时间
     */
    public void setAfternoonPunchTime(String afternoonPunchTime) {
        this.afternoonPunchTime = afternoonPunchTime;
    }

    /**
     * 获取下午打卡结果
     *
     * @return Afternoon_Punch_Result - 下午打卡结果
     */
    public String getAfternoonPunchResult() {
        return afternoonPunchResult;
    }

    /**
     * 设置下午打卡结果
     *
     * @param afternoonPunchResult 下午打卡结果
     */
    public void setAfternoonPunchResult(String afternoonPunchResult) {
        this.afternoonPunchResult = afternoonPunchResult;
    }

    /**
     * 获取下午打卡地点
     *
     * @return Afternoon_Punch_Address - 下午打卡地点
     */
    public String getAfternoonPunchAddress() {
        return afternoonPunchAddress;
    }

    /**
     * 设置下午打卡地点
     *
     * @param afternoonPunchAddress 下午打卡地点
     */
    public void setAfternoonPunchAddress(String afternoonPunchAddress) {
        this.afternoonPunchAddress = afternoonPunchAddress;
    }

    /**
     * 获取下午打卡设备
     *
     * @return Afternoon_Punch_Equipment - 下午打卡设备
     */
    public String getAfternoonPunchEquipment() {
        return afternoonPunchEquipment;
    }

    /**
     * 设置下午打卡设备
     *
     * @param afternoonPunchEquipment 下午打卡设备
     */
    public void setAfternoonPunchEquipment(String afternoonPunchEquipment) {
        this.afternoonPunchEquipment = afternoonPunchEquipment;
    }

    /**
     * 获取迟到总次数
     *
     * @return Later_Amount - 迟到总次数
     */
    public Integer getLaterAmount() {
        return laterAmount;
    }

    /**
     * 设置迟到总次数
     *
     * @param laterAmount 迟到总次数
     */
    public void setLaterAmount(Integer laterAmount) {
        this.laterAmount = laterAmount;
    }

    /**
     * 获取迟到总时间
     *
     * @return Later_Time - 迟到总时间
     */
    public String getLaterTime() {
        return laterTime;
    }

    /**
     * 设置迟到总时间
     *
     * @param laterTime 迟到总时间
     */
    public void setLaterTime(String laterTime) {
        this.laterTime = laterTime;
    }

    /**
     * 获取早退总次数
     *
     * @return Early_Amount - 早退总次数
     */
    public Integer getEarlyAmount() {
        return earlyAmount;
    }

    /**
     * 设置早退总次数
     *
     * @param earlyAmount 早退总次数
     */
    public void setEarlyAmount(Integer earlyAmount) {
        this.earlyAmount = earlyAmount;
    }

    /**
     * 获取早退总时间
     *
     * @return Early_Time - 早退总时间
     */
    public String getEarlyTime() {
        return earlyTime;
    }

    /**
     * 设置早退总时间
     *
     * @param earlyTime 早退总时间
     */
    public void setEarlyTime(String earlyTime) {
        this.earlyTime = earlyTime;
    }

    /**
     * 获取姓名
     *
     * @return Name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取部门
     *
     * @return Dept - 部门
     */
    public String getDept() {
        return dept;
    }

    /**
     * 设置部门
     *
     * @param dept 部门
     */
    public void setDept(String dept) {
        this.dept = dept;
    }
}