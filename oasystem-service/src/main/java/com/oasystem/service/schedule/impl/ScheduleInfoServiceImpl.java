package com.oasystem.service.schedule.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.schedule.ScheduleInfoMapper;
import com.oasystem.model.ScheduleInfo;
import com.oasystem.service.schedule.ScheduleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName ScheduleInfoServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/23 12:20 上午
 */
@Service
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

    @Autowired
    private ScheduleInfoMapper scheduleInfoMapper;

    @Override
    public ResultDTO<Boolean> insertSchedule(ScheduleInfo scheduleInfo) {
        int i = scheduleInfoMapper.insertSelective(scheduleInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }


    @Override
    public ResultDTO<Boolean> updateSchedule(ScheduleInfo scheduleInfo) {
        int i = scheduleInfoMapper.updateByPrimaryKeySelective(scheduleInfo);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<ScheduleInfo>> queryByUserId(Integer userId) {
        List<ScheduleInfo> scheduleInfos = scheduleInfoMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(scheduleInfos)) {
            return ResultDTO.errorResult("无日程");

        }
        return ResultDTO.successResult(scheduleInfos);
    }

    @Override
    public ResultDTO<Boolean> delete(Integer id) {
        int i = scheduleInfoMapper.delete(id);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<ScheduleInfo> queryById(Integer id) {
        ScheduleInfo scheduleInfos = scheduleInfoMapper.selectByPrimaryKey(id);
        if (scheduleInfos == null) {
            return ResultDTO.errorResult("查询失败");

        }
        return ResultDTO.successResult(scheduleInfos);
    }
}
