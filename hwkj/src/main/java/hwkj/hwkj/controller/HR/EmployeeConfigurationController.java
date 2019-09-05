package hwkj.hwkj.controller.HR;

import hwkj.hwkj.entity.HR.EmployeeConfiguration;
import hwkj.hwkj.service.HR.EmployeeConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeConfigurationController {

    @Autowired
    private EmployeeConfigurationService employeeConfigurationService;

    @RequestMapping(value ="/saveEmployeeConfiguration.do" ,method =RequestMethod.POST)
    public String saveEmployeeConfiguration(EmployeeConfiguration employeeConfiguration){
        int count=employeeConfigurationService.selectEmployeeConfigurationByJobNumber(employeeConfiguration.getJobNumber());
        if(count==0){
            String Menu_Bar=employeeConfiguration.getOrgDate()+","+employeeConfiguration.getTitleDate()+","+employeeConfiguration.getEmployeePersonalData();
            employeeConfigurationService.insertEmployeeConfiguration(employeeConfiguration.getJobNumber(),Menu_Bar);
        }
        return "employeeConfiguration";
    }

    @RequestMapping(value ="/employeeConfiguration.do" ,method = RequestMethod.GET)
    public String employeeConfiguration(){
        return "employeeConfiguration";
    }
}
