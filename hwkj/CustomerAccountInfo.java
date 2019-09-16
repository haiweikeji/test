package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_account_info")
@Getter
@Setter
public class CustomerAccountInfo {
    /**
     * 主键
     */
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
     * 中文简称
     */
    @Column(name = "Chinese_Abbreviation")
    private String chineseAbbreviation;

    /**
     * 社会统一信用代码
     */
    @Column(name = "Credit_Code")
    private String creditCode;

    /**
     * 纳税人识别号
     */
    @Column(name = "Taxpayer_Number")
    private String taxpayerNumber;

    /**
     * 工商注册号
     */
    @Column(name = "Registration_Number")
    private String registrationNumber;

    /**
     * 注册时间
     */
    @Column(name = "Registration_Time")
    private String registrationTime;

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
    @Column(name = "Credit_Amount")
    private Double creditAmount;

    /**
     * 发票地址
     */
    @Column(name = "Invoice_Address")
    private String invoiceAddress;

    /**
     * 银行
     */
    @Column(name = "Bank")
    private String bank;

    /**
     * 银行账号
     */
    @Column(name = "Bank_Account")
    private String bankAccount;

    /**
     * 银行地址
     */
    @Column(name = "Bank_Address")
    private String bankAddress;

    /**
     * 接受的币别
     */
    @Column(name = "Acceptable_Currency")
    private String acceptableCurrency;

    /**
     * 生效时间
     */
    @Column(name = "Force_Time")
    private String forceTime;

    /**
     * 失效时间
     */
    @Column(name = "Failure_Time")
    private String failureTime;

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


    public String oldCustomerCode;

}