package hwkj.hwkj.service.User;

import hwkj.hwkj.entity.HR.EmployeePersonalData;
import hwkj.hwkj.entity.HUser.UserRole;
import hwkj.hwkj.entity.pagingquery.PageModel;

import java.util.List;

public interface UserRoleService {

    /**
     * 根据User_Role_Id删除用户角色
     * @param User_Role_Id
     * @return
     */
    public boolean deleteUserRole(Integer User_Role_Id[]);

    /**
     * 更新用户角色
     * @param userRole
     * @return
     */
    public boolean updateUserRole(UserRole userRole);

    /**
     * 查询所有用户角色
     * @return
     */
    public List<UserRole> queryAllUserRole();

    /**
     * 根据User_Role_Id查询用户角色
     * @param User_Role_Id
     * @return
     */
    public UserRole queryUserRoleByUserRoleId(Integer User_Role_Id);

    /**
     * UserRole分页查询
     * @param userRolePageModel
     * @param Job_Number
     * @param Role_Name
     */
    public void queryUserRolePage(PageModel<UserRole> userRolePageModel, String Job_Number, String Role_Name);

    /**
     * by Job_Number And Role_Name查询UserRole
     * @param userRole
     * @return
     */
    public UserRole queryUserRoleByJobNumberAndRoleName(UserRole userRole);

    /**
     * 获取Role人员
     * @param Role
     * @return
     */
    public List<EmployeePersonalData> queryUserRoleForRole(String Role);

}
