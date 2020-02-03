package com.hzqing.admin.service.doc;


import com.hzqing.admin.model.dto.doc.ContentDto;
import com.hzqing.admin.model.entity.doc.Content;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface IContentService {

    List<Content> selectList(Content content);

    int insert(Content content);

    int update(Content content);

    int deletedById(Integer id);


    Content get(int id);


    /**
     * 根据文档id，获取所有的目录
     * @param docId
     * @return
     */
    List<ContentDto> getListAllByDocId(Integer docId);

    /**
     * 根据Id获取
     * @param id 文档内容id
     * @return
     */
    Content getById(int id);

    /**
     * 创建
     * @param content
     * @return
     */
    int create(Content content);

    /**
     * 根据id更新
     * @param article
     */
    void modifyById(Content content);
}
