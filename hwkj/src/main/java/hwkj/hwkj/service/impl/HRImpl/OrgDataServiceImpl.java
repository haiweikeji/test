package hwkj.hwkj.service.impl.HRImpl;

import hwkj.hwkj.dao.HR.OrgDataDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.OrgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrgDataServiceImpl implements OrgDataService {
    @Autowired
    private OrgDataDao orgDataDao;

    /**
     * by id 查询
     * @param id
     * @return
     */
    @Override
    public OrgData queryOrgDataById(Integer id) {
        return orgDataDao.queryOrgDataById(id);
    }

    /**
     * 新增数据
     * @param orgData
     * @return
     */
    @Transactional
    @Override
    public boolean insertOrgData(OrgData orgData){
        if(orgDataDao.insertOrgData(orgData)<=0){
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
    public boolean deleteOrgData(Integer Id[]){
        if(orgDataDao.deleteOrgData(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新数据
     * @param orgData
     * @return
     */
    @Transactional
    @Override
    public boolean updateOrgData(OrgData orgData){
       if(orgDataDao.updateOrgData(orgData)<=0){
           throw new GlobalException("error");
       }
       return true;
    }

    /**
     * 计算组织代码
     * @param orgData
     * @return
     */
    @Override
    public String calculateOrgCode(OrgData orgData) {
        String OrgCode="HW";
        //计算BG
        if(orgDataDao.bgCount(orgData).size()==0){
            OrgCode=OrgCode+"01";
        }
        else if (orgDataDao.bgCount(orgData).size()>0 && orgDataDao.bgCount(orgData).size()<9){
            for(int i=0;i<orgDataDao.bgCount(orgData).size();i++){
                if(orgDataDao.bgCount(orgData).get(i).getBG().contains(orgData.getBG().trim())){
                    OrgCode=OrgCode+orgDataDao.bgCount(orgData).get(i).getOrg_Code().substring(2,4);
                    break;
                }
            }
            if(OrgCode.length()==2){
                OrgCode=OrgCode+"0"+(orgDataDao.bgCount(orgData).size()+1);
            }
        }
        else {
            for(int i=0;i<orgDataDao.bgCount(orgData).size();i++){
                if(orgDataDao.bgCount(orgData).get(i).getBG().contains(orgData.getBG().trim())){
                    OrgCode=OrgCode+orgDataDao.bgCount(orgData).get(i).getOrg_Code().substring(2,4);
                    break;
                }
            }
            if(OrgCode.length()==2){
                OrgCode=OrgCode+(orgDataDao.bgCount(orgData).size()+1);
            }
        }//计算BU
        if(orgData.getBU()!=null && !("".equals(orgData.getBU()))){
            if(orgDataDao.buCount(orgData).size()==0){
                OrgCode=OrgCode+"01";
            }
            else if(orgDataDao.buCount(orgData).size()>0 && orgDataDao.buCount(orgData).size()<9){
                for(int i=0;i<orgDataDao.buCount(orgData).size();i++){
                    if(orgDataDao.buCount(orgData).get(i).getBU().contains(orgData.getBU().trim())){
                        OrgCode=OrgCode+orgDataDao.buCount(orgData).get(i).getOrg_Code().substring(4,6);
                        break;
                    }
                }
                if(OrgCode.length()==4){
                    OrgCode=OrgCode+"0"+(orgDataDao.buCount(orgData).size()+1);
                }
            }
            else {
                for(int i=0;i<orgDataDao.buCount(orgData).size();i++){
                    if(orgDataDao.buCount(orgData).get(i).getBU().contains(orgData.getBU().trim())){
                        OrgCode=OrgCode+orgDataDao.buCount(orgData).get(i).getOrg_Code().substring(4,6);
                        break;
                    }
                }
                if(OrgCode.length()==4){
                    OrgCode=OrgCode+(orgDataDao.buCount(orgData).size()+1);
                }
            }
        }
        else {
            OrgCode=OrgCode+"00";
        }//计算Dept
        if(orgData.getDept()!=null && !("".equals(orgData.getDept()))){
            if(orgDataDao.deptCount(orgData).size()==0){
                OrgCode=OrgCode+"1";
            }
            else {
                for(int i=0;i<orgDataDao.deptCount(orgData).size();i++){
                    if(orgDataDao.deptCount(orgData).get(i).getDept().contains(orgData.getDept().trim())){
                        OrgCode=OrgCode+orgDataDao.deptCount(orgData).get(i).getOrg_Code().substring(6,7);
                        break;
                    }
                }
                if(OrgCode.length()==6){
                    OrgCode=OrgCode+(orgDataDao.deptCount(orgData).size()+1);
                }
            }
        }
        else {
            OrgCode=OrgCode+"0";
        }//计算Ke
        if(orgData.getKe()!=null && !("".equals(orgData.getKe()))){
            if(orgDataDao.keCount(orgData).size()==0){
                OrgCode=OrgCode+"1";
            }
            else {
                for(int i=0;i<orgDataDao.keCount(orgData).size();i++){
                    if(orgDataDao.keCount(orgData).get(i).getKe().contains(orgData.getKe().trim())){
                        OrgCode=OrgCode+orgDataDao.keCount(orgData).get(i).getOrg_Code().substring(7,8);
                        break;
                    }
                }
                if(OrgCode.length()==7){
                    OrgCode=OrgCode+(orgDataDao.keCount(orgData).size()+1);
                }
            }
        }
        else {
            OrgCode=OrgCode+"0";
        }//计算Zu
        if(orgData.getZu()!=null && !("".equals(orgData.getZu()))){
            if(orgDataDao.zuCount(orgData).size()==0){
                OrgCode=OrgCode+"1";
            }
            else {
                for(int i=0;i<orgDataDao.zuCount(orgData).size();i++){
                    if(orgDataDao.zuCount(orgData).get(i).getZu().contains(orgData.getZu().trim())){
                        OrgCode=OrgCode+orgDataDao.zuCount(orgData).get(i).getOrg_Code().substring(8,9);
                        break;
                    }
                }
                if(OrgCode.length()==8){
                    OrgCode=OrgCode+(orgDataDao.zuCount(orgData).size()+1);
                }
            }
        }
        else {
            OrgCode=OrgCode+"0";
        }
        return OrgCode;
    }

    /**
     * 分页查询
     * @param orgDataPageModel
     * @param orgData
     * @return
     */
    @Override
    public void queryOrgDataPage(PageModel<OrgData> orgDataPageModel, OrgData orgData) {
        orgDataPageModel.setList(orgDataDao.queryOrgDataList(orgDataPageModel, orgData));
        orgDataPageModel.setTotal(orgDataDao.queryOrgDataCount(orgDataPageModel, orgData));
    }

    /**
     * for download all
     * @param orgData
     * @return
     */
    @Override
    public List<OrgData> queryOrgDataForDownLoadAll(OrgData orgData) {
        return orgDataDao.queryOrgDataForDownLoadAll(orgData);
    }

    /**
     * for download
     * @param Id
     * @return
     */
    @Override
    public List<OrgData> queryOrgDataForDownLoad(Integer Id[]) {
        return orgDataDao.queryOrgDataForDownLoad(Id);
    }
}
