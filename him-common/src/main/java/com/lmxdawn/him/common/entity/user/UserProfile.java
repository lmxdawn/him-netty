package com.lmxdawn.him.common.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * table:user_profile  
 */
@Data
public class UserProfile {

	/**
 	* 用户的消息配置表
 	*/
	private Long id;
	/**
 	* 用户ID
 	*/
	private Long uid;
	/**
 	* 好友请求的数量
 	*/
	private Integer friendAskCount;
	/**
 	* 好友数量
 	*/
	private Integer friendCount;
	private Date createTime;
	private Date modifiedTime;

}
