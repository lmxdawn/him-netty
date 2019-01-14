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
 	* 群昵称
 	*/
	private String name;
	/**
 	* 创建时间
 	*/
	private Date createTime;
	/**
 	* 更新时间
 	*/
	private Date modifiedTime;

}
