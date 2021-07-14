/*
package com.nantian.security.userservice;


import com.natian.entity.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:14
 * @Description: 用户信息扩张类
 *//*


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
public class AuthUserDetails  implements UserDetails {
    private String username;
    private String pswd;

//    private List<Role> roles;
//    private List<Permission> permissions;

    private List<GrantedAuthority>  grantedAuthorities;


   */
/* public AuthUserDetails(String username, String pswd, List<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.pswd = pswd;
        this.grantedAuthorities = grantedAuthorities;
    }*//*


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return pswd;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
*/
