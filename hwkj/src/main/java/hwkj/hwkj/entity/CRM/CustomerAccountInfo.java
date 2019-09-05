package hwkj.hwkj.entity.CRM;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_account_info")
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
     * 获取中文简称
     *
     * @return Chinese_Abbreviation - 中文简称
     */
    public String getChineseAbbreviation() {
        return chineseAbbreviation;
    }

    /**
     * 设置中文简称
     *
     * @param chineseAbbreviation 中文简称
     */
    public void setChineseAbbreviation(String chineseAbbreviation) {
        this.chineseAbbreviation = chineseAbbreviation;
    }

    /**
     * 获取社会统一信用代码
     *
     * @return Credit_Code - 社会统一信用代码
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 设置社会统一信用代码
     *
     * @param creditCode 社会统一信用代码
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    /**
     * 获取纳税人识别号
     *
     * @return Taxpayer_Number - 纳税人识别号
     */
    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * 设置纳税人识别号
     *
     * @param taxpayerNumber 纳税人识别号
     */
    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    /**
     * 获取工商注册号
     *
     * @return Registration_Number - 工商注册号
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * 设置工商注册号
     *
     * @param registrationNumber 工商注册号
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * 获取注册时间
     *
     * @return Registration_Time - 注册时间
     */
    public String getRegistrationTime() {
        return registrationTime;
    }

    /**
     * 设置注册时间
     *
     * @param registrationTime 注册时间
     */
    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    /**
     * 获取注册资金
     *
     * @return Registered_Capital - 注册资金
     */
    public Double getRegisteredCapital() {
        return registeredCapital;
    }

    /**
     * 设置注册资金
     *
     * @param registeredCapital 注册资金
     */
    public void setRegisteredCapital(Double registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    /**
     * 获取币别
     *
     * @return Currency - 币别
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币别
     *
     * @param currency 币别
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取信用等级
     *
     * @return Credit_Level - 信用等级
     */
    public String getCreditLevel() {
        return creditLevel;
    }

    /**
     * 设置信用等级
     *
     * @param creditLevel 信用等级
     */
    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    /**
     * 获取信用额度
     *
     * @return Credit_Amount - 信用额度
     */
    public Double getCreditAmount() {
        return creditAmount;
    }

    /**
     * 设置信用额度
     *
     * @param creditAmount 信用额度
     */
    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * 获取发票地址
     *
     * @return Invoice_Address - 发票地址
     */
    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    /**
     * 设置发票地址
     *
     * @param invoiceAddress 发票地址
     */
    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    /**
     * 获取银行
     *
     * @return Bank - 银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置银行
     *
     * @param bank 银行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取银行账号
     *
     * @return Bank_Account - 银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 设置银行账号
     *
     * @param bankAccount 银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 获取银行地址
     *
     * @return Bank_Address - 银行地址
     */
    public String getBankAddress() {
        return bankAddress;
    }

    /**
     * 设置银行地址
     *
     * @param bankAddress 银行地址
     */
    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    /**
     * 获取接受的币别
     *
     * @return Acceptable_Currency - 接受的币别
     */
    public String getAcceptableCurrency() {
        return acceptableCurrency;
    }

    /**
     * 设置接受的币别
     *
     * @param acceptableCurrency 接受的币别
     */
    public void setAcceptableCurrency(String acceptableCurrency) {
        this.acceptableCurrency = acceptableCurrency;
    }

    /**
     * 获取生效时间
     *
     * @return Force_Time - 生效时间
     */
    public String getForceTime() {
        return forceTime;
    }

    /**
     * 设置生效时间
     *
     * @param forceTime 生效时间
     */
    public void setForceTime(String forceTime) {
        this.forceTime = forceTime;
    }

    /**
     * 获取失效时间
     *
     * @return Failure_Time - 失效时间
     */
    public String getFailureTime() {
        return failureTime;
    }

    /**
     * 设置失效时间
     *
     * @param failureTime 失效时间
     */
    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
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