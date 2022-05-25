package io.renren.modules.archives.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ${comments}
 * 
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-06 17:35:25
 */
@Data
@TableName("tab_Item_230802_1")
public class Item2308021Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private String tybm;
	/**
	 * $column.comments
	 */
	private Integer flow;
	/**
	 * $column.comments
	 */
	private Integer status;
	/**
	 * $column.comments
	 */
	private String path;
	/**
	 * $column.comments
	 */
	private String field1;
	/**
	 * $column.comments
	 */
	private String field2;
	/**
	 * $column.comments
	 */
	private String field3;

}
