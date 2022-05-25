package io.renren.modules.archives.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.archives.dao.MoveTablenameDao;
import io.renren.modules.archives.entity.MoveTablenameEntity;
import io.renren.modules.archives.service.MoveTablenameService;


@Service("moveTablenameService")
public class MoveTablenameServiceImpl extends ServiceImpl<MoveTablenameDao, MoveTablenameEntity> implements MoveTablenameService {

    @Autowired
    private MoveTablenameDao moveTablenameDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MoveTablenameEntity> page = this.page(
                new Query<MoveTablenameEntity>().getPage(params),
                new QueryWrapper<MoveTablenameEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MoveTablenameEntity> getTalbes(int state){
        QueryWrapper queryWrapper = new QueryWrapper<MoveTablenameEntity>();
        switch (state){
            case 0:
                queryWrapper.eq("state", 0);
                queryWrapper.orderByAsc("tablename");
                break;
            case 1:
                queryWrapper.eq("state", 1);
                queryWrapper.orderByAsc("tablename");
                break;
            default:
                queryWrapper.orderByAsc("tablename");
                break;
        }
        List<MoveTablenameEntity> moveTablenameEntities = moveTablenameDao.selectList(queryWrapper);
        return moveTablenameEntities;
    }

}
