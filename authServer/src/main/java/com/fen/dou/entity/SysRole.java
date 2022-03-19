package com.fen.dou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述 角色表
* @author: smlz
* @createDate: 2019/12/20 13:48
* @version: 1.0
*/
@Data
@Builder
@TableName(value = "sys_role")//指定表名
public class SysRole implements Serializable {
    private static final long serialVersionUID = -7136537864183138269L;

    private Integer id;

    private String roleName;

    private String roleCode;

    private String roleDescription;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
