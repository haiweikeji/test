package hwkj.hwkj.entity.SCM;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_material_supply_data")
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
     * 获取hw版本
     *
     * @return Version - hw版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置hw版本
     *
     * @param version hw版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取品牌
     *
     * @return Brand - 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌
     *
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取供应商代码
     *
     * @return Vendor_code - 供应商代码
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * 设置供应商代码
     *
     * @param vendorCode 供应商代码
     */
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    /**
     * 获取最小下单量
     *
     * @return Minimum_Lower_Unit_Quantity - 最小下单量
     */
    public Integer getMinimumLowerUnitQuantity() {
        return minimumLowerUnitQuantity;
    }

    /**
     * 设置最小下单量
     *
     * @param minimumLowerUnitQuantity 最小下单量
     */
    public void setMinimumLowerUnitQuantity(Integer minimumLowerUnitQuantity) {
        this.minimumLowerUnitQuantity = minimumLowerUnitQuantity;
    }

    /**
     * 获取采购前置期（天）
     *
     * @return Purchasing_Lead_Days - 采购前置期（天）
     */
    public Double getPurchasingLeadDays() {
        return purchasingLeadDays;
    }

    /**
     * 设置采购前置期（天）
     *
     * @param purchasingLeadDays 采购前置期（天）
     */
    public void setPurchasingLeadDays(Double purchasingLeadDays) {
        this.purchasingLeadDays = purchasingLeadDays;
    }

    /**
     * 获取交易条件
     *
     * @return Deliver_Term - 交易条件
     */
    public String getDeliverTerm() {
        return deliverTerm;
    }

    /**
     * 设置交易条件
     *
     * @param deliverTerm 交易条件
     */
    public void setDeliverTerm(String deliverTerm) {
        this.deliverTerm = deliverTerm;
    }

    /**
     * 获取付款条件
     *
     * @return Payment_Term - 付款条件
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * 设置付款条件
     *
     * @param paymentTerm 付款条件
     */
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * 获取验收条件
     *
     * @return Receive_Term - 验收条件
     */
    public String getReceiveTerm() {
        return receiveTerm;
    }

    /**
     * 设置验收条件
     *
     * @param receiveTerm 验收条件
     */
    public void setReceiveTerm(String receiveTerm) {
        this.receiveTerm = receiveTerm;
    }

    /**
     * 获取供应商保固期（月）
     *
     * @return Supplier_Warranty_Period_Month - 供应商保固期（月）
     */
    public Double getSupplierWarrantyPeriodMonth() {
        return supplierWarrantyPeriodMonth;
    }

    /**
     * 设置供应商保固期（月）
     *
     * @param supplierWarrantyPeriodMonth 供应商保固期（月）
     */
    public void setSupplierWarrantyPeriodMonth(Double supplierWarrantyPeriodMonth) {
        this.supplierWarrantyPeriodMonth = supplierWarrantyPeriodMonth;
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
     * 获取采购工程师
     *
     * @return Purchase - 采购工程师
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * 设置采购工程师
     *
     * @param purchase 采购工程师
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
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