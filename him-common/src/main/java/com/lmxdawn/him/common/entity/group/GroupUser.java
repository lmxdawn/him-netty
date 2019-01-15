package com.lmxdawn.him.common.entity.group;

import lombok.Data;

import java.util.Date;

/**
 * table:group_user  
 */
@Data
public class GroupUser {

	/**
 	* 自增ID
 	*/
	private Long id;
	/**
 	* 群ID
 	*/
	private Long groupId;
	/**
 	* 用户ID
 	*/
	private Long uid;
	/**
 	* 群里的备注
 	*/
	private String remark;
	/**
 	* 最后一次确认的消息ID
 	*/
	private Long lastAckMsgId;
	/**
 	* 创建时间
 	*/
	private Date createTime;
	/**
 	* 创建时间
 	*/
	private Date modifiedTime;

}
