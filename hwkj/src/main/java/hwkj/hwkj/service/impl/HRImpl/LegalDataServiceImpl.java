package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.LegalDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.LegalDataService;
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
public class LegalDataServiceImpl implements LegalDataService {

    private final static String Regex_Registered_Time="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";
    private final static String Regex_Registered_Capital="^[0-9]+$|^([0-9]+)([.][0-9])$|^([0-9]+)([.][0-9]{2})$";
    private final static String Regex_Taxpayer_Number="^[a-zA-Z0-9]+$";
    private final static String Regex_Registration_Number="^[a-zA-Z0-9]+$";
    private final static String Regex_Bank_Account="^[0-9]{16}$|^[0-9]{19}$";

    @Autowired
    private LegalDataDao legalDataDao;

    /**
     * @param legalData
     * @return
     */
    @Override
    public List<LegalData> queryLegalDataByLegalName(LegalData legalData) {
        return legalDataDao.queryLegalDataByLegalName(legalData);
    }

    /**
     *
     * @param Legal_Name
     * @return
     */
    @Override
    public LegalData selectLegalDataByLegalName(String Legal_Name) {
        return legalDataDao.selectLegalDataByLegalName(Legal_Name);
    }

    /**
     * 新增数据
     * @param legalData
     * @return
     */
    @Transactional
    @Override
    public boolean insertLegalData(LegalData legalData){
        String Company_Code="";
        String Two_Initials=legalData.getTwo_Initials().trim().toUpperCase();
        if(Two_Initials.length()==0){
            Two_Initials=Two_Initials+"AA";
        }
        else if(Two_Initials.length()==1){
            Two_Initials=Two_Initials+"A";
        }
        else {
            Two_Initials=Two_Initials.substring(0,2);
        }
        legalData.setTwo_Initials(Two_Initials);
        if(legalDataDao.queryLegalDataForExist(legalData)!=0){
            throw new GlobalException("exist");
        }
        LegalData legalDataAdd=legalDataDao.queryLegalDataLastData(Two_Initials);
        if(legalDataAdd==null){
            Company_Code=Two_Initials+"01";
        }
        else {
            int count=Integer.parseInt(legalDataAdd.getCompany_Code().substring(2));
            if(count>0 && count<9){
                Company_Code=Two_Initials+"0"+(count+1);
            }
            else {
                Company_Code=Two_Initials+(count+1);
            }
        }
        legalData.setCompany_Code(Company_Code);
        if(legalDataDao.insertLegalData(legalData)<=0){
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
    public boolean deleteLegalData(Integer Id[]){
        if(legalDataDao.deleteLegalData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param legalData
     * @return
     */
    @Transactional
    @Override
    public boolean updateLegalData(LegalData legalData){
        if(legalDataDao.updateLegalData(legalData)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param legalDataPageModel
     * @param legalData
     */
    @Override
    public void queryLegalDataPage(PageModel<LegalData> legalDataPageModel, LegalData legalData) {
        legalDataPageModel.setList(legalDataDao.queryLegalDataList(legalDataPageModel,legalData));
        legalDataPageModel.setTotal(legalDataDao.queryLegalDataCount(legalDataPageModel,legalData));
    }

    /**
     * for download all
     * @param legalData
     * @return
     */
    @Override
    public List<LegalData> queryLegalDataForDownLoadAll(LegalData legalData) {
        return legalDataDao.queryLegalDataForDownLoadAll(legalData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<LegalData> queryLegalDataForDownLoad(Integer[] Id) {
        return legalDataDao.queryLegalDataForDownLoad(Id);
    }

    /**
     * Excel 上传
     * @param request
     * @param inputStream
     * @return
     * @throws Exception
     * @throws GlobalException
     */
    @Transactional
    @Override
    public boolean uploadLegalData(HttpServletRequest request, InputStream inputStream) throws Exception, GlobalException {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        LegalData legalData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++) {
            for (int j = 0; j < 16; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Legal_Name=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            String Two_Initials=sheet.getRow(i).getCell(1).getStringCellValue().trim().toUpperCase();
            if(Two_Initials.length()==0){
                Two_Initials=Two_Initials+"AA";
            }
            else if(Two_Initials.length()==1){
                Two_Initials=Two_Initials+"A";
            }
            else {
                Two_Initials=Two_Initials.substring(0,2);
            }
            String Country=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            String City=sheet.getRow(i).getCell(3).getStringCellValue().trim();
            legalData=new LegalData();
            legalData.setLegal_Name(Legal_Name);
            legalData.setTwo_Initials(Two_Initials);
            legalData.setCountry(Country);
            legalData.setCity(City);
            if(legalDataDao.queryLegalDataForExist(legalData)!=0){
                throw new GlobalException("exist:" + (i + 1));
            }
            String Registered_Time=sheet.getRow(i).getCell(8).getStringCellValue().trim();
            if(!Registered_Time.matches(Regex_Registered_Time)){
                throw new GlobalException("registered_Time:" + (i + 1));
            }
            String Registered_Capital=sheet.getRow(i).getCell(9).getStringCellValue().trim();
            if(!Registered_Capital.matches(Regex_Registered_Capital)){
                throw new GlobalException("registered_Capital:" + (i + 1));
            }
            String Taxpayer_Number=sheet.getRow(i).getCell(6).getStringCellValue().trim();
            if(!Taxpayer_Number.matches(Regex_Taxpayer_Number)){
                throw new GlobalException("taxpayer_Number:" + (i + 1));
            }
            String Registration_Number=sheet.getRow(i).getCell(7).getStringCellValue().trim();
            if(!Registration_Number.matches(Regex_Registration_Number)){
                throw new GlobalException("registration_Number:" + (i + 1));
            }
            String Bank_Account=sheet.getRow(i).getCell(12).getStringCellValue().trim();
            if(!Bank_Account.matches(Regex_Bank_Account)){
                throw new GlobalException("bank_Account:" + (i + 1));
            }
            LegalData legalDataAdd=legalDataDao.queryLegalDataLastData(Two_Initials);
            String Company_Code="";
            if(legalDataAdd==null){
                Company_Code=Two_Initials+"01";
            }
            else {
                int count=Integer.parseInt(legalDataAdd.getCompany_Code().substring(2));
                if(count>0 && count<9){
                    Company_Code=Two_Initials+"0"+(count+1);
                }
                else {
                    Company_Code=Two_Initials+(count+1);
                }
            }
            legalData.setCompany_Code(Company_Code);
            legalData.setRegistered_Time(Registered_Time);
            legalData.setRegistered_Capital(Double.parseDouble(Registered_Capital));
            legalData.setIndustry(sheet.getRow(i).getCell(4).getStringCellValue().trim());
            legalData.setLegal(sheet.getRow(i).getCell(5).getStringCellValue().trim());
            legalData.setTaxpayer_Number(Taxpayer_Number);
            legalData.setRegistration_Number(Registration_Number);
            legalData.setRegistered_Address(sheet.getRow(i).getCell(10).getStringCellValue().trim());
            legalData.setBank(sheet.getRow(i).getCell(11).getStringCellValue().trim());
            legalData.setBank_Account(Bank_Account);
            legalData.setBank_Address(sheet.getRow(i).getCell(13).getStringCellValue().trim());
            legalData.setCurrency(sheet.getRow(i).getCell(14).getStringCellValue().trim());
            legalData.setOffice_Address(sheet.getRow(i).getCell(15).getStringCellValue().trim());
            legalData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            legalData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(legalDataDao.insertLegalData(legalData)<=0){
                throw new GlobalException("error:" + (i + 1));
            }
        }
        return true;
    }

}
