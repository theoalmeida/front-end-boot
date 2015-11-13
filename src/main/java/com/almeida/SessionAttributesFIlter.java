package com.almeida;

import com.almeida.entity.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by theo on 12/11/15.
 */
public class SessionAttributesFIlter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session;
        session = request1.getSession();

        addUserName(session);
        chain.doFilter(request, response);
    }

    private void addUserName(HttpSession session) {
        SecurityContextImpl sci = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

        if (sci != null && session.getAttribute("username") == null) {
            UserDetails cud = (UserDetails) sci.getAuthentication().getPrincipal();
            session.setAttribute("username", cud.getUsername());
        }
    }

    @Override
    public void destroy() {

    }
}
