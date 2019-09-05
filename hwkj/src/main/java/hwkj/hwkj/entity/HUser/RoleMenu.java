package hwkj.hwkj.entity.HUser;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hwkj_role_menu")
public class RoleMenu {
    /**
     * 权限菜单id
     */
    @Id
    @Column(name = "Role_Menu_Id")
    private Integer roleMenuId;

    /**
     * 权限名称
     */
    @Column(name = "Role_Name")
    private String roleName;

    /**
     * 菜单名称
     */
    @Column(name = "Menu_Name")
    private String menuName;

    /**
     * 网页
     */
    @Column(name = "Url_Page")
    private String urlPage;

    /**
     * 新增
     */
    @Column(name = "Newly_Added")
    private String newlyAdded;

    /**
     * 修改
     */
    @Column(name = "Modify")
    private String modify;

    /**
     * 移除
     */
    @Column(name = "Remove")
    private String remove;

    /**
     * 下载
     */
    @Column(name = "Download")
    private String download;

    /**
     * 模型
     */
    @Column(name = "Model")
    private String model;

    /**
     * 上传
     */
    @Column(name = "Upload")
    private String upload;

    /**
     * 移动
     */
    @Column(name = "Move")
    private String move;

    /**
     * 获取权限菜单id
     *
     * @return Role_Menu_Id - 权限菜单id
     */
    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    /**
     * 设置权限菜单id
     *
     * @param roleMenuId 权限菜单id
     */
    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    /**
     * 获取权限名称
     *
     * @return Role_Name - 权限名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置权限名称
     *
     * @param roleName 权限名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取菜单名称
     *
     * @return Menu_Name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取网页
     *
     * @return Url_Page - 网页
     */
    public String getUrlPage() {
        return urlPage;
    }

    /**
     * 设置网页
     *
     * @param urlPage 网页
     */
    public void setUrlPage(String urlPage) {
        this.urlPage = urlPage;
    }

    /**
     * 获取新增
     *
     * @return Newly_Added - 新增
     */
    public String getNewlyAdded() {
        return newlyAdded;
    }

    /**
     * 设置新增
     *
     * @param newlyAdded 新增
     */
    public void setNewlyAdded(String newlyAdded) {
        this.newlyAdded = newlyAdded;
    }

    /**
     * 获取修改
     *
     * @return Modify - 修改
     */
    public String getModify() {
        return modify;
    }

    /**
     * 设置修改
     *
     * @param modify 修改
     */
    public void setModify(String modify) {
        this.modify = modify;
    }

    /**
     * 获取移除
     *
     * @return Remove - 移除
     */
    public String getRemove() {
        return remove;
    }

    /**
     * 设置移除
     *
     * @param remove 移除
     */
    public void setRemove(String remove) {
        this.remove = remove;
    }

    /**
     * 获取下载
     *
     * @return Download - 下载
     */
    public String getDownload() {
        return download;
    }

    /**
     * 设置下载
     *
     * @param download 下载
     */
    public void setDownload(String download) {
        this.download = download;
    }

    /**
     * 获取模型
     *
     * @return Model - 模型
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置模型
     *
     * @param model 模型
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取上传
     *
     * @return Upload - 上传
     */
    public String getUpload() {
        return upload;
    }

    /**
     * 设置上传
     *
     * @param upload 上传
     */
    public void setUpload(String upload) {
        this.upload = upload;
    }

    /**
     * 获取移动
     *
     * @return Move - 移动
     */
    public String getMove() {
        return move;
    }

    /**
     * 设置移动
     *
     * @param move 移动
     */
    public void setMove(String move) {
        this.move = move;
    }
}