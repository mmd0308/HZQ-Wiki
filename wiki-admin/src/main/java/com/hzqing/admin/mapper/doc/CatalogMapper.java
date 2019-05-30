package com.hzqing.admin.mapper.doc;

import com.hzqing.admin.domain.doc.Catalog;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface CatalogMapper {
    List<Catalog> selectList(Catalog catalog);

    int update(Catalog catalog);

    int insert(Catalog catalog);

}
