package org.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zsw
 * @date 2019/4/21 0:58
 */
@WebFilter(filterName = "EncodingFilter",
urlPatterns = "/*",
initParams = {
    @WebInitParam(name = "charset",value = "utf-8")
})
public class EncodingFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Encoding Filter");
        String encode = filterConfig.getInitParameter("charset");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

}
