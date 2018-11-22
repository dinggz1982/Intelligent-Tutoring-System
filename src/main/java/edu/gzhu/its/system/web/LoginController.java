package edu.gzhu.its.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private SessionRegistry sessionRegistry;
	/**
	 * 系统登录页面
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String longin() {
		return "login";
	}

	/**
	 * 退出登陆
	 * 
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // 使当前会话失效
		}

		SecurityContextHolder.clearContext(); // 清空安全上下文
		return "login";
	}

	/**
	 * 处理登录
	 * 
	 * @return
	 */
	@PostMapping("doLogin")
	public String doLogin() {

		return "";
	}

}
