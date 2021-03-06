package com.hzqing.admin.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.model.entity.system.Config;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:55
 */
@Repository
public interface ConfigMapper extends BaseMapper<Config> {

    List<Config> selectList();

    int updateBatch(List<Config> toList);
}
