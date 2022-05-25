package io.renren.modules.archives.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.ItemoverDao;
import io.renren.modules.archives.entity.ItemoverEntity;
import io.renren.modules.archives.service.ItemoverService;


@Service("itemoverService")
public class ItemoverServiceImpl extends ServiceImpl<ItemoverDao, ItemoverEntity> implements ItemoverService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemoverEntity> page = this.page(
                new Query<ItemoverEntity>().getPage(params),
                new QueryWrapper<ItemoverEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveEx(ItemoverEntity itemover){
        baseMapper.saveEx(itemover);
    }

    @Override
    public ItemoverEntity selectByTybm(ItemoverEntity itemover){
        return baseMapper.selectByTybm(itemover);
    }

}
