package com.hzqing.admin.model.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标签实体类
 * @author hzqing
 * @date 2020-01-17 14:49
 */
@Data
@Accessors(chain = true)
@TableName("hw_tag")
public class Tag {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
