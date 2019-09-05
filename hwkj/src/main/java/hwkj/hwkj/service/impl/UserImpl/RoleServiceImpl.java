package hwkj.hwkj.service.impl.UserImpl;

import hwkj.hwkj.dao.User.RoleDao;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Transactional
    @Override
    public boolean insertRole(Role role){
        if(roleDao.queryRoleForExist(role.getRole_Name().trim())!=0){
            throw new GlobalException("exist");
        }
        if(roleDao.insertRole(role)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 根据Role_Id删除角色
     * @param Id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteRole(Integer Id[]){
        if(roleDao.deleteRole(Id)!=Id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @Transactional
    @Override
    public boolean updateRole(Role role){
        if(role.getOld_Role_Name().trim().equals(role.getRole_Name().trim())){
            if(role.getOld_Role_Described().equals(role.getRole_Described())){
                throw new GlobalException("NoUpdate");
            }else {
                if(roleDao.updateRole(role)<=0){
                    throw new GlobalException("error");
                }
            }
        }else {
            if(roleDao.queryRoleForExist(role.getRole_Name().trim())!=0){
                throw new GlobalException("exist");
            }
            if(roleDao.updateRole(role)<=0){
                throw new GlobalException("error");
            }
        }
        return true;
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> queryAllRole() {
        return roleDao.queryAllRole();
    }

    /**
     * 根据Role_Id查询角色
     * @param Role_Id
     * @return
     */
    @Override
    public Role queryRoleByRoleId(Integer Role_Id) {
        return roleDao.queryRoleByRoleId(Role_Id);
    }

    /**
     * by Role_Name角色名称查询
     * @param Role_Name
     * @return
     */
    @Override
    public Role queryRoleByRoleName(String Role_Name) {
        return roleDao.queryRoleByRoleName(Role_Name);
    }

    /**
     * by Role_Id and Role_Name查询
     * @param Role_Id
     * @param Role_Name
     * @return
     */
    @Override
    public Role queryRoleByRoleIdAndRoleName(Integer Role_Id, String Role_Name) {
        return roleDao.queryRoleByRoleIdAndRoleName(Role_Id,Role_Name);
    }

    /**
     * Role分页查询
     * @param rolePageModel
     * @param role
     */
    @Override
    public void queryRolePage(PageModel<Role> rolePageModel,Role role) {
        rolePageModel.setList(roleDao.queryRoleList(rolePageModel,role));
        rolePageModel.setTotal(roleDao.queryRoleCount(rolePageModel,role));
    }
}
