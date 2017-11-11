package edu.gzhu.its.system.service.impl;

import org.springframework.stereotype.Service;

import edu.gzhu.its.base.impl.BaseDAOImpl;
import edu.gzhu.its.system.entity.Role;
import edu.gzhu.its.system.service.IRoleService;

/**
 * 角色服務
 * @author Administrator
 *
 */
@Service("roleService")
public class RoleService  extends BaseDAOImpl<Role, Long> implements IRoleService{

}
