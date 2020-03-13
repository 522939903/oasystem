package com.oasystem.service.message.message.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.mail.MessageInfoMapper;
import com.oasystem.dao.system.user.UserInfoMapper;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SaveMessage;
import com.oasystem.model.UserInfo;
import com.oasystem.model.dto.UserQueryDTO;
import com.oasystem.service.message.message.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MailServiceImpl
 * @Description
 * @Author suguoming
 * @Date 2020/3/1 12:35 上午
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public ResultDTO<Boolean> saveMessage(SaveMessage saveMessage) {
        List<Integer> acceptUserIds;
        // 判断消息的接收范围
        Integer messageRange = saveMessage.getMessageRange();

        switch (messageRange) {
            case 0:
                // 个人
                acceptUserIds = saveMessage.getAcceptUserId().stream()
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList());
                break;
            case 1:
                // 部门
                UserQueryDTO userQueryDTO = new UserQueryDTO();
                userQueryDTO.setDeptId(saveMessage.getDeptId());
                acceptUserIds = userInfoMapper.selectAll(userQueryDTO).stream()
                        .filter(Objects::nonNull)
                        .map(UserInfo::getId)
                        .collect(Collectors.toList());
                break;
            case 2:
                // 全公司
                acceptUserIds = userInfoMapper.selectAll(null).stream()
                        .filter(Objects::nonNull)
                        .map(UserInfo::getId)
                        .collect(Collectors.toList());
                break;
            default:
                //
                acceptUserIds = saveMessage.getAcceptUserId().stream()
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList());


        }
        try {
            String messageCode = UUID.randomUUID().toString();
            for (Integer acceptUserId : acceptUserIds) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setSendUserId(saveMessage.getSendUserId());
                messageInfo.setContent(saveMessage.getContent());
                messageInfo.setEffectiveTime(saveMessage.getEffectiveTime());
                messageInfo.setAcceptUserId(acceptUserId);
                messageInfo.setIsSave(1);
                messageInfo.setMessageCode(messageCode);
                messageInfo.setMessageRange(messageRange);
                int i = messageInfoMapper.insertSelective(messageInfo);
            }
        } catch (Exception e) {
            return ResultDTO.errorResult("发送失败！");
        }
        return ResultDTO.successResult(true);
    }

    @Override
    public ResultDTO<Boolean> saveMail(SaveMessage saveMessage) {
        List<Integer> acceptUserIds;
        // 判断消息的接收范围
        Integer messageRange = saveMessage.getMessageRange();

        switch (messageRange) {
            case 0:
                // 个人
                acceptUserIds = saveMessage.getAcceptUserId().stream()
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList());
                break;
            case 1:
                // 部门
                UserQueryDTO userQueryDTO = new UserQueryDTO();
                userQueryDTO.setDeptId(saveMessage.getDeptId());
                acceptUserIds = userInfoMapper.selectAll(userQueryDTO).stream()
                        .filter(Objects::nonNull)
                        .map(UserInfo::getId)
                        .collect(Collectors.toList());
                break;
            case 2:
                // 全公司
                acceptUserIds = userInfoMapper.selectAll(null).stream()
                        .filter(Objects::nonNull)
                        .map(UserInfo::getId)
                        .collect(Collectors.toList());
                break;
            default:
                //
                acceptUserIds = saveMessage.getAcceptUserId().stream()
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList());


        }
        try {
            String messageCode = UUID.randomUUID().toString();
            for (Integer acceptUserId : acceptUserIds) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setSendUserId(saveMessage.getSendUserId());
                messageInfo.setContent(saveMessage.getContent());
                messageInfo.setEffectiveTime(saveMessage.getEffectiveTime());
                messageInfo.setAcceptUserId(acceptUserId);
                messageInfo.setMessageCode(messageCode);
                messageInfo.setIsSave(saveMessage.getIsSave());
                messageInfo.setMessageRange(messageRange);
                int i = messageInfoMapper.insertSelective(messageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.errorResult("发送失败！");
        }
        return ResultDTO.successResult(true);
    }


    @Override
    public ResultDTO<List<MessageInfo>> quertAccessMail(Integer userId) {
        List<MessageInfo> messageInfos = messageInfoMapper.quertAccessMail(userId);
        return ResultDTO.successResult(messageInfos);
    }

    @Override
    public ResultDTO<List<MessageInfo>> quertNoRead(Integer userId) {
        List<MessageInfo> messageInfos = messageInfoMapper.quertAccessMail(userId);
        List<MessageInfo> messageInfos1 = messageInfos.stream().filter(messageInfo -> {
            return messageInfo.getIsRead() == 0;
        }).collect(Collectors.toList());
        if (messageInfos1.size() <= 0) {
            return ResultDTO.errorResult("没有消息");

        }
        return ResultDTO.successResult(messageInfos1);
    }

    @Override
    public ResultDTO<List<MessageInfo>> querySendMail(Integer userId) {
        List<MessageInfo> messageInfos = messageInfoMapper.querySendMail(userId);
        Map<String, List<MessageInfo>> messageInfoMap = messageInfos.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(MessageInfo::getMessageCode));
        Set<String> messageCodeList = messageInfoMap.keySet();
        List<MessageInfo> resultInfo = new ArrayList<MessageInfo>();
        for (String messageCode : messageCodeList) {
            List<MessageInfo> messageInfoList = messageInfoMap.get(messageCode);
            MessageInfo messageResult = messageInfoList.get(0);
            Integer messageRange = messageResult.getMessageRange();
            String accepteUserString;

            switch (messageRange) {
                case 0:
                    List<String> accepteUserList = messageInfoList.stream().filter(Objects::nonNull)
                            .map(userInfo -> userInfo.getUserInfo().getName()).collect(Collectors.toList());
                    accepteUserString = String.join(",", accepteUserList);
                    break;
                case 1:
                    accepteUserString = "全部门";
                    break;
                case 2:
                    accepteUserString = "全公司";
                    break;
                default:
                    accepteUserString = "全公司";
            }
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setMessageRange(messageResult.getMessageRange());
            messageInfo.setContent(messageResult.getContent());
            messageInfo.setEffectiveTime(messageResult.getEffectiveTime());
            messageInfo.setAcceptUserName(accepteUserString);
            messageInfo.setUserInfo(messageResult.getUserInfo());
            messageInfo.setMessageCode(messageResult.getMessageCode());
            resultInfo.add(messageInfo);

        }
        return ResultDTO.successResult(resultInfo);
    }

    @Override
    public ResultDTO<List<MessageInfo>> queryTempMail(Integer userId) {
        List<MessageInfo> messageInfos = messageInfoMapper.queryTempMail(userId);
        Map<String, List<MessageInfo>> messageInfoMap = messageInfos.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(MessageInfo::getMessageCode));
        Set<String> messageCodeList = messageInfoMap.keySet();
        List<MessageInfo> resultInfo = new ArrayList<MessageInfo>();
        for (String messageCode : messageCodeList) {
            List<MessageInfo> messageInfoList = messageInfoMap.get(messageCode);
            MessageInfo messageResult = messageInfoList.get(0);
            Integer messageRange = messageResult.getMessageRange();
            String accepteUserString;

            switch (messageRange) {
                case 0:
                    List<String> accepteUserList = messageInfoList.stream().filter(Objects::nonNull)
                            .map(userInfo -> userInfo.getUserInfo().getName()).collect(Collectors.toList());
                    accepteUserString = String.join(",", accepteUserList);
                    break;
                case 1:
                    accepteUserString = "全部门";
                    break;
                case 2:
                    accepteUserString = "全公司";
                    break;
                default:
                    accepteUserString = "全公司";
            }
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setMessageRange(messageResult.getMessageRange());
            messageInfo.setContent(messageResult.getContent());
            messageInfo.setEffectiveTime(messageResult.getEffectiveTime());
            messageInfo.setAcceptUserName(accepteUserString);
            messageInfo.setUserInfo(messageResult.getUserInfo());
            messageInfo.setMessageCode(messageResult.getMessageCode());
            resultInfo.add(messageInfo);

        }
        return ResultDTO.successResult(resultInfo);
    }

    @Override
    public ResultDTO<MessageInfo> queryTempByMessageCode(String messageCode) {
        List<MessageInfo> messageInfos = messageInfoMapper.selectByMessageCode(messageCode);
        List<Integer> userNameList = messageInfos.stream().filter(Objects::nonNull)
                .map(MessageInfo::getAcceptUserId)
                .collect(Collectors.toList());

        MessageInfo messageInfo1 = messageInfos.get(0);
        MessageInfo mesageInfo = new MessageInfo();
        BeanUtils.copyProperties(messageInfo1, mesageInfo);
        mesageInfo.setAcceptUserIdList(userNameList);
        return ResultDTO.successResult(mesageInfo);
    }

    @Override
    public ResultDTO<List<MessageInfo>> queryDeleteMail(MessageInfo messageInfo) {
        List<MessageInfo> messageInfos = messageInfoMapper.queryDeleteMail(messageInfo.getSendUserId());
        return ResultDTO.successResult(messageInfos);
    }

    @Override
    public ResultDTO<Boolean> updateMail(SaveMessage saveMessage) {

        Map<Integer, Integer> collect = messageInfoMapper.selectByMessageCode(saveMessage.getMessageCode())
                .stream().collect(Collectors.toMap(MessageInfo::getAcceptUserId, MessageInfo::getId, (k1, k2) -> k1));


        List<Integer> acceptUserIds;
        // 判断消息的接收范围
        Integer messageRange = saveMessage.getMessageRange();

        switch (messageRange) {
            case 0:
                // 个人
                acceptUserIds = saveMessage.getAcceptUserId().stream()
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList());
                break;
            case 1:
                // 部门
                UserQueryDTO userQueryDTO = new UserQueryDTO();
                userQueryDTO.setDeptId(saveMessage.getDeptId());
                acceptUserIds = userInfoMapper.selectAll(userQueryDTO).stream()
                        .filter(Objects::nonNull)
                        .map(UserInfo::getId)
                        .collect(Collectors.toList());
                break;
            case 2:
                // 全公司
                acceptUserIds = userInfoMapper.selectAll(null).stream()
                        .filter(Objects::nonNull)
                        .map(UserInfo::getId)
                        .collect(Collectors.toList());
                break;
            default:
                //
                acceptUserIds = saveMessage.getAcceptUserId().stream()
                        .filter(Objects::nonNull)
                        .filter(StringUtils::isNotBlank).map(Integer::valueOf).collect(Collectors.toList());
        }
        try {
            for (Integer acceptUserId : acceptUserIds) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setSendUserId(saveMessage.getSendUserId());
                messageInfo.setContent(saveMessage.getContent());
                messageInfo.setEffectiveTime(saveMessage.getEffectiveTime());
                messageInfo.setAcceptUserId(acceptUserId);
                messageInfo.setMessageRange(messageRange);
                messageInfo.setId(collect.get(acceptUserId));
                messageInfo.setIsSave(saveMessage.getIsSave());
                int i = messageInfoMapper.updateByPrimaryKeySelective(messageInfo);
            }
            return ResultDTO.successResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.errorResult("发送失败！");
        }
    }

    @Override
    public ResultDTO<Boolean> deleteMail(String messageCode) {
        int i = messageInfoMapper.deleteByMessageCode(messageCode);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> batchIsRead(List<Integer> ids) {
        try {
            for (Integer id : ids) {
                int i = messageInfoMapper.isRead(id);
            }
        } catch (Exception e) {
           return ResultDTO.errorResult("异常");
        }
        return ResultDTO.successResult(true);
    }

    @Override
    public ResultDTO<Boolean> isRead(Integer id) {
        int i = messageInfoMapper.isRead(id);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> updateDelte(String messageCode, Integer id) {
        if (id == 0) {
            id = Integer.valueOf(messageCode);
            messageCode = null;
        } else {
            id = null;
        }

        int i = messageInfoMapper.updateDelte(id, messageCode);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<MessageInfo>> quertDelete(Integer userId) {
        List<MessageInfo> messageInfos = messageInfoMapper.selectDeleteMessageInfo(userId);
        Map<String, List<MessageInfo>> messageInfoMap = messageInfos.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(MessageInfo::getMessageCode));
        Set<String> messageCodeList = messageInfoMap.keySet();
        List<MessageInfo> resultInfo = new ArrayList<MessageInfo>();
        for (String messageCode : messageCodeList) {
            List<MessageInfo> messageInfoList = messageInfoMap.get(messageCode);
            MessageInfo messageResult = messageInfoList.get(0);
            Integer messageRange = messageResult.getMessageRange();
            String accepteUserString;

            switch (messageRange) {
                case 0:
                    List<String> accepteUserList = messageInfoList.stream().filter(Objects::nonNull)
                            .map(userInfo -> userInfo.getUserInfo().getName()).collect(Collectors.toList());
                    accepteUserString = String.join(",", accepteUserList);
                    break;
                case 1:
                    accepteUserString = "全部门";
                    break;
                case 2:
                    accepteUserString = "全公司";
                    break;
                default:
                    accepteUserString = "全公司";
            }
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setMessageRange(messageResult.getMessageRange());
            messageInfo.setContent(messageResult.getContent());
            messageInfo.setEffectiveTime(messageResult.getEffectiveTime());
            messageInfo.setAcceptUserName(accepteUserString);
            messageInfo.setUserInfo(messageResult.getUserInfo());
            messageInfo.setMessageCode(messageResult.getMessageCode());
            resultInfo.add(messageInfo);

        }
        return ResultDTO.successResult(resultInfo);
    }

    @Override
    public ResultDTO<Boolean> back(String messageCode, Integer userId) {
        List<MessageInfo> messageInfos = messageInfoMapper.selectDeleteByMessageCode(messageCode);

        Map<Integer, MessageInfo> sendMessageInfo = messageInfos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(MessageInfo::getSendUserId, MessageInfo -> MessageInfo, (k1, k2) -> k1));

        Map<Integer, MessageInfo> acceptINfoMap = messageInfos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(MessageInfo::getAcceptUserId, MessageInfo -> MessageInfo, (k1, k2) -> k1));

        MessageInfo messageInfo = sendMessageInfo.get(userId);
        MessageInfo messageInfo1 = acceptINfoMap.get(userId);

        try {
            if (messageInfo != null) {
                messageInfoMapper.backSend(messageInfo);
            }
            if (messageInfo1 != null) {
                messageInfoMapper.backAccept(messageInfo1);
            }
        } catch (Exception e) {
            return ResultDTO.errorResult("操作失败！");
        }

        return ResultDTO.successResult(true);
    }
}
