package com.oasystem.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SaveMail
 * @Description
 * @Author suguoming
 * @Date 2020/3/5 8:41 下午
 */
@Data
public class SaveMail {
    /**
     * 邮件编号
     */
    private Integer id;

    /**
     * 主题
     */
    private String topic;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布者编号
     */
    private Integer sendUserId;

    /**
     * 接收者编号列表
     */
    private List<String> acceptUserId;

    /**
     * 抄送这编号
     */
    private List<String> ccUserId;

    /**
     * 发布时间
     */
    private Date sendTime;

    /**
     * 有效时间
     */
    private Date effectiveTime;

    /**
     * 是否保存到草稿箱 1是 0否
     */
    private Integer isSave;

    /**
     * 是否已读  1是 0否
     */
    private Integer isRead;

    /**
     * 是否被收件人删除  0 否 1是
     */
    private Integer isDeleteAccept;

    /**
     * 是否被发布人删除  0 否 1是
     */
    private Integer isDeleteSend;

    /**
     * 是否被收件人永久删除  0 否 1是
     */
    private Integer isDeleteAcceptTrash;

    /**
     * 是否被发布人永久删除 0 否 1是
     */
    private Integer isDeleteSendTrash;

    /**
     * 修改时间
     */
    private Date updateTime;
}
