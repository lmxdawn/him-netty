package com.lmxdawn.him.common.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * table:user_friend_ask  
 */
@Data
public class UserFriendAsk {

	/**
 	* 自增ID
 	*/
	private Long id;
	/**
 	* 用户ID
 	*/
	private Long uid;
	/**
 	* 发送消息的用户ID
 	*/
	private Long friendUid;
	/**
 	* 备注信息
 	*/
	private String remark;
	/**
 	* 状态（0：未确认过，1：已确认, 2: 已拒绝）
 	*/
	private Integer status;
	private Date createTime;
	private Date modifiedTime;

}
