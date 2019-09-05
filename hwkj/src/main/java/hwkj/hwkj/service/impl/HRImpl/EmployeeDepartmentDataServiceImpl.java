package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.EmployeeDepartmentDataDao;
import hwkj.hwkj.dao.HR.EmployeePersonalDataDao;
import hwkj.hwkj.dao.HR.OrgDataDao;
import hwkj.hwkj.dao.HR.TitleDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.EmployeeDepartmentDataService;
import hwkj.hwkj.utils.UploadEmployeePunchData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeDepartmentDataServiceImpl implements EmployeeDepartmentDataService {

    private static final String Service_Date="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";

    @Autowired
    private EmployeeDepartmentDataDao employeeDepartmentDataDao;
    @Autowired
    private EmployeePersonalDataDao employeePersonalDataDao;
    @Autowired
    private OrgDataDao orgDataDao;
    @Autowired
    private TitleDataDao titleDataDao;

    /**
     * 新增数据
     * @param employeeDepartmentData
     * @return
     */
    @Transactional
    @Override
    public boolean insertEmployeeDepartmentData(EmployeeDepartmentData employeeDepartmentData){
        if(employeeDepartmentDataDao.queryEmployeeDepartmentDataForExist(employeeDepartmentData.getJob_Number().trim())!=0){
            throw new GlobalException("exist");
        }
        if(employeeDepartmentDataDao.insertEmployeeDepartmentData(employeeDepartmentData)<=0){
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
    public boolean deleteEmployeeDepartmentData(Integer Id[]){
        if(employeeDepartmentDataDao.deleteEmployeeDepartmentData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param employeeDepartmentData
     * @return
     */
    @Transactional
    @Override
    public boolean updateEmployeeDepartmentData(EmployeeDepartmentData employeeDepartmentData){
        if(employeeDepartmentDataDao.queryEmployeeDepartmentDataForAllExist(employeeDepartmentData)!=0){
            throw new GlobalException("NoUpdate");
        }else {
            if(employeeDepartmentData.getJob_Number().trim().equals(employeeDepartmentData.getOld_Job_Number().trim())){
                if(employeeDepartmentDataDao.updateEmployeeDepartmentData(employeeDepartmentData)<=0){
                    throw new GlobalException("error");
                }
            }else {
                if(employeeDepartmentDataDao.queryEmployeeDepartmentDataForExist(employeeDepartmentData.getJob_Number().trim())!=0){
                    throw new GlobalException("exist");
                }
                if(employeeDepartmentDataDao.updateEmployeeDepartmentData(employeeDepartmentData)<=0){
                    throw new GlobalException("error");
                }
            }
        }
        return true;
    }

    /**
     * 分页查询
     * @param employeeDepartmentDataPageModel
     * @param employeeDepartmentData
     * @return
     */
    @Override
    public void queryEmployeeDepartmentDataPage(PageModel<EmployeeDepartmentData> employeeDepartmentDataPageModel, EmployeeDepartmentData employeeDepartmentData) throws Exception{
        List<EmployeeDepartmentData> list=employeeDepartmentDataDao.queryEmployeeDepartmentDataList(employeeDepartmentDataPageModel, employeeDepartmentData);
        for(int i=0,length=list.size();i<length;i++){
            if(UploadEmployeePunchData.materialEngineeringDataCompareTime(list.get(i).getEnd_Service(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
                list.get(i).setEffective_Position("Y");
            }
            else{
                list.get(i).setEffective_Position("N");
            }
        }
        employeeDepartmentDataPageModel.setList(list);
        employeeDepartmentDataPageModel.setTotal(employeeDepartmentDataDao.queryEmployeeDepartmentDataCount(employeeDepartmentDataPageModel, employeeDepartmentData));
    }

    /**
     * for download all
     * @param employeeDepartmentData
     * @return
     */
    @Override
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataForDownLoadAll(EmployeeDepartmentData employeeDepartmentData) {
        return employeeDepartmentDataDao.queryEmployeeDepartmentDataForDownLoadAll(employeeDepartmentData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<EmployeeDepartmentData> queryEmployeeDepartmentDataForDownLoad(Integer Id[]) {
        return employeeDepartmentDataDao.queryEmployeeDepartmentDataForDownLoad(Id);
    }

    /**
     * 根据组织代码查询org_data table 根据管理职查询title_data table
     * @param Org_Code
     * @return
     */
    @Override
    public Map<String, Object> queryEmployeeDepartmentDataForOption(String Org_Code) {
        Map<String,Object> map =new HashMap<>();
        map.put("listPersonalData",employeePersonalDataDao.queryEmployeePersonalDataNotExistJobNumberForEmployeeDepartmentData("Y"));
        map.put("listOrgData",orgDataDao.queryOrgDataForOption("Y",Org_Code));
        map.put("listTitleData",titleDataDao.queryTitleDataForOption("Y"));
        return map;
    }

    /**
     * 下拉框选择组织代码
     * @param Org_Code
     * @return
     */
    @Override
    public Map<String, Object> queryEmployeeDepartmentDataOptionOrgCode(String Org_Code) {
        Map<String,Object> map =new HashMap<>();
        map.put("listOrgData",orgDataDao.queryOrgDataForOption("Y",Org_Code));
        return map;
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
    public boolean uploadEmployeeDepartmentData(HttpServletRequest request, InputStream inputStream) throws Exception, GlobalException {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        EmployeeDepartmentData employeeDepartmentData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++) {
            for (int j = 0;j<9; j++) {
                if (sheet.getRow(i).getCell(j) == null) {
                    throw new GlobalException("blank:" + (i + 1));
                }
            }
            String Job_Number=sheet.getRow(i).getCell(0).getStringCellValue().trim();
            if(employeePersonalDataDao.queryEmployeePersonalDataForUploadJobNumber(Job_Number,"Y")==0){
                throw new GlobalException("job_Number:" + (i + 1));
            }
            if(employeeDepartmentDataDao.queryEmployeeDepartmentDataForExist(Job_Number)!=0){
                throw new GlobalException("exist:" + (i + 1));
            }
            String Org_Code=sheet.getRow(i).getCell(1).getStringCellValue().trim();
            if(orgDataDao.queryOrgDataForUploadOrgCode(Org_Code,"Y")==0){
                throw new GlobalException("org_Code:" + (i + 1));
            }
            String Management=sheet.getRow(i).getCell(2).getStringCellValue().trim();
            if(titleDataDao.queryTitleDataForUploadManagement(Management,"Y")==0){
                throw new GlobalException("management:" + (i + 1));
            }
            String Start_Service=sheet.getRow(i).getCell(4).getStringCellValue().trim();
            if(!Start_Service.matches(Service_Date)){
                throw new GlobalException("start_Service:" + (i + 1));
            }
            String End_Service=sheet.getRow(i).getCell(5).getStringCellValue().trim();
            if(!End_Service.matches(Service_Date)){
                throw new GlobalException("end_Service:" + (i + 1));
            }
            employeeDepartmentData=new EmployeeDepartmentData();
            employeeDepartmentData.setJob_Number(Job_Number);
            employeeDepartmentData.setOrg_Code(Org_Code);
            employeeDepartmentData.setManagement(Management);
            employeeDepartmentData.setJob_Type(sheet.getRow(i).getCell(3).getStringCellValue().trim());
            employeeDepartmentData.setStart_Service(Start_Service);
            employeeDepartmentData.setEnd_Service(End_Service);
            employeeDepartmentData.setEntry_Position(sheet.getRow(i).getCell(6).getStringCellValue().trim());
            employeeDepartmentData.setJob_Family(sheet.getRow(i).getCell(7).getStringCellValue().trim());
            employeeDepartmentData.setStation(sheet.getRow(i).getCell(8).getStringCellValue().trim());
            employeeDepartmentData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            employeeDepartmentData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(employeeDepartmentDataDao.insertEmployeeDepartmentData(employeeDepartmentData)<=0){
                throw new GlobalException("error:" + (i + 1));
            }
        }
        return true;
    }
}
