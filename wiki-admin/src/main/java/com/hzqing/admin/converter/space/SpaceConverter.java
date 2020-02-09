package com.hzqing.admin.converter.space;

import com.hzqing.admin.model.entity.space.Space;
import com.hzqing.admin.model.vo.space.SpaceMinimalVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 空间管理转换器
 * @author hzqing
 * @date 2019-08-13 21:24
 */
@Mapper(componentModel = "spring")
public interface SpaceConverter {

    List<SpaceMinimalVO> listSpaceToListMinimal(List<Space> spaces);
}
