package com.oasystem.service.message.message;

import com.oasystem.ResultDTO;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SaveMessage;

import java.util.List;


public interface MessageService {

    /**
     * 保存邮箱
     *
     * @return
     * @param saveMessage
     */
    ResultDTO<Boolean> saveMail(SaveMessage saveMessage);

    /**
     * 收件箱查询
     *
     * @param userId
     * @return
     */
    ResultDTO<List<MessageInfo>> quertAccessMail(Integer userId);

    /**
     * 发件箱查询
     *
     * @param userId
     * @return
     */
    ResultDTO<List<MessageInfo>> querySendMail(Integer userId);

    /**
     * 草稿箱查询
     *
     * @param userId
     * @return
     */
    ResultDTO<List<MessageInfo>> queryTempMail(Integer userId);

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
     * @param saveMessage
     * @return
     */
    ResultDTO<Boolean> updateMail(SaveMessage saveMessage);

    /**
     * 删除邮箱
     *
     * @param messageCode
     * @return
     */
    ResultDTO<Boolean> deleteMail(String messageCode);

    /**
     * 保存消息
     *
     * @param saveMessage
     * @return
     */
    ResultDTO<Boolean> saveMessage(SaveMessage saveMessage);

    ResultDTO<MessageInfo> queryTempByMessageCode(String messageCode);

    ResultDTO<Boolean> isRead(Integer id);

    ResultDTO<Boolean> updateDelte(String messageCode, Integer id);

    ResultDTO<List<MessageInfo>> quertDelete(Integer userId);

    ResultDTO<Boolean> back(String messageCode, Integer userId);

    ResultDTO<List<MessageInfo>> quertNoRead(Integer userId);

    ResultDTO<Boolean> batchIsRead(List<Integer> ids);
}
