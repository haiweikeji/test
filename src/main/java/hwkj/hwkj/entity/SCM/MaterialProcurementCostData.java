package hwkj.hwkj.entity.SCM;

import java.io.Serializable;

public class MaterialProcurementCostData implements Serializable {
    private static final long serialVersionUID=1435515995276255188L;
    private Integer Id;
    private String Material_Number;//HW料号
    private String Version;//HW版本
    private String Product_Name;//品名
    private String Brand;//品牌
    private String Model_Number;//型号
    private String Manufacturer_Abbreviation;//制造商简称
    private String Manufacturer_Material_Number;//制造商料号
    private String Vendor_Chinese_Abbreviation;//供应商中文简称
    private String Vendor_Code;//供应商代码
    private String Material_Type;//物料类型
    private String Size;//规格/尺寸
    private String Texture_Material;//材质
    private String Quantity_Unit;//数量单位
    private Integer Minimum_Lower_Unit_Quantity;//最小下单量
    private Integer Minimum_Packing_Capacity;//最小包装量
    private Double Purchase_Price;//采购价格
    private String Purchasing_Price_Unit;//采购价格单位
    private String Currency;//交易币别
    private Double Purchase_Freight;//进货费用
    private Double Allocation;//Allocation%
    private String Deliver_Term;//交易条件
    private String Payment_Term;//付款条件
    private String Receive_Term;//验收条件
    private Integer Supplier_Warranty_Period_Month;//供应商保固期(月）
    private String Customer_Designated_Transactions;//客户指定交易Y/N
    private String Customer;//客户
    private String Customer_Material_Number;//客户料号
    private String Year;//年
    private String Quarter;//季度
    private Double Customer_Price;//客户价格

    private String Category;//类别
    private String Country_Origin;//原产地

    private Integer Purchasing_Lead_Days;//采购前置期（天）


    private String Force_Time;//生效时间
    private String Failure_Time;//失效时间
    private String Purchase;//采购工程师
    private String Remark;//备注
    private String Status;//有效状态
    private String Creator;
    private String Create_Date;
    private String Updated_By;
    private String Update_Date;
}
