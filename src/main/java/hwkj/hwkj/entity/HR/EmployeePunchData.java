package hwkj.hwkj.entity.HR;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "hwkj_employee_punch_data")
@Getter
@Setter
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
     * 在。。。地方
     */
    @Column(name = "Station")
    private String Station;


}