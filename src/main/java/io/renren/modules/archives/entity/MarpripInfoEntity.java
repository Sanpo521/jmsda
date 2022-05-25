package io.renren.modules.archives.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhaoxiubin
 * @create 2022-05-09 10:26
 * @desc 企业主体实体类
 **/
@Data
public class MarpripInfoEntity implements Serializable {
    private Long marprid;
    private String entname;
    private String regno;
    private String lerep;
    private String dom;
    private String enttra;
    private String uniscid;
    private Date apprdate;
    private String enttype;
    private String enttypename;
    private String enttypeitemname;
    private String industryconame;
    private String statename;
    private String zgjg;
    private String djjg;
    private Integer smjh;
}
