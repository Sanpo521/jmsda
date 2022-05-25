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

import io.renren.modules.archives.dao.Item2308051Dao;
import io.renren.modules.archives.entity.Item2308051Entity;
import io.renren.modules.archives.service.Item2308051Service;


@Service("item2308051Service")
public class Item2308051ServiceImpl extends ServiceImpl<Item2308051Dao, Item2308051Entity> implements Item2308051Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Item2308051Entity> page = this.page(
                new Query<Item2308051Entity>().getPage(params),
                new QueryWrapper<Item2308051Entity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Item2308051Entity> notMove(){
        List<Item2308051Entity> list = baseMapper.notMove();
        return list;
    }
}
