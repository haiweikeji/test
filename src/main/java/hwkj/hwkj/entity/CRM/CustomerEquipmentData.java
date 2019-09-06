package hwkj.hwkj.entity.CRM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_customer_equipment_data")
@Getter
@Setter
public class CustomerEquipmentData {
    /**
     * id
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * aaa
     */
    @Column(name = "Customer_Code")
    private String customerCode;

    /**
     * 工厂地址
     */
    @Column(name = "Factory_Address")
    private String factoryAddress;

    /**
     * 设备名称
     */
    @Column(name = "Equipment_Name")
    private String equipmentName;

    /**
     * 品牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 主键
     */
    @Column(name = "Model_Number")
    private String modelNumber;

    /**
     * 生产日期
     */
    @Column(name = "Production_Date")
    private String productionDate;

    /**
     * 生产编号
     */
    @Column(name = "SN")
    private String sn;

    /**
     * 数量
     */
    @Column(name = "Quantity")
    private Integer quantity;

    /**
     * 产品
     */
    @Column(name = "Products")
    private String products;

    /**
     * 设备运转率
     */
    @Column(name = "Operation_Rate")
    private Double operationRate;

    /**
     * 保养记录
     */
    @Column(name = "Maintenance_Record")
    private String maintenanceRecord;

    /**
     * 维修记录
     */
    @Column(name = "Repair_Recode")
    private String repairRecode;

    /**
     * 业务员
     */
    @Column(name = "BPM")
    private String bpm;

    /**
     * 工程师
     */
    @Column(name = "Engineer")
    private String engineer;

    /**
     * 创建人
     */
    @Column(name = "Creator")
    private String creator;
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