package com.oasystem.model;

import java.util.Date;
import lombok.Data;

@Data
public class Note {
    private Integer id;

    /**
    * 内容

    */
    private String content;

    /**
    * 到期日期
    */
    private Date endtime;

    /**
    * 备注
    */
    private String remarks;

    /**
    * 是否完成 0 否 1是
    */
    private Integer isDone;

    /**
    * 便签分组
    */
    private Integer noteGroup;

    /**
     * 用户id
     */
    private Integer userId;
}