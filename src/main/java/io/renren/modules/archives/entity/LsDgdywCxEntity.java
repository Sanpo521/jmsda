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
 * @date 2022-05-11 09:46:14
 */
@Data
@TableName("LS_DGDYW_CX")
public class LsDgdywCxEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private Long id;
	/**
	 * $column.comments
	 */
	private String zch;
	/**
	 * $column.comments
	 */
	private String qymc;
	/**
	 * $column.comments
	 */
	private String fddbr;
	/**
	 * $column.comments
	 */
	private String qylx;
	/**
	 * $column.comments
	 */
	private String hy;
	/**
	 * $column.comments
	 */
	private String jydz;
	/**
	 * $column.comments
	 */
	private String qylxdm;
	/**
	 * $column.comments
	 */
	private String zgjg;
	/**
	 * $column.comments
	 */
	private String jcr;
	/**
	 * $column.comments
	 */
	private Date jcrq;
	/**
	 * $column.comments
	 */
	private String ywsllj;
	/**
	 * $column.comments
	 */
	private String qylj;
	/**
	 * $column.comments
	 */
	private Integer yxbz;
	/**
	 * $column.comments
	 */
	private String ywlx;
	/**
	 * $column.comments
	 */
	private Date hzrq;
	/**
	 * $column.comments
	 */
	private Integer dasl;
	/**
	 * $column.comments
	 */
	private Integer jh;
	/**
	 * $column.comments
	 */
	private String zmldyr;
	/**
	 * $column.comments
	 */
	private Date zmldyrq;
	/**
	 * $column.comments
	 */
	private String zmldyjg;
	/**
	 * $column.comments
	 */
	private String sfdy;
	/**
	 * $column.comments
	 */
	private String djjg;
	/**
	 * $column.comments
	 */
	private String dalx;
	/**
	 * $column.comments
	 */
	private Date qysj;
	/**
	 * $column.comments
	 */
	private String jnmldyr;
	/**
	 * $column.comments
	 */
	private Date jnmldyrq;
	/**
	 * $column.comments
	 */
	private String jnmldyjg;
	/**
	 * $column.comments
	 */
	private String qyjg;
	/**
	 * $column.comments
	 */
	private Integer archid;
	/**
	 * $column.comments
	 */
	private Integer ysmys;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	private String smjzt;
	/**
	 * $column.comments
	 */
	private Integer smjh;
	/**
	 * $column.comments
	 */
	private String zihao;
	/**
	 * $column.comments
	 */
	private String ywmc;
	/**
	 * $column.comments
	 */
	private String ywslmc;
	/**
	 * $column.comments
	 */
	private Integer ywid;
	/**
	 * $column.comments
	 */
	private Integer marprid;
	/**
	 * $column.comments
	 */
	private String bgflx;
	/**
	 * $column.comments
	 */
	private String bgnr;
	/**
	 * $column.comments
	 */
	private String daxh;
	/**
	 * $column.comments
	 */
	private String uniscid;
	/**
	 * $column.comments
	 */
	private String reelcode;
	/**
	 * $column.comments
	 */
	private Integer reelorder;
	/**
	 * $column.comments
	 */
	private String organcode;
	/**
	 * 时间戳
	 */
	private Date modifytime;

}
