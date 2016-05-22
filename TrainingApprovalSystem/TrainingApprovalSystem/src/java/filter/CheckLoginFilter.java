package filter;

import utility.Authorization;
import utility.DataConnector;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(filterName = "CheckLoginFilter")
public class CheckLoginFilter implements Filter {

    private static final String[] staticAssets = {"/css/", "/images/", "/js/", "assets"};

    public void init(FilterConfig config) throws ServletException { }

    public void destroy() { }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();

        /** Bypass static asset eg. .css, .js, .png */
        for(String str : staticAssets) {
            if(uri.contains(str)) {
                chain.doFilter(req, resp);
                return;
            }
        }

        Authorization authorization = new Authorization(DataConnector.getDBConnection(request), request.getSession());

        if(!authorization.isLogin() && !uri.endsWith("login.jsp") && !uri.endsWith("login.do")) {
            response.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(req, resp);
    }

}
