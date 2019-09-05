package hwkj.hwkj.service.User;

import hwkj.hwkj.entity.pagingquery.PageModel;

import java.util.List;

public interface RoleService {

    /**
     * 新增角色
     * @param role
     * @return
     */
    public boolean insertRole(Role role);

    /**
     * byID删除角色
     * @param Id
     * @return
     */
    public boolean deleteRole(Integer Id[]);


    /**
     * 更新角色
     * @param role
     * @return
     */
    public boolean updateRole(Role role);

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> queryAllRole();

    /**
     * byID查询角色
     * @param Role_Id
     * @return
     */
    public Role queryRoleByRoleId(Integer Role_Id);

    /**
     * by Role_Name角色名称查询
     * @param Role_Name
     * @return
     */
    public Role queryRoleByRoleName(String Role_Name);

    /**
     * by Role_Id and Role_Name查询
     * @param Role_Id
     * @param Role_Name
     * @return
     */
    public Role queryRoleByRoleIdAndRoleName(Integer Role_Id,String Role_Name);

    /**
     * Role分页查询
     * @param rolePageModel
     * @param role
     */
    public void queryRolePage(PageModel<Role> rolePageModel,Role role);

}
