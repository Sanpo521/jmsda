package io.renren.modules.archives.service.impl;

import io.renren.modules.archives.entity.Item2308001Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.Item2308031Dao;
import io.renren.modules.archives.entity.Item2308031Entity;
import io.renren.modules.archives.service.Item2308031Service;


@Service("item2308031Service")
public class Item2308031ServiceImpl extends ServiceImpl<Item2308031Dao, Item2308031Entity> implements Item2308031Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308031Entity> page = this.page(
                new Query<Item2308031Entity>().getPage(params),
                new QueryWrapper<Item2308031Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308031Entity> notMove(){
        List<Item2308031Entity> list = baseMapper.notMove();
        return list;
    }
}
