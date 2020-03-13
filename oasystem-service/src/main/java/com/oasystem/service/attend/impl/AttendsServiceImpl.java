package com.oasystem.service.attend.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.attend.AttendsDateMapper;
import com.oasystem.dao.attend.AttendsInfoMapper;
import com.oasystem.dao.system.user.UserInfoMapper;
import com.oasystem.model.AttendsDate;
import com.oasystem.model.AttendsInfo;
import com.oasystem.model.dto.AttendQueryDTO;
import com.oasystem.model.dto.WorkDate;
import com.oasystem.model.dto.WorkTime;
import com.oasystem.model.vo.AttendVo;
import com.oasystem.service.attend.AttendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName AttendsServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/2/17 6:39 下午
 */
@Service
public class AttendsServiceImpl implements AttendsService {

    @Resource
    private AttendsInfoMapper attendsInfoMapper;

    @Resource
    private AttendsDateMapper attendsDateMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void checkSignSingOut() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();

        List<AttendsInfo> attendsInfos = attendsInfoMapper.checkSignSingOut();
        List<AttendsInfo> attendsInfoList = attendsInfos.stream()
                .filter(Objects::nonNull)
                .map(attendsInfo -> {
                    AttendsInfo attendsInfo1 = new AttendsInfo();
                    attendsInfo1.setAttendTime(d);
                    attendsInfo1.setDeptId(attendsInfo.getDeptId());
                    attendsInfo1.setUserId(attendsInfo.getUserId());
                    attendsInfo1.setStatus(3);
                    return attendsInfo1;
                }).collect(Collectors.toList());

        for (AttendsInfo attendsInfo : attendsInfoList) {
            attendsInfoMapper.insertSelective(attendsInfo);
        }


    }


    @Override
    public ResultDTO<Boolean> signInAndSingOut(AttendsInfo attendsInfo) {

        return null;
    }

    @Override
    public ResultDTO<List<AttendsInfo>> queryAttendList(AttendsInfo attendsInfo) {
        return null;
    }

    @Override
    public ResultDTO<List<AttendsInfo>> attendStatistics(AttendsInfo attendsInfo) {
        return null;
    }

    @Override
    public void insertWoryDay(AttendsDate attendsDate) {
        attendsDateMapper.insertSelective(attendsDate);
    }

    @Override
    public ResultDTO<List<Boolean>> queryWoryDay(AttendsDate attendsDate) {
        return null;
    }

    @Override
    public List<AttendsInfo> queryList(Integer page, Integer limit, AttendQueryDTO attendQueryDTO) {

        return attendsInfoMapper.selectAllByDeptIdAndAttendTime(attendQueryDTO);
    }

    @Override
    public List<AttendVo> queryCountList(Integer page, Integer limit, AttendQueryDTO attendQueryDTO) {
        return attendsInfoMapper.selectCountAllByDeptIdAndAttendTime(attendQueryDTO);
    }

    @Override
    public List<AttendVo> queryCountList(AttendQueryDTO attendQueryDTO) {
        return attendsInfoMapper.selectCountAllByDeptIdAndAttendTime(attendQueryDTO);
    }

    @Override
    public ResultDTO<List<String>> queryAllholiday() {
        return ResultDTO.successResult(attendsDateMapper.selectAttendDateByIsWork());
    }

    @Override
    public ResultDTO<Boolean> setWorkDay(WorkDate workDate) {
        int i = attendsDateMapper.updateIsWorkByAttendDate(workDate.getIsWork(), workDate.getCurrentDate());
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.errorResult("修改失败！");
    }

    @Override
    public ResultDTO<Boolean> setWorkTime(WorkTime workTime) {
        int i = attendsDateMapper.updateWorkTime(workTime.getBeginTime(), workTime.getEndTime());

        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.errorResult("修改失败！");
    }

    @Override
    public ResultDTO<WorkTime> getWorkTime() {
        AttendsDate attendsDate = attendsDateMapper.selectWorkTime();
        WorkTime workTime = new WorkTime();
        workTime.setBeginTime(attendsDate.getWorkBeginTime());
        workTime.setEndTime(attendsDate.getWorkEndTime());
        return ResultDTO.successResult(workTime);
    }
}
