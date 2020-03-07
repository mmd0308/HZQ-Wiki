package com.hzqing.admin.mapper.space;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.model.dto.space.SpaceDto;
import com.hzqing.admin.model.entity.space.Space;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 衡钊清
 * @Description 空间管理Mapper接口
 * @Date 2020/3/7 09:08
 **/
@Repository
public interface SpaceMapper  extends BaseMapper<Space> {

    List<SpaceDto> selectListByUserId(Space space);

    IPage<SpaceDto> selectPageList(Page<Object> objectPage, SpaceDto spaceDto);
}
