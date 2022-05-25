package io.renren.modules.archives.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoxiubin
 * @create 2022-04-15 14:26
 * @desc MongoDB中资料目录实体类
 **/
@Data
public class ZlflEntity implements Serializable {
    /**
     * 分类名称
     */
    private String flmc;
    /**
     * 分类顺序
     */
    private Integer flsx;
    /**
     * 描述
     */
    private String description;
    /**
     * 档案起始页号
     */
    private Integer dayh;
    /**
     * 档案数量
     */
    private Integer dasl;

    /**
     * 目录密级
     */
    private String tlevel;
}
