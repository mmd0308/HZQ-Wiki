package com.hzqing.admin.mapper.doc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.model.dto.doc.ContentDto;
import com.hzqing.admin.model.entity.doc.Content;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface ContentMapper  extends BaseMapper<Content> {
    List<ContentDto> selectListByDocId(@Param("docId") Integer docId);
}
