package com.oasystem.controller.schedule;

import com.oasystem.ResultDTO;
import com.oasystem.model.ScheduleInfo;
import com.oasystem.service.schedule.ScheduleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "日程模块")
@Controller
@RequestMapping("/schedule")
public class ScheduleInfoController {

    @Autowired
    private ScheduleInfoService scheduleInfoService;


    @GetMapping(value = "/query/{userId}")
    @ApiOperation(value = "日程查询", notes = "查询个人日程查询")
    @ResponseBody
    public ResultDTO<List<ScheduleInfo>> query(@PathVariable("userId") Integer userId) {
        return scheduleInfoService.queryByUserId(userId);

    }

    @GetMapping(value = "/queryById/{id}")
    @ApiOperation(value = "日程查询", notes = "查询日程查询")
    @ResponseBody
    public ResultDTO<ScheduleInfo> queryById(@PathVariable("id") Integer id) {
        return scheduleInfoService.queryById(id);

    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增日程", notes = "新增日程")
    @ResponseBody
    public ResultDTO<Boolean> insert(@RequestBody ScheduleInfo scheduleInfo) {
        return scheduleInfoService.insertSchedule(scheduleInfo);

    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改日程", notes = "修改日程")
    @ResponseBody
    public ResultDTO<Boolean> update(@RequestBody ScheduleInfo scheduleInfo) {
        return scheduleInfoService.updateSchedule(scheduleInfo);

    }

    @GetMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除日程", notes = "删除日程")
    @ResponseBody
    public ResultDTO<Boolean> delete(@PathVariable("id")  Integer id) {
        return scheduleInfoService.delete(id);
    }



}
