package com.oasystem.service.schedule;

import com.oasystem.ResultDTO;
import com.oasystem.model.ScheduleInfo;

import java.util.List;

/**
 * @ClassName ScheduleInfoService
 * @Description
 * @Author suguoming
 * @Date 2020/2/11 3:15 下午
 */
public interface ScheduleInfoService {

    /**
     * 新增日程
     *
     * @param scheduleInfo
     * @return
     */
    ResultDTO<Boolean> insertSchedule(ScheduleInfo scheduleInfo);



    /**
     * 修改日程信息
     *
     * @param scheduleInfo
     * @return
     */
    ResultDTO<Boolean> updateSchedule(ScheduleInfo scheduleInfo);


    /**
     * 查询个人日程信息
     *
     * @param userId
     * @return
     */
    ResultDTO<List<ScheduleInfo>> queryByUserId(Integer userId);

    /**
     * 删除日程
     * @param id
     * @return
     */
    ResultDTO<Boolean> delete(Integer id);

    /**
     * 根据主键查询日程信息
     * @param id
     * @return
     */
    ResultDTO<ScheduleInfo> queryById(Integer id);
}
