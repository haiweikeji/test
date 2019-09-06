package hwkj.hwkj.entity.SCM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "hwkj_vendor_contact")
@Getter
@Setter
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
     * 供应商中文缩写
     */
    @Column(name = "Vendor_Chinese_Abbreviation")
    private String VendorChineseAbbreviation;
    /**
     * 国家地区
     */
    @Column(name = "Country_Area")
    private String CountryArea;
    /**
     * 城市
     */
    @Column(name = "City")
    private String City;

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

}