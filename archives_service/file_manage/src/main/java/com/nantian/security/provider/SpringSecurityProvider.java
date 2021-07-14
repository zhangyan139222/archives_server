/*
package com.nantian.security.provider;

import com.nantian.security.userservice.AuthUserDetails;
import com.nantian.security.userservice.CustomUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

*/
/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 9:38
 * @Description:自定义省份验证提供者
 *//*

@Component
public class SpringSecurityProvider   implements AuthenticationProvider {
    @Autowired
    private CustomUserService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 查询用户权限信息
        AuthUserDetails userInfo = (AuthUserDetails) userDetailService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("");
        }

        // 密码判断
        String encodePwd = DigestUtils.md5Hex(password).toUpperCase();
        if (!userInfo.getPassword().equals(encodePwd)) {
            throw new BadCredentialsException("");
        }

        return new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(),
                userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }



}
*/
