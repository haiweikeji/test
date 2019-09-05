package hwkj.hwkj.entity.SCM;

import javax.persistence.*;

@Table(name = "hwkj_vendor_contact")
public class VendorContact {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供应商代码 
     */
    @Column(name = "Vendor_Code")
    private String vendorCode;

    /**
     * 联系人中文简称
     */
    @Column(name = "Contact_Chinese_Name")
    private String contactChineseName;

    /**
     * 联系人英文简称
     */
    @Column(name = "Contact_English_Name")
    private String contactEnglishName;

    /**
     * 性别
     */
    @Column(name = "Sex")
    private String sex;

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
     * 权限
     */
    @Column(name = "Authority")
    private String authority;

    /**
     * 语言
     */
    @Column(name = "Language")
    private String language;

    /**
     * 公司电话
     */
    @Column(name = "Company_Telephone")
    private String companyTelephone;

    /**
     * 手机号
     */
    @Column(name = "Phone_Number")
    private String phoneNumber;

    /**
     * 公司邮箱
     */
    @Column(name = "Company_Mail")
    private String companyMail;

    /**
     * 私人邮箱
     */
    @Column(name = "Private_Mail")
    private String privateMail;

    /**
     * 微信号
     */
    @Column(name = "WeChat_Number")
    private String wechatNumber;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    /**
     * 采购
     */
    @Column(name = "Purchase")
    private String purchase;

    /**
     * 创建者
     */
    @Column(name = "Creator")
    private String creator;

    /**
     * 创建时间 
     */
    @Column(name = "Create_Date")
    private String createDate;

    /**
     * 更新者
     */
    @Column(name = "Updated_By")
    private String updatedBy;

    /**
     * 更新时间 
     */
    @Column(name = "Update_Date")
    private String updateDate;

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
     * 获取联系人中文简称
     *
     * @return Contact_Chinese_Name - 联系人中文简称
     */
    public String getContactChineseName() {
        return contactChineseName;
    }

    /**
     * 设置联系人中文简称
     *
     * @param contactChineseName 联系人中文简称
     */
    public void setContactChineseName(String contactChineseName) {
        this.contactChineseName = contactChineseName;
    }

    /**
     * 获取联系人英文简称
     *
     * @return Contact_English_Name - 联系人英文简称
     */
    public String getContactEnglishName() {
        return contactEnglishName;
    }

    /**
     * 设置联系人英文简称
     *
     * @param contactEnglishName 联系人英文简称
     */
    public void setContactEnglishName(String contactEnglishName) {
        this.contactEnglishName = contactEnglishName;
    }

    /**
     * 获取性别
     *
     * @return Sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
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
     * 获取权限
     *
     * @return Authority - 权限
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * 设置权限
     *
     * @param authority 权限
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 获取语言
     *
     * @return Language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取公司电话
     *
     * @return Company_Telephone - 公司电话
     */
    public String getCompanyTelephone() {
        return companyTelephone;
    }

    /**
     * 设置公司电话
     *
     * @param companyTelephone 公司电话
     */
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    /**
     * 获取手机号
     *
     * @return Phone_Number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取公司邮箱
     *
     * @return Company_Mail - 公司邮箱
     */
    public String getCompanyMail() {
        return companyMail;
    }

    /**
     * 设置公司邮箱
     *
     * @param companyMail 公司邮箱
     */
    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    /**
     * 获取私人邮箱
     *
     * @return Private_Mail - 私人邮箱
     */
    public String getPrivateMail() {
        return privateMail;
    }

    /**
     * 设置私人邮箱
     *
     * @param privateMail 私人邮箱
     */
    public void setPrivateMail(String privateMail) {
        this.privateMail = privateMail;
    }

    /**
     * 获取微信号
     *
     * @return WeChat_Number - 微信号
     */
    public String getWechatNumber() {
        return wechatNumber;
    }

    /**
     * 设置微信号
     *
     * @param wechatNumber 微信号
     */
    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
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
     * 获取采购
     *
     * @return Purchase - 采购
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * 设置采购
     *
     * @param purchase 采购
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
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
    public String getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间 
     *
     * @param createDate 创建时间 
     */
    public void setCreateDate(String createDate) {
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
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间 
     *
     * @param updateDate 更新时间 
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}