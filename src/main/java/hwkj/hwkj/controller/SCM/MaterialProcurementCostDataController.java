package hwkj.hwkj.controller.SCM;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MaterialProcurementCostDataController {

    @RequestMapping(value = "/materialProcurementCostData.do",method = RequestMethod.GET)
    public String materialProcurementCostData(){
        return "materialProcurementCostData";
    }
}
