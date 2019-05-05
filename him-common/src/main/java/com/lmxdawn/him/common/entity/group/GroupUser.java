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
 	* 最后一次的消息内容
 	*/
	private String lastMsgContent;
	/**
 	* 最后一次的消息时间
 	*/
	private Date lastMsgTime;
	/**
 	* 未读消息数量
 	*/
	private Integer unMsgCount;
	/**
 	* 等级（0：普通成员，1：管理员，2：群主）
 	*/
	private Integer rank;
	/**
 	* 创建时间
 	*/
	private Date createTime;
	/**
 	* 创建时间
 	*/
	private Date modifiedTime;

}
