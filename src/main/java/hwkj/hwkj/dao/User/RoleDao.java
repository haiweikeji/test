package hwkj.hwkj.dao.User;

import hwkj.hwkj.entity.HUser.Role;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {

    /**
     * 新增角色
     * @param role
     * @return
     */
    public int insertRole(Role role);

    /**
     * byID删除角色
     * @param Id
     * @return
     */
    public int deleteRole(Integer Id[]);


    /**
     * 更新角色
     * @param role
     * @return
     */
    public int updateRole(Role role);

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> queryAllRole();

    /**
     * byID查询角色
     * @param Id
     * @return
     */
    public Role queryRoleByRoleId(@Param("Id") Integer Id);

    /**
     * by Role_Name角色名称查询
     * @param Role_Name
     * @return
     */
    public Role queryRoleByRoleName(@Param("Role_Name") String Role_Name);

    /**
     * by Role_Id and Role_Name查询
     * @param Id
     * @param Role_Name
     * @return
     */
    public Role queryRoleByRoleIdAndRoleName(@Param("Id") Integer Id,@Param("Role_Name") String Role_Name);

    /**
     * Role分页查询
     * @param rolePageModel
     * @param role
     * @return
     */
    public List<Role> queryRoleList(@Param("rolePageModel") PageModel<Role> rolePageModel,@Param("role") Role role);

    /**
     * Role总数Count
     * @param rolePageModel
     * @param role
     * @return
     */
    public int queryRoleCount(@Param("rolePageModel") PageModel<Role> rolePageModel,@Param("role") Role role);

    /**
     * 检查新增的角色是否已存在
     * @param Role_Name
     * @return
     */
    public int queryRoleForExist(@Param("Role_Name") String Role_Name);

}
