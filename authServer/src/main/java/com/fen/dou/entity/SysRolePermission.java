package com.fen.dou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:角色资源映射表,一个角色可以访问多个资源,一个资源可以被多个角色访问
*/
@Data
@Builder
@TableName(value = "sys_role_permission")//指定表名
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 7402412601579098788L;
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;

    private Integer roleId;

    private Integer permissionId;
}
