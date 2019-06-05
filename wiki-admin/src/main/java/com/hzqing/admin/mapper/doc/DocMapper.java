package com.hzqing.admin.mapper.doc;

import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.doc.DocDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:55
 */
public interface DocMapper {
    public List<Doc> selectList(Doc doc);

    int update(Doc doc);

    int insert(Doc doc);

    List<DocDto> selectListByID(Doc doc);

    List<DocDto> selectListByUserIdAndVL(Doc doc);
}
