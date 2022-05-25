package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.Item2308001Entity;
import io.renren.modules.archives.entity.Item2308031Entity;
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
public interface Item2308031Dao extends BaseMapper<Item2308031Entity> {
    List<Item2308031Entity> notMove();
}
