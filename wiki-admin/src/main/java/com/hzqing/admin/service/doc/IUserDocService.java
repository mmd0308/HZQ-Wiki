package com.hzqing.admin.service.doc;

import com.hzqing.admin.domain.doc.UserDoc;
import com.hzqing.admin.domain.space.UserSpace;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.dto.doc.UserDocDto;
import com.hzqing.admin.dto.space.UserSpaceDto;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-20 08:32
 */
public interface IUserDocService {

    List<UserDocDto> selectList(UserDoc userDoc);

    List<User> selectUserListBySID(int spaceId);

    int insert(UserDoc userDoc);

    int update(UserDoc userDoc);

    int deletedById(String id);
}
