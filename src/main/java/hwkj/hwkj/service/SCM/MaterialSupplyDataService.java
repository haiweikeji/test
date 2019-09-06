package hwkj.hwkj.service.SCM;

import hwkj.hwkj.entity.SCM.MaterialSupplyData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface MaterialSupplyDataService {

    /**
     * 新增数据
     * @param materialSupplyData
     * @return
     */
    public boolean insertMaterialSupplyData(MaterialSupplyData materialSupplyData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteMaterialSupplyData(Integer Id[]);

    /**
     * 更新数据
     * @param materialSupplyData
     * @return
     */
    public boolean updateMaterialSupplyData(MaterialSupplyData materialSupplyData);

    /**
     * 分页查询
     * @param materialSupplyDataPageModel
     * @param materialSupplyData
     * @return
     */
    public void queryMaterialSupplyDataPage(PageModel<MaterialSupplyData> materialSupplyDataPageModel, MaterialSupplyData materialSupplyData) throws Exception;


    /**
     * by id 查询
     * @param Id
     * @return
     */
    public MaterialSupplyData queryMaterialSupplyDataById(Integer Id);

    /**
     * for download all
     * @param materialSupplyData
     * @return
     */
    public List<MaterialSupplyData> queryMaterialSupplyDataForDownLoadAll(MaterialSupplyData materialSupplyData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<MaterialSupplyData> queryMaterialSupplyDataForDownLoad(Integer Id[]);

    /**
     * 上传文件
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadMaterialSupplyData(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
