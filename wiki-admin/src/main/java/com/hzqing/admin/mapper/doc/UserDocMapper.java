package com.hzqing.admin.mapper.doc;

import com.hzqing.admin.domain.doc.UserDoc;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.doc.UserDocDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-21 14:47
 */
public interface UserDocMapper {

    List<UserDocDto> selectList(UserDoc userDoc);

    List<User> selectUserListBySID(int spaceId);

    int insert(UserDoc userDoc);

    int update(UserDoc userDoc);

    int deletedById(String id);
}
