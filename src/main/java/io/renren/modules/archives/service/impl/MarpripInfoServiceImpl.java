package io.renren.modules.archives.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.datasource.annotation.DataSource;
import io.renren.modules.archives.dao.MarpripInfoDao;
import io.renren.modules.archives.entity.MarpripInfoEntity;
import io.renren.modules.archives.service.MarpripInfoService;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiubin
 * @create 2022-05-09 10:42
 * @desc 企业信息服务实现类
 **/
@Service("marpripInfoService")
@DataSource("oracle")
public class MarpripInfoServiceImpl extends ServiceImpl<MarpripInfoDao, MarpripInfoEntity> implements MarpripInfoService {

    @Override
    public MarpripInfoEntity getEntInfo(String entName, String regNo) {
        MarpripInfoEntity marpripInfoEntity = null;
        marpripInfoEntity = baseMapper.getEntInfoByRegno(regNo);
        if (marpripInfoEntity==null){
            marpripInfoEntity = baseMapper.getEntInfoByEntname(entName);
        }
        return marpripInfoEntity;
    }

    @Override
    public MarpripInfoEntity getEntInfoByEntname(String entName) {
        return baseMapper.getEntInfoByEntname(entName);
    }

    @Override
    public MarpripInfoEntity getEntInfoByRegno(String regNo) {
        return baseMapper.getEntInfoByEntname(regNo);
    }
}
