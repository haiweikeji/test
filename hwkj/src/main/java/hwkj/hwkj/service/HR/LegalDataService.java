package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.LegalData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface LegalDataService {

    public List<LegalData> queryLegalDataByLegalName(LegalData legalData);

    public LegalData selectLegalDataByLegalName(String Legal_Name);

    /**
     * 新增数据
     * @param legalData
     * @return
     */
    public boolean insertLegalData(LegalData legalData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteLegalData(Integer Id[]);

    /**
     * 更新数据
     * @param legalData
     * @return
     */
    public boolean updateLegalData(LegalData legalData);

    /**
     * 分页查询
     * @param legalDataPageModel
     * @param legalData
     */
    public void queryLegalDataPage(PageModel<LegalData> legalDataPageModel, LegalData legalData);

    /**
     * for download all
     * @param legalData
     * @return
     */
    public List<LegalData> queryLegalDataForDownLoadAll(LegalData legalData);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<LegalData> queryLegalDataForDownLoad(Integer Id[]);

    /**
     * Excel 上传
     * @param request
     * @param inputStream
     * @return
     * @throws Exception
     * @throws GlobalException
     */
    public boolean uploadLegalData(HttpServletRequest request, InputStream inputStream)throws Exception, GlobalException;
}
