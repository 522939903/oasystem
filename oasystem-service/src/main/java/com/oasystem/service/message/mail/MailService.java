package com.oasystem.service.message.mail;

import com.oasystem.ResultDTO;
import com.oasystem.model.Mail;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SaveMail;

import java.util.List;

/**
 * @ClassName MailService
 * @Description
 * @Author suguoming
 * @Date 2020/2/10 4:51 下午
 */
public interface MailService {

    /**
     * 保存邮箱
     *
     * @return
     */
    ResultDTO<Boolean> saveMail(SaveMail saveMail);

    /**
     * 收件箱查询
     *
     * @param accessUserId
     * @return
     */
    ResultDTO<List<Mail>> quertAccessMail(String accessUserId);

    /**
     * 发件箱查询
     *
     * @param sendUseId
     * @return
     */
    ResultDTO<List<Mail>> querySendMail(Integer sendUseId);

    /**
     * 草稿箱查询
     *
     * @param userId
     * @return
     */
    ResultDTO<List<Mail>> queryTempMail(Integer userId);

    /**
     * 垃圾箱查询
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<List<MessageInfo>> queryDeleteMail(MessageInfo messageInfo);

    /**
     * 修改草稿邮箱内容
     *
     * @param messageInfo
     * @return
     */
    ResultDTO<Boolean> updateMail(SaveMail messageInfo);

    /**
     * 删除邮箱
     *
     * @param emailId
     * @return
     */
    ResultDTO<Boolean> deleteMail(Integer emailId);

    /**
     * 发送邮件
     * @param saveMail
     * @return
     */
    ResultDTO<Boolean> sendMail(SaveMail saveMail);

    ResultDTO<Boolean> tempSend(SaveMail saveMail);
}
