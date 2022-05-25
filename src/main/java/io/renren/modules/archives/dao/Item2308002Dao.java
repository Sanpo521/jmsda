package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.Item2308001Entity;
import io.renren.modules.archives.entity.Item2308002Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-06 17:35:24
 */
@Mapper
public interface Item2308002Dao extends BaseMapper<Item2308002Entity> {
    List<Item2308002Entity> notMove();
}
