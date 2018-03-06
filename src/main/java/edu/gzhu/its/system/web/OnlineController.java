package edu.gzhu.its.system.web;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OnlineController {

	@Resource
	private SessionRegistry sessionRegistry;
	
	@GetMapping("/userOnline")
	public String onLineUser(Model model){
		 List<Object> slist =sessionRegistry.getAllPrincipals();  
		 Set<User> userSet = new HashSet<User>();
		 for (Iterator iterator = slist.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			userSet.add(user);
			/*List<SessionInformation> ilist =   sessionRegistry.getAllSessions(user, true);
			if(ilist!=null&&ilist.size()>0){
				SessionInformation information = ilist.get(0);
				System.out.println(information.getPrincipal());
				information.getLastRequest();
			}*/
		}
		 model.addAttribute("userSize", userSet.size());
		 model.addAttribute("users", userSet);
		return "system/user/userOnline";
		
	}

}
