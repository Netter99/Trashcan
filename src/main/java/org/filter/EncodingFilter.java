package org.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
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
        String encode = filterConfig.getInitParameter("charset");
        req.setCharacterEncoding(encode);
        resp.setCharacterEncoding(encode);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

}
