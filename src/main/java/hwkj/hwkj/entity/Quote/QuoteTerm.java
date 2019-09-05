package hwkj.hwkj.entity.Quote;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_quote_term")
public class QuoteTerm {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 条件类别
     */
    @Column(name = "Condition_Type")
    private String conditionType;

    /**
     * 条件代码
     */
    @Column(name = "Condition_Code")
    private String conditionCode;

    /**
     * 条款
     */
    @Column(name = "Clause")
    private String clause;

    /**
     * 描述
     */
    @Column(name = "Described")
    private String described;

    /**
     * 资金占用天数
     */
    @Column(name = "Days")
    private Integer days;

    /**
     * 百分比
     */
    @Column(name = "Percent")
    private String percent;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    /**
     * 备注
     */
    @Column(name = "Remark")
    private String remark;

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
     * 获取条件类别
     *
     * @return Condition_Type - 条件类别
     */
    public String getConditionType() {
        return conditionType;
    }

    /**
     * 设置条件类别
     *
     * @param conditionType 条件类别
     */
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    /**
     * 获取条件代码
     *
     * @return Condition_Code - 条件代码
     */
    public String getConditionCode() {
        return conditionCode;
    }

    /**
     * 设置条件代码
     *
     * @param conditionCode 条件代码
     */
    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    /**
     * 获取条款
     *
     * @return Clause - 条款
     */
    public String getClause() {
        return clause;
    }

    /**
     * 设置条款
     *
     * @param clause 条款
     */
    public void setClause(String clause) {
        this.clause = clause;
    }

    /**
     * 获取描述
     *
     * @return Described - 描述
     */
    public String getDescribed() {
        return described;
    }

    /**
     * 设置描述
     *
     * @param described 描述
     */
    public void setDescribed(String described) {
        this.described = described;
    }

    /**
     * 获取资金占用天数
     *
     * @return Days - 资金占用天数
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 设置资金占用天数
     *
     * @param days 资金占用天数
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 获取百分比
     *
     * @return Percent - 百分比
     */
    public String getPercent() {
        return percent;
    }

    /**
     * 设置百分比
     *
     * @param percent 百分比
     */
    public void setPercent(String percent) {
        this.percent = percent;
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
     * 获取备注
     *
     * @return Remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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