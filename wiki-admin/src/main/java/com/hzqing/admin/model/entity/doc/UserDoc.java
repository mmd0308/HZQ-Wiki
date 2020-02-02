package com.hzqing.admin.model.entity.doc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.admin.model.entity.base.BaseEntity;
import com.hzqing.admin.model.enums.doc.UserDocPrivilege;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * 用户跟文档的关系实体类
 * @author hzqing
 * @date 2019-05-21 14:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hw_user_doc")
public class UserDoc extends BaseEntity {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    @TableField("user_id")
    private int userId;

    @TableField("doc_id")
    private int docId;

    private UserDocPrivilege privilege;

    @TableField("create_by")
    private int createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 到期时间
     */
    @TableField("expire_time")
    private LocalDate expireTime;

}
