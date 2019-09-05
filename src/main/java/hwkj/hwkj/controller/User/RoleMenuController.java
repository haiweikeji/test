package hwkj.hwkj.controller.User;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.MenuService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.RoleService;
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
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/queryRoleMenu.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryRoleMenu(PageModel<RoleMenu> roleMenuPageModel,RoleMenu roleMenu){
        Map<String,Object> map =new HashMap<>();
        roleMenuService.queryRoleMenuPage(roleMenuPageModel,roleMenu);
        map.put("rows",roleMenuPageModel.getList());
        map.put("total",roleMenuPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/roleMenuUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String roleMenuUpdate(RoleMenu roleMenu){
        if(!roleMenuService.updateRoleMenu(roleMenu)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/roleMenuRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String roleMenuRemove(@RequestParam("Role_Menu_Id[]") Integer [] Role_Menu_Id){
        if(!roleMenuService.deleteRoleMenu(Role_Menu_Id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryRoleMenuFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryRoleMenuFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/roleMenu.do",method = RequestMethod.GET)
    public String roleMenu(){
        return "roleMenu";
    }
}
