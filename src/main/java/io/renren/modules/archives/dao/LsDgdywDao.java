package io.renren.modules.archives.dao;

import io.renren.modules.archives.entity.LsDgdywCxEntity;
import io.renren.modules.archives.entity.LsDgdywEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 *
 * @author zhaoxiubin
 * @email sanpo521@gmail.com
 * @date 2022-05-11 09:45:54
 */
@Mapper
public interface LsDgdywDao extends BaseMapper<LsDgdywEntity> {
	Long generatedKey();

	void saveEx(LsDgdywEntity lsDgdyw);
}
