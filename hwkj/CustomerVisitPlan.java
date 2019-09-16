package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_visit_plan")
@Getter
@Setter
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

}