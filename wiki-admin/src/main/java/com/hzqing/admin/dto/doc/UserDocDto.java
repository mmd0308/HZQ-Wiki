package com.hzqing.admin.dto.doc;

import com.hzqing.admin.domain.doc.UserDoc;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-06-05 13:24
 */
@Data
public class UserDocDto extends UserDoc {
    /**
     * 用户名称
     */
    private String fullName;
}
