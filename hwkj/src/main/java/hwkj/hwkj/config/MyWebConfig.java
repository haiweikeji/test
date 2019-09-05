package hwkj.hwkj.config;

import hwkj.hwkj.interceptor.MyInterceptor;
import hwkj.hwkj.httpSessionListener.MyHttpSessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer{

    /*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hwkj/").setViewName("index");
    }*/

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/*.do");//拦截所有.do的请求,不包括静态资源请求
                //.addPathPatterns("/*");//基本拦截所有请求,包括静态资源请求
    }

    /*@SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegister() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
        frBean.addUrlPatterns("/*");
        System.out.println("filter");
        return frBean;
    }*/

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegister() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        System.out.println("httpSessionListener");
        return srb;
    }
}
