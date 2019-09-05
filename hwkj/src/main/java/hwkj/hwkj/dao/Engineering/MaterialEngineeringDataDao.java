package hwkj.hwkj.dao.Engineering;

import hwkj.hwkj.entity.Engineering.MaterialEngineeringData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialEngineeringDataDao {

    /**
     * 新增数据
     * @param materialEngineeringData
     * @return
     */
    public int insertMaterialEngineeringData(MaterialEngineeringData materialEngineeringData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteMaterialEngineeringData(@Param("Id") Integer Id);

    /**
     * 更新数据
     * @param materialEngineeringData
     * @return
     */
    public int updateMaterialEngineeringData(MaterialEngineeringData materialEngineeringData);

    /**
     * 上传图纸,规格书,图片
     * @param materialEngineeringData
     * @return
     */
    public int materialEngineeringDataFileUpload(MaterialEngineeringData materialEngineeringData);

    /**
     * 分页集合List
     * @param materialEngineeringDataPageModel
     * @param materialEngineeringData
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataList(@Param("materialEngineeringDataPageModel") PageModel<MaterialEngineeringData> materialEngineeringDataPageModel, @Param("materialEngineeringData") MaterialEngineeringData materialEngineeringData);

    /**
     * 分页总数count
     * @param materialEngineeringDataPageModel
     * @param materialEngineeringData
     * @return
     */
    public int queryMaterialEngineeringDataCount(@Param("materialEngineeringDataPageModel") PageModel<MaterialEngineeringData> materialEngineeringDataPageModel, @Param("materialEngineeringData") MaterialEngineeringData materialEngineeringData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public MaterialEngineeringData queryMaterialEngineeringDataById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param materialEngineeringData
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForDownLoadAll(@Param("materialEngineeringData") MaterialEngineeringData materialEngineeringData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForDownLoad(Integer Id[]);

    /**
     * 上传
     * @param materialEngineeringDataList
     * @return
     */
    public int materialEngineeringDataUpload(List<MaterialEngineeringData> materialEngineeringDataList);

    /**
     * check 新增数据是否已存在
     * @param materialEngineeringData
     * @return
     */
    public int queryMaterialEngineeringDataForExist(MaterialEngineeringData materialEngineeringData);

    /**
     *下拉框Material_Number
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionMaterialNumber();

    /**
     *下拉框Version
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionVersion(@Param("Material_Number") String Material_Number);

    /**
     *下拉框Manufacturer_Abbreviation
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionManufacturerAbbreviation(@Param("Material_Number") String Material_Number,@Param("Version") String Version);

    /**
     *下拉框Manufacturer_Material_Number
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionManufacturerMaterialNumber(@Param("Material_Number") String Material_Number,@Param("Version") String Version,@Param("Manufacturer_Abbreviation") String Manufacturer_Abbreviation);

    /**
     * check material_engineering_data 是否存在
     * @param materialEngineeringData
     * @return
     */
    public int queryMaterialEngineeringDataFourDataExist(MaterialEngineeringData materialEngineeringData);

    /**
     * check material_number 是否存在
     * @param Material_Number
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataByMaterialNumber(@Param("Material_Number") String Material_Number);

    /**
     * 通过查询HW料号和HW版本获取型号
     * @param Material_Number
     * @param Version
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataByMaterialNumberAndVersion(@Param("Material_Number") String Material_Number,@Param("Version") String Version);

    /**
     * check version 是否存在
     * @param Material_Number
     * @param Version
     * @return
     */
    public int queryMaterialEngineeringDataByVersion(@Param("Material_Number") String Material_Number,@Param("Version") String Version);

    /**
     * check Manufacturer_Abbreviation 是否存在
     * @param Material_Number
     * @param Version
     * @param Manufacturer_Abbreviation
     * @return
     */
    public int queryMaterialEngineeringDataByManufacturerAbbreviation(@Param("Material_Number") String Material_Number,@Param("Version") String Version,@Param("Manufacturer_Abbreviation") String Manufacturer_Abbreviation);

    /**
     * check Manufacturer_Material_Number 是否存在
     * @param Material_Number
     * @param Version
     * @param Manufacturer_Abbreviation
     * @param Manufacturer_Material_Number
     * @return
     */
    public int queryMaterialEngineeringDataByManufacturerMaterialNumber(@Param("Material_Number") String Material_Number,@Param("Version") String Version,@Param("Manufacturer_Abbreviation") String Manufacturer_Abbreviation,@Param("Manufacturer_Material_Number") String Manufacturer_Material_Number);

    /**
     * 通过查询HW料号和HW版本获取品牌
     * @param Material_Number
     * @param Version
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForOptionBrand(@Param("Material_Number") String Material_Number,@Param("Version") String Version);

    /**
     * 上传时检查品牌是否正确
     * @param Material_Number
     * @param Version
     * @param Brand
     * @return
     */
    public int queryMaterialEngineeringDataForUploadBrand(@Param("Material_Number") String Material_Number,@Param("Version") String Version ,@Param("Brand") String Brand);

    /**
     * 检查用户有没有更新内容
     * @param materialEngineeringData
     * @return
     */
    public int queryMaterialEngineeringDataForAllExist(MaterialEngineeringData materialEngineeringData);

    /**
     * 检查对应的HW料号和HW版本是否与品名、物料（服务）类型、规格/尺寸、描述、类别、型号一致
     * @param materialEngineeringData
     * @return
     */
    public int queryMaterialEngineeringDataCheckMaterialNumberAndVersion(MaterialEngineeringData materialEngineeringData);
}
