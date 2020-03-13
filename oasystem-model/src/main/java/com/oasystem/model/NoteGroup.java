package com.oasystem.model;

import lombok.Data;

@Data
public class NoteGroup {
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
     * 用户id
     */
    private Integer userId;
}