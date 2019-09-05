package hwkj.hwkj.entity.SCM;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_vendor_code")
public class VendorCode {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供应商标准中文名称
     */
    @Column(name = "Vendor_Chinese_Full")
    private String vendorChineseFull;

    /**
     * 供应商标准简称
     */
    @Column(name = "Vendor_Chinese_Abbreviation")
    private String vendorChineseAbbreviation;

    /**
     * 供应商英文全称
     */
    @Column(name = "Vendor_English_Full")
    private String vendorEnglishFull;

    /**
     * 供应商英文简称
     */
    @Column(name = "Vendor_English_Abbreviation")
    private String vendorEnglishAbbreviation;

    /**
     * 供应商性质
     */
    @Column(name = "Vendor_Nature")
    private String vendorNature;

    /**
     * 供应商代码
     */
    @Column(name = "Vendor_Code")
    private String vendorCode;

    /**
     * 国家地区
     */
    @Column(name = "Country_Area")
    private String countryArea;

    /**
     * 城市
     */
    @Column(name = "City")
    private String city;

    /**
     * 品牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 发票地址
     */
    @Column(name = "Invoice_Address")
    private String invoiceAddress;

    /**
     * 行业
     */
    @Column(name = "Industry")
    private String industry;

    /**
     * 供应商品种类
     */
    @Column(name = "Supplier_Category")
    private String supplierCategory;

    /**
     * 公司负责人
     */
    @Column(name = "Company_Owner")
    private String companyOwner;

    /**
     * 供应商等级
     */
    @Column(name = "Vendor_Level")
    private String vendorLevel;

    /**
     * 官网地址
     */
    @Column(name = "Website_Address")
    private String websiteAddress;

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
     * 接受的币别
     */
    @Column(name = "Acceptable_Currency")
    private String acceptableCurrency;

    /**
     * 年盈利
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
     * 员工人数
     */
    @Column(name = "Employee_Qty")
    private Integer employeeQty;

    /**
     * 统一社会信用代码
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
     * 币别
     */
    @Column(name = "Currency")
    private String currency;

    /**
     * 采购工程师
     */
    @Column(name = "Purchase")
    private String purchase;

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
     * 获取供应商标准中文名称
     *
     * @return Vendor_Chinese_Full - 供应商标准中文名称
     */
    public String getVendorChineseFull() {
        return vendorChineseFull;
    }

    /**
     * 设置供应商标准中文名称
     *
     * @param vendorChineseFull 供应商标准中文名称
     */
    public void setVendorChineseFull(String vendorChineseFull) {
        this.vendorChineseFull = vendorChineseFull;
    }

    /**
     * 获取供应商标准简称
     *
     * @return Vendor_Chinese_Abbreviation - 供应商标准简称
     */
    public String getVendorChineseAbbreviation() {
        return vendorChineseAbbreviation;
    }

    /**
     * 设置供应商标准简称
     *
     * @param vendorChineseAbbreviation 供应商标准简称
     */
    public void setVendorChineseAbbreviation(String vendorChineseAbbreviation) {
        this.vendorChineseAbbreviation = vendorChineseAbbreviation;
    }

    /**
     * 获取供应商英文全称
     *
     * @return Vendor_English_Full - 供应商英文全称
     */
    public String getVendorEnglishFull() {
        return vendorEnglishFull;
    }

    /**
     * 设置供应商英文全称
     *
     * @param vendorEnglishFull 供应商英文全称
     */
    public void setVendorEnglishFull(String vendorEnglishFull) {
        this.vendorEnglishFull = vendorEnglishFull;
    }

    /**
     * 获取供应商英文简称
     *
     * @return Vendor_English_Abbreviation - 供应商英文简称
     */
    public String getVendorEnglishAbbreviation() {
        return vendorEnglishAbbreviation;
    }

    /**
     * 设置供应商英文简称
     *
     * @param vendorEnglishAbbreviation 供应商英文简称
     */
    public void setVendorEnglishAbbreviation(String vendorEnglishAbbreviation) {
        this.vendorEnglishAbbreviation = vendorEnglishAbbreviation;
    }

    /**
     * 获取供应商性质
     *
     * @return Vendor_Nature - 供应商性质
     */
    public String getVendorNature() {
        return vendorNature;
    }

    /**
     * 设置供应商性质
     *
     * @param vendorNature 供应商性质
     */
    public void setVendorNature(String vendorNature) {
        this.vendorNature = vendorNature;
    }

    /**
     * 获取供应商代码
     *
     * @return Vendor_Code - 供应商代码
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * 设置供应商代码
     *
     * @param vendorCode 供应商代码
     */
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    /**
     * 获取国家地区
     *
     * @return Country_Area - 国家地区
     */
    public String getCountryArea() {
        return countryArea;
    }

    /**
     * 设置国家地区
     *
     * @param countryArea 国家地区
     */
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
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
     * 获取品牌
     *
     * @return Brand - 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌
     *
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
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
     * 获取供应商品种类
     *
     * @return Supplier_Category - 供应商品种类
     */
    public String getSupplierCategory() {
        return supplierCategory;
    }

    /**
     * 设置供应商品种类
     *
     * @param supplierCategory 供应商品种类
     */
    public void setSupplierCategory(String supplierCategory) {
        this.supplierCategory = supplierCategory;
    }

    /**
     * 获取公司负责人
     *
     * @return Company_Owner - 公司负责人
     */
    public String getCompanyOwner() {
        return companyOwner;
    }

    /**
     * 设置公司负责人
     *
     * @param companyOwner 公司负责人
     */
    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    /**
     * 获取供应商等级
     *
     * @return Vendor_Level - 供应商等级
     */
    public String getVendorLevel() {
        return vendorLevel;
    }

    /**
     * 设置供应商等级
     *
     * @param vendorLevel 供应商等级
     */
    public void setVendorLevel(String vendorLevel) {
        this.vendorLevel = vendorLevel;
    }

    /**
     * 获取官网地址
     *
     * @return Website_Address - 官网地址
     */
    public String getWebsiteAddress() {
        return websiteAddress;
    }

    /**
     * 设置官网地址
     *
     * @param websiteAddress 官网地址
     */
    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
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
     * 获取年盈利
     *
     * @return Annual_Revenue - 年盈利
     */
    public Double getAnnualRevenue() {
        return annualRevenue;
    }

    /**
     * 设置年盈利
     *
     * @param annualRevenue 年盈利
     */
    public void setAnnualRevenue(Double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    /**
     * 获取行业等级
     *
     * @return Industry_Rank - 行业等级
     */
    public String getIndustryRank() {
        return industryRank;
    }

    /**
     * 设置行业等级
     *
     * @param industryRank 行业等级
     */
    public void setIndustryRank(String industryRank) {
        this.industryRank = industryRank;
    }

    /**
     * 获取企业性质
     *
     * @return Enterprise_Nature - 企业性质
     */
    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    /**
     * 设置企业性质
     *
     * @param enterpriseNature 企业性质
     */
    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    /**
     * 获取员工人数
     *
     * @return Employee_Qty - 员工人数
     */
    public Integer getEmployeeQty() {
        return employeeQty;
    }

    /**
     * 设置员工人数
     *
     * @param employeeQty 员工人数
     */
    public void setEmployeeQty(Integer employeeQty) {
        this.employeeQty = employeeQty;
    }

    /**
     * 获取统一社会信用代码
     *
     * @return Credit_Code - 统一社会信用代码
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param creditCode 统一社会信用代码
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
     * 获取采购工程师
     *
     * @return Purchase - 采购工程师
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * 设置采购工程师
     *
     * @param purchase 采购工程师
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
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