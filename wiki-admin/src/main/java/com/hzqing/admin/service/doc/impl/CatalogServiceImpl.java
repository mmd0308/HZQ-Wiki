package com.hzqing.admin.service.doc.impl;

import com.hzqing.admin.domain.doc.Catalog;
import com.hzqing.admin.mapper.doc.CatalogMapper;
import com.hzqing.admin.service.doc.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-30 09:45
 */
@Service
public class CatalogServiceImpl implements ICatalogService {
    @Autowired
    private CatalogMapper catalogMapper;

    @Override
    public List<Catalog> selectList(Catalog catalog) {
        return catalogMapper.selectList(catalog);
    }

    @Override
    public int insert(Catalog catalog) {
        return catalogMapper.insert(catalog);
    }

    @Override
    public int update(Catalog catalog) {
        return catalogMapper.update(catalog);
    }

}
