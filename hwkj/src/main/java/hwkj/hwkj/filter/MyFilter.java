package hwkj.hwkj.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Component
public class MyFilter implements Filter {

    /**@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器的名称：" + filterConfig.getFilterName());
        System.out.println("Filter初始化");
    }**/

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println(servletRequest.getParameter("job_Number"));
        /*HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper=new HttpServletResponseWrapper((HttpServletResponse)servletResponse);
        if(request.getRequestURI().indexOf("/index.do")!=-1 || request.getRequestURI().indexOf("/login.do")!=-1){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            wrapper.sendRedirect("login.do");
        }*/
        System.out.println("执行Filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter销毁");
    }
}
