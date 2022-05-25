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

import io.renren.modules.archives.dao.Item2308021Dao;
import io.renren.modules.archives.entity.Item2308021Entity;
import io.renren.modules.archives.service.Item2308021Service;


@Service("item2308021Service")
public class Item2308021ServiceImpl extends ServiceImpl<Item2308021Dao, Item2308021Entity> implements Item2308021Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308021Entity> page = this.page(
                new Query<Item2308021Entity>().getPage(params),
                new QueryWrapper<Item2308021Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308021Entity> notMove(){
        List<Item2308021Entity> list = baseMapper.notMove();
        return list;
    }
}
