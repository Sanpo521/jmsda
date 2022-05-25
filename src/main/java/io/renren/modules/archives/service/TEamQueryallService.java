package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.LsDgdmxEntity;
import io.renren.modules.archives.entity.TEamQueryallEntity;

import java.util.Collection;
import java.util.Map;

/**
 * 电子档案全量查询表
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:47:43
 */
public interface TEamQueryallService extends IService<TEamQueryallEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveEx(TEamQueryallEntity queryalls);

    void delEx(TEamQueryallEntity queryalls);
}

