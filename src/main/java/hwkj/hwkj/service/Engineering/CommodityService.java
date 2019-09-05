package hwkj.hwkj.service.Engineering;

import hwkj.hwkj.entity.Engineering.Commodity;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface CommodityService {

    /**
     * 新增数据
     * @param commodity
     * @return
     */
    public boolean insertCommodity(Commodity commodity);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteCommodity(Integer Id[]);

    /**
     * 更新数据
     * @param commodity
     * @return
     */
    public boolean updateCommodity(Commodity commodity);

    /**
     * 分页查询
     * @param commodityPageModel
     * @param commodity
     * @return
     */
    public void queryCommodityPage(PageModel<Commodity> commodityPageModel, Commodity commodity);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public Commodity queryCommodityById(Integer Id);

    /**
     * for download all
     * @param commodity
     * @return
     */
    public List<Commodity> queryCommodityForDownLoadAll(Commodity commodity);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<Commodity> queryCommodityForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws Exception
     * @throws GlobalException
     */
    public boolean uploadCommodity(HttpServletRequest request, InputStream inputStream)throws Exception, GlobalException;

    /**
     * 查询所有Product_Name and Category
     * @param Product_Name
     * @return
     */
    public List<Commodity> queryCommodityByProductName(String Product_Name);
}
