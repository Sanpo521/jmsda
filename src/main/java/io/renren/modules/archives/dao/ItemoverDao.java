package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.ItemoverEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-09 12:38:30
 */
@Mapper
public interface ItemoverDao extends BaseMapper<ItemoverEntity> {
    void saveEx(ItemoverEntity itemover);

    ItemoverEntity selectByTybm(ItemoverEntity itemover);
}
