package hwkj.hwkj.service.impl.SCMImpl;

import hwkj.hwkj.dao.SCM.VendorCodeDao;
import hwkj.hwkj.dao.SCM.VendorContactDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.VendorContactService;
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
public class VendorContactServiceImpl implements VendorContactService {
    @Autowired
    private VendorContactDao vendorContactDao;
    @Autowired
    private VendorCodeDao vendorCodeDao;

    /**
     * 新增数据
     * @param vendorContact
     * @return
     */
    @Transactional
    @Override
    public boolean insertVendorContact(VendorContact vendorContact) {
        if(vendorContactDao.queryVendorContactForExist(vendorContact.getVendor_Code())>0){
            throw new GlobalException("exist");
        }
        if(vendorContactDao.insertVendorContact(vendorContact)<=0){
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
    public boolean deleteVendorContact(Integer[] Id) {
        if(vendorContactDao.deleteVendorContact(Id)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param vendorContact
     * @param Old_Vendor_Code
     * @return
     */
    @Transactional
    @Override
    public boolean updateVendorContact(VendorContact vendorContact,String Old_Vendor_Code) {
        if(Old_Vendor_Code!=null && Old_Vendor_Code.trim().length()!=0){
            if(Old_Vendor_Code.trim().equals(vendorContact.getVendor_Code().trim())){
                if(vendorContactDao.updateVendorContact(vendorContact)<=0){
                    throw new GlobalException("error");
                }
            }
            else {
                if(vendorContactDao.queryVendorContactForExist(vendorContact.getVendor_Code())>0){
                    throw new GlobalException("exist");
                }
                if(vendorContactDao.updateVendorContact(vendorContact)<=0){
                    throw new GlobalException("error");
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 分页查询
     * @param vendorContactPageModel
     * @param vendorContact
     * @return
     */
    @Override
    public void queryVendorContactPage(PageModel<VendorContact> vendorContactPageModel, VendorContact vendorContact) {
        vendorContactPageModel.setList(vendorContactDao.queryVendorContactList(vendorContactPageModel, vendorContact));
        vendorContactPageModel.setTotal(vendorContactDao.queryVendorContactCount(vendorContactPageModel, vendorContact));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public VendorContact queryVendorContactById(Integer Id) {
        return vendorContactDao.queryVendorContactById(Id);
    }

    /**
     * for download all
     * @param vendorContact
     * @return
     */
    @Override
    public List<VendorContact> queryVendorContactForDownLoadAll(VendorContact vendorContact) {
        return vendorContactDao.queryVendorContactForDownLoadAll(vendorContact);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public VendorContact queryVendorContactForDownLoad(Integer Id) {
        return vendorContactDao.queryVendorContactForDownLoad(Id);
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
    public boolean uploadVendorContact(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        InputStream inputStream=multipartFile.getInputStream();
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet=xssfWorkbook.getSheetAt(0);
        List<VendorContact> list=new ArrayList<>();
        VendorContact vendorContact=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("blank:"+(i+1));
            }
            String Vendor_Code=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            List<VendorCode> vendorCodeList=vendorCodeDao.queryVendorCodeForOption(Vendor_Code);
            if(vendorCodeList.size()==0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("unExist:"+(i+1));
            }
            if(vendorContactDao.queryVendorContactForExist(Vendor_Code)>0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("exist:"+(i+1));
            }
            vendorContact=new VendorContact();
            vendorContact.setVendor_Code(Vendor_Code);
            vendorContact.setVendor_Chinese_Abbreviation(vendorCodeList.get(0).getVendor_Chinese_Abbreviation());
            vendorContact.setCountry_Area(vendorCodeList.get(0).getCountry_Area());
            vendorContact.setCity(vendorCodeList.get(0).getCity());
            if(sheet.getRow(i).getCell(1)!=null){
                vendorContact.setContact_Chinese_Name(sheet.getRow(i).getCell(1).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(2)!=null){
                vendorContact.setContact_English_Name(sheet.getRow(i).getCell(2).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(3)!=null){
                vendorContact.setSex(sheet.getRow(i).getCell(3).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(4)!=null){
                vendorContact.setTitle(sheet.getRow(i).getCell(4).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(5)!=null){
                vendorContact.setDept(sheet.getRow(i).getCell(5).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(6)!=null){
                vendorContact.setAuthority(sheet.getRow(i).getCell(6).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(7)!=null){
                vendorContact.setLanguage(sheet.getRow(i).getCell(7).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(8)!=null){
                vendorContact.setCompany_Telephone(sheet.getRow(i).getCell(8).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(9)!=null){
                vendorContact.setPhone_Number(sheet.getRow(i).getCell(9).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(10)!=null){
                vendorContact.setCompany_Mail(sheet.getRow(i).getCell(10).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(11)!=null){
                vendorContact.setPrivate_Mail(sheet.getRow(i).getCell(11).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(12)!=null){
                vendorContact.setWeChat_Number(sheet.getRow(i).getCell(12).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(13)!=null){
                vendorContact.setPurchase(sheet.getRow(i).getCell(13).getStringCellValue());
            }
            vendorContact.setStatus("Y");
            vendorContact.setCreator(((User)request.getSession().getAttribute("user")).getName());
            vendorContact.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //list.add(vendorContact);
            if(vendorContactDao.insertVendorContact(vendorContact)<=0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("error");
            }
        }
        /*if(vendorContactDao.uploadVendorContact(list)<=0){
            if(xssfWorkbook!=null){
                xssfWorkbook.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            throw new GlobalException("error");
        }*/
        if(xssfWorkbook!=null){
            xssfWorkbook.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        return true;
    }

}
