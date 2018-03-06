package edu.gzhu.its.system.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import edu.gzhu.its.system.entity.Resource;
import edu.gzhu.its.system.service.IResourceService;

@Service
public class CtSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private IResourceService resourceService;

	// 权限集合
	private HashMap<String, Collection<ConfigAttribute>> map = null;

	/**
	 * 加载权限表中所有权限
	 * @throws SQLException 
	 */
	public void loadResourceDefine() throws SQLException {
		map = new HashMap<>();
		Collection<ConfigAttribute> array;
		ConfigAttribute cfg;
		List<Resource> resources = this.resourceService.findAll();
		//找到所有的按鈕資源
		
		for (Resource resource : resources) {
			array = new ArrayList<>();
			cfg = new SecurityConfig(resource.getUrl());
			// 此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
			array.add(cfg);
			// 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
			map.put(resource.getUrl(), array);
		}

	}

	// 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null)
			try {
				loadResourceDefine();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// object 中包含用户请求的request 信息
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();  
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			if (requestMatcher.matches(request)) {
				return map.get(resURL);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 如果为真则说明支持当前格式类型,才会到上面的 getAttributes 方法中
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		// 需要返回true表示支持
		return true;
	}

}
