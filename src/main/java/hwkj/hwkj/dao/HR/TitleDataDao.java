package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.TitleData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TitleDataDao {

    /**
     * 新增数据
     * @param titleData
     * @return
     */
    public int insertTitleData(TitleData titleData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteTitleData(Integer Id[]);

    /**
     * 更新数据
     * @param titleData
     * @return
     */
    public int updateTitleData(TitleData titleData);

    /**
     * 分页查询List
     * @param titleDataPageModel
     * @param titleData
     * @return
     */
    public List<TitleData> queryTitleDataList(@Param("titleDataPageModel") PageModel<TitleData> titleDataPageModel,@Param("titleData") TitleData titleData);

    /**
     * 分页查询Count
     * @param titleDataPageModel
     * @param titleData
     * @return
     */
    public int queryTitleDataCount(@Param("titleDataPageModel") PageModel<TitleData> titleDataPageModel,@Param("titleData") TitleData titleData);

    /**
     * for download all
     * @param titleData
     * @return
     */
    public List<TitleData> queryTitleDataForDownLoadAll(@Param("titleData") TitleData titleData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<TitleData> queryTitleDataForDownLoad(Integer Id[]);

    /**
     * check 数据是否已存在
     * @param titleData
     * @return
     */
    public int queryTitleDataForExist(TitleData titleData);

    /**
     * 下拉框
     * @param Status
     * @return
     */
    public List<TitleData> queryTitleDataForOption(@Param("Status") String Status);

    /**
     * 检查上传时组织代码是否填写正确
     * @param Management
     * @param Status
     * @return
     */
    public int queryTitleDataForUploadManagement(@Param("Management") String Management,@Param("Status") String Status);
}
