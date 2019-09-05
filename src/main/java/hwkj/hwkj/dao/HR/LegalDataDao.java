package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.LegalData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LegalDataDao {

    public List<LegalData> queryLegalDataByLegalName(LegalData legalData);

    public LegalData selectLegalDataByLegalName(@Param("Legal_Name") String Legal_Name);

    /**
     * 新增数据
     * @param legalData
     * @return
     */
    public int insertLegalData(LegalData legalData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteLegalData(Integer Id[]);

    /**
     * 更新数据
     * @param legalData
     * @return
     */
    public int updateLegalData(LegalData legalData);

    /**
     * 分页查询List
     * @param legalDataPageModel
     * @param legalData
     * @return
     */
    public List<LegalData> queryLegalDataList(@Param("legalDataPageModel") PageModel<LegalData> legalDataPageModel,@Param("legalData") LegalData legalData);

    /**
     * 分页查询Count
     * @param legalDataPageModel
     * @param legalData
     * @return
     */
    public int queryLegalDataCount(@Param("legalDataPageModel") PageModel<LegalData> legalDataPageModel,@Param("legalData") LegalData legalData);

    /**
     * for download all
     * @param legalData
     * @return
     */
    public List<LegalData> queryLegalDataForDownLoadAll(@Param("legalData") LegalData legalData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<LegalData> queryLegalDataForDownLoad(Integer Id[]);

    /**
     * check 数据是否已存在
     * @param legalData
     * @return
     */
    public int queryLegalDataForExist(LegalData legalData);

    /**
     * 查询最后一条数据
     * @param Two_Initials
     * @return
     */
    public LegalData queryLegalDataLastData(@Param("Two_Initials") String Two_Initials);

}
