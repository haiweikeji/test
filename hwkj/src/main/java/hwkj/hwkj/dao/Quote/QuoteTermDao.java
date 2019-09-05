package hwkj.hwkj.dao.Quote;

import hwkj.hwkj.entity.Quote.QuoteTerm;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuoteTermDao {

    /**
     * 新增数据
     * @param quoteTerm
     * @return
     */
    public int insertQuoteTerm(QuoteTerm quoteTerm);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteQuoteTerm(Integer Id[]);

    /**
     * 更新数据
     * @param quoteTerm
     * @return
     */
    public int updateQuoteTerm(QuoteTerm quoteTerm);

    /**
     * 分页查询集合
     * @param quoteTermPageModel
     * @param quoteTerm
     * @return
     */
    public List<QuoteTerm> queryQuoteTermList(@Param("quoteTermPageModel") PageModel<QuoteTerm> quoteTermPageModel,@Param("quoteTerm") QuoteTerm quoteTerm);

    /**
     * 分页查询总数count
     * @param quoteTermPageModel
     * @param quoteTerm
     * @return
     */
    public int  queryQuoteTermCount(@Param("quoteTermPageModel") PageModel<QuoteTerm> quoteTermPageModel,@Param("quoteTerm") QuoteTerm quoteTerm);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public QuoteTerm queryQuoteTermById(@Param("Id") Integer Id);

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
     * 检查上传时交易条件是否正确
     * @param Deliver_Term
     * @return
     */
    public int queryQuoteTermForUploadDeliverTerm(@Param("Deliver_Term") String Deliver_Term);

    /**
     * 检查上传时付款条件是否正确
     * @param Payment_Term
     * @return
     */
    public int queryQuoteTermForUploadPaymentTerm(@Param("Payment_Term") String Payment_Term);

    /**
     * 检查上传时验收条件是否正确
     * @param Receive_Term
     * @return
     */
    public int queryQuoteTermForUploadReceiveTerm(@Param("Receive_Term") String Receive_Term);

    /**
     * for download all
     * @param quoteTerm
     * @return
     */
    public List<QuoteTerm> queryQuoteTermForDownLoadAll(@Param("quoteTerm") QuoteTerm quoteTerm);

    /**
     * for download
     * @param Id
     * @return
     */
    public List<QuoteTerm> queryQuoteTermForDownLoad(Integer Id[]);

    /**
     * 检查新增数据是否已存在
     * @param Condition_Code
     * @param Status
     * @return
     */
    public int queryQuoteTermForExist(@Param("Condition_Code") String Condition_Code,@Param("Status") String Status);

    /**
     * 检查用户有没有更新内容
     * @param quoteTerm
     * @return
     */
    public int queryQuoteTermForAllExist(QuoteTerm quoteTerm);
}
