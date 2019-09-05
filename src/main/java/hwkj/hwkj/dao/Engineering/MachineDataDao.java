package hwkj.hwkj.dao.Engineering;

import hwkj.hwkj.entity.Engineering.MachineData;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MachineDataDao {

    /**
     * 新增数据
     * @param machineData
     * @return
     */
    public int insertMachineData(MachineData machineData);

    /**
     * 删除数据
     * @param Id
     * @return
     */
    public int deleteMachineData(@Param("Id") Integer Id);

    /**
     * 更新数据
     * @param machineData
     * @return
     */
    public int updateMachineData(MachineData machineData);

    /**
     * 分页集合List
     * @param machineDataPageModel
     * @param machineData
     * @return
     */
    public List<MachineData> queryMachineDataList(@Param("machineDataPageModel") PageModel<MachineData> machineDataPageModel, @Param("machineData") MachineData machineData);

    /**
     * 分页总数count
     * @param machineDataPageModel
     * @param machineData
     * @return
     */
    public int queryMachineDataCount(@Param("machineDataPageModel") PageModel<MachineData> machineDataPageModel, @Param("machineData") MachineData machineData);

    /**
     * by Id 查询
     * @param Id
     * @return
     */
    public MachineData queryMachineDataById(@Param("Id") Integer Id);

    /**
     * for download all
     * @param machineData
     * @return
     */
    public List<MachineData> queryMachineDataForDownLoadAll(@Param("machineData") MachineData machineData);

    /**
     * for download
     * @param Id
     * @return
     */
    public MachineData queryMachineDataForDownLoad(@Param("Id") Integer Id);

    /**
     * check 新增数据是否已存在
     * @param machineData
     * @return
     */
    public int queryMachineDataForExist(MachineData machineData);
}
