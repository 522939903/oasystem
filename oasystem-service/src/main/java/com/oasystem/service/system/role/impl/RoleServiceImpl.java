package com.oasystem.service.system.role.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.system.role.RoleInfoMapper;
import com.oasystem.model.RoleInfo;
import com.oasystem.service.system.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.Id;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/3/1 12:21 上午
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public ResultDTO<Boolean> deleteRole(Integer roleId) {
        int i = roleInfoMapper.deleteByPrimaryKey(roleId);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> addRole(RoleInfo roleInfo) {
        int i = roleInfoMapper.insertSelective(roleInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> upadteRole(RoleInfo roleInfo) {
        int i = roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<RoleInfo>> queryRole() {
        List<RoleInfo> selectall = roleInfoMapper.selectall();
        return ResultDTO.successResult(selectall);
    }
}
