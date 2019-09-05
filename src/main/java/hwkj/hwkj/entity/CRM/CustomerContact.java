package hwkj.hwkj.entity.CRM;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_contact")
public class CustomerContact {
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
     * 中文名称
     */
    @Column(name = "Contact_Chinese_Name")
    private String contactChineseName;

    /**
     * 中文拼音
     */
    @Column(name = "Contact_English_Name")
    private String contactEnglishName;

    /**
     * 中文缩写
     */
    @Column(name = "Chinese_Abbreviation")
    private String ChineseAbbreviation;

    /**
     * 性别
     */
    @Column(name = "Sex")
    private String sex;

    /**
     * 国家
     */
    @Column(name = "Country")
    private String Country;

    /**
     * 城市
     */
    @Column(name = "City")
    private String City;

    /**
     * 标题
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
     * 电话号码
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
     * 身份证号
     */
    @Column(name = "Id_Card")
    private String idCard;

    /**
     * 银行
     */
   @Column(name = "Bank")
   private String Bank;

    /**
     * 银行账户
     */
     @Column(name = "Bank_Account")
     private int BankAccount;
    /**
     * 护照编号
     */
    @Column(name = "Passport_Number")
    private String passportNumber;

    /**
     * 生日
     */
    @Column(name = "Birth_Date")
    private String birthDate;

    /**
     * 籍贯
     */
    @Column(name = "Native_Place")
    private String nativePlace;

    /**
     * 民族
     */
    @Column(name = "Nation")
    private String nation;

    /**
     * 宗教信仰
     */
    @Column(name = "Religious_Belief")
    private String religiousBelief;

    /**
     * 饮食禁忌
     */
    @Column(name = "Diet_Taboo")
    private String dietTaboo;

    /**
     * 利益
     */
    @Column(name = "Interests")
    private String interests;

    /**
     * 爱好
     */
    @Column(name = "Habit")
    private String habit;

    /**
     * 家庭成员
     */
    @Column(name = "Member_Family")
    private String memberFamily;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    @Column(name = "BPM")
    private String bpm;

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
     * 更新人
     */
    @Column(name = "Updated_By")
    private String updatedBy;

    /**
     * 更新时间
     */

    @Column(name = "Update_Date")
    private Date updateDate;


    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getChineseAbbreviation() {
        return ChineseAbbreviation;
    }

    public void setChineseAbbreviation(String chineseAbbreviation) {
        ChineseAbbreviation = chineseAbbreviation;
    }

    public int getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(int bankAccount) {
        BankAccount = bankAccount;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
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
     * 获取中文名称
     *
     * @return Contact_Chinese_Name - 中文名称
     */
    public String getContactChineseName() {
        return contactChineseName;
    }

    /**
     * 设置中文名称
     *
     * @param contactChineseName 中文名称
     */
    public void setContactChineseName(String contactChineseName) {
        this.contactChineseName = contactChineseName;
    }

    /**
     * 获取中文拼音
     *
     * @return Contact_English_Name - 中文拼音
     */
    public String getContactEnglishName() {
        return contactEnglishName;
    }

    /**
     * 设置中文拼音
     *
     * @param contactEnglishName 中文拼音
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
     * 获取标题
     *
     * @return Title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
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
     * 获取电话号码
     *
     * @return Phone_Number - 电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号码
     *
     * @param phoneNumber 电话号码
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
     * 获取身份证号
     *
     * @return Id_Card - 身份证号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号
     *
     * @param idCard 身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取护照编号
     *
     * @return Passport_Number - 护照编号
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * 设置护照编号
     *
     * @param passportNumber 护照编号
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * 获取生日
     *
     * @return Birth_Date - 生日
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * 设置生日
     *
     * @param birthDate 生日
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取籍贯
     *
     * @return Native_Place - 籍贯
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置籍贯
     *
     * @param nativePlace 籍贯
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 获取民族
     *
     * @return Nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取宗教信仰
     *
     * @return Religious_Belief - 宗教信仰
     */
    public String getReligiousBelief() {
        return religiousBelief;
    }

    /**
     * 设置宗教信仰
     *
     * @param religiousBelief 宗教信仰
     */
    public void setReligiousBelief(String religiousBelief) {
        this.religiousBelief = religiousBelief;
    }

    /**
     * 获取饮食禁忌
     *
     * @return Diet_Taboo - 饮食禁忌
     */
    public String getDietTaboo() {
        return dietTaboo;
    }

    /**
     * 设置饮食禁忌
     *
     * @param dietTaboo 饮食禁忌
     */
    public void setDietTaboo(String dietTaboo) {
        this.dietTaboo = dietTaboo;
    }

    /**
     * 获取利益
     *
     * @return Interests - 利益
     */
    public String getInterests() {
        return interests;
    }

    /**
     * 设置利益
     *
     * @param interests 利益
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * 获取爱好
     *
     * @return Habit - 爱好
     */
    public String getHabit() {
        return habit;
    }

    /**
     * 设置爱好
     *
     * @param habit 爱好
     */
    public void setHabit(String habit) {
        this.habit = habit;
    }

    /**
     * 获取家庭成员
     *
     * @return Member_Family - 家庭成员
     */
    public String getMemberFamily() {
        return memberFamily;
    }

    /**
     * 设置家庭成员
     *
     * @param memberFamily 家庭成员
     */
    public void setMemberFamily(String memberFamily) {
        this.memberFamily = memberFamily;
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
     * @return BPM
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * @param bpm
     */
    public void setBpm(String bpm) {
        this.bpm = bpm;
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
     * 获取更新人
     *
     * @return Updated_By - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
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