package hwkj.hwkj.controller.User;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.MenuService;
import hwkj.hwkj.service.User.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryMenu.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMenu(PageModel<Menu> menuPageModel,Menu menu){
        Map<String,Object> map =new HashMap<>();
        menuService.queryMenuPage(menuPageModel,menu);
        map.put("rows",menuPageModel.getList());
        map.put("total",menuPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/menuAdd.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String menuAdd(Menu menu){
        if(!menuService.insertMenu(menu)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/menuUpdate.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String menuUpdate(Menu menu){
        if(!menuService.updateMenu(menu)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/menuRemove.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String menuRemove(@RequestParam("id[]") Integer id[]){
        if(!menuService.deleteMenu(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/importRoleMenu.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String importRoleMenu(@RequestParam("Url_Page[]") String [] Url_Page,@RequestParam("Menu_Name[]") String [] Menu_Name,@RequestParam("Role_Name") String Role_Name,
                                 @RequestParam("Newly_Added") String Newly_Added,@RequestParam("Modify") String Modify,@RequestParam("Remove") String Remove,
                                 @RequestParam("Download") String Download,@RequestParam("Model") String Model,@RequestParam("Upload") String Upload,@RequestParam("Move") String Move){
        if(!roleMenuService.importRoleMenu(Url_Page, Menu_Name, Role_Name, Newly_Added, Modify, Remove, Download, Model, Upload,Move)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryMenuFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryMenuFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/menu.do",method = RequestMethod.GET)
    public String menu(){
        return "menu";
    }
}
