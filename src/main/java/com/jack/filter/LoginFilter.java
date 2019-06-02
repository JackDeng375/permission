package com.jack.filter;

import com.jack.common.RequestHolder;
import com.jack.model.SysUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 某些功能只能用户登录，没有登录跳转用户登录界面
 */
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取session对象
        HttpSession session = request.getSession();
        //拿取登录对象
        SysUser sysUser = (SysUser) session.getAttribute("user");
        if (sysUser == null) {
            //说明没有登录，跳转至登录界面
            String path = "/signin.jsp";
            response.sendRedirect(path);
            return;
        }
        //用户存在
        RequestHolder.add(sysUser);
        RequestHolder.add(request);

        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }
}
