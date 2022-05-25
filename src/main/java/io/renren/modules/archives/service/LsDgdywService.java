package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.LsDgdywCxEntity;
import io.renren.modules.archives.entity.LsDgdywEntity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:45:54
 */
public interface LsDgdywService extends IService<LsDgdywEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveEx(LsDgdywEntity lsDgdyw);

    Long generatedKey();
}

