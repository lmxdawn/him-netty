package com.lmxdawn.him.common.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * table:user_qq  
 */
@Data
public class UserQq {

	private Integer id;
	/**
 	* 用户uid（关联 user 表）
 	*/
	private Long uid;
	/**
 	* 用户openID
 	*/
	private String openid;
	/**
 	* 创建时间
 	*/
	private Date createTime;

}
