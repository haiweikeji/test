package hwkj.hwkj.service.Engineering;

import hwkj.hwkj.entity.Engineering.MachineData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

public interface MachineDataService {

    /**
     * 新增数据
     * @param machineData
     * @return
     */
    public boolean insertMachineData(MachineData machineData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public boolean deleteMachineData(Integer Id);

    /**
     * 更新数据
     * @param machineData
     * @return
     */
    public boolean updateMachineData(MachineData machineData);

    /**
     * 分页查询
     * @param machineDataPageModel
     * @param machineData
     * @return
     */
    public void queryMachineDataPage(PageModel<MachineData> machineDataPageModel,MachineData machineData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public MachineData queryMachineDataById(Integer Id);

    /**
     * for download all
     * @param machineData
     * @return
     */
    public List<MachineData> queryMachineDataForDownLoadAll(MachineData machineData);

    /**
     * for download
     * @param Id
     * @return
     */
    public MachineData queryMachineDataForDownLoad(Integer Id);

    /**
     * check 新增数据是否已存在
     * @param machineData
     * @return
     */
    public int queryMachineDataForExist(MachineData machineData);

    /**
     * 上传
     * @param request
     * @param inputStream
     * @return
     * @throws GlobalException
     * @throws Exception
     */
    public boolean machineDataUpload(HttpServletRequest request, InputStream inputStream) throws GlobalException,Exception;
}
