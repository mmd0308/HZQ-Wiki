package com.hzqing.admin.service.space;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.dto.space.SpaceDto;
import com.hzqing.admin.model.entity.space.Space;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
public interface ISpaceService {
    List<SpaceDto> selectList(Space space);




    /**
     * 根据用户id，获取该用户的所有空间
     * @param space
     * @return
     */
    List<SpaceDto> selectListByUserId(Space space);

    /**
     * 获取分页信息
     * @param num
     * @param size
     * @param spaceDto
     * @return
     */
    Page<SpaceDto> getPage(int num, int size, SpaceDto spaceDto);

    /**
     * 创建空间
     * @param space
     * @return 返回新增数据的id
     */
    int create(Space space);

    /**
     * 根据id修改空间
     * @param space
     */
    void modifyById(Space space);

    /**
     * 根据id删除数据
     * @param id
     * @return 影响的行数
     */
    int removedById(int id);

    /**
     * 获取所有的文档
     * @return
     */
    List<Space> getListAll();


}
