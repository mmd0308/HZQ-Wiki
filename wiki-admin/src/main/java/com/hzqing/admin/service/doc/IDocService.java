package com.hzqing.admin.service.doc;

import com.hzqing.admin.domain.doc.Doc;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.doc.DocDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:53
 */
public interface IDocService {

    List<Doc> selectList(Doc doc);

    int insert(Doc doc);

    int update(Doc doc);

    List<DocDto> selectListByID(Doc doc);

    List<DocDto> selectListByUserIdAndVL(Doc doc);

    DocDto get(int id);

    int deletedById(String id);
}
