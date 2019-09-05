package hwkj.hwkj.service.impl.EngineeringImpl;

import hwkj.hwkj.dao.Engineering.MachineDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.Engineering.MachineDataService;
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
public class MachineDataServiceImpl implements MachineDataService {
    @Autowired
    private MachineDataDao machineDataDao;

    /**
     * 新增数据
     * @param machineData
     * @return
     */
    @Transactional
    @Override
    public boolean insertMachineData(MachineData machineData) throws RuntimeException{
        int eff=machineDataDao.insertMachineData(machineData);
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
    public boolean deleteMachineData(Integer Id) throws RuntimeException{
        int eff=machineDataDao.deleteMachineData(Id);
        if(eff>0){
            return true;
        }
        return false;
    }

    /**
     * 更新数据
     * @param machineData
     * @return
     */
    @Transactional
    @Override
    public boolean updateMachineData(MachineData machineData) throws RuntimeException{
        int eff=machineDataDao.updateMachineData(machineData);
        if(eff>0){
            return true;
        }
        return false;
    }

    /**
     * 分页查询
     * @param machineDataPageModel
     * @param machineData
     * @return
     */
    @Override
    public void queryMachineDataPage(PageModel<MachineData> machineDataPageModel, MachineData machineData) {
        machineDataPageModel.setList(machineDataDao.queryMachineDataList(machineDataPageModel, machineData));
        machineDataPageModel.setTotal(machineDataDao.queryMachineDataCount(machineDataPageModel, machineData));
    }

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    @Override
    public MachineData queryMachineDataById(Integer Id) {
        return machineDataDao.queryMachineDataById(Id);
    }

    /**
     * for download all
     * @param machineData
     * @return
     */
    @Override
    public List<MachineData> queryMachineDataForDownLoadAll(MachineData machineData) {
        return machineDataDao.queryMachineDataForDownLoadAll(machineData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public MachineData queryMachineDataForDownLoad(Integer Id) {
        return machineDataDao.queryMachineDataForDownLoad(Id);
    }

    /**
     * check 新增数据是否已存在
     * @param machineData
     * @return
     */
    @Override
    public int queryMachineDataForExist(MachineData machineData) {
        return machineDataDao.queryMachineDataForExist(machineData);
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
    public boolean machineDataUpload(HttpServletRequest request, InputStream inputStream) throws GlobalException, Exception {
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);
        XSSFSheet sheet =xssfWorkbook.getSheetAt(0);
        MachineData machineData=null;
        for(int i=1,length=sheet.getLastRowNum();i<=length;i++){
            if(sheet.getRow(i).getCell(0)==null || sheet.getRow(i).getCell(0).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(2)==null || sheet.getRow(i).getCell(2).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(3)==null || sheet.getRow(i).getCell(3).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(11)==null || sheet.getRow(i).getCell(11).getStringCellValue().trim().length()==0 ||
                    sheet.getRow(i).getCell(12)==null || sheet.getRow(i).getCell(12).getStringCellValue().trim().length()==0){
                throw new GlobalException("blank:"+(i+1));//代表必填项没填
            }
            if(sheet.getRow(i).getCell(29)!=null){
                if(!("研发中".equals(sheet.getRow(i).getCell(29).getStringCellValue().trim())||"导入中".equals(sheet.getRow(i).getCell(29).getStringCellValue().trim())||
                        "试产中".equals(sheet.getRow(i).getCell(29).getStringCellValue().trim())||"量产中".equals(sheet.getRow(i).getCell(29).getStringCellValue().trim())||
                        "EOL".equals(sheet.getRow(i).getCell(29).getStringCellValue().trim()))){
                    throw new GlobalException("life_cycle_state:"+(i+1));//生命周期状态填写错误
                }
            }
            machineData=new MachineData();
            machineData.setMachine_Name(sheet.getRow(i).getCell(0).getStringCellValue());
            machineData.setMachine_Type(sheet.getRow(i).getCell(1).getStringCellValue());
            machineData.setBrand(sheet.getRow(i).getCell(2).getStringCellValue());
            machineData.setModel_Number(sheet.getRow(i).getCell(3).getStringCellValue());
            if(sheet.getRow(i).getCell(4)!=null){
                machineData.setSize(sheet.getRow(i).getCell(4).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(5)!=null){
                machineData.setCountry_Origin(sheet.getRow(i).getCell(5).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(6)!=null){
                machineData.setDescribed(sheet.getRow(i).getCell(6).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(7)!=null){
                machineData.setMachine_Tool_Accuracy_Grade(sheet.getRow(i).getCell(7).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(8)!=null){
                machineData.setMax_Machining_Size(sheet.getRow(i).getCell(8).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(9)!=null){
                machineData.setMin_Machining_Size(sheet.getRow(i).getCell(9).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(10)!=null){
                machineData.setTrack_Travel(sheet.getRow(i).getCell(10).getStringCellValue());
            }
            machineData.setMachining_Product(sheet.getRow(i).getCell(11).getStringCellValue());
            machineData.setEquipment_Advantages(sheet.getRow(i).getCell(12).getStringCellValue());
            if(sheet.getRow(i).getCell(13)!=null){
                machineData.setTool_Magazine_Type(sheet.getRow(i).getCell(13).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(14)!=null){
                machineData.setSpindle_Cone_Hole_Type(sheet.getRow(i).getCell(14).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(15)!=null){
                machineData.setSpeed_Type(sheet.getRow(i).getCell(15).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(16)!=null){
                machineData.setSpeed_Unit(sheet.getRow(i).getCell(16).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(17)!=null){
                machineData.setMax_Speed(sheet.getRow(i).getCell(17).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(18)!=null){
                machineData.setMax_Pressure(sheet.getRow(i).getCell(18).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(19)!=null){
                machineData.setAir_Type(sheet.getRow(i).getCell(19).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(20)!=null){
                machineData.setRated_Pressure(sheet.getRow(i).getCell(20).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(21)!=null){
                machineData.setUnit_Gas_Consumption(sheet.getRow(i).getCell(21).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(22)!=null){
                machineData.setRated_Voltage(sheet.getRow(i).getCell(22).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(23)!=null){
                machineData.setCurrent_Rating(sheet.getRow(i).getCell(23).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(24)!=null){
                machineData.setPower_Rating(sheet.getRow(i).getCell(24).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(25)!=null){
                machineData.setContour_Size(sheet.getRow(i).getCell(25).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(26)!=null){
                machineData.setWeight(sheet.getRow(i).getCell(26).getNumericCellValue());
            }
            if(sheet.getRow(i).getCell(27)!=null){
                machineData.setManufacturer_Abbreviation(sheet.getRow(i).getCell(27).getStringCellValue());
            }
            if(sheet.getRow(i).getCell(28)!=null){
                machineData.setFactory_Time(sheet.getRow(i).getCell(28).getStringCellValue());
            }if(sheet.getRow(i).getCell(29)!=null){
                machineData.setLife_Cycle_State(sheet.getRow(i).getCell(29).getStringCellValue());
            }if(sheet.getRow(i).getCell(30)!=null){
                machineData.setRemark(sheet.getRow(i).getCell(30).getStringCellValue());
            }
            machineData.setCreator(((User)request.getSession().getAttribute("user")).getName());
            machineData.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(machineDataDao.queryMachineDataForExist(machineData)>0){
                throw new GlobalException("exist:"+(i+1));
            }
            if(machineDataDao.insertMachineData(machineData)<=0){
                throw new GlobalException("error:"+(i+1));
            }
        }
        return true;
    }
}
