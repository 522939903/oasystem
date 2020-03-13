package com.oasystem.service.sysLog;

import com.oasystem.ResultDTO;
import com.oasystem.model.SysLog;

import java.util.List;


public interface SysLogService {

    /**
     * 新增操作日志
     *
     * @param sysLog
     * @return
     */
    Boolean insertSysLog(SysLog sysLog);

    ResultDTO<List<SysLog>> querySysLog();

   List<SysLog> queryList(Integer page, Integer limit);
}
