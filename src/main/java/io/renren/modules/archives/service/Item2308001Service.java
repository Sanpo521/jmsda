package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.Item2308001Entity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-06 17:35:24
 */
public interface Item2308001Service extends IService<Item2308001Entity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Item2308001Entity> notMove();
}

