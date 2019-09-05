package hwkj.hwkj.service.User;

import hwkj.hwkj.entity.pagingquery.PageModel;
import java.util.List;

public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    public boolean insertUser(User user);

    /**
     * 根据Job_Number删除用户
     * @param Job_Number
     * @return
     */
    public boolean deleteUser(String Job_Number[]);

    /**
     * 更新用户
     * @param user
     * @return
     */
    public boolean updateUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    public boolean updatePassword(User user);

    /**
     * 查询所有
     * @return
     */
    public List<User> queryAllUser();

    /**
     * by Job_Number查询
     * @param Job_Number
     * @return
     */
    public User queryUserByJobNumber(String Job_Number);

    /**
     *User分页查询
     * @param userPageModel
     * @param Job_Number
     * @param Name
     */
    public void queryUserPage(PageModel<User> userPageModel,String Job_Number,String Name);

    /**
     * 给用户分配角色
     * @param Job_Number
     * @param Role_Name
     * @return
     */
    public boolean importUserRole(String Job_Number[],String Role_Name);

    /**
     *获取所有用户工号
     * @return
     */
    public List<EmployeePersonalData> queryJobNumber(String Job_Number);

    public List<Menu> userRoleMenu(String Job_Number);

    /**
     * 查询没有授权的用户工号
     * @param Job_Number
     * @return
     */
    public List<EmployeePersonalData> queryUserNotExistJobNumber(String Job_Number);

    /**
     * 下拉框查询名字
     * @param Job_Number
     * @return
     */
    public List<EmployeePersonalData> queryUserForOption(String Job_Number);
}
