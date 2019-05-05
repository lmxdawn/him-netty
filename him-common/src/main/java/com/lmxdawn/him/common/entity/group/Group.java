package com.lmxdawn.him.common.entity.group;

import lombok.Data;

import java.util.Date;

/**
 * table:group  
 */
@Data
public class Group {

	/**
 	* 群ID
 	*/
	private Long groupId;
	/**
 	* 创建者用户ID
 	*/
	private Long uid;
	/**
 	* 群昵称
 	*/
	private String name;
	/**
 	* 群头像
 	*/
	private String avatar;
	/**
 	* 成员数量
 	*/
	private Integer memberNum;
	/**
 	* 描述
 	*/
	private String remark;
	/**
 	* 创建时间
 	*/
	private Date createTime;
	/**
 	* 更新时间
 	*/
	private Date modifiedTime;

}
