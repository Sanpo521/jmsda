package io.renren.modules.archives.service.impl;

import io.renren.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.LsDgdywCxDao;
import io.renren.modules.archives.entity.LsDgdywCxEntity;
import io.renren.modules.archives.service.LsDgdywCxService;


@Service("lsDgdywCxService")
@DataSource("oracle")
public class LsDgdywCxServiceImpl extends ServiceImpl<LsDgdywCxDao, LsDgdywCxEntity> implements LsDgdywCxService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LsDgdywCxEntity> page = this.page(
                new Query<LsDgdywCxEntity>().getPage(params),
                new QueryWrapper<LsDgdywCxEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long generatedKey(){
        return baseMapper.generatedKey();
    }

    @Override
    public void insertByDgdyw(Long id){
        baseMapper.insertByDgdyw(id);
    }

}
