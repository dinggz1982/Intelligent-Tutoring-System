package edu.gzhu.its.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import edu.gzhu.its.system.entity.User;

public class InvalidateSession {
	
	@Autowired
    SessionRegistry sessionRegistry;

	/**
	 * 用户session 
	 * <p>方法名:invalidateSession </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年1月31日 上午2:10:42
	 * @param user
	 */
	private void invalidateSession(User user){
        List<Object> o= sessionRegistry.getAllPrincipals();
        for (Object principal : o) {
            if (principal instanceof User) {
                final User loggedUser = (User) principal;
                if (user.getUsername().equals(loggedUser.getUsername())) {
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                        }
                    }
                }
            }
        }
    }
}
