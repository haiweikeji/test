package hwkj.hwkj.controller.User;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.HUser.UserRole;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.RoleService;
import hwkj.hwkj.service.User.UserRoleService;
import hwkj.hwkj.service.User.UserService;
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
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value ="/queryUserRole.do" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUserRole(PageModel<UserRole> userRolePageModel, UserRole userRole){
        Map<String,Object> map =new HashMap<>();
        userRoleService.queryUserRolePage(userRolePageModel,userRole.getJobNumber(),userRole.getRoleName());
        map.put("rows",userRolePageModel.getList());
        map.put("total",userRolePageModel.getTotal());
        return (JSONObject) JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/userRoleUpdate.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String userRoleUpdate(UserRole userRole){
        if(!userRoleService.updateUserRole(userRole)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/userRoleRemove.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String userRoleRemove(@RequestParam("User_Role_Id[]") Integer User_Role_Id[]){
        if(!userRoleService.deleteUserRole(User_Role_Id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryUserRoleFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUserRoleFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJobNumber(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/userRole.do",method = RequestMethod.GET)
    public String userRole(){
        return "userRole";
    }
}
