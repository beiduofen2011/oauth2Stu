package com.fen.dou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:用户角色关联表(一个用户可以有多个角色，一个角色可以分配给多个用户)
*/
@Data
@Builder
@TableName(value = "sys_user_role")//指定表名
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -1810195806444298544L;
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;

    private Integer userId;

    private Integer roleId;
}
