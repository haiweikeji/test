package hwkj.hwkj.service.impl.UserImpl;

import hwkj.hwkj.dao.User.UserRoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 根据User_Role_Id删除用户角色
     * @param User_Role_Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteUserRole(Integer User_Role_Id[]){
        if(userRoleDao.deleteUserRole(User_Role_Id)!=User_Role_Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新用户角色
     * @param userRole
     * @return
     */
    @Transactional
    @Override
    public boolean updateUserRole(UserRole userRole){
        if(userRoleDao.queryUserRoleForExist(userRole)!=0){
            throw new GlobalException("NoUpdateOrExist");
        }
        if(userRoleDao.updateUserRole(userRole)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 查询所有用户角色
     * @return
     */
    @Override
    public List<UserRole> queryAllUserRole() {
        return userRoleDao.queryAllUserRole();
    }

    /**
     * 根据User_Role_Id查询用户角色
     * @param User_Role_Id
     * @return
     */
    @Override
    public UserRole queryUserRoleByUserRoleId(Integer User_Role_Id) {
        return userRoleDao.queryUserRoleByUserRoleId(User_Role_Id);
    }

    /**
     * UserRole分页查询
     * @param userRolePageModel
     * @param Job_Number
     * @param Role_Name
     */
    @Override
    public void queryUserRolePage(PageModel<UserRole> userRolePageModel,String Job_Number, String Role_Name) {
        userRolePageModel.setList(userRoleDao.queryUserRoleList(userRolePageModel,Job_Number, Role_Name));
        userRolePageModel.setTotal(userRoleDao.queryUserRoleCount(userRolePageModel,Job_Number, Role_Name));
    }

    /**
     * by Job_Number and Role_Name 查询
     * @param userRole
     * @return
     */
    @Override
    public UserRole queryUserRoleByJobNumberAndRoleName(UserRole userRole) {
        return userRoleDao.queryUserRoleByJobNumberAndRoleName(userRole);
    }

    /**
     * 获取Role人员
     * @param Role
     * @return
     */
    @Override
    public List<EmployeePersonalData> queryUserRoleForRole(String Role) {
        return userRoleDao.queryUserRoleForRole(Role);
    }
}
