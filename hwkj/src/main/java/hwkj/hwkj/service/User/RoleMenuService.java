package hwkj.hwkj.service.User;

import hwkj.hwkj.entity.HUser.RoleMenu;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuService {

    /**
     * 获取当前网页功能
     * @param Job_Number
     * @param Url_Page
     * @return
     */
    public RoleMenu queryFunction(String Job_Number, String Url_Page);
    

    /**
     * 根据Role_Menu_Id删除角色菜单
     * @param Role_Menu_Id
     * @return
     */
    public boolean deleteRoleMenu(Integer Role_Menu_Id[]);

    /**
     * 更新角色菜单
     * @param roleMenu
     * @return
     */
    public boolean updateRoleMenu(RoleMenu roleMenu);

    /**
     *查询所有角色菜单
     * @return
     */
    public List<RoleMenu> queryAllRoleMenu();

    /**
     * 根据by Role_Menu_Id查询角色菜单
     * @param Role_Menu_Id
     * @return
     */
    public RoleMenu queryRoleMenuByRoleMenuId(@Param("Role_Menu_Id") Integer Role_Menu_Id);

    /**
     * RoleMenu分页查询
     * @param roleMenuPageModel
     * @return
     */
    public void queryRoleMenuPage(PageModel<RoleMenu> roleMenuPageModel,RoleMenu roleMenu);

    /**
     * by page and role_name 查询
     * @param roleMenu
     * @return
     */
    public RoleMenu queryRoleMenuByPageAndRoleNameAndMenuName(RoleMenu roleMenu);

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
    public boolean importRoleMenu(String [] Url_Page,String [] Menu_Name,String Role_Name,
                              String Newly_Added,String Modify,String Remove,
                              String Download,String Model,String Upload,String Move);
}
