package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.Item2308002Entity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-06 17:35:24
 */
public interface Item2308002Service extends IService<Item2308002Entity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Item2308002Entity> notMove();
}

