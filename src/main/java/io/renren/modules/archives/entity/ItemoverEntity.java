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
 * @date 2022-05-09 12:38:30
 */
@Data
@TableName("move_itemover")
public class ItemoverEntity implements Serializable {
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
