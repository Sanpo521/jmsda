package io.renren.modules.archives.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.renren.datasource.annotation.DataSource;
import io.renren.modules.archives.entity.LsDgdywEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.LsDgdmxDao;
import io.renren.modules.archives.entity.LsDgdmxEntity;
import io.renren.modules.archives.service.LsDgdmxService;


@Service("lsDgdmxService")
@DataSource("oracle")
public class LsDgdmxServiceImpl extends ServiceImpl<LsDgdmxDao, LsDgdmxEntity> implements LsDgdmxService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LsDgdmxEntity> page = this.page(
                new Query<LsDgdmxEntity>().getPage(params),
                new QueryWrapper<LsDgdmxEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveEx(LsDgdmxEntity lsDgdmx){
        baseMapper.saveEx(lsDgdmx);
    }
}
