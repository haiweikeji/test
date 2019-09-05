package hwkj.hwkj.dao.Engineering;

import hwkj.hwkj.entity.Engineering.Commodity;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityDao {

    /**
     * 新增数据
     * @param commodity
     * @return
     */
    public int insertCommodity(Commodity commodity);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteCommodity(Integer Id[]);

    /**
     * 更新数据
     * @param commodity
     * @return
     */
    public int updateCommodity(Commodity commodity);

    /**
     * 分页集合List
     * @param commodityPageModel
     * @param commodity
     * @return
     */
    public List<Commodity> queryCommodityList(@Param("commodityPageModel") PageModel<Commodity> commodityPageModel, @Param("commodity") Commodity commodity);

    /**
     * 分页总数count
     * @param commodityPageModel
     * @param commodity
     * @return
     */
    public int queryCommodityCount(@Param("commodityPageModel") PageModel<Commodity> commodityPageModel, @Param("commodity") Commodity commodity);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public Commodity queryCommodityById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param commodity
     * @return
     */
    public List<Commodity> queryCommodityForDownLoadAll(@Param("commodity") Commodity commodity);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<Commodity> queryCommodityForDownLoad(Integer Id[]);

    /**
     *check Product_Name 是否存在
     * @param Product_Name
     * @return
     */
    public int queryCommodityProductNameExits(@Param("Product_Name") String Product_Name);

    /**
     * 查询所有Product_Name and Category
     * @param Product_Name
     * @return
     */
    public List<Commodity> queryCommodityByProductName(@Param("Product_Name") String Product_Name);

    /**
     * 检查用户有没有选择内容更新
     * @param commodity
     * @return
     */
    public int queryCommodityForAllExist(Commodity commodity);
}
