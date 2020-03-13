package com.oasystem.service.message.mail.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.mail.MailMapper;
import com.oasystem.dao.mail.MessageInfoMapper;
import com.oasystem.model.Mail;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SaveMail;
import com.oasystem.service.message.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Autowired
    private MailMapper mailMapper;

    @Override
    public ResultDTO<Boolean> sendMail(SaveMail saveMail) {
        Mail mail = new Mail();
        mail.setContent(saveMail.getContent());
        mail.setSendUserId(saveMail.getSendUserId());
        mail.setTopic(saveMail.getTopic());
        mail.setAcceptUserId(String.join(",", saveMail.getAcceptUserId()));
        mail.setCcUserId(String.join(",", saveMail.getCcUserId()));
        int i = mailMapper.insertSelective(mail);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> saveMail(SaveMail saveMail) {

        Mail mail = new Mail();
        mail.setContent(saveMail.getContent());
        mail.setIsSave(1);
        mail.setSendUserId(saveMail.getSendUserId());
        mail.setTopic(saveMail.getTopic());
        mail.setAcceptUserId(String.join(",", saveMail.getAcceptUserId()));
        mail.setCcUserId(String.join(",", saveMail.getCcUserId()));
        int i = mailMapper.insertSelective(mail);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<Mail>> quertAccessMail(String accessUserId) {
        List<Mail> mail = mailMapper.selectAllByAcceptUserId(accessUserId);
        return ResultDTO.successResult(mail);
    }

    @Override
    public ResultDTO<List<Mail>> querySendMail(Integer sendUseId) {
        List<Mail> mail = mailMapper.querySendMail(sendUseId);
        return ResultDTO.successResult(mail);
    }

    @Override
    public ResultDTO<List<Mail>> queryTempMail(Integer userId) {
        List<Mail> mail = mailMapper.selectAllBySendUserId(userId);
        return ResultDTO.successResult(mail);
    }

    @Override
    public ResultDTO<List<MessageInfo>> queryDeleteMail(MessageInfo messageInfo) {
        List<MessageInfo> messageInfos = messageInfoMapper.queryDeleteMail(messageInfo.getSendUserId());
        return ResultDTO.successResult(messageInfos);
    }

    @Override
    public ResultDTO<Boolean> updateMail(SaveMail saveMail) {
        Mail mail = new Mail();
        mail.setId(saveMail.getId());
        mail.setContent(saveMail.getContent());
        mail.setIsSave(1);
        mail.setSendUserId(saveMail.getSendUserId());
        mail.setTopic(saveMail.getTopic());
        mail.setAcceptUserId(String.join(",", saveMail.getAcceptUserId()));
        mail.setCcUserId(String.join(",", saveMail.getCcUserId()));
        int i = mailMapper.updateByPrimaryKeySelective(mail);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> tempSend(SaveMail saveMail) {
        Mail mail = new Mail();
        mail.setId(saveMail.getId());
        mail.setContent(saveMail.getContent());
        mail.setIsSave(0);
        mail.setSendUserId(saveMail.getSendUserId());
        mail.setTopic(saveMail.getTopic());
        mail.setAcceptUserId(String.join(",", saveMail.getAcceptUserId()));
        mail.setCcUserId(String.join(",", saveMail.getCcUserId()));
        int i = mailMapper.updateByPrimaryKeySelective(mail);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> deleteMail(Integer emailId) {
        int i = messageInfoMapper.deleteByPrimaryKey(emailId);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }
}
