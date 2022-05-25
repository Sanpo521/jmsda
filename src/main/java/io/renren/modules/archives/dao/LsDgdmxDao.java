package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.LsDgdmxEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.archives.entity.LsDgdywEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:46:24
 */
@Mapper
public interface LsDgdmxDao extends BaseMapper<LsDgdmxEntity> {
    void saveEx(LsDgdmxEntity lsDgdmx);
}
