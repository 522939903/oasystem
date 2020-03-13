package com.oasystem.service.system.login;

import com.oasystem.ResultDTO;
import com.oasystem.model.UserLoginInfo;
import com.oasystem.model.dto.LoginInfo;


public interface LoginSerivce {

    /**
     * 新增用户登录信息
     *
     * @param userLoginInfo
     * @return
     */
    ResultDTO<Boolean> addLoginInfo(UserLoginInfo userLoginInfo);


    /**
     * 登录验证
     * @param loginInfo
     * @return
     */
    String chechUser(LoginInfo loginInfo);

    ResultDTO<Boolean> checkPwd(LoginInfo loginInfo);

    ResultDTO<Boolean> updatePwd(LoginInfo loginInfo);
}
