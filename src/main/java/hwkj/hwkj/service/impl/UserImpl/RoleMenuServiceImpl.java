package hwkj.hwkj.service.impl.UserImpl;

import hwkj.hwkj.dao.User.RoleMenuDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 获取当前网页功能
     * @param Job_Number
     * @param Url_Page
     * @return
     */
    @Override
    public RoleMenu queryFunction(String Job_Number, String Url_Page) {
        return roleMenuDao.queryFunction(Job_Number, Url_Page);
    }

    /**
     * 根据Role_Menu_Id删除角色菜单
     * @param Role_Menu_Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteRoleMenu(Integer Role_Menu_Id[]) {
        if(roleMenuDao.deleteRoleMenu(Role_Menu_Id)!=Role_Menu_Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新角色菜单
     * @param roleMenu
     * @return
     */
    @Transactional
    @Override
    public boolean updateRoleMenu(RoleMenu roleMenu){
        if(roleMenuDao.updateRoleMenu(roleMenu)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 查询所有角色菜单
     * @return
     */
    @Override
    public List<RoleMenu> queryAllRoleMenu() {
        return roleMenuDao.queryAllRoleMenu();
    }

    /**
     * 根据by Role_Menu_Id查询角色菜单
     * @param Role_Menu_Id
     * @return
     */
    @Override
    public RoleMenu queryRoleMenuByRoleMenuId(Integer Role_Menu_Id) {
        return roleMenuDao.queryRoleMenuByRoleMenuId(Role_Menu_Id);
    }

    /**
     * RoleMenu分页查询
     * @param roleMenuPageModel
     * @param roleMenu
     */
    @Override
    public void queryRoleMenuPage(PageModel<RoleMenu> roleMenuPageModel,RoleMenu roleMenu) {
        roleMenuPageModel.setList(roleMenuDao.queryRoleMenuList(roleMenuPageModel,roleMenu));
        roleMenuPageModel.setTotal(roleMenuDao.queryRoleMenuCount(roleMenuPageModel,roleMenu));
    }

    /**
     * by page and role_name 查询
     * @param roleMenu
     * @return
     */
    @Override
    public RoleMenu queryRoleMenuByPageAndRoleNameAndMenuName(RoleMenu roleMenu) {
        return roleMenuDao.queryRoleMenuByPageAndRoleNameAndMenuName(roleMenu);
    }

    /**
     * 批量插入数据
     * @param Url_Page
     * @param Menu_Name
     * @param Role_Name
     * @param Newly_Added
     * @param Modify
     * @param Remove
     * @param Download
     * @param Model
     * @param Upload
     * @param Move
     * @return
     */
    @Transactional
    @Override
    public boolean importRoleMenu(String[] Url_Page, String[] Menu_Name, String Role_Name, String Newly_Added, String Modify, String Remove, String Download, String Model, String Upload,String Move) {
        List<RoleMenu> list=new ArrayList<>();
        RoleMenu roleMenu=null;
        for(int i=0,length=Menu_Name.length;i<length;i++){
            roleMenu=new RoleMenu();
            roleMenu.setUrl_Page(Url_Page[i]);
            roleMenu.setMenu_Name(Menu_Name[i]);
            roleMenu.setRole_Name(Role_Name);
            roleMenu.setNewly_Added(Newly_Added);
            roleMenu.setModify(Modify);
            roleMenu.setRemove(Remove);
            roleMenu.setDownload(Download);
            roleMenu.setModel(Model);
            roleMenu.setUpload(Upload);
            roleMenu.setMove(Move);
            if(roleMenuDao.queryRoleMenuForExist(roleMenu)!=0){
                continue;
            }
            list.add(roleMenu);
        }
        if(list.size()==0){
            return true;
        }
        if(roleMenuDao.importRoleMenu(list)!=list.size()){
            throw new GlobalException("error");
        }
        return true;
    }

}
