package com.oasystem.controller.message;

import com.oasystem.ResultDTO;
import com.oasystem.model.Mail;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SaveMail;
import com.oasystem.service.message.mail.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MailController
 * @Description
 * @Author suguoming
 * @Date 2020/3/1 12:56 上午
 */
@Api(tags = "邮箱模块")
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;


    @GetMapping(value = "/queryAccept/{userId}")
    @ApiOperation(value = "收件箱查询", notes = "收件箱查询")
    @ResponseBody
    public ResultDTO<List<Mail>> queryAccept(@PathVariable("userId") String userId) {
        return mailService.quertAccessMail(userId);
    }

    @GetMapping(value = "/querySend/{userId}")
    @ApiOperation(value = "发件箱查询", notes = "发件箱查询")
    @ResponseBody
    public ResultDTO<List<Mail>> querySend(@PathVariable("userId") Integer userId) {
        return mailService.querySendMail(userId);
    }

    @GetMapping(value = "/queryTemp/{userId}")
    @ApiOperation(value = "草稿箱列表查询", notes = "草稿箱列表查询")
    @ResponseBody
    public ResultDTO<List<Mail>> queryTempList(@PathVariable("userId") Integer userId) {
        return mailService.queryTempMail(userId);
    }

    @GetMapping(value = "/queryDelete/{userId}")
    @ApiOperation(value = "垃圾箱查询", notes = "垃圾箱查询")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> queryDelete(@PathVariable("userId") Integer userId) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setSendUserId(userId);
        return mailService.queryDeleteMail(messageInfo);
    }


    @PostMapping(value = "/save")
    @ApiOperation(value = "保存草稿箱", notes = "发送邮箱")
    @ResponseBody
    public ResultDTO<Boolean> save(@RequestBody SaveMail saveMail) {
        return mailService.saveMail(saveMail);
    }

    @PostMapping(value = "/send")
    @ApiOperation(value = "发送", notes = "发送邮箱")
    @ResponseBody
    public ResultDTO<Boolean> send(@RequestBody SaveMail saveMail) {
        return mailService.sendMail(saveMail);
    }
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改草稿箱内容", notes = "修改草稿箱内容")
    @ResponseBody
    public ResultDTO<Boolean> update(@RequestBody SaveMail saveMail) {
        return mailService.updateMail(saveMail);
    }


    @PostMapping(value = "/tempSend")
    @ApiOperation(value = "草稿箱发送", notes = "草稿箱发送")
    @ResponseBody
    public ResultDTO<Boolean> tempSend(@RequestBody SaveMail saveMail) {
        return mailService.tempSend(saveMail);
    }



}
