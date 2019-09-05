package hwkj.hwkj.entity.Engineering;

import java.io.Serializable;

public class CustomerMaterialData implements Serializable {
    private static final long serialVersionUID=1435515995276255188L;
    private Integer Id;
    private String Customer_Abbreviation;//客户简称
    private String Customer_Material_Number;//客户料号
    private String Customer_Version;//客户版本
    private String Material_Number;//HW料号
    private String Version;//HW版本
    private String Customer_Described;//客户料号描述
    private String Described;//HW料号描述
    private String Manufacturer_Abbreviation;//制造商简称
    private String Manufacturer_Material_Number;//制造商料号
    private String Life_Cycle_State;//生命周期状态
    private String Force_Time;//生效时间
    private String Failure_Time;//失效时间
    private String Status;//有效状态
    private String Remark;//备注
    private String Creator;
    private String Create_Date;
    private String Updated_By;
    private String Update_Date;
    private String Date_From;
    private String Date_To;
    private String Old_Customer_Abbreviation;
    private String Old_Customer_Material_Number;
    private String Old_Customer_Version;
    private String Old_Material_Number;
    private String Old_Version;
    private String Old_Manufacturer_Abbreviation;
    private String Old_Manufacturer_Material_Number;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCustomer_Abbreviation() {
        return Customer_Abbreviation;
    }

    public void setCustomer_Abbreviation(String customer_Abbreviation) {
        Customer_Abbreviation = customer_Abbreviation;
    }

    public String getCustomer_Material_Number() {
        return Customer_Material_Number;
    }

    public void setCustomer_Material_Number(String customer_Material_Number) {
        Customer_Material_Number = customer_Material_Number;
    }

    public String getCustomer_Version() {
        return Customer_Version;
    }

    public void setCustomer_Version(String customer_Version) {
        Customer_Version = customer_Version;
    }

    public String getMaterial_Number() {
        return Material_Number;
    }

    public void setMaterial_Number(String material_Number) {
        Material_Number = material_Number;
    }

    public String getDescribed() {
        return Described;
    }

    public void setDescribed(String described) {
        Described = described;
    }

    public String getCustomer_Described() {
        return Customer_Described;
    }

    public void setCustomer_Described(String customer_Described) {
        Customer_Described = customer_Described;
    }

    public String getManufacturer_Abbreviation() {
        return Manufacturer_Abbreviation;
    }

    public void setManufacturer_Abbreviation(String manufacturer_Abbreviation) {
        Manufacturer_Abbreviation = manufacturer_Abbreviation;
    }

    public String getManufacturer_Material_Number() {
        return Manufacturer_Material_Number;
    }

    public void setManufacturer_Material_Number(String manufacturer_Material_Number) {
        Manufacturer_Material_Number = manufacturer_Material_Number;
    }

    public String getLife_Cycle_State() {
        return Life_Cycle_State;
    }

    public void setLife_Cycle_State(String life_Cycle_State) {
        Life_Cycle_State = life_Cycle_State;
    }

    public String getForce_Time() {
        return Force_Time;
    }

    public void setForce_Time(String force_Time) {
        Force_Time = force_Time;
    }

    public String getFailure_Time() {
        return Failure_Time;
    }

    public void setFailure_Time(String failure_Time) {
        Failure_Time = failure_Time;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(String create_Date) {
        Create_Date = create_Date;
    }

    public String getUpdated_By() {
        return Updated_By;
    }

    public void setUpdated_By(String updated_By) {
        Updated_By = updated_By;
    }

    public String getUpdate_Date() {
        return Update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        Update_Date = update_Date;
    }

    public String getDate_From() {
        return Date_From;
    }

    public void setDate_From(String date_From) {
        Date_From = date_From;
    }

    public String getDate_To() {
        return Date_To;
    }

    public void setDate_To(String date_To) {
        Date_To = date_To;
    }

    public String getOld_Customer_Abbreviation() {
        return Old_Customer_Abbreviation;
    }

    public void setOld_Customer_Abbreviation(String old_Customer_Abbreviation) {
        Old_Customer_Abbreviation = old_Customer_Abbreviation;
    }

    public String getOld_Customer_Material_Number() {
        return Old_Customer_Material_Number;
    }

    public void setOld_Customer_Material_Number(String old_Customer_Material_Number) {
        Old_Customer_Material_Number = old_Customer_Material_Number;
    }

    public String getOld_Customer_Version() {
        return Old_Customer_Version;
    }

    public void setOld_Customer_Version(String old_Customer_Version) {
        Old_Customer_Version = old_Customer_Version;
    }

    public String getOld_Material_Number() {
        return Old_Material_Number;
    }

    public void setOld_Material_Number(String old_Material_Number) {
        Old_Material_Number = old_Material_Number;
    }

    public String getOld_Version() {
        return Old_Version;
    }

    public void setOld_Version(String old_Version) {
        Old_Version = old_Version;
    }

    public String getOld_Manufacturer_Abbreviation() {
        return Old_Manufacturer_Abbreviation;
    }

    public void setOld_Manufacturer_Abbreviation(String old_Manufacturer_Abbreviation) {
        Old_Manufacturer_Abbreviation = old_Manufacturer_Abbreviation;
    }

    public String getOld_Manufacturer_Material_Number() {
        return Old_Manufacturer_Material_Number;
    }

    public void setOld_Manufacturer_Material_Number(String old_Manufacturer_Material_Number) {
        Old_Manufacturer_Material_Number = old_Manufacturer_Material_Number;
    }
}
