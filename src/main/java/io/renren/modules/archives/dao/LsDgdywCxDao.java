package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.LsDgdywCxEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:46:14
 */
@Mapper
public interface LsDgdywCxDao extends BaseMapper<LsDgdywCxEntity> {
    Long generatedKey();

    void insertByDgdyw(Long id);
}
