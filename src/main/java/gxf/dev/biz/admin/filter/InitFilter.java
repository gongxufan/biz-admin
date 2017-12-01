package gxf.dev.biz.admin.filter;

import gxf.dev.biz.admin.config.SystemConfig;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 系统启动初始化一些web参数
 * @author gongxufan
 * @date 17-11-21
 **/
@WebFilter(filterName = "initFilter", urlPatterns = "/")
public class InitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (SystemConfig.domian == null)
            SystemConfig.domian = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/";
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
