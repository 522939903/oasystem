package com.oasystem.dao.schedule;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.oasystem.model.ScheduleInfo;

public interface ScheduleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ScheduleInfo record);

    ScheduleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduleInfo record);

    List<ScheduleInfo> selectAllByUserId(@Param("userId") Integer userId);

    int delete(@Param("id") Integer id);


}