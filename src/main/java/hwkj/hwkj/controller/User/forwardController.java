package hwkj.hwkj.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class forwardController {

    /**
     * 转发
     * @return
     */
    @RequestMapping(value = "/forward.do",method = RequestMethod.GET)
    public String forward(){
        return "forward";
    }

    /**
     * 重定向
     * @return
     */
    @RequestMapping(value = "/sendRedirect.do",method = RequestMethod.GET)
    public String sendRedirect(){
        return "redirect:/forward.do";
    }
}
