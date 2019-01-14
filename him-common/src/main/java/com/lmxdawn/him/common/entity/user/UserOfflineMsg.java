package com.lmxdawn.him.common.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * table:user_offline_msg  
 */
@Data
public class UserOfflineMsg {

	/**
 	* 消息ID
 	*/
	private Long msgId;
	/**
 	* 消息接收方
 	*/
	private Long receiverUid;
	/**
 	* 发送方用户ID
 	*/
	private Long senderUid;
	/**
 	* 消息类型 ( 0: 文本消息 )
 	*/
	private Long msgType;
	/**
 	* 消息内容
 	*/
	private String msgContent;
	/**
 	* 消息创建时间
 	*/
	private Date createTime;

}
