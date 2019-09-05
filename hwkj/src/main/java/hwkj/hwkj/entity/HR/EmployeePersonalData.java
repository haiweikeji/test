package hwkj.hwkj.entity.HR;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_employee_personal_data")
public class EmployeePersonalData {
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
     * 照片
     */
    @Column(name = "Photo")
    private String photo;

    /**
     * 中文名
     */
    @Column(name = "China_Name")
    private String chinaName;

    /**
     * 英文名
     */
    @Column(name = "English_Name")
    private String englishName;

    /**
     * 性别
     */
    @Column(name = "Sex")
    private String sex;

    /**
     * 年龄
     */
    @Column(name = "Age")
    private Integer age;

    /**
     * 手机号
     */
    @Column(name = "Phone_Number")
    private String phoneNumber;

    /**
     * 微信号
     */
    @Column(name = "WeChat_Number")
    private String wechatNumber;

    /**
     * 公司邮箱
     */
    @Column(name = "Company_Mail")
    private String companyMail;

    /**
     * 个人邮箱
     */
    @Column(name = "Private_Mail")
    private String privateMail;

    /**
     * 入职时间
     */
    @Column(name = "Entry_Date")
    private String entryDate;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

    /**
     * 入职资位
     */
    @Column(name = "Entry_Position")
    private String entryPosition;

    /**
     * 离职时间
     */
    @Column(name = "Leave_Date")
    private String leaveDate;

    /**
     * 生日
     */
    @Column(name = "Birth_Date")
    private String birthDate;

    /**
     * 身份证号
     */
    @Column(name = "Id_Card")
    private String idCard;

    /**
     * 身份证地址
     */
    @Column(name = "Id_Card_Address")
    private String idCardAddress;

    /**
     * 现住址
     */
    @Column(name = "Present_Address")
    private String presentAddress;

    /**
     * 学历
     */
    @Column(name = "Education")
    private String education;

    /**
     * 专业
     */
    @Column(name = "Major")
    private String major;

    /**
     * 毕业院校
     */
    @Column(name = "Graduated_From")
    private String graduatedFrom;

    /**
     * 毕业时间
     */
    @Column(name = "Graduated_Time")
    private String graduatedTime;

    /**
     * 语言
     */
    @Column(name = "Language")
    private String language;

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
     * 婚烟状况
     */
    @Column(name = "Marital_Status")
    private String maritalStatus;

    /**
     * 疾病史
     */
    @Column(name = "History_Disease")
    private String historyDisease;

    /**
     * 家庭成员
     */
    @Column(name = "Member_Family")
    private String memberFamily;

    /**
     * 人际关系
     */
    @Column(name = "Relationship")
    private String relationship;

    /**
     * 紧急联系人
     */
    @Column(name = "Emergency_Contact_Person")
    private String emergencyContactPerson;

    /**
     * 紧急联系人关系
     */
    @Column(name = "Emergency_Contact_Person_Relationship")
    private String emergencyContactPersonRelationship;

    /**
     * 联系人手机号
     */
    @Column(name = "Relationship_Phone")
    private String relationshipPhone;

    /**
     * 招募途径
     */
    @Column(name = "Recruitment_Sources")
    private String recruitmentSources;

    /**
     * 期数
     */
    @Column(name = "Periods_Number")
    private String periodsNumber;

    /**
     * 推荐人
     */
    @Column(name = "Referee")
    private String referee;

    /**
     * 黑名单
     */
    @Column(name = "Blacklist")
    private String blacklist;

    /**
     * 备注
     */
    @Column(name = "Remarks")
    private String remarks;

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
     * 获取工号
     *
     * @return Job_Number - 工号
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * 设置工号
     *
     * @param jobNumber 工号
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * 获取照片
     *
     * @return Photo - 照片
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置照片
     *
     * @param photo 照片
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取中文名
     *
     * @return China_Name - 中文名
     */
    public String getChinaName() {
        return chinaName;
    }

    /**
     * 设置中文名
     *
     * @param chinaName 中文名
     */
    public void setChinaName(String chinaName) {
        this.chinaName = chinaName;
    }

    /**
     * 获取英文名
     *
     * @return English_Name - 英文名
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 设置英文名
     *
     * @param englishName 英文名
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
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
     * 获取年龄
     *
     * @return Age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
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
     * 获取个人邮箱
     *
     * @return Private_Mail - 个人邮箱
     */
    public String getPrivateMail() {
        return privateMail;
    }

    /**
     * 设置个人邮箱
     *
     * @param privateMail 个人邮箱
     */
    public void setPrivateMail(String privateMail) {
        this.privateMail = privateMail;
    }

    /**
     * 获取入职时间
     *
     * @return Entry_Date - 入职时间
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * 设置入职时间
     *
     * @param entryDate 入职时间
     */
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
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
     * 获取入职资位
     *
     * @return Entry_Position - 入职资位
     */
    public String getEntryPosition() {
        return entryPosition;
    }

    /**
     * 设置入职资位
     *
     * @param entryPosition 入职资位
     */
    public void setEntryPosition(String entryPosition) {
        this.entryPosition = entryPosition;
    }

    /**
     * 获取离职时间
     *
     * @return Leave_Date - 离职时间
     */
    public String getLeaveDate() {
        return leaveDate;
    }

    /**
     * 设置离职时间
     *
     * @param leaveDate 离职时间
     */
    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
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
     * 获取身份证地址
     *
     * @return Id_Card_Address - 身份证地址
     */
    public String getIdCardAddress() {
        return idCardAddress;
    }

    /**
     * 设置身份证地址
     *
     * @param idCardAddress 身份证地址
     */
    public void setIdCardAddress(String idCardAddress) {
        this.idCardAddress = idCardAddress;
    }

    /**
     * 获取现住址
     *
     * @return Present_Address - 现住址
     */
    public String getPresentAddress() {
        return presentAddress;
    }

    /**
     * 设置现住址
     *
     * @param presentAddress 现住址
     */
    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    /**
     * 获取学历
     *
     * @return Education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取专业
     *
     * @return Major - 专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置专业
     *
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 获取毕业院校
     *
     * @return Graduated_From - 毕业院校
     */
    public String getGraduatedFrom() {
        return graduatedFrom;
    }

    /**
     * 设置毕业院校
     *
     * @param graduatedFrom 毕业院校
     */
    public void setGraduatedFrom(String graduatedFrom) {
        this.graduatedFrom = graduatedFrom;
    }

    /**
     * 获取毕业时间
     *
     * @return Graduated_Time - 毕业时间
     */
    public String getGraduatedTime() {
        return graduatedTime;
    }

    /**
     * 设置毕业时间
     *
     * @param graduatedTime 毕业时间
     */
    public void setGraduatedTime(String graduatedTime) {
        this.graduatedTime = graduatedTime;
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
     * 获取婚烟状况
     *
     * @return Marital_Status - 婚烟状况
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚烟状况
     *
     * @param maritalStatus 婚烟状况
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 获取疾病史
     *
     * @return History_Disease - 疾病史
     */
    public String getHistoryDisease() {
        return historyDisease;
    }

    /**
     * 设置疾病史
     *
     * @param historyDisease 疾病史
     */
    public void setHistoryDisease(String historyDisease) {
        this.historyDisease = historyDisease;
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
     * 获取人际关系
     *
     * @return Relationship - 人际关系
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 设置人际关系
     *
     * @param relationship 人际关系
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * 获取紧急联系人
     *
     * @return Emergency_Contact_Person - 紧急联系人
     */
    public String getEmergencyContactPerson() {
        return emergencyContactPerson;
    }

    /**
     * 设置紧急联系人
     *
     * @param emergencyContactPerson 紧急联系人
     */
    public void setEmergencyContactPerson(String emergencyContactPerson) {
        this.emergencyContactPerson = emergencyContactPerson;
    }

    /**
     * 获取紧急联系人关系
     *
     * @return Emergency_Contact_Person_Relationship - 紧急联系人关系
     */
    public String getEmergencyContactPersonRelationship() {
        return emergencyContactPersonRelationship;
    }

    /**
     * 设置紧急联系人关系
     *
     * @param emergencyContactPersonRelationship 紧急联系人关系
     */
    public void setEmergencyContactPersonRelationship(String emergencyContactPersonRelationship) {
        this.emergencyContactPersonRelationship = emergencyContactPersonRelationship;
    }

    /**
     * 获取联系人手机号
     *
     * @return Relationship_Phone - 联系人手机号
     */
    public String getRelationshipPhone() {
        return relationshipPhone;
    }

    /**
     * 设置联系人手机号
     *
     * @param relationshipPhone 联系人手机号
     */
    public void setRelationshipPhone(String relationshipPhone) {
        this.relationshipPhone = relationshipPhone;
    }

    /**
     * 获取招募途径
     *
     * @return Recruitment_Sources - 招募途径
     */
    public String getRecruitmentSources() {
        return recruitmentSources;
    }

    /**
     * 设置招募途径
     *
     * @param recruitmentSources 招募途径
     */
    public void setRecruitmentSources(String recruitmentSources) {
        this.recruitmentSources = recruitmentSources;
    }

    /**
     * 获取期数
     *
     * @return Periods_Number - 期数
     */
    public String getPeriodsNumber() {
        return periodsNumber;
    }

    /**
     * 设置期数
     *
     * @param periodsNumber 期数
     */
    public void setPeriodsNumber(String periodsNumber) {
        this.periodsNumber = periodsNumber;
    }

    /**
     * 获取推荐人
     *
     * @return Referee - 推荐人
     */
    public String getReferee() {
        return referee;
    }

    /**
     * 设置推荐人
     *
     * @param referee 推荐人
     */
    public void setReferee(String referee) {
        this.referee = referee;
    }

    /**
     * 获取黑名单
     *
     * @return Blacklist - 黑名单
     */
    public String getBlacklist() {
        return blacklist;
    }

    /**
     * 设置黑名单
     *
     * @param blacklist 黑名单
     */
    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    /**
     * 获取备注
     *
     * @return Remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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