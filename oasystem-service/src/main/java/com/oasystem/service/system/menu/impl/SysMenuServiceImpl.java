package com.oasystem.service.system.menu.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.system.menu.SysMenuMapper;
import com.oasystem.model.SysMenu;
import com.oasystem.service.system.menu.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public ResultDTO<Boolean> add(SysMenu sysMenu) {
        int i = sysMenuMapper.insertSelective(sysMenu);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> update(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> delete(Integer id) {
        int i = sysMenuMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<SysMenu>> query() {
        List<SysMenu> selectall = sysMenuMapper.selectall();
        return ResultDTO.successResult(selectall);
    }
}
