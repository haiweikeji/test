package hwkj.hwkj.service.Quote;

import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface QuoteTermService {

    /**
     * 新增数据
     * @param quoteTerm
     * @return
     */
    public boolean insertQuoteTerm(QuoteTerm quoteTerm);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteQuoteTerm(Integer Id[]);

    /**
     * 更新数据
     * @param quoteTerm
     * @return
     */
    public boolean updateQuoteTerm(QuoteTerm quoteTerm);

    /**
     * 分页查询
     * @param quoteTermPageModel
     * @param quoteTerm
     * @return
     */
    public void queryQuoteTermPage(PageModel<QuoteTerm> quoteTermPageModel,QuoteTerm quoteTerm);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public QuoteTerm queryQuoteTermById(Integer Id);

    /**
     * by Deliver_Term 查询
     * @return
     */
    public List<QuoteTerm> queryQuoteTermByDeliver_Term();

    /**
     * by Payment_Term 查询
     * @return
     */
    public List<QuoteTerm> queryQuoteTermByPayment_Term();

    /**
     * by Receive_Term 查询
     * @return
     */
    public List<QuoteTerm> queryQuoteTermByReceive_Term();

    /**
     * for download
     * @param quoteTerm
     * @return
     */
    public List<QuoteTerm> queryQuoteTermForDownLoadAll(QuoteTerm quoteTerm);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<QuoteTerm> queryQuoteTermForDownLoad(Integer Id[]);

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws Exception
     * @throws GlobalException
     */
    public boolean uploadQuoteTerm(HttpServletRequest request, InputStream inputStream)throws Exception, GlobalException;
}
