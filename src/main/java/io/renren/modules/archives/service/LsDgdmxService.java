package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.LsDgdmxEntity;
import io.renren.modules.archives.entity.LsDgdywEntity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:46:24
 */
public interface LsDgdmxService extends IService<LsDgdmxEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveEx(LsDgdmxEntity lsDgdmx);
}

