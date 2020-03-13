package com.oasystem.dao.mail;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.oasystem.model.MessageInfo;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);


    List<MessageInfo> quertAccessMail(Integer acceptUserId);

    List<MessageInfo> querySendMail(@Param("sendUserId") Integer sendUserId);

    List<MessageInfo> queryTempMail(@Param("sendUserId") Integer sendUserId);

    List<MessageInfo> queryDeleteMail(@Param("sendUserId") Integer sendUserId);

    int deleteByMessageCode(String messageCode);

    List<MessageInfo> selectByMessageCode(String messageCode);

    int isRead(Integer id);

    int updateDelte(@Param("id") Integer id, @Param("messageCode") String messageCode);

    List<MessageInfo> selectDeleteMessageInfo(@Param("uesrId") Integer uesrId);
    int backAccept(MessageInfo record);

    int backSend(MessageInfo record);

    List<MessageInfo> selectDeleteByMessageCode(String messageCode);

}