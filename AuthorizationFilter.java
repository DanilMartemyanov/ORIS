package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponce, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest ;
        HttpServletResponse response = (HttpServletResponse) servletResponce;
        HttpSession session = request.getSession();


        Boolean isAuthenticated = false ;
        Boolean sessionExist = session != null ;
        Boolean isLogin  = request.getRequestURI().equals("/authorization");

        if(sessionExist){
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null){
                isAuthenticated = false ;
            }
        }
        if(isAuthenticated && !isLogin || !isAuthenticated && isLogin){
            filterChain.doFilter(servletRequest ,servletResponce);
        } else if (isAuthenticated && isLogin){
            response.sendRedirect("/users");
        }else{
            response.sendRedirect("/authorization");
        }
    }

    @Override
    public void destroy() {

    }
}
