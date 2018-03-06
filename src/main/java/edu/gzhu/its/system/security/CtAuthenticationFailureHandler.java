package edu.gzhu.its.system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 认证异常处理器
 * <p>Title : AuthenticationFailureHandler</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2017年12月28日 上午1:25:32
 */

public class CtAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//例3：自定义跳转到哪个URL  
        //假设login.jsp在webapp路径下  
        //注意：不能访问WEB-INF下的jsp。  
        String strUrl = request.getContextPath() + "/login.jsp";  
        request.getSession().setAttribute("ok", 0);  
        request.getSession().setAttribute("message", exception.getLocalizedMessage());  
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);  
        response.sendRedirect(strUrl);  
	}


}
