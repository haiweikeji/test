package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface TitleDataService {

    /**
     * 更新数据
     * @param titleData
     * @return
     */
    public boolean updateTitleData(TitleData titleData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteTitleData(Integer Id[]);

    /**
     * 新增数据
     * @param titleData
     * @return
     */
    public boolean insertTitleData(TitleData titleData);

    /**
     * 分页查询
     * @param titleDataPageModel
     * @param titleData
     */
    public void queryTitleDataPage(PageModel<TitleData> titleDataPageModel,TitleData titleData);

    /**
     * for download all
     * @param titleData
     * @return
     */
    public List<TitleData> queryTitleDataForDownLoadAll(TitleData titleData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<TitleData> queryTitleDataForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean uploadTitleData(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
