package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.LsDgdywCxEntity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:46:14
 */
public interface LsDgdywCxService extends IService<LsDgdywCxEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Long generatedKey();

    void insertByDgdyw(Long id);
}

