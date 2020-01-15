package com.hzqing.admin.service.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.entity.article.Article;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:53
 */
public interface IArticleService {
    /**
     * 查询所有
     * @param article
     * @return
     */
    List<Article> getList(Article article);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Article getById(int id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int removedById(int id);


    /**
     * 根据条件获取分页数据
     * @param num
     * @param size
     * @param article
     * @return
     */
    Page<Article> getPage(int num, int size, Article article);

    /**
     * 新增
     * 返回新增的数据id
     * @param article
     */
    int create(Article article);

    /**
     * 根据id，修改文章
     * @param article
     */
    void modifyById(Article article);
}
