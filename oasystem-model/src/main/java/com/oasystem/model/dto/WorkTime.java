package com.oasystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(value = "设定工作时间条件", description = "设定工作时间条件")
public class WorkTime implements Serializable {

    @ApiModelProperty(value = "开始时间", name = "currentDate", example = "1")
    private String beginTime;

    @ApiModelProperty(value = "结束时间", name = "isWork", example = "1")
    private String endTime;
}
