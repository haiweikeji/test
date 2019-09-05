package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.OrgData;
import hwkj.hwkj.entity.pagingquery.PageModel;

import java.util.List;

public interface OrgDataService {

    /**
     * by id 查询
     * @param id
     * @return
     */
    public OrgData queryOrgDataById(Integer id);


    /**
     * 新增数据
     * @param orgData
     * @return
     */
    public boolean insertOrgData(OrgData orgData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteOrgData(Integer Id[]);

    /**
     * 更新数据
     * @param orgData
     * @return
     */
    public boolean updateOrgData(OrgData orgData);

    /**
     * 计算组织代码
     * @param orgData
     * @return
     */
    public String calculateOrgCode(OrgData orgData);

    /**
     * 分页查询
     * @param orgDataPageModel
     * @param orgData
     * @return
     */
    public void queryOrgDataPage(PageModel<OrgData> orgDataPageModel, OrgData orgData);

    /**
     * for download all
     * @param orgData
     * @return
     */
    public List<OrgData> queryOrgDataForDownLoadAll(OrgData orgData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<OrgData> queryOrgDataForDownLoad(Integer Id[]);

}
