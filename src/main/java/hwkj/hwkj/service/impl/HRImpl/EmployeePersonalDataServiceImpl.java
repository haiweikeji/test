package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.EmployeePersonalDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.EmployeePersonalDataService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmployeePersonalDataServiceImpl implements EmployeePersonalDataService {
    @Autowired
    private EmployeePersonalDataDao employeePersonalDataDao;

    /**
     * 新增数据
     * @param request
     * @param employeePersonalData
     * @param multipartFile
     * @return
     */
    @Transactional
    @Override
    public boolean insertEmployeePersonalData(HttpServletRequest request, EmployeePersonalData employeePersonalData, MultipartFile multipartFile){
        if(employeePersonalDataDao.queryEmployeePersonalDataForExist(employeePersonalData.getId_Card())!=0){
            throw new GlobalException("exist");
        }
        employeePersonalData.setBirth_Date(employeePersonalData.getId_Card().substring(6,14));
        employeePersonalData.setAge(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(employeePersonalData.getId_Card().substring(6,10)));
        employeePersonalData.setStatus("Y");
        employeePersonalData.setCreator(((User)request.getSession().getAttribute("user")).getName());
        employeePersonalData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        EmployeePersonalData employeePersonalDataAdd=employeePersonalDataDao.queryEmployeePersonalDataLastData();
        if(employeePersonalDataAdd!=null){
            int count=Integer.parseInt(employeePersonalDataAdd.getJob_Number().substring(2));//去除HW
            if(count<9){
                employeePersonalData.setJob_Number("HW00000"+(count+1));
            }
            else if(count>=9 && count<99){
                employeePersonalData.setJob_Number("HW0000"+(count+1));
            }
            else if(count>=99 && count<999){
                employeePersonalData.setJob_Number("HW000"+(count+1));
            }
            else if(count>=999 && count<9999){
                employeePersonalData.setJob_Number("HW00"+(count+1));
            }
            else if(count>=9999 && count<99999){
                employeePersonalData.setJob_Number("HW0"+(count+1));
            }
            else{
                employeePersonalData.setJob_Number("HW"+(count+1));
            }
        }
        if(multipartFile!=null && !"".equals(multipartFile.getOriginalFilename())){
            FileOutputStream fileOutputStream=null;
            long Photo_Time=new Date().getTime();
            String Photo_Name=multipartFile.getOriginalFilename();
            String Photo_Suffix_Name=Photo_Name.substring(Photo_Name.lastIndexOf("."));//后缀名
            String New_Photo_Name=employeePersonalData.getChina_Name()+Photo_Time+Photo_Suffix_Name;
            try {
                fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/employee_photo/"+New_Photo_Name);
                fileOutputStream.write(multipartFile.getBytes());
                fileOutputStream.flush();
                String Photo_Url="/hwkj/upload/employee_photo/"+New_Photo_Name;
                employeePersonalData.setPhoto(Photo_Url);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        int eff= employeePersonalDataDao.insertEmployeePersonalData(employeePersonalData);
        if(eff>0){
            return true;
        }
        return false;
    }

    /**
     * 删除数据
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteEmployeePersonalData(Integer Id[]){
        if(employeePersonalDataDao.deleteEmployeePersonalData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param employeePersonalData
     * @return
     */
    @Transactional
    @Override
    public boolean updateEmployeePersonalData(EmployeePersonalData employeePersonalData) {
        int eff= employeePersonalDataDao.updateEmployeePersonalData(employeePersonalData);
        if(eff>0){
            return true;
        }else {
            throw new RuntimeException("更新数据失败!");
        }
    }

    /**
     * 分页查询List
     *
     * @param employeePersonalDataPageModel
     * @param employeePersonalData
     * @return
     */
    @Override
    public void queryEmployeePersonalDataPage(PageModel<EmployeePersonalData> employeePersonalDataPageModel, EmployeePersonalData employeePersonalData) {
        employeePersonalDataPageModel.setList(employeePersonalDataDao.queryEmployeePersonalDataList(employeePersonalDataPageModel, employeePersonalData));
        employeePersonalDataPageModel.setTotal(employeePersonalDataDao.queryEmployeePersonalDataCount(employeePersonalDataPageModel, employeePersonalData));
    }

    /**
     * for download all
     *
     * @param employeePersonalData
     * @return
     */
    @Override
    public List<EmployeePersonalData> queryEmployeePersonalDataForDownLoadAll(EmployeePersonalData employeePersonalData) {
        return employeePersonalDataDao.queryEmployeePersonalDataForDownLoadAll(employeePersonalData);
    }

    /**
     * for download
     *
     * @param Id
     * @return
     */
    @Override
    public List<EmployeePersonalData> queryEmployeePersonalDataForDownLoad(Integer Id[]) {
        return employeePersonalDataDao.queryEmployeePersonalDataForDownLoad(Id);
    }

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean employeePersonalDataUpload(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        EmployeePersonalData employeePersonalData=null;
        try{
            for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
                if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(2)==null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(7)==null || sheet.getRow(i).getCell(7).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(8)==null || sheet.getRow(i).getCell(8).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(9)==null || sheet.getRow(i).getCell(9).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(10)==null || sheet.getRow(i).getCell(10).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(11)==null || sheet.getRow(i).getCell(11).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(12)==null || sheet.getRow(i).getCell(12).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(13)==null || sheet.getRow(i).getCell(13).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(14)==null || sheet.getRow(i).getCell(14).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(15)==null || sheet.getRow(i).getCell(15).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(17)==null || sheet.getRow(i).getCell(17).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(18)==null || sheet.getRow(i).getCell(18).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(21)==null || sheet.getRow(i).getCell(21).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(24)==null || sheet.getRow(i).getCell(24).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(26)==null || sheet.getRow(i).getCell(26).getStringCellValue().trim().length()==0 ||
                        sheet.getRow(i).getCell(27)==null || sheet.getRow(i).getCell(27).getStringCellValue().trim().length()==0){
                    throw new GlobalException("blank:"+(i+1));//代表必填项没填
                }
                if(employeePersonalDataDao.queryEmployeePersonalDataForExist(sheet.getRow(i).getCell(9).getStringCellValue())>0){
                    throw new GlobalException("exist:"+(i+1));//代表身份证已存在
                }
                employeePersonalData=new EmployeePersonalData();
                employeePersonalData.setChina_Name(sheet.getRow(i).getCell(0).getStringCellValue());
                if(sheet.getRow(i).getCell(1)!=null){
                    employeePersonalData.setEnglish_Name(sheet.getRow(i).getCell(1).getStringCellValue());
                }
                employeePersonalData.setSex(sheet.getRow(i).getCell(2).getStringCellValue());
                employeePersonalData.setPhone_Number(sheet.getRow(i).getCell(3).getStringCellValue());
                if(sheet.getRow(i).getCell(4)!=null){
                    employeePersonalData.setWeChat_Number(sheet.getRow(i).getCell(4).getStringCellValue());
                }
                if(sheet.getRow(i).getCell(5)!=null){
                    employeePersonalData.setCompany_Mail(sheet.getRow(i).getCell(5).getStringCellValue());
                }
                if(sheet.getRow(i).getCell(6)!=null){
                    employeePersonalData.setPrivate_Mail(sheet.getRow(i).getCell(6).getStringCellValue());
                }
                employeePersonalData.setEntry_Date(sheet.getRow(i).getCell(7).getStringCellValue());
                employeePersonalData.setEntry_Position(sheet.getRow(i).getCell(8).getStringCellValue());
                employeePersonalData.setId_Card(sheet.getRow(i).getCell(9).getStringCellValue());
                employeePersonalData.setId_Card_Address(sheet.getRow(i).getCell(10).getStringCellValue());
                employeePersonalData.setPresent_Address(sheet.getRow(i).getCell(11).getStringCellValue());
                employeePersonalData.setEducation(sheet.getRow(i).getCell(12).getStringCellValue());
                employeePersonalData.setMajor(sheet.getRow(i).getCell(13).getStringCellValue());
                employeePersonalData.setGraduated_From(sheet.getRow(i).getCell(14).getStringCellValue());
                employeePersonalData.setGraduated_Time(sheet.getRow(i).getCell(15).getStringCellValue());
                if(sheet.getRow(i).getCell(16)!=null){
                    employeePersonalData.setLanguage(sheet.getRow(i).getCell(16).getStringCellValue());
                }
                employeePersonalData.setNative_Place(sheet.getRow(i).getCell(17).getStringCellValue());
                employeePersonalData.setNation(sheet.getRow(i).getCell(18).getStringCellValue());
                if(sheet.getRow(i).getCell(19)!=null){
                    employeePersonalData.setReligious_Belief(sheet.getRow(i).getCell(19).getStringCellValue());
                }
                if(sheet.getRow(i).getCell(20)!=null){
                    employeePersonalData.setMarital_Status(sheet.getRow(i).getCell(20).getStringCellValue());
                }
                employeePersonalData.setHistory_Disease(sheet.getRow(i).getCell(21).getStringCellValue());
                if(sheet.getRow(i).getCell(22)!=null){
                    employeePersonalData.setMember_Family(sheet.getRow(i).getCell(22).getStringCellValue());
                }
                if(sheet.getRow(i).getCell(23)!=null){
                    employeePersonalData.setRelationship(sheet.getRow(i).getCell(23).getStringCellValue());
                }
                employeePersonalData.setEmergency_Contact_Person(sheet.getRow(i).getCell(24).getStringCellValue());
                if(sheet.getRow(i).getCell(25)!=null){
                    employeePersonalData.setEmergency_Contact_Person_Relationship(sheet.getRow(i).getCell(25).getStringCellValue());
                }
                employeePersonalData.setRelationship_Phone(sheet.getRow(i).getCell(26).getStringCellValue());
                employeePersonalData.setRecruitment_Sources(sheet.getRow(i).getCell(27).getStringCellValue());
                if(sheet.getRow(i).getCell(28)!=null){
                    employeePersonalData.setPeriods_Number(sheet.getRow(i).getCell(28).getStringCellValue());
                }
                if(sheet.getRow(i).getCell(29)!=null){
                    employeePersonalData.setReferee(sheet.getRow(i).getCell(29).getStringCellValue());
                }
                if(sheet.getRow(i).getCell(30)!=null){
                    employeePersonalData.setRemarks(sheet.getRow(i).getCell(30).getStringCellValue());
                }
                employeePersonalData.setBirth_Date(employeePersonalData.getId_Card().substring(6,14));
                employeePersonalData.setAge(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(employeePersonalData.getId_Card().substring(6,10)));
                employeePersonalData.setStatus("Y");
                employeePersonalData.setCreator(((User)request.getSession().getAttribute("user")).getName());
                employeePersonalData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                EmployeePersonalData employeePersonalDataAdd=employeePersonalDataDao.queryEmployeePersonalDataLastData();
                if(employeePersonalDataAdd!=null){
                    int count=Integer.parseInt(employeePersonalDataAdd.getJob_Number().substring(2));//去除HW
                    if(count<9){
                        employeePersonalData.setJob_Number("HW00000"+(count+1));
                    }
                    else if(count>=9 && count<99){
                        employeePersonalData.setJob_Number("HW0000"+(count+1));
                    }
                    else if(count>=99 && count<999){
                        employeePersonalData.setJob_Number("HW000"+(count+1));
                    }
                    else if(count>=999 && count<9999){
                        employeePersonalData.setJob_Number("HW00"+(count+1));
                    }
                    else if(count>=9999 && count<99999){
                        employeePersonalData.setJob_Number("HW0"+(count+1));
                    }
                    else{
                        employeePersonalData.setJob_Number("HW"+(count+1));
                    }
                }
                int eff= employeePersonalDataDao.insertEmployeePersonalData(employeePersonalData);
                if(eff<=0){
                    throw new GlobalException("error");
                }
            }
            xssfWorkbook.close();
            inputStream.close();
        }catch (RuntimeException r){
            r.printStackTrace();
        }finally {
            if(xssfWorkbook!=null){
                xssfWorkbook.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return true;
    }
}
