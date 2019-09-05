package hwkj.hwkj.entity.pagingquery;

import java.io.Serializable;
import java.util.List;

public class SelectModel<T> implements Serializable {
    private static final long serialVersionUID=1435515995276255188L;
    private Integer page; //当前页数
    private Integer rows;   //一页显示数量
    private Integer startRow;   //查询起始行
    private Integer total;      //总记录行数
    private List<T> list;       //查询结果数据
    private T queryObj;         //查询对象
    private String BG;       //事业群

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getStartRow() {
        if(page!=null && rows!=null) {
            return (page - 1) * rows;
        } else {
            return 0;
        }
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(T queryObj) {
        this.queryObj = queryObj;
    }

    public String getBG() {
        return BG;
    }

    public void setBG(String BG) {
        this.BG = BG;
    }

    public String getBU() {
        return BU;
    }

    public void setBU(String BU) {
        this.BU = BU;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getKe() {
        return Ke;
    }

    public void setKe(String ke) {
        Ke = ke;
    }

    public String getZu() {
        return Zu;
    }

    public void setZu(String zu) {
        Zu = zu;
    }

    public String getLegal_Name() {
        return Legal_Name;
    }

    public void setLegal_Name(String legal_Name) {
        Legal_Name = legal_Name;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    private String BU;       //事业处
    private String Dept;     //部门
    private String Ke;      //课别
    private String Zu;  //组别
    private String Legal_Name;  //法人
    private String Region;  //区域
}
