package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.ItemoverEntity;
import io.renren.modules.archives.entity.ItemoverFileEntity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-09 12:38:30
 */
public interface ItemoverFileService extends IService<ItemoverFileEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveEx(ItemoverFileEntity itemover);

    ItemoverFileEntity selectByTybm(ItemoverFileEntity itemover);
}

