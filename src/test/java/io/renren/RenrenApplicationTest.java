package io.renren;

import io.renren.modules.archives.entity.DocsEntity;
import io.renren.modules.archives.entity.ZlflEntity;
import io.renren.modules.archives.repository.DocsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
public class RenrenApplicationTest {


    @Autowired
    private DocsRepository docsRepository;

    @Test
    public void saveDemoTest() {
        DocsEntity demoEntity = new DocsEntity();
        demoEntity.setQymc("Spring Boot 中使用 MongoDB1");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享1");
        demoEntity.setZch("souyunku1");
        demoEntity.setFddbr("http://www.souyunku.com1");
        List<ZlflEntity> zlfls = new ArrayList<ZlflEntity>(5);
        ZlflEntity zlflEntity = new ZlflEntity();
        zlflEntity.setFlmc("setFlmc");
        zlflEntity.setFlsx(1);
        zlflEntity.setDescription("setDescription");
        zlflEntity.setDayh(1);
        zlflEntity.setDasl(2);
        zlflEntity.setTlevel("setTlevel");
        zlfls.add(zlflEntity);
        zlflEntity = new ZlflEntity();
        zlflEntity.setFlmc("setFlmc");
        zlflEntity.setFlsx(2);
        zlflEntity.setDescription("setDescription");
        zlflEntity.setDayh(3);
        zlflEntity.setDasl(4);
        zlflEntity.setTlevel("setTlevel");
        zlfls.add(zlflEntity);
        demoEntity.setZlfls(zlfls);
        docsRepository.save(demoEntity);

        demoEntity = new DocsEntity();
        demoEntity.setQymc("Spring Boot 中使用 MongoDB2");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享2");
        demoEntity.setZch("souyunku2");
        demoEntity.setFddbr("http://www.souyunku.com2");

        docsRepository.save(demoEntity);
    }
//
//    @Test
//    public void removeDemoTest() {
////        docsDao.removeDocs(2L);
//    }
//
//    @Test
//    public void updateDemoTest() {
//
//        DocsEntity demoEntity = new DocsEntity();
////        demoEntity.setId(1L);
////        demoEntity.setTitle("Spring Boot 中使用 MongoDB 更新数据");
////        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
////        demoEntity.setBy("souyunku");
////        demoEntity.setUrl("http://www.souyunku.com");
//
//        docsService.updateDocs(demoEntity);
//    }
//
//    @Test
//    public void findDemoByIdTest() {
//        DocsEntity demoEntity = docsService.findDocsById("");
//        System.out.println(JSONObject.toJSONString(demoEntity));
//    }
}
