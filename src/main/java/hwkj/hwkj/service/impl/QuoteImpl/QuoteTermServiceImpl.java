package hwkj.hwkj.service.impl.QuoteImpl;

import hwkj.hwkj.dao.Quote.QuoteTermDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Quote.QuoteTermService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QuoteTermServiceImpl implements QuoteTermService {

    private final static String Regex_Days="^[0-9]$|^[1-9][0-9]+$";
    private final static String Regex_Percent="^100$|^([0-9]|[1-9][0-9])([.][0-9])$|^([0-9]|[1-9][0-9])([.][0-9]{2})*$";

    @Autowired
    private QuoteTermDao quoteTermDao;

    /**
     * 新增数据
     * @param quoteTerm
     * @return
     */
    @Transactional
    @Override
    public boolean insertQuoteTerm(QuoteTerm quoteTerm){
        if(quoteTerm.getPercent()!=null && quoteTerm.getPercent().trim().length()!=0){
            quoteTerm.setPercent(quoteTerm.getPercent()+"%");
        }
        quoteTerm.setStatus("Y");
        if(quoteTermDao.queryQuoteTermForExist(quoteTerm.getCondition_Code().trim(),"Y")!=0){
            throw new GlobalException("exist");
        }
        if(quoteTermDao.insertQuoteTerm(quoteTerm)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 删除数据
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteQuoteTerm(Integer Id[]){
        if(quoteTermDao.deleteQuoteTerm(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param quoteTerm
     * @return
     */
    @Transactional
    @Override
    public boolean updateQuoteTerm(QuoteTerm quoteTerm){
        if(quoteTermDao.queryQuoteTermForAllExist(quoteTerm)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(quoteTerm.getCondition_Code().trim().equals(quoteTerm.getOld_Condition_Code().trim())){
                if(quoteTermDao.updateQuoteTerm(quoteTerm)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(quoteTermDao.queryQuoteTermForExist(quoteTerm.getCondition_Code().trim(),"Y")!=0){
                    throw new GlobalException("exist");
                }
                if(quoteTermDao.updateQuoteTerm(quoteTerm)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param quoteTermPageModel
     * @param quoteTerm
     * @return
     */
    @Override
    public void queryQuoteTermPage(PageModel<QuoteTerm> quoteTermPageModel, QuoteTerm quoteTerm) {
        quoteTermPageModel.setList(quoteTermDao.queryQuoteTermList(quoteTermPageModel,quoteTerm));
        quoteTermPageModel.setTotal(quoteTermDao.queryQuoteTermCount(quoteTermPageModel,quoteTerm));
    }

    /**
     * bu Id 查询
     * @param Id
     * @return
     */
    @Override
    public QuoteTerm queryQuoteTermById(Integer Id) {
        return quoteTermDao.queryQuoteTermById(Id);
    }

    /**
     * by Deliver_Term 查询
     * @return
     */
    @Override
    public List<QuoteTerm> queryQuoteTermByDeliver_Term() {
        return quoteTermDao.queryQuoteTermByDeliver_Term();
    }

    /**
     * by Payment_Term 查询
     * @return
     */
    @Override
    public List<QuoteTerm> queryQuoteTermByPayment_Term() {
        return quoteTermDao.queryQuoteTermByPayment_Term();
    }

    /**
     * by Receive_Term 查询
     * @return
     */
    @Override
    public List<QuoteTerm> queryQuoteTermByReceive_Term() {
        return quoteTermDao.queryQuoteTermByReceive_Term();
    }

    /**
     * for download
     * @param quoteTerm
     * @return
     */
    @Override
    public List<QuoteTerm> queryQuoteTermForDownLoadAll(QuoteTerm quoteTerm) {
        return quoteTermDao.queryQuoteTermForDownLoadAll(quoteTerm);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<QuoteTerm> queryQuoteTermForDownLoad(Integer[] Id) {
        return quoteTermDao.queryQuoteTermForDownLoad(Id);
    }

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws Exception
     * @throws GlobalException
     */
    @Transactional
    @Override
    public boolean uploadQuoteTerm(HttpServletRequest request, InputStream inputStream) throws Exception, GlobalException {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        QuoteTerm quoteTerm=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++) {
            if (sheet.getRow(i).getCell(0) == null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length() == 0 ||
                    sheet.getRow(i).getCell(1) == null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length() == 0 ||
                    sheet.getRow(i).getCell(2) == null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length() == 0 ||
                    sheet.getRow(i).getCell(3) == null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length() == 0) {
                throw new GlobalException("blank:" + (i + 1));//代表必填项没填
            }
            quoteTerm=new QuoteTerm();
            String Condition_Type=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Condition_Code=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(quoteTermDao.queryQuoteTermForExist(Condition_Code,"Y")!=0){
                throw new GlobalException("exist:" + (i + 1));
            }
            String Clause=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            String Described=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            quoteTerm.setCondition_Type(Condition_Type);
            quoteTerm.setCondition_Code(Condition_Code);
            quoteTerm.setClause(Clause);
            quoteTerm.setDescribed(Described);
            if(sheet.getRow(i).getCell(4)!=null && sheet.getRow(i).getCell(4).getStringCellValue().trim().length()!=0){
                String Days=sheet.getRow(i).getCell(4).getStringCellValue().trim();
                if(!Days.matches(Regex_Days)){
                    throw new GlobalException("days:" + (i + 1));
                }
                quoteTerm.setDays(Integer.parseInt(Days));
            }else {
                quoteTerm.setDays(null);
            }
            if(sheet.getRow(i).getCell(5)!=null && sheet.getRow(i).getCell(5).getStringCellValue().trim().length()!=0){
                String Percent=sheet.getRow(i).getCell(5).getStringCellValue().trim();
                if(!Percent.matches(Regex_Percent)){
                    throw new GlobalException("percent:" + (i + 1));
                }
                quoteTerm.setPercent(Percent+"%");
            }else {
                quoteTerm.setPercent("");
            }
            if(sheet.getRow(i).getCell(6)!=null){
                quoteTerm.setRemark(sheet.getRow(i).getCell(6).getStringCellValue().trim());
            }else {
                quoteTerm.setRemark("");
            }
            quoteTerm.setStatus("Y");
            quoteTerm.setCreator(((User)request.getSession().getAttribute("user")).getName());
            quoteTerm.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(quoteTermDao.insertQuoteTerm(quoteTerm)<=0){
                throw new GlobalException("error:" + (i + 1));
            }
        }
        return true;
    }
}
