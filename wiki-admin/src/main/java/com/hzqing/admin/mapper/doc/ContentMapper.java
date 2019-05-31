package com.hzqing.admin.mapper.doc;

import com.hzqing.admin.domain.doc.Content;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface ContentMapper {
    List<Content> selectList(Content content);

    int update(Content content);

    int insert(Content content);

}
