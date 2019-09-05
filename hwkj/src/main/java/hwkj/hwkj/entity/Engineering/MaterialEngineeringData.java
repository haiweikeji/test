package hwkj.hwkj.entity.Engineering;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_material_engineering_data")
@Getter
@Setter
public class MaterialEngineeringData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 料号
     */
    @Column(name = "Material_Number")
    private String materialNumber;

    /**
     * 版本
     */
    @Column(name = "Version")
    private String version;

    /**
     * 产品名称
     */
    @Column(name = "Product_Name")
    private String productName;

    /**
     * 物料类型
     */
    @Column(name = "Material_Type")
    private String materialType;

    /**
     * 类别
     */
    @Column(name = "Category")
    private String category;

    /**
     * 尺寸
     */
    @Column(name = "Size")
    private String size;

    /**
     * 材质
     */
    @Column(name = "Texture_Material")
    private String textureMaterial;

    /**
     * 型号
     */
    @Column(name = "Model_Number")
    private String modelNumber;

    /**
     * 描述
     */
    @Column(name = "Described")
    private String described;

    /**
     * 品牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 制造商简称
     */
    @Column(name = "Manufacturer_Abbreviation")
    private String manufacturerAbbreviation;

    /**
     * 制造商料号
     */
    @Column(name = "Manufacturer_Material_Number")
    private String manufacturerMaterialNumber;

    /**
     * 原产地
     */
    @Column(name = "Country_Origin")
    private String countryOrigin;

    /**
     * 图纸
     */
    @Column(name = "Drawing")
    private String drawing;

    /**
     * 规格书
     */
    @Column(name = "Specification")
    private String specification;

    /**
     * 图片
     */
    @Column(name = "Photo")
    private String photo;

    /**
     * 数量单位
     */
    @Column(name = "Quantity_Unit")
    private String quantityUnit;

    /**
     * 净重
     */
    @Column(name = "Net_Weight")
    private Double netWeight;

    /**
     * 毛重
     */
    @Column(name = "Gross_Weight")
    private Double grossWeight;

    /**
     * 重量单位
     */
    @Column(name = "Weight_Unit")
    private String weightUnit;

    /**
     * 材积
     */
    @Column(name = "Volume")
    private Double volume;

    /**
     * 包装材料
     */
    @Column(name = "Packaging")
    private String packaging;

    /**
     * 最小包装量
     */
    @Column(name = "Minimum_Packing_Capacity")
    private Integer minimumPackingCapacity;

    /**
     * 标准包装方式
     */
    @Column(name = "Standard_Packing_Method")
    private String standardPackingMethod;

    /**
     * 生命周期状态
     */
    @Column(name = "Life_Cycle_State")
    private String lifeCycleState;

    /**
     * 包装数量
     */
    @Column(name = "Package_Quantity")
    private Integer packageQuantity;

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
     * 状态
     */
    @Column(name = "Status")
    private String Status;
}