package com.hzqing.admin.converter.system;

import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.model.params.system.UserRegister;
import com.hzqing.admin.model.vo.system.UserMinimalVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 文章管理转换器
 * @author hzqing
 * @date 2019-08-13 21:24
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    User userRegisterToUser(UserRegister userRegister);

    List<UserMinimalVO> userToMinimalVO(List<User> userList);
}
