package hwkj.hwkj.entity.HUser;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hwkj_user")
public class User {
    /**
     * 工作编号
     */
    @Id
    @Column(name = "Job_Number")
    private String jobNumber;

    /**
     * 用户名
     */
    @Column(name = "Name")
    private String name;

    /**
     * 密码
     */
    @Column(name = "Password")
    private String password;

    /**
     * 密码确认
     */
    @Column(name = "RePassword")
    private String repassword;

    /**
     * 实际地址
     */
    @Column(name = "Physical_Address")
    private String physicalAddress;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

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
     * 获取用户名
     *
     * @return Name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return Password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码确认
     *
     * @return RePassword - 密码确认
     */
    public String getRepassword() {
        return repassword;
    }

    /**
     * 设置密码确认
     *
     * @param repassword 密码确认
     */
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    /**
     * 获取实际地址
     *
     * @return Physical_Address - 实际地址
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * 设置实际地址
     *
     * @param physicalAddress 实际地址
     */
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    /**
     * 获取状态
     *
     * @return Status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
}