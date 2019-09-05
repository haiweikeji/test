package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_strategy_and_support")
@Setter
@Getter
public class CustomerStrategyAndSupport {
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

    @Column(name = "Date_From")
    private String dateFrom;

    @Column(name = "Date_To")
    private String dateTo;
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
     * 前三的客户
     */
    @Column(name = "Top_Three_Customer")
    private String topThreeCustomer;

    /**
     * 三大产品
     */
    @Column(name = "Top_Three_Product")
    private String topThreeProduct;

    /**
     * 年产量
     */
    @Column(name = "Annual_Output")
    private String annualOutput;

    /**
     * 占客户年营收比率
     */
    @Column(name = "Annual_Revenue")
    private Double annualRevenue;

    /**
     * 商业战略
     */
    @Column(name = "Business_Strategy")
    private String businessStrategy;

    /**
     * 支援需求
     */
    @Column(name = "Support_Need")
    private String supportNeed;

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


}