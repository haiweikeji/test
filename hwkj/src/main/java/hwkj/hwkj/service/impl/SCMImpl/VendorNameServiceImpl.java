package hwkj.hwkj.service.impl.SCMImpl;

import hwkj.hwkj.dao.SCM.VendorCodeDao;
import hwkj.hwkj.dao.SCM.VendorNameDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.VendorNameService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendorNameServiceImpl implements VendorNameService {
    @Autowired
    private VendorNameDao vendorNameDao;
    @Autowired
    private VendorCodeDao vendorCodeDao;

    /**
     * 新增数据
     * @param vendorName
     * @return
     */
    @Transactional
    @Override
    public boolean insertVendorName(VendorName vendorName) {
        if(vendorNameDao.queryVendorNameForExist(vendorName)>0){
            throw new GlobalException("exist");
        }
        if(vendorNameDao.insertVendorName(vendorName)<=0){
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
    public boolean deleteVendorName(Integer Id) {
        if(vendorNameDao.deleteVendorName(Id)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param vendorName
     * @param Old_Actual_Chinese_Full
     * @return
     */
    @Transactional
    @Override
    public boolean updateVendorName(VendorName vendorName,String Old_Actual_Chinese_Full) {
        /*if(Old_Actual_Chinese_Full!=null && Old_Actual_Chinese_Full.trim().length()!=0){
            if(Old_Actual_Chinese_Full.trim().equals(vendorName.getActual_Chinese_Full().trim())){
                if(vendorNameDao.updateVendorName(vendorName)<=0){
                    throw new GlobalException("error");
                }
            }
            else {
                if(vendorNameDao.queryVendorNameForExist(vendorName)>0){
                    throw new GlobalException("exist");
                }
                if(vendorNameDao.updateVendorName(vendorName)<=0){
                    throw new GlobalException("error");
                }
            }
            return true;
        }*/
        if(vendorNameDao.queryVendorNameForExist(vendorName)>0){
            throw new GlobalException("exist");
        }
        if(vendorNameDao.updateVendorName(vendorName)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询List
     * @param vendorNamePageModel
     * @param vendorName
     * @return
     */
    @Override
    public void queryVendorNamePage(PageModel<VendorName> vendorNamePageModel, VendorName vendorName) {
        vendorNamePageModel.setList(vendorNameDao.queryVendorNameList(vendorNamePageModel, vendorName));
        vendorNamePageModel.setTotal(vendorNameDao.queryVendorNameCount(vendorNamePageModel, vendorName));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public VendorName queryVendorNameById(Integer Id) {
        return vendorNameDao.queryVendorNameById(Id);
    }

    /**
     * for download all
     * @param vendorName
     * @return
     */
    @Override
    public List<VendorName> queryVendorNameForDownLoadAll(VendorName vendorName) {
        return vendorNameDao.queryVendorNameForDownLoadAll(vendorName);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public VendorName queryVendorNameForDownLoad(Integer Id) {
        return vendorNameDao.queryVendorNameForDownLoad(Id);
    }

    /**
     * 文件上传
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean vendorNameUpload(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        InputStream inputStream =multipartFile.getInputStream();
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        List<VendorName> list=new ArrayList<>();
        VendorName vendorName=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(2)==null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length()==0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("blank:"+(i+1));
            }
            vendorName=new VendorName();
            String Standard_Chinese_Full=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Actual_Chinese_Full=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            String Actual_English_Full=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            List<VendorCode> vendorCodeList=vendorCodeDao.queryVendorCodeForCheck(Standard_Chinese_Full);
            if(vendorCodeList.size()==0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("uExist:"+(i+1));
            }
            vendorName.setStandard_Chinese_Full(Standard_Chinese_Full);
            vendorName.setStandard_Chinese_Abbreviation(vendorCodeList.get(0).getVendor_Chinese_Abbreviation());
            vendorName.setStandard_English_Full(vendorCodeList.get(0).getVendor_English_Full());
            vendorName.setStandard_English_Abbreviation(vendorCodeList.get(0).getVendor_English_Abbreviation());
            vendorName.setActual_Chinese_Full(Actual_Chinese_Full);
            vendorName.setActual_English_Full(Actual_English_Full);
            if(vendorNameDao.queryVendorNameForExist(vendorName)>0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("exist:"+(i+1));
            }
            vendorName.setStatus("Y");
            vendorName.setCreator(((User)request.getSession().getAttribute("user")).getName());
            vendorName.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //list.add(vendorName);
            if(vendorNameDao.insertVendorName(vendorName)<=0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("error");
            }
        }
        if(xssfWorkbook!=null){
            xssfWorkbook.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        //if(vendorNameDao.uploadVendorName(list)<=0){
            //throw new GlobalException("error");
        //}
        return true;
    }

    /**
     * for 供应商中文全称
     * @param Vendor_Chinese_Full
     * @return
     */
    @Override
    public List<VendorName> queryVendorNameForOption(String Vendor_Chinese_Full) {
        return vendorNameDao.queryVendorNameForOption(Vendor_Chinese_Full);
    }

    /**
     * for 品牌
     * @param Brand
     * @return
     */
    @Override
    public List<VendorName> queryVendorNameForBrandOption(String Brand) {
        return vendorNameDao.queryVendorNameForBrandOption(Brand);
    }

}
