package com.cg.utils.entity;

import lombok.Data;

/**
 * Date:    2016/2/1
 * Time:    14:40
 * details: 数据库信息
 */
@Data
public class Table {
	/**
	 * 实体类名
	 */
	private String columnName;
	/**
	 * 数据库类型
	 */
	private String dataType;
	/**
	 * 数据库注释
	 */
	private String columnComment;
	/**
	 * 首字母大写列名
	 */
	private String columnNameUpper;
	/**
	 * 数据无转换列名
	 */
	private String typeName;
}
