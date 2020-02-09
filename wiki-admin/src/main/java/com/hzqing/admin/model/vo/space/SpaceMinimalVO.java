package com.hzqing.admin.model.vo.space;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author hzqing
 * @date 2020-02-09 17:25
 */
@Data
@ToString
public class SpaceMinimalVO implements Serializable {

    private Integer id;

    private String name;
}
