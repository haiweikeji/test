package hwkj.hwkj.entity.CRM;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_base_data")
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

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
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
     * 获取四个字母

     *
     * @return Four_Initials - 四个字母

     */
    public String getFourInitials() {
        return fourInitials;
    }

    /**
     * 设置四个字母

     *
     * @param fourInitials 四个字母

     */
    public void setFourInitials(String fourInitials) {
        this.fourInitials = fourInitials;
    }

    /**
     * 获取国籍
     *
     * @return Country - 国籍
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国籍
     *
     * @param country 国籍
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
     * 获取中文全称
     *
     * @return Chinese_Full_Name - 中文全称
     */
    public String getChineseFullName() {
        return chineseFullName;
    }

    /**
     * 设置中文全称
     *
     * @param chineseFullName 中文全称
     */
    public void setChineseFullName(String chineseFullName) {
        this.chineseFullName = chineseFullName;
    }

    /**
     * 获取中文缩写
     *
     * @return Chinese_Abbreviation - 中文缩写
     */
    public String getChineseAbbreviation() {
        return chineseAbbreviation;
    }

    /**
     * 设置中文缩写
     *
     * @param chineseAbbreviation 中文缩写
     */
    public void setChineseAbbreviation(String chineseAbbreviation) {
        this.chineseAbbreviation = chineseAbbreviation;
    }

    /**
     * 获取英文全称
     *
     * @return English_Full_Name - 英文全称
     */
    public String getEnglishFullName() {
        return englishFullName;
    }

    /**
     * 设置英文全称
     *
     * @param englishFullName 英文全称
     */
    public void setEnglishFullName(String englishFullName) {
        this.englishFullName = englishFullName;
    }

    /**
     * 获取英文缩写
     *
     * @return English_Abbreviation - 英文缩写
     */
    public String getEnglishAbbreviation() {
        return englishAbbreviation;
    }

    /**
     * 设置英文缩写
     *
     * @param englishAbbreviation 英文缩写
     */
    public void setEnglishAbbreviation(String englishAbbreviation) {
        this.englishAbbreviation = englishAbbreviation;
    }

    /**
     * 获取富士康集团
     *
     * @return Foxconn_Group - 富士康集团
     */
    public String getFoxconnGroup() {
        return foxconnGroup;
    }

    /**
     * 设置富士康集团
     *
     * @param foxconnGroup 富士康集团
     */
    public void setFoxconnGroup(String foxconnGroup) {
        this.foxconnGroup = foxconnGroup;
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
     * 获取公司老板
     *
     * @return Company_Owner - 公司老板
     */
    public String getCompanyOwner() {
        return companyOwner;
    }

    /**
     * 设置公司老板
     *
     * @param companyOwner 公司老板
     */
    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    /**
     * 获取年收入

     *
     * @return Annual_Revenue - 年收入

     */
    public Double getAnnualRevenue() {
        return annualRevenue;
    }

    /**
     * 设置年收入

     *
     * @param annualRevenue 年收入

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
     * 获取员工数量
     *
     * @return Employee_Qty - 员工数量
     */
    public Integer getEmployeeQty() {
        return employeeQty;
    }

    /**
     * 设置员工数量
     *
     * @param employeeQty 员工数量
     */
    public void setEmployeeQty(Integer employeeQty) {
        this.employeeQty = employeeQty;
    }

    /**
     * 获取客户等级
     *
     * @return Customer_Level - 客户等级
     */
    public String getCustomerLevel() {
        return customerLevel;
    }

    /**
     * 设置客户等级
     *
     * @param customerLevel 客户等级
     */
    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    /**
     * 获取交易条件
     *
     * @return Deliver_Term - 交易条件
     */
    public String getDeliverTerm() {
        return deliverTerm;
    }

    /**
     * 设置交易条件
     *
     * @param deliverTerm 交易条件
     */
    public void setDeliverTerm(String deliverTerm) {
        this.deliverTerm = deliverTerm;
    }

    /**
     * 获取付款条件
     *
     * @return Payment_Term - 付款条件
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * 设置付款条件
     *
     * @param paymentTerm 付款条件
     */
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * 获取验收条件
     *
     * @return Receive_Term - 验收条件
     */
    public String getReceiveTerm() {
        return receiveTerm;
    }

    /**
     * 设置验收条件
     *
     * @param receiveTerm 验收条件
     */
    public void setReceiveTerm(String receiveTerm) {
        this.receiveTerm = receiveTerm;
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
     * 获取开发日期
     *
     * @return Development_Date - 开发日期
     */
    public String getDevelopmentDate() {
        return developmentDate;
    }

    /**
     * 设置开发日期
     *
     * @param developmentDate 开发日期
     */
    public void setDevelopmentDate(String developmentDate) {
        this.developmentDate = developmentDate;
    }

    /**
     * 获取业务流程
     *
     * @return BPM - 业务流程
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * 设置业务流程
     *
     * @param bpm 业务流程
     */
    public void setBpm(String bpm) {
        this.bpm = bpm;
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
     * @return credit_Amount - 信用额度
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
     * 获取收入
     *
     * @return Revenue_From - 收入
     */
    public Double getRevenueFrom() {
        return revenueFrom;
    }

    /**
     * 设置收入
     *
     * @param revenueFrom 收入
     */
    public void setRevenueFrom(Double revenueFrom) {
        this.revenueFrom = revenueFrom;
    }

    /**
     * 获取支出
     *
     * @return Revenue_To - 支出
     */
    public Double getRevenueTo() {
        return revenueTo;
    }

    /**
     * 设置支出
     *
     * @param revenueTo 支出
     */
    public void setRevenueTo(Double revenueTo) {
        this.revenueTo = revenueTo;
    }

    /**
     * 获取收入时间
     *
     * @return Date_From - 收入时间
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * 设置收入时间
     *
     * @param dateFrom 收入时间
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * 获取支出时间
     *
     * @return Date_To - 支出时间
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * 设置支出时间
     *
     * @param dateTo 支出时间
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
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