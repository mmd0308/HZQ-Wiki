package com.hzqing.admin.dto.space;

import com.hzqing.admin.model.entity.space.UserSpace;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-06-05 13:24
 */
@Data
public class UserSpaceDto extends UserSpace {
    /**
     * 用户名称
     */
    private String fullName;
}
