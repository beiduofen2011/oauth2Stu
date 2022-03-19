package com.fen.dou.service;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fen.dou.entity.*;
import com.fen.dou.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:认证服务器加载用户的类
*/
@Slf4j
@Component
public class UserDetailService implements UserDetailsService {

    /**
     * 方法实现说明:用户登陆
     */

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    //密码加密组件
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if(StringUtils.isEmpty(userName)) {
            log.warn("用户登陆用户名为空:{}",userName);
            throw new UsernameNotFoundException("用户名不能为空");
        }

        SysUser sysUser = getByUsername(userName);

        if(null == sysUser) {
            log.warn("根据用户名:{}查询用户信息为空",userName);
            throw new UsernameNotFoundException(userName);
        }
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUserRole::getUserId,sysUser.getId());
        List<SysUserRole> userRoleList = sysUserRoleMapper.selectList(queryWrapper);
        userRoleList.stream().forEach(x->{
            LambdaQueryWrapper<SysRolePermission> sysRolePermissionLambdaQueryWrapper = new LambdaQueryWrapper();
            sysRolePermissionLambdaQueryWrapper.eq(SysRolePermission::getRoleId,x.getRoleId());
            List<SysRolePermission> sysRolePermissionList = sysRolePermissionMapper.selectList(sysRolePermissionLambdaQueryWrapper);
            List<Integer> permisionIds = sysRolePermissionList.stream().map(y->y.getPermissionId()).collect(Collectors.toList());

            List<SysPermission> permissionList = sysPermissionMapper.selectBatchIds(permisionIds);
            permissionList.forEach(z->{
                authorityList.add(new SimpleGrantedAuthority(z.getUri()));
            });
        });

        AuthUser authUser = new AuthUser(sysUser.getUsername(),passwordEncoder.encode(sysUser.getPassword()),authorityList);
        authUser.setUserId(sysUser.getId());
        authUser.setNickName(sysUser.getNickname());
        authUser.setEmail(sysUser.getEmail());
        log.info("用户登陆成功:{}", JSON.toJSONString(authUser));

        return authUser;
    }

    /**
     * 方法实现说明:根据用户名获取用户信息
     */
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getUsername,username);
        List<SysUser> memberList = userMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }
}
