package hwkj.hwkj.entity.CRM;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_visit_plan")
public class CustomerVisitPlan {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 访问ID
     */
    @Column(name = "Visit_Id")
    private String visitId;

    /**
     * 客户代码
     */
    @Column(name = "Customer_Code")
    private String customerCode;

    /**
     * 计划开始时间
     */
    @Column(name = "Plan_Date_From")
    private Date planDateFrom;

    /**
     * 计划结束时间
     */
    @Column(name = "Plan_Date_To")
    private Date planDateTo;

    /**
     * 实际开始时间
     */
    @Column(name = "Actual_Start_Time")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @Column(name = "Actual_End_Time")
    private Date actualEndTime;

    /**
     * 联系人中文名
     */
    @Column(name = "Contact_Chinese_Name")
    private String contactChineseName;

    /**
     * 联系人英文名
     */
    @Column(name = "Contact_English_Name")
    private String contactEnglishName;

    /**
     * 职级
     */
    @Column(name = "Title")
    private String title;

    /**
     * 部门
     */
    @Column(name = "Dept")
    private String dept;

    /**
     * 拜访事项
     */
    @Column(name = "Visit_Item")
    private String visitItem;

    /**
     * 拜访目的
     */
    @Column(name = "Visit_Purpose")
    private String visitPurpose;

    /**
     * 拜访结果
     */
    @Column(name = "Visit_Result")
    private String visitResult;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    /**
     * 业务员
     */
    @Column(name = "BPM")
    private String bpm;

    /**
     * 中文缩写
     */
    @Column(name = "Chinaese_Abbreviation")
    private String chinaeseAbbreviation;
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
     * 审核人
     */
    @Column(name = "Approved_By")
    private String approvedBy;

    /**
     * 审核日期
     */
    @Column(name = "Approved_Date")
    private Date approvedDate;
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
     * 获取访问ID
     *
     * @return Visit_Id - 访问ID
     */
    public String getVisitId() {
        return visitId;
    }

    /**
     * 设置访问ID
     *
     * @param visitId 访问ID
     */
    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    /**
     * 获取客户代码
     *
     * @return Customer_Code - 客户代码
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * 设置客户代码
     *
     * @param customerCode 客户代码
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * 获取计划开始时间
     *
     * @return Plan_Date_From - 计划开始时间
     */
    public Date getPlanDateFrom() {
        return planDateFrom;
    }

    /**
     * 设置计划开始时间
     *
     * @param planDateFrom 计划开始时间
     */
    public void setPlanDateFrom(Date planDateFrom) {
        this.planDateFrom = planDateFrom;
    }

    /**
     * 获取计划结束时间
     *
     * @return Plan_Date_To - 计划结束时间
     */
    public Date getPlanDateTo() {
        return planDateTo;
    }

    /**
     * 设置计划结束时间
     *
     * @param planDateTo 计划结束时间
     */
    public void setPlanDateTo(Date planDateTo) {
        this.planDateTo = planDateTo;
    }

    /**
     * 获取实际开始时间
     *
     * @return Actual_Start_Time - 实际开始时间
     */
    public Date getActualStartTime() {
        return actualStartTime;
    }

    /**
     * 设置实际开始时间
     *
     * @param actualStartTime 实际开始时间
     */
    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    /**
     * 获取实际结束时间
     *
     * @return Actual_End_Time - 实际结束时间
     */
    public Date getActualEndTime() {
        return actualEndTime;
    }

    /**
     * 设置实际结束时间
     *
     * @param actualEndTime 实际结束时间
     */
    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    /**
     * 获取联系人中文名
     *
     * @return Contact_Chinese_Name - 联系人中文名
     */
    public String getContactChineseName() {
        return contactChineseName;
    }

    /**
     * 设置联系人中文名
     *
     * @param contactChineseName 联系人中文名
     */
    public void setContactChineseName(String contactChineseName) {
        this.contactChineseName = contactChineseName;
    }

    /**
     * 获取联系人英文名
     *
     * @return Contact_English_Name - 联系人英文名
     */
    public String getContactEnglishName() {
        return contactEnglishName;
    }

    /**
     * 设置联系人英文名
     *
     * @param contactEnglishName 联系人英文名
     */
    public void setContactEnglishName(String contactEnglishName) {
        this.contactEnglishName = contactEnglishName;
    }

    /**
     * 获取职级
     *
     * @return Title - 职级
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置职级
     *
     * @param title 职级
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取拜访事项
     *
     * @return Visit_Item - 拜访事项
     */
    public String getVisitItem() {
        return visitItem;
    }

    /**
     * 设置拜访事项
     *
     * @param visitItem 拜访事项
     */
    public void setVisitItem(String visitItem) {
        this.visitItem = visitItem;
    }

    /**
     * 获取拜访目的
     *
     * @return Visit_Purpose - 拜访目的
     */
    public String getVisitPurpose() {
        return visitPurpose;
    }

    /**
     * 设置拜访目的
     *
     * @param visitPurpose 拜访目的
     */
    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    /**
     * 获取拜访结果
     *
     * @return Visit_Result - 拜访结果
     */
    public String getVisitResult() {
        return visitResult;
    }

    /**
     * 设置拜访结果
     *
     * @param visitResult 拜访结果
     */
    public void setVisitResult(String visitResult) {
        this.visitResult = visitResult;
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
     * 获取业务员
     *
     * @return BPM - 业务员
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * 设置业务员
     *
     * @param bpm 业务员
     */
    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getChinaeseAbbreviation() {
        return chinaeseAbbreviation;
    }

    public void setChinaeseAbbreviation(String chinaeseAbbreviation) {
        this.chinaeseAbbreviation = chinaeseAbbreviation;
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

    /**
     * 获取审核人
     *
     * @return Approved_By - 审核人
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * 设置审核人
     *
     * @param approvedBy 审核人
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * 获取审核日期
     *
     * @return Approved_Date - 审核日期
     */
    public Date getApprovedDate() {
        return approvedDate;
    }

    /**
     * 设置审核日期
     *
     * @param approvedDate 审核日期
     */
    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

}