package hwkj.hwkj.dao.User;

import hwkj.hwkj.entity.HR.EmployeePersonalData;
import hwkj.hwkj.entity.HUser.UserRole;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {

    /**
     * 给用户分配角色
     * @param userRoleList
     * @return
     */
    public int importUserRole(List<UserRole> userRoleList);

    /**
     * 根据User_Role_Id删除用户角色
     * @param User_Role_Id
     * @return
     */
    public Integer deleteUserRole(Integer User_Role_Id[]);

    /**
     * 更新用户角色
     * @param userRole
     * @return
     */
    public Integer updateUserRole(UserRole userRole);

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
    public UserRole queryUserRoleByUserRoleId(@Param("User_Role_Id") Integer User_Role_Id);

    /**
     * UserRole分页查询
     * @param userRolePageModel
     * @param Job_Number
     * @param Role_Name
     * @return
     */
    public List<UserRole> queryUserRoleList(@Param("userRolePageModel")PageModel<UserRole> userRolePageModel,
                                            @Param("Job_Number") String Job_Number,@Param("Role_Name") String Role_Name);

    /**
     * UserRole总数count
     * @param userRolePageModel
     * @param Job_Number
     * @param Role_Name
     * @return
     */
    public Integer queryUserRoleCount(@Param("userRolePageModel")PageModel<UserRole> userRolePageModel,
                                      @Param("Job_Number") String Job_Number,@Param("Role_Name") String Role_Name);

    /**
     * by Job_Number And Role_Name查询UserRole
     * @param userRole
     * @return
     */
    public UserRole queryUserRoleByJobNumberAndRoleName(UserRole userRole);

    /**
     * 获取BPM人员
     * @param Role
     * @return
     */
    public List<EmployeePersonalData> queryUserRoleForRole(@Param("Role") String Role);

    /**
     * 检查上传时用户名字是否是对应的角色名称
     * @param Role
     * @param China_Name
     * @return
     */
    public int queryUserRoleForUploadRole(@Param("Role") String Role,@Param("China_Name") String China_Name);

    /**
     * 检查数据是否已存在
     * @param userRole
     * @return
     */
    public int queryUserRoleForExist(UserRole userRole);

}
