package io.renren.modules.archives.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoxiubin
 * @create 2022-04-15 15:31
 * @desc MongoDB档案信息实体类
 *     spring-data-mongodb中的实体映射是通过MongoMappingConverter这个类实现的。它可以通过注释把java类转换为mongodb的文档。
 *
 *     它有以下几种注释：
 *
 *     @Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。
 *
 *     @Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。@Document(collection=“mongodb”) mongodb对应表
 *
 *     @DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存，如下面例子的Person和Account。
 *
 *     @Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。
 *
 *     @CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。
 *
 *     @GeoSpatialIndexed - 声明该字段为地理信息的索引。
 *
 *     @Transient - 映射忽略的字段，该字段不会保存到mongodb。
 *
 *     @PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据
 * ————————————————
 *     版权声明：本文为CSDN博主「架构师专栏」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 *     原文链接：https://blog.csdn.net/yanpenglei/article/details/79261875
 **/
@Data
@Document(collection = "docs")
public class DocsEntity implements Serializable {

    /**
     * docId mongoDB存储后产生
     */
    @Id
    private String id;
    /**
     * 卷号
     */
    private Integer dajh;
    /**
     * 档案状态 该方法已过期
     */
    private String dazt;
    /**
     * 应扫描页数
     */
    private Integer ysmys;
    /**
     * 备注
     */
    private String bz;
    /**
     * 企业名称
     */
    private String qymc;
    /**
     * 注册号
     */
    private String zch;
    /**
     * 法定代表人
     */
    private String fddbr;
    /**
     * 字号
     */
    private String zihao;
    /**
     * 主管机关
     */
    private String zgjg;
    /**
     * 操作员
     */
    private String czy;
    /**
     * 企业类型
     */
    private String qylx;
    /**
     * 企业类型代码
     */
    private String qylxDm;
    /**
     * 企业中类
     */
    private String qyzl;
    /**
     * 企业中类代码
     */
    private String qyzlDm;
    /**
     * 行业
     */
    private String hy;
    /**
     * 经营地址
     */
    private String jydz;
    /**
     * 企业描述
     */
    private String description;
    /**
     * 提交日期
     */
    private String tjrq;
    /**
     * 企业状态
     */
    private String qyzt;
    /**
     * 业务名称
     */
    private String ywmc;
    /**
     * 业务实例名称
     */
    private String ywslmc;
    /**
     * 业务ID
     */
    private Integer ywid;
    /**
     * 核准日期
     */
    private String hzrq;
    /**
     * 检查描述
     */
    private String checkDescription;
    /**
     * 检查人
     */
    private String jcr;
    /**
     * 检查日期
     */
    private String jcrq;
    /**
     * 归档人
     */
    private String gdr;
    /**
     * 归档日期
     */
    private String gdrq;
    /**
     * 统一社会信用代码
     */
    private String uniscid;
    /**
     * 资料分类
     */
    private List<ZlflEntity> zlfls;
}
