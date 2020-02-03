package com.hzqing.admin.service.doc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.model.dto.doc.DocDto;
import com.hzqing.admin.model.entity.doc.Doc;
import com.hzqing.admin.model.entity.space.Space;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:53
 */
public interface IDocService {

    List<Doc> selectList(Doc doc);



    List<DocDto> selectListByID(Doc doc);

    List<DocDto> selectListByUserIdAndVL(Doc doc);

    DocDto get(int id);



    DocDto selectByIDandUserId(Doc doc);

    Integer selectPrivilegeById(MemberDto memberDto);

    /**
     * 首页展示，根据空间id及级别获取分页的文档
     * @param num
     * @param size
     * @param doc
     * @return
     */
    Page<DocDto> getPageBySpaceOrLevel(int num, int size, Doc doc);

    /**
     * 文档分页数据
     * @param num
     * @param size
     * @param doc
     * @return
     */
    Page<Doc> getPage(int num, int size, Doc doc);

    /**
     * 新增空间
     * @param doc
     * @return
     */
    int create(Doc doc);

    /**
     * 根据id进行更新
     * @param doc
     */
    void modifyById(Doc doc);

    /**
     * 根据id进行删除
     * @param id
     * @return
     */
    int removedById(int id);

    /**
     * 根据id获取数据
     * @param id
     * @return
     */
    Doc getById(int id);
}
