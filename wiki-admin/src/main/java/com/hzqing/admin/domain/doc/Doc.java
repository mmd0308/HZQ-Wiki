package com.hzqing.admin.domain.doc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hzqing
 * @date 2019-05-17 09:53
 */
@Data
public class Doc implements Serializable {

    private int id;

    private String name;

    private int spaceId;

    private String remark;

    private int visitLevel;

    private String img;

    private int createBy;

    private LocalDateTime createTime;

    private int updateBy;

    private LocalDateTime updateTime;


}
