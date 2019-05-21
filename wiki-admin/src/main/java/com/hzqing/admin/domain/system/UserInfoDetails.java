package com.hzqing.admin.domain.system;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
/**
 * @author hzqing
 * @date 2019-05-21 10:47
 */
@Data
public class UserInfoDetails implements UserDetails {
    /**
     * 用户id
     */
    int userId;

    /**
     * 用户登陆名称
     */
    String username;

    /**
     * 用户登陆密码
     */
    String password;

    /**
     * 所有的资源id
     */
    List<GrantedAuthority> authorities;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;


    public UserInfoDetails() {
    }

    public UserInfoDetails(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserInfoDetails(int userId, String username, String password, List<GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
