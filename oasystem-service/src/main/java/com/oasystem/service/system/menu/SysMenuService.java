package com.oasystem.service.system.menu;

import com.oasystem.ResultDTO;
import com.oasystem.model.SysMenu;

import java.util.List;

/**
 * @ClassName SysMenuService
 * @Description
 * @Author suguoming
 * @Date 2020/3/1 1:09 上午
 */
public interface SysMenuService {

    /**
     * 新增菜单
     * @param sysMenu
     * @return
     */
    ResultDTO<Boolean> add(SysMenu sysMenu);

    /**
     * 修改菜单
     *
     * @param sysMenu
     * @return
     */
    ResultDTO<Boolean> update(SysMenu sysMenu);


    /**
     * 删除菜单
     * @param id
     * @return
     */
    ResultDTO<Boolean> delete(Integer id);

    /**
     * 查询所有菜单
     * @return
     */
    ResultDTO<List<SysMenu>> query();
}
