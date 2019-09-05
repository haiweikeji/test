package hwkj.hwkj.dao.User;

import hwkj.hwkj.entity.HUser.RoleMenu;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMenuDao {

    /**
     * 获取当前网页功能
     * @param Job_Number
     * @param Url_Page
     * @return
     */
    public RoleMenu queryFunction(@Param("Job_Number") String Job_Number, @Param("Url_Page") String Url_Page);

    /**
     * 根据Role_Menu_Id删除角色菜单
     * @param Role_Menu_Id
     * @return
     */
    public int deleteRoleMenu(Integer Role_Menu_Id[]);

    /**
     * 更新角色菜单
     * @param roleMenu
     * @return
     */
    public int updateRoleMenu(RoleMenu roleMenu);

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
     * @param roleMenu
     * @return
     */
    public List<RoleMenu> queryRoleMenuList(@Param("roleMenuPageModel")PageModel<RoleMenu> roleMenuPageModel,@Param("roleMenu") RoleMenu roleMenu);

    /**
     * RoleMenu总数count
     * @param roleMenuPageModel
     * @param roleMenu
     * @return
     */
    public int queryRoleMenuCount(@Param("roleMenuPageModel")PageModel<RoleMenu> roleMenuPageModel,@Param("roleMenu") RoleMenu roleMenu);

    /**
     * by page and role_name 查询
     * @param roleMenu
     * @return
     */
    public RoleMenu queryRoleMenuByPageAndRoleNameAndMenuName(RoleMenu roleMenu);

    /**
     * 批量插入数据
     * @param roleMenuList
     * @return
     */
    public int importRoleMenu(List<RoleMenu> roleMenuList);

    /**
     * 检查新增的数据是否已存在
     * @param roleMenu
     * @return
     */
    public int queryRoleMenuForExist(RoleMenu roleMenu);

}
