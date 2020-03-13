package com.oasystem.controller.system;

import com.oasystem.ResultDTO;
import com.oasystem.model.RoleInfo;
import com.oasystem.service.system.role.RoleService;
import com.oasystem.service.system.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "角色管理")
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    @GetMapping(value = "/get")
    @ResponseBody
    public ResultDTO<List<RoleInfo>> get() {
        return roleService.queryRole();
    }

    @ApiOperation(value = "角色删除", notes = "角色删除")
    @GetMapping(value = "/deleteById/{id}")
    @ResponseBody
    public ResultDTO<Boolean> deleteById(@PathVariable("id") Integer id) {
        return roleService.deleteRole(id);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "角色修改", notes = "角色修改")
    @ResponseBody
    public ResultDTO<Boolean> update(@RequestBody RoleInfo roleInfo) {
        return roleService.upadteRole(roleInfo);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ResponseBody
    public ResultDTO<Boolean> add(@RequestBody RoleInfo roleInfo) {
        return roleService.addRole(roleInfo);
    }


}
