/*
package com.nantian.security.userservice;

import com.nantian.security.mapper.*;
import com.nantian.security.service.*;
import com.natian.entity.security.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author zhangyan
 * @version 1.0
 * @date 2021/6/15 10:08
 * @Description:自定义用户查询服务
 *//*

@Component
public class CustomUserService  implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
   */
/* @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleFunctionMapper roleFunctionMapper;*//*

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userMapper.findByUserName(username);
        if(user != null){
        List<Permission> permissions = permissionMapper.PermissionListByUser(user.getUserId());
        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();
        for(Permission  permission:permissions){
            if(permission!=null && StringUtils.isNotEmpty(permission.getFunctionId())){
               GrantedAuthority  grantedAuthority=  new SimpleGrantedAuthority(permission.getFunctionId());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorityList.add(grantedAuthority);
            }
        }
        return new AuthUserDetails(user.getUserId(),user.getPwd(),grantedAuthorityList);
        }else{

            throw new UsernameNotFoundException("admin: " + username + " do not exist!");

        }
    }


}
*/
