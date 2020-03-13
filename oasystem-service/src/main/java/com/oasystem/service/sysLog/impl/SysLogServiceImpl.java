package com.oasystem.service.sysLog.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.sysLog.SysLogMapper;
import com.oasystem.model.SysLog;
import com.oasystem.service.sysLog.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;


@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public List<SysLog> queryList(Integer page, Integer limit) {
        List<SysLog> sysLogs = sysLogMapper.selectAll();
        if (CollectionUtils.isEmpty(sysLogs)) {
            return Collections.EMPTY_LIST;
        }

        return sysLogs;
    }

    @Override
    public ResultDTO<List<SysLog>> querySysLog() {
        List<SysLog> sysLogs = sysLogMapper.selectAll();
        if (CollectionUtils.isEmpty(sysLogs)) {
            return ResultDTO.successResult(Collections.EMPTY_LIST);
        }

        return ResultDTO.successResult(sysLogs);
    }

    @Override
    public Boolean insertSysLog(SysLog sysLog) {
        int i = sysLogMapper.insertSelective(sysLog);
        return i > 0;
    }
}
