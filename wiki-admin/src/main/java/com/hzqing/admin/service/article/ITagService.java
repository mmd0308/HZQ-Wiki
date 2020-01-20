package com.hzqing.admin.service.article;

import com.hzqing.admin.model.entity.article.Article;
import com.hzqing.admin.model.entity.article.Tag;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:53
 */
public interface ITagService {
    /**
     * 查询所有
     * @param tag
     * @return
     */
    List<Article> getList(Tag tag);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    int removedById(int id);


    /**
     * 新增
     * 返回新增的数据id
     * @param tag
     */
    int create(Tag tag);

    /**
     * 根据id，修改
     * @param tag
     */
    void modifyById(Tag tag);

    /**
     * 获取所有的数据
     * @return
     */
    List<Tag> getListAll();

    /**
     * 根据标签名称，批量创建标签
     * @param tagNames
     */
    void createForBatchByNames(List<String> tagNames);

    /**
     * 根据标签名称，获取所有的标签
     * @param tagNames
     * @return
     */
    List<Tag> getListByNames(List<String> tagNames);
}
