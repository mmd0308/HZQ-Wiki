package com.hzqing.admin.dto.space;

import com.hzqing.admin.domain.space.Space;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-21 16:05
 */
@Data
public class SpaceDto extends Space {
    /**
     * 空间成员操作权限  1 浏览者 2 编辑者 3 管理员
     */
    private Integer privilege;
}
