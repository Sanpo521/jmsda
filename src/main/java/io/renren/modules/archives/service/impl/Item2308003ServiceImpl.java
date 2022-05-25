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

import io.renren.modules.archives.dao.Item2308003Dao;
import io.renren.modules.archives.entity.Item2308003Entity;
import io.renren.modules.archives.service.Item2308003Service;


@Service("item2308003Service")
public class Item2308003ServiceImpl extends ServiceImpl<Item2308003Dao, Item2308003Entity> implements Item2308003Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308003Entity> page = this.page(
                new Query<Item2308003Entity>().getPage(params),
                new QueryWrapper<Item2308003Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308003Entity> notMove(){
        List<Item2308003Entity> list = baseMapper.notMove();
        return list;
    }
}
