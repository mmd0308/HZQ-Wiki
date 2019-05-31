package com.hzqing.admin.service.doc;

import com.hzqing.admin.domain.doc.Catalog;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
public interface ICatalogService {

    List<Catalog> selectList(Catalog catalog);

    int insert(Catalog catalog);

    int update(Catalog catalog);

}
