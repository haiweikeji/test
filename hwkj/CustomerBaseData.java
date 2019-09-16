package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_base_data")
@Getter
@Setter
public class CustomerBaseData {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户代码
     */
    @Column(name = "Customer_Code")
    private String customerCode;

    /**
     * 四个字母

     */
    @Column(name = "Four_Initials")
    private String fourInitials;

    /**
     * 国籍
     */
    @Column(name = "Country")
    private String country;

    /**
     * 城市
     */
    @Column(name = "City")
    private String city;

    /**
     * 中文全称
     */
    @Column(name = "Chinese_Full_Name")
    private String chineseFullName;

    /**
     * 中文缩写
     */
    @Column(name = "Chinese_Abbreviation")
    private String chineseAbbreviation;

    /**
     * 英文全称
     */
    @Column(name = "English_Full_Name")
    private String englishFullName;

    /**
     * 英文缩写
     */
    @Column(name = "English_Abbreviation")
    private String englishAbbreviation;

    /**
     * 富士康集团
     */
    @Column(name = "Foxconn_Group")
    private String foxconnGroup;

    /**
     * 行业
     */
    @Column(name = "Industry")
    private String industry;

    /**
     * 公司老板
     */
    @Column(name = "Company_Owner")
    private String companyOwner;

    /**
     * 年收入

     */
    @Column(name = "Annual_Revenue")
    private Double annualRevenue;

    /**
     * 行业等级
     */
    @Column(name = "Industry_Rank")
    private String industryRank;

    /**
     * 企业性质
     */
    @Column(name = "Enterprise_Nature")
    private String enterpriseNature;

    /**
     * 员工数量
     */
    @Column(name = "Employee_Qty")
    private Integer employeeQty;

    /**
     * 客户等级
     */
    @Column(name = "Customer_Level")
    private String customerLevel;

    /**
     * 交易条件
     */
    @Column(name = "Deliver_Term")
    private String deliverTerm;

    /**
     * 付款条件
     */
    @Column(name = "Payment_Term")
    private String paymentTerm;

    /**
     * 验收条件
     */
    @Column(name = "Receive_Term")
    private String receiveTerm;

    /**
     * 办公地址
     */
    @Column(name = "Office_Address")
    private String officeAddress;

    /**
     * 注册地址
     */
    @Column(name = "Registered_Address")
    private String registeredAddress;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    /**
     * 开发日期
     */
    @Column(name = "Development_Date")
    private String developmentDate;

    /**
     * 业务流程
     */
    @Column(name = "BPM")
    private String bpm;

    /**
     * 注册资金
     */
    @Column(name = "Registered_Capital")
    private Double registeredCapital;

    /**
     * 币别
     */
    @Column(name = "Currency")
    private String currency;

    /**
     * 信用等级
     */
    @Column(name = "Credit_Level")
    private String creditLevel;

    /**
     * 信用额度
     */
    @Column(name = "credit_Amount")
    private Double creditAmount;

    /**
     * 收入
     */
    @Column(name = "Revenue_From")
    private Double revenueFrom;

    /**
     * 支出
     */
    @Column(name = "Revenue_To")
    private Double revenueTo;

    /**
     * 收入时间
     */
    @Column(name = "Date_From")
    private String dateFrom;

    /**
     * 支出时间
     */
    @Column(name = "Date_To")
    private String dateTo;

    /**
     * 创建者
     */
    @Column(name = "Creator")
    private String creator;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Update_Date")
    private Date updateDate;

}