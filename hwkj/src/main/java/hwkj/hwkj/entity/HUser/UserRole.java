package hwkj.hwkj.entity.HUser;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hwkj_user_role")
public class UserRole {
    /**
     * 用户权限ID
     */
    @Id
    @Column(name = "User_Role_Id")
    private Integer userRoleId;

    /**
     * 工号
     */
    @Column(name = "Job_Number")
    private String jobNumber;

    /**
     * 权限名称
     */
    @Column(name = "Role_Name")
    private String roleName;

    /**
     * 获取用户权限ID
     *
     * @return User_Role_Id - 用户权限ID
     */
    public Integer getUserRoleId() {
        return userRoleId;
    }

    /**
     * 设置用户权限ID
     *
     * @param userRoleId 用户权限ID
     */
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

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
     * 获取权限名称
     *
     * @return Role_Name - 权限名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置权限名称
     *
     * @param roleName 权限名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}