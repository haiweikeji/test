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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryRole.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryRole(PageModel<Role> rolePageModel, Role role){
        Map<String,Object> map=new HashMap<>();
        roleService.queryRolePage(rolePageModel,role);
        map.put("rows",rolePageModel.getList());
        map.put("total",rolePageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/roleAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String roleAdd(Role role){
        if(!roleService.insertRole(role)){
           throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/roleUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String roleUpdate(Role role){
        if(!roleService.updateRole(role)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/roleRemove.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String roleRemove(@RequestParam("id[]") Integer id[]){
        if(!roleService.deleteRole(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/roleZTree.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> roleZTree(){
        Map<String,Object> map =new HashMap<>();
        List<Map<String,Object>> AddList =new ArrayList<>();
        List<Menu> list=menuService.queryAllMenu();
        //JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(list));
        //for (int i=0;i<jsonArray.size();i++){
            //System.out.println(jsonArray.get(i));
       // }
        for(int i=0;i<list.size();i++){
            Map<String,Object> AddMap =new HashMap<>();
            AddMap.put("id",list.get(i).getId());
            AddMap.put("pId",list.get(i).getpId());
            AddMap.put("name",list.get(i).getName());
            AddMap.put("page",list.get(i).getPage());
            AddList.add(AddMap);
        }
        //System.out.println(AddList);
        //String []arrayZtree= new String[]{s1,s2,s3,s4};
        return list;
        //return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/queryRoleFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryRoleFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/role.do",method = RequestMethod.GET)
    public String role(){
        return "role";
    }

}
