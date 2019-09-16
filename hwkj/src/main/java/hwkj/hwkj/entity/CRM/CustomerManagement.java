package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_management")
@Setter
@Getter
public class CustomerManagement {
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
     * 年
     */
    @Column(name = "Year")
    private String year;

    /**
     * 季度
     */
    @Column(name = "Quarter")
    private String quarter;

    /**
     * 营收贡献
     */
    @Column(name = "Revenue")
    private Double revenue;

    /**
     * 逾期金额
     */
    @Column(name = "Overdue_Amount")
    private Double overdueAmount;

    /**
     * 评分
     */
    @Column(name = "Score")
    private String score;

    /**
     * 重要项次
     */
    @Column(name = "Important_Item")
    private String importantItem;

    /**
     * 创建者
     */
    @Column(name = "Creator")
    private String creator;
    /**
     * 业务员
     */
     @Column(name = "Bpm")
    private String Bpm;
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
     * 货币
     */
    @Column(name = "Currency")
    private Double Currency;
    /**
     * 信用等级
     */
    @Column(name = "Credit_Level")
    private int CreditLevel;
}