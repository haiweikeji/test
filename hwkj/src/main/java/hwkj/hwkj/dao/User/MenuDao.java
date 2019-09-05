package hwkj.hwkj.dao.User;

import hwkj.hwkj.entity.HUser.Menu;
import hwkj.hwkj.entity.pagingquery.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    public int insertMenu(Menu menu);

    /**
     * 根据Menu_Id删除菜单
     * @param id
     * @return
     */
    public int deleteMenu(Integer id[]);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    public int updateMenu(Menu menu);

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
    public Menu queryMenuByMenuId(@Param("id") Integer id);

    /**
     * by page 查询
     * @param page
     * @return
     */
    public Menu queryMenuByPage(@Param("page") String page);

    /**
     * by name and page 查询
     * @param name
     * @param page
     * @return
     */
    public Menu queryMenuByNameAndPage(@Param("name") String name,@Param("page") String page);

    /**
     * by name 查询
     * @param name
     * @return
     */
    public Menu queryMenuByName(@Param("name") String name);

    /**
     *Menu分页查询
     * @param menuPageModel
     * @param menu
     * @return
     */
    public List<Menu> queryMenuList(@Param("menuPageModel")PageModel<Menu> menuPageModel,@Param("menu") Menu menu);

    /**
     *Menu总数Count
     * @param menuPageModel
     * @param menu
     * @return
     */
    public int queryMenuCount(@Param("menuPageModel")PageModel<Menu> menuPageModel,@Param("menu") Menu menu);

    /**
     * 查询Menu table max Menu_Id
     * @return
     */
    public int queryMenuMaxMenuId();

    /**
     * 检查新增的数据是否已存在
     * @param menu
     * @return
     */
    public int queryMenuForExist(Menu menu);
}
