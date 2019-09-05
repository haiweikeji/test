package hwkj.hwkj.service.Engineering;

import hwkj.hwkj.entity.Engineering.MaterialEngineeringData;
import hwkj.hwkj.entity.pagingquery.PageModel;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface MaterialEngineeringDataService {

    /**
     * 新增数据
     * @param materialEngineeringData
     * @return
     */
    public boolean insertMaterialEngineeringData(MaterialEngineeringData materialEngineeringData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteMaterialEngineeringData(Integer Id);

    /**
     * 更新数据
     * @param materialEngineeringData
     * @return
     */
    public boolean updateMaterialEngineeringData(MaterialEngineeringData materialEngineeringData);

    /**
     * 上传图纸,规格书,图片
     * @param materialEngineeringData
     * @return
     */
    public boolean materialEngineeringDataFileUpload(MaterialEngineeringData materialEngineeringData);

    /**
     * 分页查询
     * @param materialEngineeringDataPageModel
     * @param materialEngineeringData
     * @return
     */
    public void queryMaterialEngineeringDataPage(PageModel<MaterialEngineeringData> materialEngineeringDataPageModel,MaterialEngineeringData materialEngineeringData) throws Exception;

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public MaterialEngineeringData queryMaterialEngineeringDataById(Integer Id);

    /**
     * for download all
     * @param materialEngineeringData
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForDownLoadAll(MaterialEngineeringData materialEngineeringData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForDownLoad(Integer Id[]);

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     */
    public boolean materialEngineeringDataUpload(HttpServletRequest request, InputStream inputStream) throws RuntimeException,Exception;

    /**
     *下拉框Material_Number
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionMaterialNumber();

    /**
     *下拉框Version
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionVersion(String Material_Number);

    /**
     *下拉框Manufacturer_Abbreviation
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionManufacturerAbbreviation(String Material_Number,String Version);

    /**
     *下拉框Manufacturer_Material_Number
     * @return
     */
    public abstract List<MaterialEngineeringData> queryMaterialEngineeringDataOptionManufacturerMaterialNumber(String Material_Number,String Version,String Manufacturer_Material_Number);

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
    public List<MaterialEngineeringData> queryMaterialEngineeringDataByMaterialNumber(String Material_Number);

    /**
     * 通过查询HW料号和HW版本获取型号
     * @param Material_Number
     * @param Version
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataByMaterialNumberAndVersion(String Material_Number,String Version);

    /**
     * 通过查询HW料号和HW版本获取品牌
     * @param Material_Number
     * @param Version
     * @return
     */
    public List<MaterialEngineeringData> queryMaterialEngineeringDataForOptionBrand(String Material_Number,String Version);

}
