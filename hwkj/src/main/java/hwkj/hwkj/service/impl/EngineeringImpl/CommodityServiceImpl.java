package hwkj.hwkj.service.impl.EngineeringImpl;

import hwkj.hwkj.dao.Engineering.CommodityDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.CommodityService;
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
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityDao commodityDao;

    /**
     * 新增数据
     * @param commodity
     * @return
     */
    @Transactional
    @Override
    public boolean insertCommodity(Commodity commodity){
        if(commodityDao.queryCommodityProductNameExits(commodity.getProduct_Name().trim())!=0){
            throw new GlobalException("exist");
        }
        if(commodityDao.insertCommodity(commodity)<=0){
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
    public boolean deleteCommodity(Integer Id[]){
        if(commodityDao.deleteCommodity(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param commodity
     * @return
     */
    @Transactional
    @Override
    public boolean updateCommodity(Commodity commodity){
        if(commodityDao.queryCommodityForAllExist(commodity)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(commodity.getProduct_Name().trim().equals(commodity.getOld_Product_Name().trim())){
                if(commodityDao.updateCommodity(commodity)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(commodityDao.queryCommodityProductNameExits(commodity.getProduct_Name().trim())!=0){
                    throw new GlobalException("exist");
                }
                if(commodityDao.updateCommodity(commodity)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param commodityPageModel
     * @param commodity
     * @return
     */
    @Override
    public void queryCommodityPage(PageModel<Commodity> commodityPageModel, Commodity commodity) {
        commodityPageModel.setList(commodityDao.queryCommodityList(commodityPageModel, commodity));
        commodityPageModel.setTotal(commodityDao.queryCommodityCount(commodityPageModel, commodity));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public Commodity queryCommodityById(Integer Id) {
        return commodityDao.queryCommodityById(Id);
    }

    /**
     * for download all
     * @param commodity
     * @return
     */
    @Override
    public List<Commodity> queryCommodityForDownLoadAll(Commodity commodity) {
        return commodityDao.queryCommodityForDownLoadAll(commodity);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<Commodity> queryCommodityForDownLoad(Integer Id[]) {
        return commodityDao.queryCommodityForDownLoad(Id);
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
    public boolean uploadCommodity(HttpServletRequest request, InputStream inputStream) throws Exception, GlobalException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        Commodity commodity = null;
        for (int i = 1, length = sheet.getLastRowNum(); i <= length; i++) {
            for (int j = 0; j < 2; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Product_Name=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if(commodityDao.queryCommodityProductNameExits(Product_Name)!=0){
                throw new GlobalException("exist:" + (i + 1));
            }
            commodity=new Commodity();
            commodity.setProduct_Name(Product_Name);
            commodity.setCategory(sheet.getRow(i).getCell(1).getStringCellValue().trim());
            commodity.setCreator(((User)request.getSession().getAttribute("user")).getName());
            commodity.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(commodityDao.insertCommodity(commodity)<=0){
                throw new GlobalException("error:" + (i + 1));
            }
        }
        return true;
    }

    /**
     *查询所有Product_Name and Category
     * @param Product_Name
     * @return
     */
    @Override
    public List<Commodity> queryCommodityByProductName(String Product_Name) {
        return commodityDao.queryCommodityByProductName(Product_Name);
    }
}
