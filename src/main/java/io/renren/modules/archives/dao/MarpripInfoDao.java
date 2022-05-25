package io.renren.modules.archives.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.archives.entity.MarpripInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarpripInfoDao extends BaseMapper<MarpripInfoEntity> {
    MarpripInfoEntity getEntInfoByEntname(String entName);

    MarpripInfoEntity getEntInfoByRegno(String regNo);
}
