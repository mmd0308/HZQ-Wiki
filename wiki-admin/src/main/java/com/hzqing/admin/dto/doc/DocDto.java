package com.hzqing.admin.dto.doc;

import com.hzqing.admin.domain.doc.Doc;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-21 16:05
 */
@Data
public class DocDto extends Doc {
    /**
     * 空间成员操作权限  1 浏览者 2 编辑者 3 管理员
     */
    private Integer privilege;
}
