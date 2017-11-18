package edu.gzhu.its.system.service.impl;

import org.springframework.stereotype.Service;

import edu.gzhu.its.base.dao.impl.BaseDAOImpl;
import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.service.IRoleService;

/**
 * 角色
 * <p>Title : RoleService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2017年11月18日 下午7:00:16
 */
@Service("roleService")
public class RoleService  extends BaseDAOImpl<Role, Long> implements IRoleService{

}
