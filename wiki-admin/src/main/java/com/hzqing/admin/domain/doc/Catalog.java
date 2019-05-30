package com.hzqing.admin.domain.doc;

import com.hzqing.admin.domain.base.Base;
import com.hzqing.admin.domain.base.TreeNode;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-30 09:42
 */
@Data
public class Catalog extends TreeNode {

    private Integer docId;

    private String  name;

    /**
     * 排序
     */
    private Integer sequence;

}
