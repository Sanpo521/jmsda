package io.renren.modules.archives.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.Item2308001Dao;
import io.renren.modules.archives.entity.Item2308001Entity;
import io.renren.modules.archives.service.Item2308001Service;


@Service("item2308001Service")
public class Item2308001ServiceImpl extends ServiceImpl<Item2308001Dao, Item2308001Entity> implements Item2308001Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308001Entity> page = this.page(
                new Query<Item2308001Entity>().getPage(params),
                new QueryWrapper<Item2308001Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308001Entity> notMove(){
        List<Item2308001Entity> list = baseMapper.notMove();
        return list;
    }
}
