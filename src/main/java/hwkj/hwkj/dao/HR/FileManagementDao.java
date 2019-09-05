package hwkj.hwkj.dao.HR;

import hwkj.hwkj.entity.HR.FileManagement;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileManagementDao {

    /**
     * 新增
     * @param fileManagement
     * @return
     */
    public int insertFileManagement(FileManagement fileManagement);

    /**
     * 删除
     * @param Id
     * @return
     */
    public int deleteFileManagement(Integer Id[]);

    /**
     * 分页查询List
     * @param fileManagementPageModel
     * @param fileManagement
     * @return
     */
    public List<FileManagement> queryFileManagementList(@Param("fileManagementPageModel") PageModel<FileManagement> fileManagementPageModel, @Param("fileManagement") FileManagement fileManagement);

    /**
     * 分页查询Count
     * @param fileManagementPageModel
     * @param fileManagement
     * @return
     */
    public int queryFileManagementCount(@Param("fileManagementPageModel") PageModel<FileManagement> fileManagementPageModel,@Param("fileManagement") FileManagement fileManagement);

    /**
     * check 数据是否已存在
     * @param fileManagement
     * @return
     */
    public int queryFileManagementForExist(FileManagement fileManagement);
}
