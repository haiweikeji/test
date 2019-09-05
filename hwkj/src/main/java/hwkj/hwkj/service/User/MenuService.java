package hwkj.hwkj.service.User;

import hwkj.hwkj.entity.HUser.Menu;
import hwkj.hwkj.entity.pagingquery.PageModel;

import java.util.List;

public interface MenuService {
    /**
     * 新增菜单
     * @param menu
     * @return
     */
    public boolean insertMenu(Menu menu);

    /**
     * 根据Menu_Id删除菜单
     * @param id
     * @return
     */
    public boolean deleteMenu(Integer id[]);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    public boolean updateMenu(Menu menu);

    /**
     * 查询所有菜单
     * @return
     */
    public List<Menu> queryAllMenu();

    /**
     * 根据Menu_Id查询菜单
     * @param id
     * @return
     */
    public Menu queryMenuByMenuId(Integer id);

    /**
     * by page 查询
     * @param page
     * @return
     */
    public Menu queryMenuByPage(String page);

    /**
     * by name and page 查询
     * @param name
     * @param page
     * @return
     */
    public Menu queryMenuByNameAndPage(String name,String page);

    /**
     * by name 查询
     * @param name
     * @return
     */
    public Menu queryMenuByName(String name);

    /**
     * Menu分页查询
     * @param menuPageModel
     * @param menu
     */
    public void queryMenuPage(PageModel<Menu> menuPageModel,Menu menu);

    /**
     * 查询Menu table max Menu_Id
     * @return
     */
    public Integer queryMenuMaxMenuId();

}
