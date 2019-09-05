package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.FileManagementDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileManagementServiceImpl implements FileManagementService {

    @Autowired
    private FileManagementDao fileManagementDao;

    /**
     * 新增
     * @param fileManagement
     * @return
     */
    @Transactional
    @Override
    public boolean insertFileManagement(FileManagement fileManagement) {
        if(fileManagementDao.queryFileManagementForExist(fileManagement)!=0){
            throw new GlobalException("exist");
        }
        if(fileManagementDao.insertFileManagement(fileManagement)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 删除
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteFileManagement(Integer[] Id) {
        if(fileManagementDao.deleteFileManagement(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 分页查询
     * @param fileManagementPageModel
     * @param fileManagement
     * @return
     */
    @Override
    public void queryFileManagementPage(PageModel<FileManagement> fileManagementPageModel, FileManagement fileManagement) {
        fileManagementPageModel.setList(fileManagementDao.queryFileManagementList(fileManagementPageModel, fileManagement));
        fileManagementPageModel.setTotal(fileManagementDao.queryFileManagementCount(fileManagementPageModel, fileManagement));
    }
}
