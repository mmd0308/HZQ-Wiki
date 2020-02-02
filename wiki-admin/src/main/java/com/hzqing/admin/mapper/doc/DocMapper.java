package com.hzqing.admin.mapper.doc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.model.entity.doc.Doc;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:55
 */
public interface DocMapper  extends BaseMapper<Doc> {
    List<Doc> selectList(Doc doc);

    int update(Doc doc);

    int insert(Doc doc);

    List<DocDto> selectListByID(Doc doc);

    List<DocDto> selectListByUserIdAndVL(Doc doc);

    DocDto get(int id);

    int deletedById(String id);

    DocDto selectByIDandUserId(Doc doc);

    Integer selectPrivilegeById(MemberDto memberDto);
}
