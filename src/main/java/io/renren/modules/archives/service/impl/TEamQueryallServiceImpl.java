package io.renren.modules.archives.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.renren.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.TEamQueryallDao;
import io.renren.modules.archives.entity.TEamQueryallEntity;
import io.renren.modules.archives.service.TEamQueryallService;


@Service("tEamQueryallService")
@DataSource("oracle")
public class TEamQueryallServiceImpl extends ServiceImpl<TEamQueryallDao, TEamQueryallEntity> implements TEamQueryallService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TEamQueryallEntity> page = this.page(
                new Query<TEamQueryallEntity>().getPage(params),
                new QueryWrapper<TEamQueryallEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveEx(TEamQueryallEntity queryall){
        baseMapper.saveEx(queryall);
    }

    @Override
    public void delEx(TEamQueryallEntity queryall){
        baseMapper.delEx(queryall);
    }
}
