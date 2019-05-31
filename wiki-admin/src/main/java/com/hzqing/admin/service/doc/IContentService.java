package com.hzqing.admin.service.doc;

import com.hzqing.admin.domain.doc.Content;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface IContentService {

    List<Content> selectList(Content content);

    int insert(Content content);

    int update(Content content);

}
