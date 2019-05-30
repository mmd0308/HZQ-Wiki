package com.hzqing.admin.domain.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 14:08
 */
@Data
public class TreeNode extends Base{

    private Integer id;

    private Integer parentId;

    private List<TreeNode> children = new ArrayList<>();
}
