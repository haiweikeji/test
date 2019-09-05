package hwkj.hwkj.entity.HUser;

import javax.persistence.*;

@Table(name = "hwkj_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pId")
    private Integer pid;

    private String name;

    private String page;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return pId
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page
     */
    public void setPage(String page) {
        this.page = page;
    }
}