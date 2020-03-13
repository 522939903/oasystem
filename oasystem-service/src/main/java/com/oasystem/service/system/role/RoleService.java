package com.oasystem.service.system.role;

import com.oasystem.ResultDTO;
import com.oasystem.model.RoleInfo;
import com.oasystem.model.RoleInfo;

import java.util.List;


public interface RoleService {

    /**
     * 删除角色
     *
     * @param role_id
     * @return
     */
    ResultDTO<Boolean> deleteRole(Integer role_id);

    /**
     * 新增角色信息
     *
     * @param roleInfo
     * @return
     */
    ResultDTO<Boolean> addRole(RoleInfo roleInfo);

    /**
     * 修改角色信息
     *
     * @param roleInfo
     * @return
     */
    ResultDTO<Boolean> upadteRole(RoleInfo roleInfo);

    /**
     * 查询所有角色
     *
     * @return
     */
    ResultDTO<List<RoleInfo>> queryRole();

}
