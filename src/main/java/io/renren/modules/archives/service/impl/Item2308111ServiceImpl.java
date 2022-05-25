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

import io.renren.modules.archives.dao.Item2308111Dao;
import io.renren.modules.archives.entity.Item2308111Entity;
import io.renren.modules.archives.service.Item2308111Service;


@Service("item2308111Service")
public class Item2308111ServiceImpl extends ServiceImpl<Item2308111Dao, Item2308111Entity> implements Item2308111Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308111Entity> page = this.page(
                new Query<Item2308111Entity>().getPage(params),
                new QueryWrapper<Item2308111Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308111Entity> notMove(){
        List<Item2308111Entity> list = baseMapper.notMove();
        return list;
    }
}
