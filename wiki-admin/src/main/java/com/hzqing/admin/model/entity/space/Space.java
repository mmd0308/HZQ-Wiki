package com.hzqing.admin.model.entity.space;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.SimpleBaseEntity;
import com.hzqing.admin.model.enums.space.SpaceVisitLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *  空间实体类
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_space")
public class Space extends SimpleBaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String remark;

    /**
     * 空间访问级别
     */
    @TableField("visit_level")
    private SpaceVisitLevel visitLevel;

}
