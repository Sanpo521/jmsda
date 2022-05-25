package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.Item2308003Entity;
import io.renren.modules.archives.entity.Item2308041Entity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-06 17:35:25
 */
public interface Item2308041Service extends IService<Item2308041Entity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Item2308041Entity> notMove();
}

