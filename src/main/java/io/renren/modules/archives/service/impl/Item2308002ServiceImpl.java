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

import io.renren.modules.archives.dao.Item2308002Dao;
import io.renren.modules.archives.entity.Item2308002Entity;
import io.renren.modules.archives.service.Item2308002Service;


@Service("item2308002Service")
public class Item2308002ServiceImpl extends ServiceImpl<Item2308002Dao, Item2308002Entity> implements Item2308002Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308002Entity> page = this.page(
                new Query<Item2308002Entity>().getPage(params),
                new QueryWrapper<Item2308002Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308002Entity> notMove(){
        List<Item2308002Entity> list = baseMapper.notMove();
        return list;
    }
}
