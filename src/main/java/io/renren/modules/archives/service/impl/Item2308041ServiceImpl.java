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

import io.renren.modules.archives.dao.Item2308041Dao;
import io.renren.modules.archives.entity.Item2308041Entity;
import io.renren.modules.archives.service.Item2308041Service;


@Service("item2308041Service")
public class Item2308041ServiceImpl extends ServiceImpl<Item2308041Dao, Item2308041Entity> implements Item2308041Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308041Entity> page = this.page(
                new Query<Item2308041Entity>().getPage(params),
                new QueryWrapper<Item2308041Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308041Entity> notMove(){
        List<Item2308041Entity> list = baseMapper.notMove();
        return list;
    }
}
