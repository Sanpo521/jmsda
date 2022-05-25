package io.renren.modules.archives.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.archives.entity.MoveTablenameEntity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-04-14 13:51:23
 */
public interface MoveTablenameService extends IService<MoveTablenameEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MoveTablenameEntity> getTalbes(int state);
}
