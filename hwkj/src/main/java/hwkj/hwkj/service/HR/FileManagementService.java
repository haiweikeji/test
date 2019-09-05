package hwkj.hwkj.service.HR;

import hwkj.hwkj.entity.HR.FileManagement;
import hwkj.hwkj.entity.pagingquery.PageModel;

public interface FileManagementService {

    /**
     * 新增
     * @param fileManagement
     * @return
     */
    public boolean insertFileManagement(FileManagement fileManagement);

    /**
     * 删除
     * @param Id
     * @return
     */
    public boolean deleteFileManagement(Integer Id[]);

    /**
     * 分页查询
     * @param fileManagementPageModel
     * @param fileManagement
     * @return
     */
    public void queryFileManagementPage(PageModel<FileManagement> fileManagementPageModel, FileManagement fileManagement);

}
