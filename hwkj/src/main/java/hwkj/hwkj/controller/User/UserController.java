package hwkj.hwkj.controller.User;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.httpSessionListener.MyHttpSessionListener;
import hwkj.hwkj.service.HR.EmployeePersonalDataService;
import hwkj.hwkj.service.User.RoleMenuService;
import hwkj.hwkj.service.User.RoleService;
import hwkj.hwkj.service.User.UserRoleService;
import hwkj.hwkj.service.User.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private EmployeePersonalDataService employeePersonalDataService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryUser.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUser(PageModel<User> userPageModel, @RequestParam("Job_Number") String Job_Number,@RequestParam("Name") String Name){
        Map<String,Object> map =new HashMap<>();
        userService.queryUserPage(userPageModel,Job_Number,Name);
        map.put("rows",userPageModel.getList());
        map.put("total",userPageModel.getTotal());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/userAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String userAdd(User user){
        if(!userService.insertUser(user)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/userUpdate.do",method = RequestMethod.POST)
    @ResponseBody
    public String userUpdate(User user){
        if(!userService.updateUser(user)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/updatePassword.do",method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(User user){
        if(!userService.updatePassword(user)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/userRemove.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String userRemove(@RequestParam("Job_Number[]") String Job_Number[]){
        if(!userService.deleteUser(Job_Number)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryUserNotExistJobNumber.do" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUserNotExistJobNumber(@RequestParam("Job_Number") String Job_Number){
        Map<String,Object> map =new HashMap<>();
        map.put("listJobNumber",userService.queryUserNotExistJobNumber(Job_Number));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/queryUserForOption.do" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUserForOption(@RequestParam("Job_Number") String Job_Number){
        Map<String,Object> map =new HashMap<>();
        map.put("listJobNumber",userService.queryUserForOption(Job_Number));
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/queryAllRole.do" ,method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllRoleAndImportUserRole(){
        Map<String,Object> map =new HashMap<>();
        map.put("listRole",roleService.queryAllRole());
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value ="/importUserRole.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String importUserRole(@RequestParam("Job_Number[]") String [] Job_Number,@RequestParam("Role_Name") String Role_Name){
        if(!userService.importUserRole(Job_Number, Role_Name)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/userRoleMenu.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> userRoleMenu(HttpServletRequest request){
        List<Menu> list=userService.userRoleMenu(((User)request.getSession().getAttribute("user")).getJob_Number());
        return list;
    }

    @RequestMapping(value = "/index.do",method = {RequestMethod.POST,RequestMethod.GET})
    public  String index(HttpServletRequest request, Model model){
        String Job_Number=request.getParameter("job_Number");
        String Password=request.getParameter("password");
        if(Job_Number==null || Job_Number.trim().length()==0){
            return "login";
        }
        User user=userService.queryUserByJobNumber(Job_Number.trim());
        if(user==null){
            model.addAttribute("login_error","工号不存在!");
            return "login";
        }
        else if(!user.getPassword().equals(DigestUtils.md5Hex(Password.trim()))){
            model.addAttribute("login_error","密码错误!");
            return "login";
        }
        else if(user.getPassword().equals(DigestUtils.md5Hex(Password.trim()))){
            System.out.println(user.toString());
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            model.addAttribute("online", MyHttpSessionListener.online);
            return "index";
        }
        else {
            return "redirect:/error.do";
        }
    }

    @RequestMapping(value ="/queryUserFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryUserFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        /*getIp getIp =new getIp();
        try {
            String ipAddress=getIp.getIpAddress(request);
            System.out.println("ipAddress:"+ipAddress);
            UdpGetClientMacAddr udpGetClientMacAddr =new UdpGetClientMacAddr(ipAddress);
            String macAddress=udpGetClientMacAddr.GetRemoteMacAddr();
            System.out.println("macAddress:"+macAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        HttpSession session=request.getSession();
        if(session!=null){
            session.invalidate();
        }
        return "login";
    }

    @RequestMapping(value = "/error.do",method = RequestMethod.GET)
    public String error(){
        return "403";
    }

    @RequestMapping(value = "/login.do2",method = RequestMethod.GET)
    public String user2(){
        return "login.do2";
    }

    @RequestMapping(value = "/user.do",method = RequestMethod.GET)
    public String user(){
        return "user";
    }

    @RequestMapping(value = "/test3.do",method = RequestMethod.GET)
    public String test3(){
        return "test3";
    }
}
