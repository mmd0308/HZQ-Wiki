package com.hzqing.admin.model.params;

import lombok.Data;

import java.util.List;

/**
 * 发布文章需要传递的参数
 * @author hzqing
 * @date 2020-01-19 10:56
 */
@Data
public class ArticleRelease {


    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章的摘要
     */
    private String hwDesc;

    /**
     * 封装的该文章的tagId
     */
    private List<Integer> tagIds;

}
