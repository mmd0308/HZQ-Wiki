package com.hzqing.admin.service.doc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.model.entity.doc.Doc;

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


    DocDto selectByIDandUserId(Doc doc);

    Integer selectPrivilegeById(MemberDto memberDto);

    /**
     * 首页展示，根据空间id及级别获取分页的文档
     * @param num
     * @param size
     * @param doc
     * @return
     */
    Page<Doc> getPageBySpaceOrLevel(int num, int size, Doc doc);
}
