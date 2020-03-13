package com.oasystem.controller.system;

import com.oasystem.ResultDTO;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SysMenu;
import com.oasystem.service.system.menu.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "菜单管理")
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping(value = "/query")
    @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单")
    @ResponseBody
    public ResultDTO<List<SysMenu>> query() {
        return sysMenuService.query();
    }

    @GetMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @ResponseBody
    public ResultDTO<Boolean> querySend(@PathVariable("id") Integer id) {
        return sysMenuService.delete(id);
    }


    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    @ResponseBody
    public ResultDTO<Boolean> insert(@RequestBody SysMenu sysMenu) {
        return sysMenuService.add(sysMenu);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @ResponseBody
    public ResultDTO<Boolean> update(@RequestBody SysMenu sysMenu) {
        return sysMenuService.update(sysMenu);
    }
}
