package io.renren.modules.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.archives.dao.ItemoverDao;
import io.renren.modules.archives.dao.ItemoverFileDao;
import io.renren.modules.archives.entity.ItemoverFileEntity;
import io.renren.modules.archives.service.ItemoverFileService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("ItemoverFileService")
public class ItemoverFileServiceImpl extends ServiceImpl<ItemoverFileDao, ItemoverFileEntity> implements ItemoverFileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemoverFileEntity> page = this.page(
                new Query<ItemoverFileEntity>().getPage(params),
                new QueryWrapper<ItemoverFileEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveEx(ItemoverFileEntity itemover){
        baseMapper.saveEx(itemover);
    }

    @Override
    public ItemoverFileEntity selectByTybm(ItemoverFileEntity itemover){
        return baseMapper.selectByTybm(itemover);
    }

}
