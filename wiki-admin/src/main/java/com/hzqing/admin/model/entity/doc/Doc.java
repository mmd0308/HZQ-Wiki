package com.hzqing.admin.model.entity.doc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author hzqing
 * @date 2019-05-17 09:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_doc")
public class Doc extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文档名称
     */
    private String name;

    /**
     * 空间id
     */
    @TableField("space_id")
    private Integer spaceId;

    /**
     * 文档备注说明
     */
    private String remark;

    /**
     * 访问级别 0 私有 1 公开
     */
    @TableField("visit_level")
    private Integer visitLevel;

    /**
     * 文档封面图片
     */
    private String img;



}
