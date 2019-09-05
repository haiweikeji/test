package hwkj.hwkj.service.impl.UserImpl;

import hwkj.hwkj.dao.User.MenuDao;
import hwkj.hwkj.entity.HUser.Menu;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @Transactional
    @Override
    public boolean insertMenu(Menu menu){
        if(menuDao.queryMenuForExist(menu)!=0){
            throw new GlobalException("exist");
        }
        if(menuDao.insertMenu(menu)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 根据Menu_Id删除菜单
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteMenu(Integer id[]){
        if(menuDao.deleteMenu(id)!=id.length){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    @Transactional
    @Override
    public boolean updateMenu(Menu menu){
        if(menuDao.queryMenuForExist(menu)!=0){
            throw new GlobalException("exist");
        }
        if(menuDao.updateMenu(menu)<=0){
            throw new GlobalException("error");
        }
        return true;
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> queryAllMenu() {
        return menuDao.queryAllMenu();
    }

    /**
     * 根据Menu_Id查询菜单
     * @param Menu_Id
     * @return
     */
    @Override
    public Menu queryMenuByMenuId(Integer Menu_Id) {
        return menuDao.queryMenuByMenuId(Menu_Id);
    }

    /**
     * by page 查询
     * @param page
     * @return
     */
    @Override
    public Menu queryMenuByPage(String page) {
        return menuDao.queryMenuByPage(page);
    }

    /**
     * by name and page 查询
     * @param name
     * @param page
     * @return
     */
    @Override
    public Menu queryMenuByNameAndPage(String name, String page) {
        return menuDao.queryMenuByNameAndPage(name,page);
    }

    /**
     * by name 查询
     * @param name
     * @return
     */
    @Override
    public Menu queryMenuByName(String name) {
        return menuDao.queryMenuByName(name);
    }

    /**
     * Menu分页查询
     * @param menuPageModel
     * @param menu
     */
    @Override
    public void queryMenuPage(PageModel<Menu> menuPageModel,Menu menu) {
        menuPageModel.setList(menuDao.queryMenuList(menuPageModel,menu));
        menuPageModel.setTotal(menuDao.queryMenuCount(menuPageModel,menu));
    }

    /**
     * 查询Menu table max Menu_Id
     * @return
     */
    @Override
    public Integer queryMenuMaxMenuId() {
        return menuDao.queryMenuMaxMenuId();
    }
}
