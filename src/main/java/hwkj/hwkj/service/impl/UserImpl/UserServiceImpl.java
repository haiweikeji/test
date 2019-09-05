package hwkj.hwkj.service.impl.UserImpl;

import hwkj.hwkj.dao.User.RoleDao;
import hwkj.hwkj.dao.User.UserDao;
import hwkj.hwkj.dao.User.UserRoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Transactional
    @Override
    public boolean insertUser(User user){
        if(!user.getPassword().trim().equals(user.getRePassword().trim())){
            throw new GlobalException("rePassword_error");
        }
        if(userDao.queryUserForExist(user.getJob_Number().trim())!=0){
            throw new GlobalException("exist");
        }
        String MD5=DigestUtils.md5Hex(user.getPassword().trim());
        user.setPassword(MD5);
        user.setStatus("Y");
        if(userDao.insertUser(user)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 根据Job_Number删除用户
     * @param Job_Number
     * @return
     */
    @Transactional
    @Override
    public boolean deleteUser(String Job_Number[]){
        if(userDao.deleteUser(Job_Number)!=Job_Number.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Transactional
    @Override
    public boolean updateUser(User user){
        if(userDao.updateUser(user)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @Transactional
    @Override
    public boolean updatePassword(User user) {
        if(!DigestUtils.md5Hex(user.getOld_Password().trim()).equals(userDao.queryUserByJobNumber(user.getJob_Number().trim()).getPassword())){
            throw new GlobalException("old_password_error");
        }else {
            if(!user.getPassword().trim().equals(user.getRePassword().trim())){
                throw new GlobalException("rePassword_error");
            }
            user.setPassword(DigestUtils.md5Hex(user.getPassword().trim()));
            if(userDao.updatePassword(user)<=0){
                throw new GlobalException("error");
            }
        }
        return true;
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    /**
     * 根据Job_Number查询用户信息
     * @param Job_Number
     * @return
     */
    @Override
    public User queryUserByJobNumber(String Job_Number){
        return userDao.queryUserByJobNumber(Job_Number);
    }

    /**
     *User分页查询
     * @param userPageModel
     * @param Job_Number
     * @param Name
     */
    @Override
    public void queryUserPage(PageModel<User> userPageModel, String Job_Number, String Name) {
        userPageModel.setList(userDao.queryUserList(userPageModel,Job_Number,Name));
        userPageModel.setTotal(userDao.queryUserCount(userPageModel,Job_Number,Name));
    }

    /**
     * 给用户分配角色
     * @param Job_Number
     * @param Role_Name
     * @return
     */
    @Transactional
    @Override
    public boolean importUserRole(String[] Job_Number, String Role_Name) {
        List<UserRole> list=new ArrayList<>();
        UserRole userRole=null;
        for(int i=0,length=Job_Number.length;i<length;i++){
            userRole=new UserRole();
            userRole.setJob_Number(Job_Number[i]);
            userRole.setRole_Name(Role_Name);
            if(userRoleDao.queryUserRoleForExist(userRole)!=0){
                continue;
            }
            list.add(userRole);
        }
        if(list.size()==0){
            return true;
        }
        if(userRoleDao.importUserRole(list)!=list.size()){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 获取所有用户工号
     * @param Job_Number
     * @return
     */
    @Override
    public List<EmployeePersonalData> queryJobNumber(String Job_Number) {
        return userDao.queryJobNumber(Job_Number);
    }

    @Override
    public List<Menu> userRoleMenu(String Job_Number) {
        return userDao.userRoleMenu(Job_Number);
    }

    /**
     * 查询没有授权的用户工号
     * @param Job_Number
     * @return
     */
    @Override
    public List<EmployeePersonalData> queryUserNotExistJobNumber(String Job_Number) {
        return userDao.queryUserNotExistJobNumber(Job_Number);
    }

    /**
     * 下拉框查询名字
     * @param Job_Number
     * @return
     */
    @Override
    public List<EmployeePersonalData> queryUserForOption(String Job_Number) {
        return userDao.queryUserForOption(Job_Number);
    }

}
