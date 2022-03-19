package com.fen.dou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:资源表
* @author: smlz
* @createDate: 2019/12/20 14:02
* @version: 1.0
*/
@Data
@Builder
@TableName(value = "sys_permission")//指定表名
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 4285835478693487481L;
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;

    private Integer pid;

    private Integer type;

    private String name;

    private String code;

    private String uri;

    private Integer seq = 1;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

}
