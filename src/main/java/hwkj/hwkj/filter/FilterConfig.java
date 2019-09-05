package hwkj.hwkj.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private MyFilter myFilter;

    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myFilter);
        registration.addUrlPatterns("*.do");//过滤所以.do的请求
        //registration.addUrlPatterns("/*");//过滤所以请求
        //registration.addUrlPatterns("/index.do");过滤单个请求
        //registration.addUrlPatterns("/login.do");
        registration.setName("myFilter");
        registration.setOrder(10);
        System.out.println("Filter初始化");
        return registration;
    }
}
