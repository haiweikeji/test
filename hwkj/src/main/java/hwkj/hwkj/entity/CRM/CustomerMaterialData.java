package hwkj.hwkj.entity.CRM;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hwkj_customer_material_data")
public class CustomerMaterialData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户简称
     */
    @Column(name = "Customer_Abbreviation")
    private String customerAbbreviation;

    /**
     * 客户料号
     */
    @Column(name = "Customer_Material_Number")
    private String customerMaterialNumber;

    /**
     * 客户版本
     */
    @Column(name = "Customer_Version")
    private String customerVersion;

    /**
     * HW料号
     */
    @Column(name = "Material_Number")
    private String materialNumber;

    /**
     * HW版本
     */
    @Column(name = "Version")
    private String version;

    /**
     * 客户料号描述
     */
    @Column(name = "Customer_Described")
    private String customerDescribed;

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
     * 获取客户简称
     *
     * @return Customer_Abbreviation - 客户简称
     */
    public String getCustomerAbbreviation() {
        return customerAbbreviation;
    }

    /**
     * 设置客户简称
     *
     * @param customerAbbreviation 客户简称
     */
    public void setCustomerAbbreviation(String customerAbbreviation) {
        this.customerAbbreviation = customerAbbreviation;
    }

    /**
     * 获取客户料号
     *
     * @return Customer_Material_Number - 客户料号
     */
    public String getCustomerMaterialNumber() {
        return customerMaterialNumber;
    }

    /**
     * 设置客户料号
     *
     * @param customerMaterialNumber 客户料号
     */
    public void setCustomerMaterialNumber(String customerMaterialNumber) {
        this.customerMaterialNumber = customerMaterialNumber;
    }

    /**
     * 获取客户版本
     *
     * @return Customer_Version - 客户版本
     */
    public String getCustomerVersion() {
        return customerVersion;
    }

    /**
     * 设置客户版本
     *
     * @param customerVersion 客户版本
     */
    public void setCustomerVersion(String customerVersion) {
        this.customerVersion = customerVersion;
    }

    /**
     * 获取HW料号
     *
     * @return Material_Number - HW料号
     */
    public String getMaterialNumber() {
        return materialNumber;
    }

    /**
     * 设置HW料号
     *
     * @param materialNumber HW料号
     */
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    /**
     * 获取HW版本
     *
     * @return Version - HW版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置HW版本
     *
     * @param version HW版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取客户料号描述
     *
     * @return Customer_Described - 客户料号描述
     */
    public String getCustomerDescribed() {
        return customerDescribed;
    }

    /**
     * 设置客户料号描述
     *
     * @param customerDescribed 客户料号描述
     */
    public void setCustomerDescribed(String customerDescribed) {
        this.customerDescribed = customerDescribed;
    }

    /**
     * 获取制造商简称
     *
     * @return Manufacturer_Abbreviation - 制造商简称
     */
    public String getManufacturerAbbreviation() {
        return manufacturerAbbreviation;
    }

    /**
     * 设置制造商简称
     *
     * @param manufacturerAbbreviation 制造商简称
     */
    public void setManufacturerAbbreviation(String manufacturerAbbreviation) {
        this.manufacturerAbbreviation = manufacturerAbbreviation;
    }

    /**
     * 获取制造商料号
     *
     * @return Manufacturer_Material_Number - 制造商料号
     */
    public String getManufacturerMaterialNumber() {
        return manufacturerMaterialNumber;
    }

    /**
     * 设置制造商料号
     *
     * @param manufacturerMaterialNumber 制造商料号
     */
    public void setManufacturerMaterialNumber(String manufacturerMaterialNumber) {
        this.manufacturerMaterialNumber = manufacturerMaterialNumber;
    }

    /**
     * 获取生效时间
     *
     * @return Force_Time - 生效时间
     */
    public String getForceTime() {
        return forceTime;
    }

    /**
     * 设置生效时间
     *
     * @param forceTime 生效时间
     */
    public void setForceTime(String forceTime) {
        this.forceTime = forceTime;
    }

    /**
     * 获取失效时间
     *
     * @return Failure_Time - 失效时间
     */
    public String getFailureTime() {
        return failureTime;
    }

    /**
     * 设置失效时间
     *
     * @param failureTime 失效时间
     */
    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
    }

    /**
     * 获取备注
     *
     * @return Remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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