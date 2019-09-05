package hwkj.hwkj.entity.HR;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_org_data")
public class OrgData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分组名称
     */
    @Column(name = "Group_Name")
    private String groupName;

    /**
     * 组编号
     */
    @Column(name = "Group_Code")
    private String groupCode;

    /**
     * 法律名称
     */
    @Column(name = "Legal_Name")
    private String legalName;

    /**
     * 公司代码
     */
    @Column(name = "Company_Code")
    private String companyCode;

    @Column(name = "BG")
    private String bg;

    @Column(name = "BU")
    private String bu;

    /**
     * 地区
     */
    @Column(name = "Region")
    private String region;

    /**
     * 部门
     */
    @Column(name = "Dept")
    private String dept;

    @Column(name = "Ke")
    private String ke;

    @Column(name = "Zu")
    private String zu;

    /**
     * 成本代码
     */
    @Column(name = "Cost_Code")
    private String costCode;

    /**
     * 组织代码
     */
    @Column(name = "Org_Code")
    private String orgCode;

    /**
     * 上级名称
     */
    @Column(name = "Upper_Name")
    private String upperName;

    /**
     * 上级编号
     */
    @Column(name = "Upper_ID")
    private String upperId;

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
    private String updateDate;

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
     * 获取分组名称
     *
     * @return Group_Name - 分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组名称
     *
     * @param groupName 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取组编号
     *
     * @return Group_Code - 组编号
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置组编号
     *
     * @param groupCode 组编号
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取法律名称
     *
     * @return Legal_Name - 法律名称
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置法律名称
     *
     * @param legalName 法律名称
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取公司代码
     *
     * @return Company_Code - 公司代码
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置公司代码
     *
     * @param companyCode 公司代码
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * @return BG
     */
    public String getBg() {
        return bg;
    }

    /**
     * @param bg
     */
    public void setBg(String bg) {
        this.bg = bg;
    }

    /**
     * @return BU
     */
    public String getBu() {
        return bu;
    }

    /**
     * @param bu
     */
    public void setBu(String bu) {
        this.bu = bu;
    }

    /**
     * 获取地区
     *
     * @return Region - 地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置地区
     *
     * @param region 地区
     */
    public void setRegion(String region) {
        this.region = region;
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

    /**
     * @return Ke
     */
    public String getKe() {
        return ke;
    }

    /**
     * @param ke
     */
    public void setKe(String ke) {
        this.ke = ke;
    }

    /**
     * @return Zu
     */
    public String getZu() {
        return zu;
    }

    /**
     * @param zu
     */
    public void setZu(String zu) {
        this.zu = zu;
    }

    /**
     * 获取成本代码
     *
     * @return Cost_Code - 成本代码
     */
    public String getCostCode() {
        return costCode;
    }

    /**
     * 设置成本代码
     *
     * @param costCode 成本代码
     */
    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    /**
     * 获取组织代码
     *
     * @return Org_Code - 组织代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置组织代码
     *
     * @param orgCode 组织代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取上级名称
     *
     * @return Upper_Name - 上级名称
     */
    public String getUpperName() {
        return upperName;
    }

    /**
     * 设置上级名称
     *
     * @param upperName 上级名称
     */
    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    /**
     * 获取上级编号
     *
     * @return Upper_ID - 上级编号
     */
    public String getUpperId() {
        return upperId;
    }

    /**
     * 设置上级编号
     *
     * @param upperId 上级编号
     */
    public void setUpperId(String upperId) {
        this.upperId = upperId;
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
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}