package hwkj.hwkj.entity.HR;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_title_data")
public class TitleData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 管理
     */
    @Column(name = "Management")
    private String management;

    /**
     * 管理代码
     */
    @Column(name = "Management_Code")
    private String managementCode;

    /**
     * 上级
     */
    @Column(name = "Upper")
    private String upper;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    /**
     * 创建者
     */
    @Column(name = "Creator")
    private String creator;

    /**
     * 创建时间 
     */
    @Column(name = "Create_Date")
    private Date createDate;

    /**
     * 更新者
     */
    @Column(name = "Updated_By")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "Update_Date")
    private Date updateDate;

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
     * 获取管理
     *
     * @return Management - 管理
     */
    public String getManagement() {
        return management;
    }

    /**
     * 设置管理
     *
     * @param management 管理
     */
    public void setManagement(String management) {
        this.management = management;
    }

    /**
     * 获取管理代码
     *
     * @return Management_Code - 管理代码
     */
    public String getManagementCode() {
        return managementCode;
    }

    /**
     * 设置管理代码
     *
     * @param managementCode 管理代码
     */
    public void setManagementCode(String managementCode) {
        this.managementCode = managementCode;
    }

    /**
     * 获取上级
     *
     * @return Upper - 上级
     */
    public String getUpper() {
        return upper;
    }

    /**
     * 设置上级
     *
     * @param upper 上级
     */
    public void setUpper(String upper) {
        this.upper = upper;
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

    /**
     * 获取创建者
     *
     * @return Creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间 
     *
     * @return Create_Date - 创建时间 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间 
     *
     * @param createDate 创建时间 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新者
     *
     * @return Updated_By - 更新者
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新者
     *
     * @param updatedBy 更新者
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取更新时间
     *
     * @return Update_Date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}