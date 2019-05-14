package com.lmxdawn.him.common.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * table:user_friend_msg  
 */
@Data
public class UserFriendMsg {

	/**
 	* 消息ID
 	*/
	private Long msgId;
	/**
 	* 用户ID
 	*/
	private Long uid;
	/**
 	* 和上面的uid做查询用
 	*/
	private Long toUid;
	/**
 	* 发送方用户ID
 	*/
	private Long senderUid;
	/**
 	* 消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）
 	*/
	private Integer msgType;
	/**
 	* 消息内容
 	*/
	private String msgContent;
	/**
 	* 消息创建时间
 	*/
	private Date createTime;

}
