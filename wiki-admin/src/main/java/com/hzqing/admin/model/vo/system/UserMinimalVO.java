package com.hzqing.admin.model.vo.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author hzqing
 * @date 2020-02-05 15:55
 */
@Data
@ToString
public class UserMinimalVO implements Serializable {

    private Integer id;

    private String name;
}
