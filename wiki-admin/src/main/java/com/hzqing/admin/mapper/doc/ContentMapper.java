package com.hzqing.admin.mapper.doc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.domain.doc.Content;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface ContentMapper  extends BaseMapper<Content> {
    List<Content> selectList(Content content);

    int update(Content content);

    int insert(Content content);

    int deletedById(Integer id);

    Content get(int id);
}
