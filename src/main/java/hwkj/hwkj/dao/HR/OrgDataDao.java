package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.OrgData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrgDataDao {

    /**
     * by id查询
     * @param id
     * @return
     */
    public OrgData queryOrgDataById(Integer id);

    /**
     * 插入数据
     * @param orgData
     * @return
     */
    public int insertOrgData(OrgData orgData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteOrgData(Integer Id[]);

    /**
     * 更新数据
     * @param orgData
     * @return
     */
    public int updateOrgData(OrgData orgData);

    /**
     * 求bg事业群count
     * @param orgData
     * @return
     */
    public List<OrgData> bgCount(OrgData orgData);

    /**
     * 求bu事业处count
     * @param orgData
     * @return
     */
    public List<OrgData> buCount(OrgData orgData);

    /**
     * 求dept部门count
     * @param orgData
     * @return
     */
    public List<OrgData> deptCount(OrgData orgData);

    /**
     * 求ke count
     * @param orgData
     * @return
     */
    public List<OrgData> keCount(OrgData orgData);

    /**
     * 求zu count
     * @param orgData
     * @return
     */
    public List<OrgData> zuCount(OrgData orgData);

    /**
     * 分页查询List
     * @param orgDataPageModel
     * @param orgData
     * @return
     */
    public List<OrgData> queryOrgDataList(@Param("orgDataPageModel") PageModel<OrgData> orgDataPageModel,@Param("orgData") OrgData orgData);

    /**
     * 分页查询Count
     * @param orgDataPageModel
     * @param orgData
     * @return
     */
    public int queryOrgDataCount(@Param("orgDataPageModel") PageModel<OrgData> orgDataPageModel,@Param("orgData") OrgData orgData);

    /**
     * for download all
     * @param orgData
     * @return
     */
    public List<OrgData> queryOrgDataForDownLoadAll(@Param("orgData") OrgData orgData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<OrgData> queryOrgDataForDownLoad(Integer Id[]);

    /**
     * 下拉框
     * @param Status
     * @param Org_Data
     * @return
     */
    public List<OrgData> queryOrgDataForOption(@Param("Status") String Status,@Param("Org_Code") String Org_Data);

    /**
     * 检查上传时组织代码是否填写正确
     * @param Org_Code
     * @param Status
     * @return
     */
    public int queryOrgDataForUploadOrgCode(@Param("Org_Code") String Org_Code,@Param("Status") String Status);
}
