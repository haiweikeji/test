package hwkj.hwkj.entity.pagingquery;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {
    private static final long serialVersionUID=1435515995276255188L;
    private Integer page; //当前页数
    private Integer rows;   //一页显示数量
    private Integer startRow;   //查询起始行
    private Integer total;      //总记录行数
    private List<T> list;       //查询结果数据
    private T queryObj;         //查询对象

    public Integer getStartRow() {
        if(page!=null && rows!=null) {
            return (page - 1) * rows;
        } else {
            return 0;
        }
    }
    public void setStartRow(Integer startRow){
        this.startRow=startRow;
    }

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

    public void setQueryObj(T queryObj) {
        this.queryObj = queryObj;
    }

    public T getQueryObj() {
        return queryObj;
    }
}
