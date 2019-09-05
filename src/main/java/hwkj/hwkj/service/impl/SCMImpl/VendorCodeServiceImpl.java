package hwkj.hwkj.service.impl.SCMImpl;

import hwkj.hwkj.dao.SCM.VendorCodeDao;
import hwkj.hwkj.dao.SCM.VendorNameDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.SCM.VendorCodeService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class VendorCodeServiceImpl implements VendorCodeService {
    @Autowired
    private VendorCodeDao vendorCodeDao;
    @Autowired
    private VendorNameDao vendorNameDao;

    /**
     * 新增数据
     * @param vendorCode
     * @return
     */
    @Transactional
    @Override
    public boolean insertVendorCode(VendorCode vendorCode) {
        List<VendorCode> vendorCodeList=vendorCodeDao.queryVendorCodeForCheck(vendorCode.getVendor_Chinese_Full());
        if(vendorCodeList.size()==0){
            if("原厂".equals(vendorCode.getVendor_Nature().trim())){
                 if(vendorCodeDao.queryVendorCodeForBrandExist(vendorCode.getBrand())>0){
                     throw new GlobalException("brandExist");
                 }
                VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("原厂");
                if(vendorCodeAdd==null){
                    vendorCode.setVendor_Code("B000001");
                }
                else {
                    int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                    if(count<9){
                        vendorCode.setVendor_Code("B00000"+(count+1));
                    }
                    else if(count>=9 && count<99){
                        vendorCode.setVendor_Code("B0000"+(count+1));
                    }
                    else if(count>=99 && count<999){
                        vendorCode.setVendor_Code("B000"+(count+1));
                    }
                    else if(count>=999 && count<9999){
                        vendorCode.setVendor_Code("B00"+(count+1));
                    }
                    else if(count>=9999 && count<99999){
                        vendorCode.setVendor_Code("B0"+(count+1));
                    }
                    else {
                        vendorCode.setVendor_Code("B"+(count+1));
                    }
                }
            }
            else if("代理商".equals(vendorCode.getVendor_Nature().trim())){
                VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("代理商");
                if(vendorCodeAdd==null){
                    vendorCode.setVendor_Code("A000001");
                }
                else {
                    int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                    if(count<9){
                        vendorCode.setVendor_Code("A00000"+(count+1));
                    }
                    else if(count>=9 && count<99){
                        vendorCode.setVendor_Code("A0000"+(count+1));
                    }
                    else if(count>=99 && count<999){
                        vendorCode.setVendor_Code("A000"+(count+1));
                    }
                    else if(count>=999 && count<9999){
                        vendorCode.setVendor_Code("A00"+(count+1));
                    }
                    else if(count>=9999 && count<99999){
                        vendorCode.setVendor_Code("A0"+(count+1));
                    }
                    else {
                        vendorCode.setVendor_Code("A"+(count+1));
                    }
                }
            }
        }
        else {
            if(vendorCode.getVendor_Chinese_Abbreviation().trim().equals(vendorCodeList.get(0).getVendor_Chinese_Abbreviation().trim()) &&
            vendorCode.getVendor_English_Full().trim().equals(vendorCodeList.get(0).getVendor_English_Full().trim()) &&
            vendorCode.getVendor_English_Abbreviation().trim().equals(vendorCodeList.get(0).getVendor_English_Abbreviation().trim())){
                if(vendorCodeDao.queryVendorCodeForExist(vendorCode)>0){
                    throw new GlobalException("exist");
                }
                if("原厂".equals(vendorCode.getVendor_Nature().trim())){
                    if(vendorCodeDao.queryVendorCodeForBrandExist(vendorCode.getBrand())>0){
                        throw new GlobalException("brandExist");
                    }
                    VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("原厂");
                    if(vendorCodeAdd==null){
                        vendorCode.setVendor_Code("B000001");
                    }
                    else {
                        int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                        if(count<9){
                            vendorCode.setVendor_Code("B00000"+(count+1));
                        }
                        else if(count>=9 && count<99){
                            vendorCode.setVendor_Code("B0000"+(count+1));
                        }
                        else if(count>=99 && count<999){
                            vendorCode.setVendor_Code("B000"+(count+1));
                        }
                        else if(count>=999 && count<9999){
                            vendorCode.setVendor_Code("B00"+(count+1));
                        }
                        else if(count>=9999 && count<99999){
                            vendorCode.setVendor_Code("B0"+(count+1));
                        }
                        else {
                            vendorCode.setVendor_Code("B"+(count+1));
                        }
                    }
                }
                else if("代理商".equals(vendorCode.getVendor_Nature().trim())){
                    VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("代理商");
                    if(vendorCodeAdd==null){
                        vendorCode.setVendor_Code("A000001");
                    }
                    else {
                        int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                        if(count<9){
                            vendorCode.setVendor_Code("A00000"+(count+1));
                        }
                        else if(count>=9 && count<99){
                            vendorCode.setVendor_Code("A0000"+(count+1));
                        }
                        else if(count>=99 && count<999){
                            vendorCode.setVendor_Code("A000"+(count+1));
                        }
                        else if(count>=999 && count<9999){
                            vendorCode.setVendor_Code("A00"+(count+1));
                        }
                        else if(count>=9999 && count<99999){
                            vendorCode.setVendor_Code("A0"+(count+1));
                        }
                        else {
                            vendorCode.setVendor_Code("A"+(count+1));
                        }
                    }
                }
            }
            else {
                throw new GlobalException("unCheck");
            }
        }
        if(vendorCodeDao.insertVendorCode(vendorCode)<=0){
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
    public boolean deleteVendorCode(Integer Id[]) {
        if(vendorCodeDao.deleteVendorCode(Id)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param vendorCode
     * @return
     */
    @Transactional
    @Override
    public boolean updateVendorCode(VendorCode vendorCode) {
        if(vendorCodeDao.updateVendorCode(vendorCode)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param vendorCodePageModel
     * @param vendorCode
     * @return
     */
    @Override
    public void queryVendorCodePage(PageModel<VendorCode> vendorCodePageModel, VendorCode vendorCode) {
        vendorCodePageModel.setList(vendorCodeDao.queryVendorContactList(vendorCodePageModel, vendorCode));
        vendorCodePageModel.setTotal(vendorCodeDao.queryVendorCodeCount(vendorCodePageModel, vendorCode));
    }

    /**
     * by id 查询
     * @param Id
     * @return
     */
    @Override
    public VendorCode queryVendorCodeById(Integer Id) {
        return vendorCodeDao.queryVendorCodeById(Id);
    }

    /**
     * for download all
     * @param vendorCode
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForDownLoadAll(VendorCode vendorCode) {
        return vendorCodeDao.queryVendorCodeForDownLoadAll(vendorCode);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public VendorCode queryVendorCodeForDownLoad(Integer Id) {
        return vendorCodeDao.queryVendorCodeForDownLoad(Id);
    }

    /**
     * 上传文件
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean uploadVendorCode(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        InputStream inputStream =multipartFile.getInputStream();
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet=xssfWorkbook.getSheetAt(0);
        VendorCode vendorCode=null;
        List<VendorName> vendorNameList=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(2)==null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(4)==null || sheet.getRow(i).getCell(4).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(5)==null || sheet.getRow(i).getCell(5).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(6)==null || sheet.getRow(i).getCell(6).getStringCellValue().trim().length()==0){
                if(xssfWorkbook!=null){
                    xssfWorkbook.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                throw new GlobalException("blank:"+(i+1));
            }
            vendorCode=new VendorCode();
            String Vendor_Nature=sheet.getRow(i).getCell(4).getStringCellValue().trim();
            if("原厂".equals(Vendor_Nature)){
                if(sheet.getRow(i).getCell(7)==null || sheet.getRow(i).getCell(7).getStringCellValue().trim().length()==0){
                    if(xssfWorkbook!=null){
                        xssfWorkbook.close();
                    }
                    if(inputStream!=null){
                        inputStream.close();
                    }
                    throw new GlobalException("brand:"+(i+1));
                }
                String Brand=sheet.getRow(i).getCell(7).getStringCellValue().trim();
                vendorCode.setBrand(Brand);
                if(vendorCodeDao.queryVendorCodeForBrandExist(Brand)>0){
                    if(xssfWorkbook!=null){
                        xssfWorkbook.close();
                    }
                    if(inputStream!=null){
                        inputStream.close();
                    }
                    throw new GlobalException("brandExist:"+(i+1));
                }
            }
            String Vendor_Chinese_Full=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Vendor_Chinese_Abbreviation=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            String Vendor_English_Full=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            String Vendor_English_Abbreviation=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            String Country_Area=sheet.getRow(i).getCell(5).getStringCellValue().trim();
            String City=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            vendorCode.setVendor_Chinese_Full(Vendor_Chinese_Full);
            vendorCode.setVendor_Chinese_Abbreviation(Vendor_Chinese_Abbreviation);
            vendorCode.setVendor_English_Full(Vendor_English_Full);
            vendorCode.setVendor_English_Abbreviation(Vendor_English_Abbreviation);
            vendorCode.setVendor_Nature(Vendor_Nature);
            vendorCode.setCountry_Area(Country_Area);
            vendorCode.setCity(City);
            List<VendorCode> vendorCodeList=vendorCodeDao.queryVendorCodeForCheck(Vendor_Chinese_Full);
            if(vendorCodeList.size()==0){
                if("原厂".equals(Vendor_Nature.trim())){
                    VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("原厂");
                    if(vendorCodeAdd==null){
                        vendorCode.setVendor_Code("B000001");
                    }
                    else {
                        int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                        if(count<9){
                            vendorCode.setVendor_Code("B00000"+(count+1));
                        }
                        else if(count>=9 && count<99){
                            vendorCode.setVendor_Code("B0000"+(count+1));
                        }
                        else if(count>=99 && count<999){
                            vendorCode.setVendor_Code("B000"+(count+1));
                        }
                        else if(count>=999 && count<9999){
                            vendorCode.setVendor_Code("B00"+(count+1));
                        }
                        else if(count>=9999 && count<99999){
                            vendorCode.setVendor_Code("B0"+(count+1));
                        }
                        else {
                            vendorCode.setVendor_Code("B"+(count+1));
                        }
                    }
                }
                else if("代理商".equals(Vendor_Nature.trim())){
                    VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("代理商");
                    if(vendorCodeAdd==null){
                        vendorCode.setVendor_Code("A000001");
                    }
                    else {
                        int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                        if(count<9){
                            vendorCode.setVendor_Code("A00000"+(count+1));
                        }
                        else if(count>=9 && count<99){
                            vendorCode.setVendor_Code("A0000"+(count+1));
                        }
                        else if(count>=99 && count<999){
                            vendorCode.setVendor_Code("A000"+(count+1));
                        }
                        else if(count>=999 && count<9999){
                            vendorCode.setVendor_Code("A00"+(count+1));
                        }
                        else if(count>=9999 && count<99999){
                            vendorCode.setVendor_Code("A0"+(count+1));
                        }
                        else {
                            vendorCode.setVendor_Code("A"+(count+1));
                        }
                    }
                }
            }
            else {
                if(Vendor_Chinese_Abbreviation.trim().equals(vendorCodeList.get(0).getVendor_Chinese_Abbreviation().trim()) &&
                        Vendor_English_Full.trim().equals(vendorCodeList.get(0).getVendor_English_Full().trim()) &&
                        Vendor_English_Abbreviation.trim().equals(vendorCodeList.get(0).getVendor_English_Abbreviation().trim())){
                    if(vendorCodeDao.queryVendorCodeForExist(vendorCode)>0){
                        if(xssfWorkbook!=null){
                            xssfWorkbook.close();
                        }
                        if(inputStream!=null){
                            inputStream.close();
                        }
                        throw new GlobalException("exist:"+(i+1));
                    }
                    if("原厂".equals(Vendor_Nature.trim())){
                        VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("原厂");
                        if(vendorCodeAdd==null){
                            vendorCode.setVendor_Code("B000001");
                        }
                        else {
                            int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                            if(count<9){
                                vendorCode.setVendor_Code("B00000"+(count+1));
                            }
                            else if(count>=9 && count<99){
                                vendorCode.setVendor_Code("B0000"+(count+1));
                            }
                            else if(count>=99 && count<999){
                                vendorCode.setVendor_Code("B000"+(count+1));
                            }
                            else if(count>=999 && count<9999){
                                vendorCode.setVendor_Code("B00"+(count+1));
                            }
                            else if(count>=9999 && count<99999){
                                vendorCode.setVendor_Code("B0"+(count+1));
                            }
                            else {
                                vendorCode.setVendor_Code("B"+(count+1));
                            }
                        }
                    }
                    else if("代理商".equals(Vendor_Nature.trim())){
                        VendorCode vendorCodeAdd=vendorCodeDao.queryVendorCodeLastData("代理商");
                        if(vendorCodeAdd==null){
                            vendorCode.setVendor_Code("A000001");
                        }
                        else {
                            int count=Integer.parseInt(vendorCodeAdd.getVendor_Code().substring(1));//去除B
                            if(count<9){
                                vendorCode.setVendor_Code("A00000"+(count+1));
                            }
                            else if(count>=9 && count<99){
                                vendorCode.setVendor_Code("A0000"+(count+1));
                            }
                            else if(count>=99 && count<999){
                                vendorCode.setVendor_Code("A000"+(count+1));
                            }
                            else if(count>=999 && count<9999){
                                vendorCode.setVendor_Code("A00"+(count+1));
                            }
                            else if(count>=9999 && count<99999){
                                vendorCode.setVendor_Code("A0"+(count+1));
                            }
                            else {
                                vendorCode.setVendor_Code("A"+(count+1));
                            }
                        }
                    }
                }
                else {
                    if(xssfWorkbook!=null){
                        xssfWorkbook.close();
                    }
                    if(inputStream!=null){
                        inputStream.close();
                    }
                    throw new GlobalException("unCheck:"+(i+1));
                }
            }
            if(sheet.getRow(i).getCell(8)!=null){
                vendorCode.setInvoice_Address(sheet.getRow(i).getCell(8).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(9)!=null){
                vendorCode.setIndustry(sheet.getRow(i).getCell(9).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(10)!=null){
                vendorCode.setSupplier_Category(sheet.getRow(i).getCell(10).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(11)!=null){
                vendorCode.setCompany_Owner(sheet.getRow(i).getCell(11).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(12)!=null){
                vendorCode.setVendor_Level(sheet.getRow(i).getCell(12).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(13)!=null){
                vendorCode.setWebsite_Address(sheet.getRow(i).getCell(13).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(14)!=null){
                vendorCode.setBank(sheet.getRow(i).getCell(14).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(15)!=null){
                vendorCode.setBank_Account(sheet.getRow(i).getCell(15).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(16)!=null){
                vendorCode.setBank_Address(sheet.getRow(i).getCell(16).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(17)!=null){
                vendorCode.setAcceptable_Currency(sheet.getRow(i).getCell(17).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(18)!=null){
                vendorCode.setAnnual_Revenue(sheet.getRow(i).getCell(18).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(19)!=null){
                vendorCode.setIndustry_Rank(sheet.getRow(i).getCell(19).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(20)!=null){
                vendorCode.setEnterprise_Nature(sheet.getRow(i).getCell(20).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(21)!=null){
                vendorCode.setEmployee_Qty(Integer.parseInt(sheet.getRow(i).getCell(21).getStringCellValue()));
            }
            if(sheet.getRow(i).getCell(22)!=null){
                vendorCode.setCredit_Code(sheet.getRow(i).getCell(22).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(23)!=null){
                vendorCode.setTaxpayer_Number(sheet.getRow(i).getCell(23).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(24)!=null){
                vendorCode.setRegistration_Number(sheet.getRow(i).getCell(24).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(25)!=null){
                vendorCode.setRegistration_Time(sheet.getRow(i).getCell(25).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(26)!=null){
                vendorCode.setRegistered_Capital(sheet.getRow(i).getCell(26).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(27)!=null){
                vendorCode.setCredit_Level(sheet.getRow(i).getCell(27).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(28)!=null){
                vendorCode.setCredit_Amount(sheet.getRow(i).getCell(28).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(29)!=null){
                vendorCode.setCurrency(sheet.getRow(i).getCell(29).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(30)!=null){
                vendorCode.setPurchase(sheet.getRow(i).getCell(30).getStringCellValue());
            }
            vendorCode.setStatus("Y");
            vendorCode.setCreator(((User)request.getSession().getAttribute("user")).getName());
            vendorCode.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(vendorCodeDao.insertVendorCode(vendorCode)<=0){
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
        return true;
    }

    /**
     * for 供应商代码
     * @param Vendor_Code
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForOption(String Vendor_Code) {
        return vendorCodeDao.queryVendorCodeForOption(Vendor_Code);
    }

    /**
     * like语句查询
     * @param Brand
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForLikeBrandOption(String Brand) {
        return vendorCodeDao.queryVendorCodeForLikeBrandOption(Brand);
    }

    /**
     * for 供应商标准中文全称
     * @param Vendor_Chinese_Full
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForVendorChineseFullOption(String Vendor_Chinese_Full) {
        return vendorCodeDao.queryVendorCodeForVendorChineseFullOption(Vendor_Chinese_Full);
    }

    /**
     * for 品牌
     * @param Brand
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForBrandOption(String Brand) {
        return vendorCodeDao.queryVendorCodeForBrandOption(Brand);
    }

    /**
     * 查询所有代理商的vendor_code
     *
     * @param Vendor_Nature
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForVendorCodeOption(String Vendor_Nature) {
        return vendorCodeDao.queryVendorCodeForVendorCodeOption(Vendor_Nature);
    }

    /**
     * 通过查询品牌来获取供应商代码
     *
     * @param Brand
     * @return
     */
    @Override
    public List<VendorCode> queryVendorCodeForOptionVendorCode(String Brand) {
        return vendorCodeDao.queryVendorCodeForOptionVendorCode(Brand);
    }
}
