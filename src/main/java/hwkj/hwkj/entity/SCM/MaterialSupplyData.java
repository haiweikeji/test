package hwkj.hwkj.entity.SCM;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_material_supply_data")
@Getter
@Setter
public class MaterialSupplyData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * HW料号
     */
    @Column(name = "Material_Number")
    private String materialNumber;

    /**
     * hw版本
     */
    @Column(name = "Version")
    private String version;

    /**
     * 品牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 供应商代码
     */
    @Column(name = "Vendor_code")
    private String vendorCode;

    /**
     * 最小下单量
     */
    @Column(name = "Minimum_Lower_Unit_Quantity")
    private Integer minimumLowerUnitQuantity;

    /**
     * 采购前置期（天）
     */
    @Column(name = "Purchasing_Lead_Days")
    private Double purchasingLeadDays;

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
     * 制造商缩写
     */
    @Column(name = "Manufacturer_Abbreviation")
    private String ManufacturerAbbreviation;
    /**
     * 制造商材料编号
     */
    @Column(name = "Manufacturer_Material_Number")
    private String ManufacturerMaterialNumber;
    /**
     * 供应商中文缩写
     */
    @Column(name = "Vendor_Chinese_Abbreviation")
    private String VendorChineseAbbreviation;
    /**
     * 材料类型
     */
    @Column(name = "Material_Type")
    private String MaterialType;
    /**
     * 类别
     */
    @Column(name = "Category")
    private String Category;
    /**
     * 原产国
     */
    @Column(name = "Country_Origin")
    private String CountryOrigin;
    /**
     * 数量单位
     */
    @Column(name = "Quantity_Unit")
    private String QuantityUnit;
    /**
     * 最小包容量
     */
    @Column(name = "Minimum_Packing_Capacity")
    private String MinimumPackingCapacity;

    /**
     * 供应商保固期（月）
     */
    @Column(name = "Supplier_Warranty_Period_Month")
    private Double supplierWarrantyPeriodMonth;

    /**
     * 生效时间
     */
    @Column(name = "Force_Time")
    private String forceTime;

    /**
     * 失效时间
     */
    @Column(name = "Failure_Time")
    private String failureTime;


    /**
     * 采购工程师
     */
    @Column(name = "Purchase")
    private String purchase;

    /**
     * 备注
     */
    @Column(name = "Remark")
    private String remark;

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
     * 产品名称
     */
    @Column(name = "Product_Name")
    private String ProductName;
    /**
     * 状态
     */
    @Column(name = "Status")
    private String Status;
    /**
     * 型号
     */
    @Column(name = "Model_Number")
    private String ModelNumber;
    /**
     * 尺寸
     */
    @Column(name = "Size")
    private String Size;



}