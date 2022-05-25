package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.LsDgdmxEntity;
import io.renren.modules.archives.entity.TEamQueryallEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 电子档案全量查询表
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:47:43
 */
@Mapper
public interface TEamQueryallDao extends BaseMapper<TEamQueryallEntity> {
    void saveEx(TEamQueryallEntity queryall);

    void delEx(TEamQueryallEntity queryall);
}
