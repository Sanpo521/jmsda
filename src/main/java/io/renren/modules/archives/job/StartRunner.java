package io.renren.modules.archives.job;

import io.renren.modules.archives.controller.FileArchives;
import io.renren.modules.archives.controller.HuaRuiScan;
import io.renren.modules.archives.dao.DocsDao;
import io.renren.modules.archives.entity.DocsEntity;
import io.renren.modules.archives.entity.ZlflEntity;
import io.renren.modules.archives.repository.DocsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoxiubin
 * @create 2022-04-14 14:14
 * @desc 启动后运行类
 **/
@Configuration
public class StartRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(StartRunner.class);

    @Autowired
    private HuaRuiScan huaRuiScan;

    @Autowired
    private FileArchives aileArchives;

    @Override
    public void run(String... args) throws Exception {
        LOG.info("佳木斯历史档案迁移开始");
        LOG.info("******************************************************************");
//        huaRuiScan.run();
        aileArchives.run();
        LOG.info("******************************************************************");
        LOG.info("佳木斯历史档案迁移结束");
    }
}
