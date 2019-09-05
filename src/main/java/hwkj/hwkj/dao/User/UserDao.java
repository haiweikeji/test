package hwkj.hwkj.dao.User;

import hwkj.hwkj.entity.HR.EmployeePersonalData;
import hwkj.hwkj.entity.HUser.Menu;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 根据Job_Number删除用户
     * @param Job_Number
     * @return
     */
    public int deleteUser(String Job_Number[]);

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int updatePassword(User user);

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
    public User queryUserByJobNumber(@Param("Job_Number") String Job_Number);

    /**
     *User分页查询
     * @param userPageModel
     * @param Job_Number
     * @param Name
     * @return
     */
    public List<User> queryUserList(@Param("userPageModel") PageModel<User> userPageModel,@Param("Job_Number") String Job_Number,@Param("Name") String Name);

    /**
     *User总数Count
     * @param userPageModel
     * @param Job_Number
     * @param Name
     * @return
     */
    public Integer queryUserCount(@Param("userPageModel") PageModel<User> userPageModel,@Param("Job_Number") String Job_Number,@Param("Name") String Name);

    /**
     *获取所有用户工号
     * @param Job_Number
     * @return
     */
    public List<EmployeePersonalData> queryJobNumber(@Param("Job_Number") String Job_Number);

    public List<Menu> userRoleMenu(@Param("Job_Number") String Job_Number);

    /**
     * 查询没有授权的用户工号
     * @param Job_Number
     * @return
     */
    public List<EmployeePersonalData> queryUserNotExistJobNumber(@Param("Job_Number") String Job_Number);

    /**
     * 检查授权用户是否已存在
     * @param Job_Number
     * @return
     */
    public int queryUserForExist(@Param("Job_Number") String Job_Number);

    /**
     * 下拉框查询名字
     * @param Job_Number
     * @return
     */
    public List<EmployeePersonalData> queryUserForOption(@Param("Job_Number") String Job_Number);
}
