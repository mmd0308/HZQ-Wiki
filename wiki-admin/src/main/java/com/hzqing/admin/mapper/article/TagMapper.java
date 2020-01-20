package com.hzqing.admin.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.model.entity.article.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:50
 */
public interface TagMapper extends BaseMapper<Tag> {


    List<Tag> selectByNames(@Param("tagNames") List<String> tagNames);

    void insertForBatch(List<Tag> tags);
}
