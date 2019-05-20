package com.hzqing.admin.domain.space;

import com.hzqing.admin.domain.base.Base;
import lombok.Data;

/**
 * @author hzqing
 * @date 2019-05-20 08:26
 */
@Data
public class Space extends Base {

    private int id;

    private String name;

    private String remark;

    private int visitLevel;

}
