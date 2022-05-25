package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.archives.entity.MarpripInfoEntity;

public interface MarpripInfoService extends IService<MarpripInfoEntity> {
    MarpripInfoEntity getEntInfo(String entName, String regNo);

    MarpripInfoEntity getEntInfoByEntname(String entName);

    MarpripInfoEntity getEntInfoByRegno(String regNo);
}
