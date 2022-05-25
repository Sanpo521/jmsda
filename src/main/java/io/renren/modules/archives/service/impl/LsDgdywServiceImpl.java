package io.renren.modules.archives.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.renren.datasource.annotation.DataSource;
import io.renren.modules.archives.entity.LsDgdmxEntity;
import io.renren.modules.archives.entity.LsDgdywCxEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.LsDgdywDao;
import io.renren.modules.archives.entity.LsDgdywEntity;
import io.renren.modules.archives.service.LsDgdywService;


@Service("lsDgdywService")
@DataSource("oracle")
public class LsDgdywServiceImpl extends ServiceImpl<LsDgdywDao, LsDgdywEntity> implements LsDgdywService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LsDgdywEntity> page = this.page(
                new Query<LsDgdywEntity>().getPage(params),
                new QueryWrapper<LsDgdywEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public void saveEx(LsDgdywEntity lsDgdyw){
        baseMapper.saveEx(lsDgdyw);
    }

    @Override
    public Long generatedKey(){
        return baseMapper.generatedKey();
    }
}
