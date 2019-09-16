package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_contact")
@Getter
@Setter
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
 private  String OldCustomerCode;
 @Column(name = "we_chat_number")
 private String WeChatNumber;
 private  String  OldContactChineseName;
}