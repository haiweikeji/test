package hwkj.hwkj.entity.HUser;

import javax.persistence.*;

@Table(name = "hwkj_role")
public class Role {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    @Column(name = "Role_Name")
    private String roleName;

    /**
     * 权限描述
     */
    @Column(name = "Role_Described")
    private String roleDescribed;

    /**
     * 旧权限名称
     */
    @Column(name = "old_role_name")
    private String oldRoleName;

    /**
     * 旧权限描述
     */
    @Column(name = "old_role_described")
    private String oldRoleDescribed;

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

    /**
     * 获取权限描述
     *
     * @return Role_Described - 权限描述
     */
    public String getRoleDescribed() {
        return roleDescribed;
    }

    /**
     * 设置权限描述
     *
     * @param roleDescribed 权限描述
     */
    public void setRoleDescribed(String roleDescribed) {
        this.roleDescribed = roleDescribed;
    }

    /**
     * 获取旧权限名称
     *
     * @return old_role_name - 旧权限名称
     */
    public String getOldRoleName() {
        return oldRoleName;
    }

    /**
     * 设置旧权限名称
     *
     * @param oldRoleName 旧权限名称
     */
    public void setOldRoleName(String oldRoleName) {
        this.oldRoleName = oldRoleName;
    }

    /**
     * 获取旧权限描述
     *
     * @return old_role_described - 旧权限描述
     */
    public String getOldRoleDescribed() {
        return oldRoleDescribed;
    }

    /**
     * 设置旧权限描述
     *
     * @param oldRoleDescribed 旧权限描述
     */
    public void setOldRoleDescribed(String oldRoleDescribed) {
        this.oldRoleDescribed = oldRoleDescribed;
    }
}