package com.hzqing.admin.mapper.doc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzqing.admin.dto.doc.UserDocDto;
import com.hzqing.admin.model.entity.doc.UserDoc;
import com.hzqing.admin.model.entity.system.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-21 14:47
 */
@Repository
public interface UserDocMapper  extends BaseMapper<UserDoc> {
    List<UserDocDto> selectList(UserDoc userDoc);

    List<User> selectUserListBySID(int spaceId);


    int update(UserDoc userDoc);

    int deletedById(String id);
}
