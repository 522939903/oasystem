package com.oasystem.controller.message;

import com.oasystem.ResultDTO;
import com.oasystem.model.MessageInfo;
import com.oasystem.model.SaveMessage;
import com.oasystem.service.message.message.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "信箱模块")
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping(value = "/save")
    @ApiOperation(value = "保存草稿箱", notes = "发送邮箱")
    @ResponseBody
    public ResultDTO<Boolean> save(@RequestBody SaveMessage saveMessage) {
        return messageService.saveMessage(saveMessage);
    }

    @GetMapping(value = "/delete/{messageCode}")
    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    public ResultDTO<Boolean> delete(@PathVariable("messageCode") String messageCode) {
        return messageService.deleteMail(messageCode);
    }

    @GetMapping(value = "/back/{messageCode}/{userId}")
    @ApiOperation(value = "废弃箱还原操作", notes = "废弃箱还原操作")
    @ResponseBody
    public ResultDTO<Boolean> back(@PathVariable("messageCode") String messageCode, @PathVariable("userId") Integer userId) {
        return messageService.back(messageCode, userId);
    }


    @GetMapping(value = "/quertDelete/{userId}")
    @ApiOperation(value = "查询删除", notes = "查询删除")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> quertDelete(@PathVariable("userId") Integer userId) {
        return messageService.quertDelete(userId);
    }


    @GetMapping(value = "/updateDelte/{messageCode}/{id}")
    @ApiOperation(value = "删除邮件", notes = "删除邮件")
    @ResponseBody
    public ResultDTO<Boolean> updateDelte(@PathVariable("messageCode") String messageCode, @PathVariable("id") Integer id) {
        return messageService.updateDelte(messageCode, id);
    }

    @GetMapping(value = "/queryAccept/{userId}")
    @ApiOperation(value = "收件箱查询", notes = "收件箱查询")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> queryAccept(@PathVariable("userId") Integer userId) {
        return messageService.quertAccessMail(userId);
    }

    @GetMapping(value = "/quertNoRead/{userId}")
    @ApiOperation(value = "收件箱查询", notes = "收件箱查询")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> quertNoRead(@PathVariable("userId") Integer userId) {
        return messageService.quertNoRead(userId);
    }


    @GetMapping(value = "/isRead/{id}")
    @ApiOperation(value = "已读", notes = "已读")
    @ResponseBody
    public ResultDTO<Boolean> isRead(@PathVariable("id") Integer id) {
        return messageService.isRead(id);
    }

    @PostMapping(value = "/batchIsRead")
    @ApiOperation(value = "已读", notes = "已读")
    @ResponseBody
    public ResultDTO<Boolean> batchIsRead(@RequestBody List<Integer> ids) {
        return messageService.batchIsRead(ids);
    }

    @GetMapping(value = "/querySend/{userId}")
    @ApiOperation(value = "发件箱查询", notes = "发件箱查询")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> querySend(@PathVariable("userId") Integer userId) {
        return messageService.querySendMail(userId);
    }

    @GetMapping(value = "/queryTemp/{userId}")
    @ApiOperation(value = "草稿箱查询", notes = "草稿箱查询")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> queryTemp(@PathVariable("userId") Integer userId) {
        return messageService.queryTempMail(userId);
    }

    @GetMapping(value = "/queryTempByMessageCode/{messageCode}")
    @ApiOperation(value = "草稿箱查询", notes = "草稿箱查询")
    @ResponseBody
    public ResultDTO<MessageInfo> queryTempByMessageCode(@PathVariable("messageCode") String messageCode) {
        return messageService.queryTempByMessageCode(messageCode);
    }

    @GetMapping(value = "/queryDelete/{userId}")
    @ApiOperation(value = "垃圾箱查询", notes = "垃圾箱查询")
    @ResponseBody
    public ResultDTO<List<MessageInfo>> queryDelete(@PathVariable("userId") Integer userId) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setSendUserId(userId);
        return messageService.queryDeleteMail(messageInfo);
    }


    @PostMapping(value = "/send")
    @ApiOperation(value = "发送邮箱", notes = "发送邮箱")
    @ResponseBody
    public ResultDTO<Boolean> queryDelete(@RequestBody SaveMessage saveMessage) {
        return messageService.saveMail(saveMessage);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改草稿箱内容", notes = "修改草稿箱内容")
    @ResponseBody
    public ResultDTO<Boolean> update(@RequestBody SaveMessage saveMessage) {
        return messageService.updateMail(saveMessage);
    }
}
