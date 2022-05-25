package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.Item2308001Entity;
import io.renren.modules.archives.entity.Item2308111Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-06 17:35:25
 */
@Mapper
public interface Item2308111Dao extends BaseMapper<Item2308111Entity> {
    List<Item2308111Entity> notMove();
}
