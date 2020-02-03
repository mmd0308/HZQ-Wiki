package com.hzqing.admin.converter.doc;

import com.hzqing.admin.model.dto.doc.ContentDto;
import com.hzqing.admin.model.vo.doc.ContentMinimalVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 文章管理转换器
 * @author hzqing
 * @date 2019-08-13 21:24
 */
@Mapper(componentModel = "spring")
public interface ContentConverter {

    List<ContentMinimalVO> listContentDtoToListMinimalVo(List<ContentDto> contents);
}
