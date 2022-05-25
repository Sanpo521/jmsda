package io.renren.modules.archives.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-09 12:38:30
 */
@Data
@TableName("move_itemoverfile")
public class ItemoverFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
//	@TableId
	private String tybm;
	/**
	 * $column.comments
	 */
	private String state;

}
