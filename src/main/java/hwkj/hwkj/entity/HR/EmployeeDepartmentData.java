package hwkj.hwkj.entity.HR;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_employee_department_data")
@Getter
@Setter
public class EmployeeDepartmentData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 工号
     */
    @Column(name = "Job_Number")
    private String jobNumber;
    /**
     * 中文名字
     */
    @Column(name = "China_Name")
    private String ChinaName;
    /**
     * 英文 名字
     */
    @Column(name = "English_Name")
    private String  EnglishName;
    /**
     * 年龄
     */
    @Column(name = "Age")
    private int Age;
    /**
     * 性别
     */
    @Column(name = "Sex")
    private String Sex;
    /**
     * 手机号码
     */
    @Column(name = "Phone_Number")
    private  int PhoneNumber;
    /**
     * 微信账号
     */
    @Column(name = "WeChat_Number")
    private int WeChatNumber;
    /**
     * 公司邮件
     */
    @Column(name = "Company_Mail")
    private String CompanyMail;
    /**
     * 输入日期
     */
    @Column(name = "Entry_Date")
    private Date EntryDate;
    /**
     * 法定名称
     */
    @Column(name = "Legal_Name")
    private String LegalName;

    /**
     * 管理职
     */
    @Column(name = "Management")
    private String management;

    /**
     * 组织代码
     */
    @Column(name = "Org_Code")
    private String orgCode;
    /**
     * 公司背景
     */
    @Column(name = "BG")
    private String Bg;
    @Column(name = "BU")
    private String Bu;




    /**
     * 任职类型
     */
    @Column(name = "Job_Type")
    private String jobType;

    /**
     * 开始任职时间
     */
    @Column(name = "Start_Service")
    private String startService;

    /**
     * 任职结束时间
     */
    @Column(name = "End_Service")
    private String endService;

    /**
     * 登录位置
     */
    @Column(name = "Entry_Position")
    private String entryPosition;

    /**
     * 职系
     */
    @Column(name = "Job_Family")
    private String jobFamily;

    /**
     * 岗位
     */
    @Column(name = "Station")
    private String station;

    /**
     * 调动原因
     */
    @Column(name = "Move_Reason")
    private String moveReason;

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
     * 审核者
     */
    @Column(name = "Reviewed_By")
    private String reviewedBy;

    /**
     * 审核日期
     */
    @Column(name = "Reviewed_Date")
    private Date reviewedDate;

    /**
     * 批准者
     */
    @Column(name = "Approved_By")
    private String approvedBy;

    /**
     * 批准时间
     */
    @Column(name = "Approved_Date")
    private Date approvedDate;
    /**
     * 有效位置
     */
    @Column(name = "Effective_Position")
    private String EffectivePosition;

    }