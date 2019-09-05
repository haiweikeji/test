package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_factory_address")
@Setter
@Getter
public class CustomerFactoryAddress {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户编号
     */
    @Column(name = "Customer_Code")
    private String customerCode;

    /**
     * 工厂地址
     */
    @Column(name = "Factory_Address")
    private String factoryAddress;

    /**
     * 工厂编号
     */
    @Column(name = "Factory_Code")
    private String factoryCode;

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
     * 中文缩写
     */
    @Column(name = "Chinese_Abbreviation")
    private String ChineseAbbreviation;
    /**
     * 英文缩写
     */
    @Column(name = "English_Abbreviation")
    private String EnglishAbbreviation;
/**
 *  国家
 */
    @Column(name = "Country")
    private String Country;
    /**
     * 城市
     */
    @Column(name = "City")
    private String City;
}
