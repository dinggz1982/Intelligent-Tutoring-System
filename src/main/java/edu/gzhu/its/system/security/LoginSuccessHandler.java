package edu.gzhu.its.system.security;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IUserService;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Resource
	private HttpSession session;
	
	@Resource
	private IUserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 获得授权后可得到用户信息 可使用SUserService进行数据库操作
		UserDetails user =  (UserDetails) authentication.getPrincipal();
		User currentUser =  this.userService.findByName(user.getUsername());
		session.setAttribute("currentUser",currentUser);
		/* Set<SysRole> roles = userDetails.getSysRoles(); */
		
		// forward ： 可以隐藏url
		// request.getRequestDispatcher("/admin").forward(request, response);
		// 可以针对不同的用户跳转到不同的页面
		String url =currentUser.getUrl();
		if(url!=null&&!url.equals("")){
			response.sendRedirect(url);
		}else{
			response.sendRedirect("/admin");
		}
		
		// super.onAuthenticationSuccess(request, response, authentication);
	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}