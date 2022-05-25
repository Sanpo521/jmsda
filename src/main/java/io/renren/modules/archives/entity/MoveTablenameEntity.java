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
 * @date 2022-04-14 13:51:23
 */
@Data
@TableName("move_tablename")
public class MoveTablenameEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.comments
     */
    @TableId
    private String tablename;
    /**
     * $column.comments
     */
    private Integer state;
    /**
     * $column.comments
     */
    private String path;

}
