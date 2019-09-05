package hwkj.hwkj.controller.HR;

import com.alibaba.fastjson.JSONObject;
import hwkj.hwkj.entity.HR.FileManagement;
import hwkj.hwkj.entity.HUser.User;
import hwkj.hwkj.entity.pagingquery.PageModel;
import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.service.HR.FileManagementService;
import hwkj.hwkj.service.User.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileManagementController {

    @Autowired
    private FileManagementService fileManagementService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/queryFileManagement.do",method = RequestMethod.POST)
    @ResponseBody
    public Map queryFileManagement(PageModel<FileManagement> fileManagementPageModel, FileManagement fileManagement){
        Map<String,Object> map =new HashMap<>();
        fileManagementService.queryFileManagementPage(fileManagementPageModel, fileManagement);
        map.put("rows",fileManagementPageModel.getList());
        map.put("total",fileManagementPageModel.getTotal());
        return map;
    }

    @RequestMapping(value = "/fileManagementAdd.do",method = RequestMethod.POST)
    @ResponseBody
    public String fileManagementAdd(HttpServletRequest request, FileManagement fileManagement, @RequestParam("multipartFile") MultipartFile multipartFile){
        if(((User)(request.getSession().getAttribute("user")))==null){
            throw new GlobalException("timeOut");
        }
        if(multipartFile!=null && !"".equals(multipartFile.getOriginalFilename())){
            FileOutputStream fileOutputStream=null;
            String File_Name=multipartFile.getOriginalFilename();//源文件名
            try {
                fileOutputStream=new FileOutputStream("F:/ideaproject/hwkj/target/classes/static/upload/file_management/"+File_Name);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.write(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String File_Url="/hwkj/upload/file_management/"+File_Name;
            fileManagement.setFile_Url(File_Url);
            fileManagement.setCreator(((User)request.getSession().getAttribute("user")).getName());
            fileManagement.setCreate_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(!fileManagementService.insertFileManagement(fileManagement)){
                throw new GlobalException("error");
            }
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value = "/fileManagementRemove.do",method = RequestMethod.POST)
    @ResponseBody
    public String fileManagementRemove(@RequestParam("id[]") Integer id[]){
        if(!fileManagementService.deleteFileManagement(id)){
            throw new GlobalException("error");
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(value ="/queryFileManagementFunction.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryFileManagementFunction(HttpServletRequest request, @RequestParam("Url_Page") String Url_Page){
        Map<String,Object> map=new HashMap<>();
        if(request.getSession()==null || ((User)(request.getSession().getAttribute("user")))==null){
            map.put("list","timeOut");
        }else {
            User user=(User) request.getSession().getAttribute("user");
            map.put("list",roleMenuService.queryFunction(user.getJob_Number(), Url_Page));
        }
        return (JSONObject)JSONObject.toJSON(map);
    }

    @RequestMapping(value = "/fileManagement.do",method = RequestMethod.GET)
    public String fileManagement(){
        return "fileManagement";
    }
}
