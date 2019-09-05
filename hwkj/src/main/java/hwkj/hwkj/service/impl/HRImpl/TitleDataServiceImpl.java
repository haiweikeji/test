package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.TitleDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.TitleDataService;
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
public class TitleDataServiceImpl implements TitleDataService {
    @Autowired
    private TitleDataDao titleDataDao;

    /**
     * 新增数据
     * @param titleData
     * @return
     */
    @Transactional
    @Override
    public boolean insertTitleData(TitleData titleData) {
        titleData.setStatus("Y");
        if(titleDataDao.queryTitleDataForExist(titleData)!=0){
            throw new GlobalException("exist");
        }
        if(titleDataDao.insertTitleData(titleData)<=0){
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
    public boolean deleteTitleData(Integer Id[]){
        if(titleDataDao.deleteTitleData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param titleData
     * @return
     */
    @Transactional
    @Override
    public boolean updateTitleData(TitleData titleData) {
        if(titleDataDao.updateTitleData(titleData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param titleDataPageModel
     * @param titleData
     */
    @Override
    public void queryTitleDataPage(PageModel<TitleData> titleDataPageModel, TitleData titleData) {
        titleDataPageModel.setList(titleDataDao.queryTitleDataList(titleDataPageModel, titleData));
        titleDataPageModel.setTotal(titleDataDao.queryTitleDataCount(titleDataPageModel, titleData));
    }

    /**
     * for download all
     * @param titleData
     * @return
     */
    @Override
    public List<TitleData> queryTitleDataForDownLoadAll(TitleData titleData) {
        return titleDataDao.queryTitleDataForDownLoadAll(titleData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<TitleData> queryTitleDataForDownLoad(Integer[] Id) {
        return titleDataDao.queryTitleDataForDownLoad(Id);
    }

    /**
     * 上传Excel
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean uploadTitleData(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        TitleData titleData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++) {
            for (int j = 0;j<3; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Management=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Management_Code=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            String Upper=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            titleData=new TitleData();
            titleData.setManagement(Management);
            titleData.setManagement_Code(Management_Code);
            titleData.setUpper(Upper);
            titleData.setStatus("Y");
            titleData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            titleData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(titleDataDao.queryTitleDataForExist(titleData)!=0){
                throw new GlobalException("exist:" + (i + 1));
            }
            if(titleDataDao.insertTitleData(titleData)<=0){
                throw new GlobalException("error:" + (i + 1));
            }
        }
        return true;
    }
}
