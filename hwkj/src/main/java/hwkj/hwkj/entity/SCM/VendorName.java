package hwkj.hwkj.entity.SCM;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_vendor_name")
public class VendorName {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标准中文全称
     */
    @Column(name = "Standard_Chinese_Full")
    private String standardChineseFull;

    /**
     * 标准中文简称
     */
    @Column(name = "Standard_Chinese_Abbreviation")
    private String standardChineseAbbreviation;

    /**
     * 标准英文全称
     */
    @Column(name = "Standard_English_Full")
    private String standardEnglishFull;

    /**
     * 标准英文简称
     */
    @Column(name = "Standard_English_Abbreviation")
    private String standardEnglishAbbreviation;

    /**
     * 实际中文全称
     */
    @Column(name = "Actual_Chinese_Full")
    private String actualChineseFull;

    /**
     * 实际英文全称
     */
    @Column(name = "Actual_English_Full")
    private String actualEnglishFull;

    /**
     * 品牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 状态
     */
    @Column(name = "Status")
    private String status;

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
     * 获取标准中文全称
     *
     * @return Standard_Chinese_Full - 标准中文全称
     */
    public String getStandardChineseFull() {
        return standardChineseFull;
    }

    /**
     * 设置标准中文全称
     *
     * @param standardChineseFull 标准中文全称
     */
    public void setStandardChineseFull(String standardChineseFull) {
        this.standardChineseFull = standardChineseFull;
    }

    /**
     * 获取标准中文简称
     *
     * @return Standard_Chinese_Abbreviation - 标准中文简称
     */
    public String getStandardChineseAbbreviation() {
        return standardChineseAbbreviation;
    }

    /**
     * 设置标准中文简称
     *
     * @param standardChineseAbbreviation 标准中文简称
     */
    public void setStandardChineseAbbreviation(String standardChineseAbbreviation) {
        this.standardChineseAbbreviation = standardChineseAbbreviation;
    }

    /**
     * 获取标准英文全称
     *
     * @return Standard_English_Full - 标准英文全称
     */
    public String getStandardEnglishFull() {
        return standardEnglishFull;
    }

    /**
     * 设置标准英文全称
     *
     * @param standardEnglishFull 标准英文全称
     */
    public void setStandardEnglishFull(String standardEnglishFull) {
        this.standardEnglishFull = standardEnglishFull;
    }

    /**
     * 获取标准英文简称
     *
     * @return Standard_English_Abbreviation - 标准英文简称
     */
    public String getStandardEnglishAbbreviation() {
        return standardEnglishAbbreviation;
    }

    /**
     * 设置标准英文简称
     *
     * @param standardEnglishAbbreviation 标准英文简称
     */
    public void setStandardEnglishAbbreviation(String standardEnglishAbbreviation) {
        this.standardEnglishAbbreviation = standardEnglishAbbreviation;
    }

    /**
     * 获取实际中文全称
     *
     * @return Actual_Chinese_Full - 实际中文全称
     */
    public String getActualChineseFull() {
        return actualChineseFull;
    }

    /**
     * 设置实际中文全称
     *
     * @param actualChineseFull 实际中文全称
     */
    public void setActualChineseFull(String actualChineseFull) {
        this.actualChineseFull = actualChineseFull;
    }

    /**
     * 获取实际英文全称
     *
     * @return Actual_English_Full - 实际英文全称
     */
    public String getActualEnglishFull() {
        return actualEnglishFull;
    }

    /**
     * 设置实际英文全称
     *
     * @param actualEnglishFull 实际英文全称
     */
    public void setActualEnglishFull(String actualEnglishFull) {
        this.actualEnglishFull = actualEnglishFull;
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
     * 获取状态
     *
     * @return Status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
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