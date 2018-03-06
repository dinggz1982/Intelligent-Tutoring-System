package edu.gzhu.its.system.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.system.entity.Resource;
import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.entity.User;
import edu.gzhu.its.system.service.IResourceService;

/**
 * 资源服务
 * <p>
 * Title : ResourceService
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Company :
 * </p>
 * 
 * @author 丁国柱
 * @date 2017年11月26日 上午1:55:55
 */
@Service("resourceService")
public class ResourceService extends BaseDAOImpl<Resource, Long> implements IResourceService {

	@Override
	public Set<Resource> getResourcesByRoleId(Role role) {
		return role.getResources();
	}

	@Override
	public Set<Resource> getResourcesByUserId(User user) {
		Set<Resource> resources = new HashSet<Resource>();
		Set<Role> roles = user.getRoles();
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			resources.addAll(role.getResources());
		}
		return resources;
	}

	@Override
	public Set<Resource> getMenuByUser(User user) {
		Set<Resource> resources = new HashSet<Resource>();
		// Set<Role> roles = user.getRoles();
		Set<Role> roleList = user.getRoles();
		if (roleList != null && roleList.size() > 0) {
			String ids = "";
			for (Iterator iterator = roleList.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				long roleId = role.getId();
				ids += roleId + ",";
			}
			ids = ids.substring(0, ids.length() - 1);

			List<Object[]> fatherModule = this.findBySql(
					"select r.id,r.url,r.name from sys_resource r,sys_role_resources rr where rr.role_id in(" + ids
							+ ") and rr.resources_id = r.id and  r.parentId is null and r.isMenu =1 and r.delFlag=0 order by r.id asc");

			for (Iterator iterator = fatherModule.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				Resource resource = new Resource();
				resource.setId(Integer.parseInt(objects[0].toString()));
				resource.setUrl(objects[1].toString());
				resource.setName(objects[2].toString());

				// 找到子菜单
				Set<Resource> children = new HashSet<Resource>();
				List<Object[]> childrenModule = this.findBySql(
						"select r.id,r.url,r.name from sys_resource r,sys_role_resources rr where rr.role_id in(" + ids
								+ ") and  r.parentId=" + objects[0]
								+ " and rr.resources_id = r.id and r.isMenu =1 and r.delFlag=0 order by r.id  asc");
				for (Iterator iterator2 = childrenModule.iterator(); iterator2.hasNext();) {
					Object[] objects2 = (Object[]) iterator2.next();
					Resource resource1 = new Resource();
					resource1.setId(Integer.parseInt(objects2[0].toString()));
					resource1.setUrl(objects2[1].toString());
					resource1.setName(objects2[2].toString());
					children.add(resource1);
				}
				List<Resource> list = new ArrayList<Resource>();
				list.addAll(children);
				resource.setChildren(list);
				resources.add(resource);
			}
		}
		return resources;
	}

	@Override
	public String getResourceTree(Integer roleId) {
		if (roleId != null && roleId > 0) {
			List<Object[]> objects = this.findBySql(
					"select r.id as id,r.parentId as pId,r.name as name from sys_resource r,sys_role_resources rr where r.delFlag=0 and r.id= rr.resources_id and rr.role_id="
							+ roleId);
			// { id:1, pId:0, name:"父节点1 - 展开", open:true},

			if (objects != null && objects.size() > 0) {
				StringBuffer bf = new StringBuffer();
				bf.append("[");
				for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					bf.append("{id:\"" + object[0] + "\",pId:\"" + object[1] + "\",name:\"" + object[2] + "\"");

					int childNum = this.getCountBySql("select count(*) from sys_resource where parentId=" + object[0]);
					if (childNum > 0) {
						// 有子树的情况
						bf.append(",open:true");
					}
					bf.append("},");
				}
				return bf.deleteCharAt(bf.length() - 1).toString() + "]";
			} else {
				return "";
			}

		} else {
			List<Object[]> objects = this.findBySql(
					"select r.id as id,r.parentId as pId,r.name as name from sys_resource r where r.delFlag=0");
			// { id:1, pId:0, name:"父节点1 - 展开", open:true},

			if (objects != null && objects.size() > 0) {
				StringBuffer bf = new StringBuffer();
				bf.append("[");
				for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					bf.append("{id:\"" + object[0] + "\",pId:\"" + object[1] + "\",name:\"" + object[2] + "\"");
					int childNum = this.getCountBySql("select  count(*) from sys_resource where parentId=" + object[0]);
					if (childNum > 0) {
						// 有子树的情况
						bf.append(",open:true");
					}
					bf.append("},");
					
					//判断当前资源中是否有对应的按钮操作
					List<Object[]> buttonObjects = this.findBySql(
							"select rb.id as id,rb.name as name from sys_resource_button rb where rb.resource_id=" +object[0] );
					if(buttonObjects!=null&&buttonObjects.size()>0){
						for (Iterator iterator2 = buttonObjects.iterator(); iterator2.hasNext();) {
							Object[] objects2 = (Object[]) iterator2.next();
							int buttonId = 10000+ Integer.parseInt(objects2[0].toString());
							bf.append("{id:\"" + buttonId + "\",pId:\"" + object[0] + "\",name:\"" + objects2[1] + "\"},");
						}
					}
					
				}
				return bf.deleteCharAt(bf.length() - 1).toString() + "]";
			} else {
				return "";
			}
		}
	}

	@Override
	public Resource getResourceByURL(String url) {
		// TODO Auto-generated method stub
		return this.findOneBySql("url", url);
	}

}
