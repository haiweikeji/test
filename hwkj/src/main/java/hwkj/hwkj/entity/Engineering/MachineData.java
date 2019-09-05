package hwkj.hwkj.entity.Engineering;

import javax.persistence.*;
import java.util.Date;

@Table(name = "hwkj_machine_data")
public class MachineData {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备名称
     */
    @Column(name = "Machine_Name")
    private String machineName;

    /**
     * 设备类型
     */
    @Column(name = "Machine_Type")
    private String machineType;

    /**
     * 品牌
     */
    @Column(name = "Brand")
    private String brand;

    /**
     * 型号
     */
    @Column(name = "Model_Number")
    private String modelNumber;

    /**
     * 规格
     */
    @Column(name = "Size")
    private String size;

    /**
     * 原产地
     */
    @Column(name = "Country_Origin")
    private String countryOrigin;

    /**
     * 描述
     */
    @Column(name = "Described")
    private String described;

    /**
     * 机床精度等级
     */
    @Column(name = "Machine_Tool_Accuracy_Grade")
    private String machineToolAccuracyGrade;

    /**
     * 最大加工尺寸
     */
    @Column(name = "Max_Machining_Size")
    private String maxMachiningSize;

    /**
     * 最小加工尺寸
     */
    @Column(name = "Min_Machining_Size")
    private String minMachiningSize;

    /**
     * 轨道行程
     */
    @Column(name = "Track_Travel")
    private String trackTravel;

    /**
     * 适用加工的产品
     */
    @Column(name = "Machining_Product")
    private String machiningProduct;

    /**
     * 设备有点简述
     */
    @Column(name = "Equipment_Advantages")
    private String equipmentAdvantages;

    /**
     * 刀库种类
     */
    @Column(name = "Tool_Magazine_Type")
    private String toolMagazineType;

    /**
     * 主轴锥孔类型
     */
    @Column(name = "Spindle_Cone_Hole_Type")
    private String spindleConeHoleType;

    /**
     * 速度类型
     */
    @Column(name = "Speed_Type")
    private String speedType;

    /**
     * 速度单位
     */
    @Column(name = "Speed_Unit")
    private String speedUnit;

    /**
     * 最高运行速度
     */
    @Column(name = "Max_Speed")
    private Double maxSpeed;

    /**
     * 最大压力
     */
    @Column(name = "Max_Pressure")
    private Double maxPressure;

    /**
     * 用气种类
     */
    @Column(name = "Air_Type")
    private String airType;

    /**
     * 额定气压Pa
     */
    @Column(name = "Rated_Pressure")
    private Double ratedPressure;

    /**
     * 单位耗气量升
     */
    @Column(name = "Unit_Gas_Consumption")
    private Double unitGasConsumption;

    /**
     * 额定电压
     */
    @Column(name = "Rated_Voltage")
    private Double ratedVoltage;

    /**
     * 额定电流
     */
    @Column(name = "Current_Rating")
    private Double currentRating;

    /**
     * 额定功率
     */
    @Column(name = "Power_Rating")
    private Double powerRating;

    /**
     * 设备外形尺寸
     */
    @Column(name = "Contour_Size")
    private String contourSize;

    /**
     * 设备重量
     */
    @Column(name = "Weight")
    private Double weight;

    /**
     * 制造商简称
     */
    @Column(name = "Manufacturer_Abbreviation")
    private String manufacturerAbbreviation;

    /**
     * 最早出厂时间
     */
    @Column(name = "Factory_Time")
    private String factoryTime;

    /**
     * 生命周期状态
     */
    @Column(name = "Life_Cycle_State")
    private String lifeCycleState;

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
     * 获取设备名称
     *
     * @return Machine_Name - 设备名称
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * 设置设备名称
     *
     * @param machineName 设备名称
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    /**
     * 获取设备类型
     *
     * @return Machine_Type - 设备类型
     */
    public String getMachineType() {
        return machineType;
    }

    /**
     * 设置设备类型
     *
     * @param machineType 设备类型
     */
    public void setMachineType(String machineType) {
        this.machineType = machineType;
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
     * 获取型号
     *
     * @return Model_Number - 型号
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * 设置型号
     *
     * @param modelNumber 型号
     */
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    /**
     * 获取规格
     *
     * @return Size - 规格
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置规格
     *
     * @param size 规格
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 获取原产地
     *
     * @return Country_Origin - 原产地
     */
    public String getCountryOrigin() {
        return countryOrigin;
    }

    /**
     * 设置原产地
     *
     * @param countryOrigin 原产地
     */
    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    /**
     * 获取描述
     *
     * @return Described - 描述
     */
    public String getDescribed() {
        return described;
    }

    /**
     * 设置描述
     *
     * @param described 描述
     */
    public void setDescribed(String described) {
        this.described = described;
    }

    /**
     * 获取机床精度等级
     *
     * @return Machine_Tool_Accuracy_Grade - 机床精度等级
     */
    public String getMachineToolAccuracyGrade() {
        return machineToolAccuracyGrade;
    }

    /**
     * 设置机床精度等级
     *
     * @param machineToolAccuracyGrade 机床精度等级
     */
    public void setMachineToolAccuracyGrade(String machineToolAccuracyGrade) {
        this.machineToolAccuracyGrade = machineToolAccuracyGrade;
    }

    /**
     * 获取最大加工尺寸
     *
     * @return Max_Machining_Size - 最大加工尺寸
     */
    public String getMaxMachiningSize() {
        return maxMachiningSize;
    }

    /**
     * 设置最大加工尺寸
     *
     * @param maxMachiningSize 最大加工尺寸
     */
    public void setMaxMachiningSize(String maxMachiningSize) {
        this.maxMachiningSize = maxMachiningSize;
    }

    /**
     * 获取最小加工尺寸
     *
     * @return Min_Machining_Size - 最小加工尺寸
     */
    public String getMinMachiningSize() {
        return minMachiningSize;
    }

    /**
     * 设置最小加工尺寸
     *
     * @param minMachiningSize 最小加工尺寸
     */
    public void setMinMachiningSize(String minMachiningSize) {
        this.minMachiningSize = minMachiningSize;
    }

    /**
     * 获取轨道行程
     *
     * @return Track_Travel - 轨道行程
     */
    public String getTrackTravel() {
        return trackTravel;
    }

    /**
     * 设置轨道行程
     *
     * @param trackTravel 轨道行程
     */
    public void setTrackTravel(String trackTravel) {
        this.trackTravel = trackTravel;
    }

    /**
     * 获取适用加工的产品
     *
     * @return Machining_Product - 适用加工的产品
     */
    public String getMachiningProduct() {
        return machiningProduct;
    }

    /**
     * 设置适用加工的产品
     *
     * @param machiningProduct 适用加工的产品
     */
    public void setMachiningProduct(String machiningProduct) {
        this.machiningProduct = machiningProduct;
    }

    /**
     * 获取设备有点简述
     *
     * @return Equipment_Advantages - 设备有点简述
     */
    public String getEquipmentAdvantages() {
        return equipmentAdvantages;
    }

    /**
     * 设置设备有点简述
     *
     * @param equipmentAdvantages 设备有点简述
     */
    public void setEquipmentAdvantages(String equipmentAdvantages) {
        this.equipmentAdvantages = equipmentAdvantages;
    }

    /**
     * 获取刀库种类
     *
     * @return Tool_Magazine_Type - 刀库种类
     */
    public String getToolMagazineType() {
        return toolMagazineType;
    }

    /**
     * 设置刀库种类
     *
     * @param toolMagazineType 刀库种类
     */
    public void setToolMagazineType(String toolMagazineType) {
        this.toolMagazineType = toolMagazineType;
    }

    /**
     * 获取主轴锥孔类型
     *
     * @return Spindle_Cone_Hole_Type - 主轴锥孔类型
     */
    public String getSpindleConeHoleType() {
        return spindleConeHoleType;
    }

    /**
     * 设置主轴锥孔类型
     *
     * @param spindleConeHoleType 主轴锥孔类型
     */
    public void setSpindleConeHoleType(String spindleConeHoleType) {
        this.spindleConeHoleType = spindleConeHoleType;
    }

    /**
     * 获取速度类型
     *
     * @return Speed_Type - 速度类型
     */
    public String getSpeedType() {
        return speedType;
    }

    /**
     * 设置速度类型
     *
     * @param speedType 速度类型
     */
    public void setSpeedType(String speedType) {
        this.speedType = speedType;
    }

    /**
     * 获取速度单位
     *
     * @return Speed_Unit - 速度单位
     */
    public String getSpeedUnit() {
        return speedUnit;
    }

    /**
     * 设置速度单位
     *
     * @param speedUnit 速度单位
     */
    public void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    /**
     * 获取最高运行速度
     *
     * @return Max_Speed - 最高运行速度
     */
    public Double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 设置最高运行速度
     *
     * @param maxSpeed 最高运行速度
     */
    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 获取最大压力
     *
     * @return Max_Pressure - 最大压力
     */
    public Double getMaxPressure() {
        return maxPressure;
    }

    /**
     * 设置最大压力
     *
     * @param maxPressure 最大压力
     */
    public void setMaxPressure(Double maxPressure) {
        this.maxPressure = maxPressure;
    }

    /**
     * 获取用气种类
     *
     * @return Air_Type - 用气种类
     */
    public String getAirType() {
        return airType;
    }

    /**
     * 设置用气种类
     *
     * @param airType 用气种类
     */
    public void setAirType(String airType) {
        this.airType = airType;
    }

    /**
     * 获取额定气压Pa
     *
     * @return Rated_Pressure - 额定气压Pa
     */
    public Double getRatedPressure() {
        return ratedPressure;
    }

    /**
     * 设置额定气压Pa
     *
     * @param ratedPressure 额定气压Pa
     */
    public void setRatedPressure(Double ratedPressure) {
        this.ratedPressure = ratedPressure;
    }

    /**
     * 获取单位耗气量升
     *
     * @return Unit_Gas_Consumption - 单位耗气量升
     */
    public Double getUnitGasConsumption() {
        return unitGasConsumption;
    }

    /**
     * 设置单位耗气量升
     *
     * @param unitGasConsumption 单位耗气量升
     */
    public void setUnitGasConsumption(Double unitGasConsumption) {
        this.unitGasConsumption = unitGasConsumption;
    }

    /**
     * 获取额定电压
     *
     * @return Rated_Voltage - 额定电压
     */
    public Double getRatedVoltage() {
        return ratedVoltage;
    }

    /**
     * 设置额定电压
     *
     * @param ratedVoltage 额定电压
     */
    public void setRatedVoltage(Double ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    /**
     * 获取额定电流
     *
     * @return Current_Rating - 额定电流
     */
    public Double getCurrentRating() {
        return currentRating;
    }

    /**
     * 设置额定电流
     *
     * @param currentRating 额定电流
     */
    public void setCurrentRating(Double currentRating) {
        this.currentRating = currentRating;
    }

    /**
     * 获取额定功率
     *
     * @return Power_Rating - 额定功率
     */
    public Double getPowerRating() {
        return powerRating;
    }

    /**
     * 设置额定功率
     *
     * @param powerRating 额定功率
     */
    public void setPowerRating(Double powerRating) {
        this.powerRating = powerRating;
    }

    /**
     * 获取设备外形尺寸
     *
     * @return Contour_Size - 设备外形尺寸
     */
    public String getContourSize() {
        return contourSize;
    }

    /**
     * 设置设备外形尺寸
     *
     * @param contourSize 设备外形尺寸
     */
    public void setContourSize(String contourSize) {
        this.contourSize = contourSize;
    }

    /**
     * 获取设备重量
     *
     * @return Weight - 设备重量
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置设备重量
     *
     * @param weight 设备重量
     */
    public void setWeight(Double weight) {
        this.weight = weight;
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
     * 获取最早出厂时间
     *
     * @return Factory_Time - 最早出厂时间
     */
    public String getFactoryTime() {
        return factoryTime;
    }

    /**
     * 设置最早出厂时间
     *
     * @param factoryTime 最早出厂时间
     */
    public void setFactoryTime(String factoryTime) {
        this.factoryTime = factoryTime;
    }

    /**
     * 获取生命周期状态
     *
     * @return Life_Cycle_State - 生命周期状态
     */
    public String getLifeCycleState() {
        return lifeCycleState;
    }

    /**
     * 设置生命周期状态
     *
     * @param lifeCycleState 生命周期状态
     */
    public void setLifeCycleState(String lifeCycleState) {
        this.lifeCycleState = lifeCycleState;
    }

    /**
     * 获取图纸
     *
     * @return Drawing - 图纸
     */
    public String getDrawing() {
        return drawing;
    }

    /**
     * 设置图纸
     *
     * @param drawing 图纸
     */
    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    /**
     * 获取规格书
     *
     * @return Specification - 规格书
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 设置规格书
     *
     * @param specification 规格书
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * 获取图片
     *
     * @return Photo - 图片
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置图片
     *
     * @param photo 图片
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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