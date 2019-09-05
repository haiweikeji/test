package hwkj.hwkj.entity.HR;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_legal_data")
public class LegalData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 法人名称
     */
    @Column(name = "Legal_Name")
    private String legalName;

    /**
     * 前两位首字母
     */
    @Column(name = "Two_Initials")
    private String twoInitials;

    /**
     * 公司代码
     */
    @Column(name = "Company_Code")
    private String companyCode;

    /**
     * 国家
     */
    @Column(name = "Country")
    private String country;

    /**
     * 城市
     */
    @Column(name = "City")
    private String city;

    /**
     * 行业
     */
    @Column(name = "Industry")
    private String industry;

    /**
     * 法人代表
     */
    @Column(name = "Legal")
    private String legal;

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
    @Column(name = "Registered_Time")
    private String registeredTime;

    /**
     * 注册资金
     */
    @Column(name = "Registered_Capital")
    private Double registeredCapital;

    /**
     * 注册地址
     */
    @Column(name = "Registered_Address")
    private String registeredAddress;

    /**
     * 开户银行
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
     * 可以接受的币别
     */
    @Column(name = "Currency")
    private String currency;

    /**
     * 办公地址
     */
    @Column(name = "Office_Address")
    private String officeAddress;

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
     * 获取法人名称
     *
     * @return Legal_Name - 法人名称
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置法人名称
     *
     * @param legalName 法人名称
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取前两位首字母
     *
     * @return Two_Initials - 前两位首字母
     */
    public String getTwoInitials() {
        return twoInitials;
    }

    /**
     * 设置前两位首字母
     *
     * @param twoInitials 前两位首字母
     */
    public void setTwoInitials(String twoInitials) {
        this.twoInitials = twoInitials;
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
     * 获取国家
     *
     * @return Country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取城市
     *
     * @return City - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取行业
     *
     * @return Industry - 行业
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置行业
     *
     * @param industry 行业
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取法人代表
     *
     * @return Legal - 法人代表
     */
    public String getLegal() {
        return legal;
    }

    /**
     * 设置法人代表
     *
     * @param legal 法人代表
     */
    public void setLegal(String legal) {
        this.legal = legal;
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
     * @return Registered_Time - 注册时间
     */
    public String getRegisteredTime() {
        return registeredTime;
    }

    /**
     * 设置注册时间
     *
     * @param registeredTime 注册时间
     */
    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
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
     * 获取注册地址
     *
     * @return Registered_Address - 注册地址
     */
    public String getRegisteredAddress() {
        return registeredAddress;
    }

    /**
     * 设置注册地址
     *
     * @param registeredAddress 注册地址
     */
    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    /**
     * 获取开户银行
     *
     * @return Bank - 开户银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置开户银行
     *
     * @param bank 开户银行
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
     * 获取可以接受的币别
     *
     * @return Currency - 可以接受的币别
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置可以接受的币别
     *
     * @param currency 可以接受的币别
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取办公地址
     *
     * @return Office_Address - 办公地址
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * 设置办公地址
     *
     * @param officeAddress 办公地址
     */
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
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