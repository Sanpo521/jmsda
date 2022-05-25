package io.renren.modules.archives.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.archives.entity.ItemoverFileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-09 12:38:30
 */
@Mapper
public interface ItemoverFileDao extends BaseMapper<ItemoverFileEntity> {
    void saveEx(ItemoverFileEntity itemover);

    ItemoverFileEntity selectByTybm(ItemoverFileEntity itemover);
}
