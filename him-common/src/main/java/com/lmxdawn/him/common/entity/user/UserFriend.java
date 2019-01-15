package com.lmxdawn.him.common.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * table:user_friend  
 */
@Data
public class UserFriend {

	/**
 	* 自增id
 	*/
	private Long id;
	/**
 	* 用户id
 	*/
	private Long uid;
	/**
 	* 朋友的用户id
 	*/
	private Long friendUid;
	/**
 	* 备注
 	*/
	private String remark;
	/**
 	* 创建时间
 	*/
	private Date createTime;
	/**
 	* 更新的时间
 	*/
	private Date modifiedTime;

}
