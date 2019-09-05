package hwkj.hwkj.dao.SCM;

import hwkj.hwkj.entity.SCM.MaterialSupplyData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialSupplyDataDao {

    /**
     * 新增数据
     * @param materialSupplyData
     * @return
     */
    public int insertMaterialSupplyData(MaterialSupplyData materialSupplyData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteMaterialSupplyData(Integer Id[]);

    /**
     * 更新数据
     * @param materialSupplyData
     * @return
     */
    public int updateMaterialSupplyData(MaterialSupplyData materialSupplyData);

    /**
     * 分页查询List
     * @param materialSupplyDataPageModel
     * @param materialSupplyData
     * @return
     */
    public List<MaterialSupplyData> queryMaterialSupplyDataList(@Param("materialSupplyDataPageModel") PageModel<MaterialSupplyData> materialSupplyDataPageModel, @Param("materialSupplyData")MaterialSupplyData materialSupplyData);

    /**
     * 分页查询Count
     * @param materialSupplyDataPageModel
     * @param materialSupplyData
     * @return
     */
    public int queryMaterialSupplyDataCount(@Param("materialSupplyDataPageModel")PageModel<MaterialSupplyData> materialSupplyDataPageModel,@Param("materialSupplyData")MaterialSupplyData materialSupplyData);

    /**
     * by id 查询
     * @param Id
     * @return
     */
    public MaterialSupplyData queryMaterialSupplyDataById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param materialSupplyData
     * @return
     */
    public List<MaterialSupplyData> queryMaterialSupplyDataForDownLoadAll(@Param("materialSupplyData")MaterialSupplyData materialSupplyData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<MaterialSupplyData> queryMaterialSupplyDataForDownLoad(Integer Id[]);

    /**
     * check 数据是否已存在
     * @param materialSupplyData
     * @return
     */
    public int queryMaterialSupplyDataForExist(MaterialSupplyData materialSupplyData);

    /**
     * 检查用户有没有更新内容
     * @param materialSupplyData
     * @return
     */
    public int queryMaterialSupplyDataForAllExist(MaterialSupplyData materialSupplyData);
}
