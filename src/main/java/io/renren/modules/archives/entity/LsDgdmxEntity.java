package io.renren.modules.archives.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
 * @date 2022-05-11 09:46:24
 */
@Data
@TableName("LS_DGDMX")
//@KeySequence(value = "seq_topeam")
public class LsDgdmxEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
//	@TableId
	private Long id;
	/**
	 * $column.comments
	 */
	private String zlmc;
	/**
	 * $column.comments
	 */
	private Integer ymq;
	/**
	 * $column.comments
	 */
	private Integer ymz;
	/**
	 * $column.comments
	 */
	private Long dgdywid;
	/**
	 * 目录来源 EAM 档案  ELE 电子化
	 */
	private String source;
	/**
	 * 业务类型  如果来源是电子化的，业务类型为电子化业务类型；如果来源是档案的，则为EAM
	 */
	private String bustype;
	/**
	 * 时间戳
	 */
	private Date modifytime;

}
