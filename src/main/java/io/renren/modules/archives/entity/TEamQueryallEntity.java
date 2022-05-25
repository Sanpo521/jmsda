package io.renren.modules.archives.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 电子档案全量查询表
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:47:43
 */
@Data
@TableName("T_EAM_QUERYALL")
public class TEamQueryallEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 注册号
	 */
//	@TableId
	private String zch;
	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * 法定代表人
	 */
	private String fddbr;
	/**
	 * 主体ID
	 */
	private String marprid;
	/**
	 * 机构ID
	 */
	private String orgid;

}
