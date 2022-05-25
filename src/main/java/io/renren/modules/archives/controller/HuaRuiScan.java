package io.renren.modules.archives.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.FileUtils;
import io.renren.common.utils.result.SingleDataResponse;
import io.renren.modules.archives.entity.*;
import io.renren.modules.archives.repository.DocsRepository;
import io.renren.modules.archives.service.*;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoxiubin
 * @create 2022-04-14 13:42
 * @desc 华瑞电子档案系统迁移控制类
 **/

@Component
public class HuaRuiScan {
    private static final Logger LOG = LoggerFactory.getLogger(HuaRuiScan.class);

    @Autowired
    private MoveTablenameService moveTablenameService;

    @Autowired
    private Item2308001Service item2308001Service;

    @Autowired
    private Item2308002Service item2308002Service;

    @Autowired
    private Item2308003Service item2308003Service;

    @Autowired
    private Item2308021Service item2308021Service;

    @Autowired
    private Item2308031Service item2308031Service;

    @Autowired
    private Item2308041Service item2308041Service;

    @Autowired
    private Item2308051Service item2308051Service;

    @Autowired
    private Item2308111Service item2308111Service;

    @Autowired
    private MarpripInfoService marpripInfoService;

    @Autowired
    private ItemoverService itemoverService;

    @Autowired
    private DocsRepository docsRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    private LsDgdmxService lsDgdmxService;

    @Autowired
    private LsDgdywCxService lsDgdywCxService;

    @Autowired
    private LsDgdywService lsDgdywService;

    @Autowired
    private TEamQueryallService tEamQueryallService;

    @Value("${jmsda.debug}")
    private boolean debug;

    public void run() {
        LOG.info("");
        LOG.info("华瑞历史档案迁移开始");
        LOG.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        List<MoveTablenameEntity> notMoveTablenameEntities = getNotMoveTablesInfo();
        if (null!=notMoveTablenameEntities && notMoveTablenameEntities.size()>0){
            for (MoveTablenameEntity moveTablenameEntity: notMoveTablenameEntities){
                move(moveTablenameEntity);
            }
            LOG.info("");
        }
        LOG.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LOG.info("华瑞历史档案迁移结束");
    }

    private List<MoveTablenameEntity> getNotMoveTablesInfo(){
        List<MoveTablenameEntity> notMoveTablenameEntities = moveTablenameService.getTalbes(0);
        List<MoveTablenameEntity> movedTablenameEntities = moveTablenameService.getTalbes(1);
        LOG.info("共有{}个表中档案需要迁移，已迁移完毕{}个表", notMoveTablenameEntities.size()+movedTablenameEntities.size(), movedTablenameEntities.size());
        return notMoveTablenameEntities;
    }

    private DocsEntity setEntInfo(String tybm, String entName, String regNo, Integer pages){
        DocsEntity docs = new DocsEntity();
        MarpripInfoEntity marpripInfoEntity = null;
        if (!debug){
            marpripInfoEntity = marpripInfoService.getEntInfo(entName, regNo);
        }
        if (null==marpripInfoEntity){
            docs.setDajh(1);
            docs.setYsmys(pages);
            docs.setBz("华瑞系统迁移档案");
            if (StringUtils.hasText(entName)){
                docs.setQymc(entName);
            }else{
                docs.setQymc(" ");
            }
            if (StringUtils.hasText(regNo)){
                docs.setZch(regNo);
            }else{
                docs.setZch(" ");
            }
            docs.setUniscid(" ");
            docs.setQylx("公司");
            docs.setQylxDm("1001");
            docs.setFddbr(" ");
            docs.setZihao(" ");
            docs.setQyzl("公司");
            docs.setQyzlDm("1001");
            docs.setQyzt(" ");
            docs.setZgjg(" ");
            docs.setHy(" ");
            docs.setJydz(" ");
            docs.setDazt("CWBDR");
//            docs.setCzy("迁移程序");
//            docs.setDescription("华瑞系统迁移档案");
//            docs.setCheckDescription("华瑞系统迁移档案");
            docs.setCzy("华瑞系统迁移档案");
            docs.setDescription(" ");
            docs.setCheckDescription(docs.getZch());
            docs.setTjrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            docs.setYwmc("设立登记");
            docs.setYwslmc("2000-01-01设立登记");
            docs.setYwid(1);
            docs.setHzrq("2000-01-01 00:00:00");
            docs.setJcr(" ");
            docs.setJcrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            docs.setGdr(" ");
            docs.setGdrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            List<ZlflEntity> zlfls = new ArrayList<ZlflEntity>(1);
            ZlflEntity zlfl = new ZlflEntity();
            zlfl.setFlmc("设立登记");
            zlfl.setDasl(pages);
            zlfl.setDayh(1);
            zlfl.setDescription("华瑞系统迁移档案");
            zlfl.setFlsx(1);
            zlfl.setTlevel("03");
            zlfls.add(zlfl);
            docs.setZlfls(zlfls);
        }else{
            docs.setDajh(marpripInfoEntity.getSmjh());
            docs.setYsmys(pages);
            docs.setBz("华瑞系统迁移档案");
            if (StringUtils.hasText(marpripInfoEntity.getEntname())){
                docs.setQymc(marpripInfoEntity.getEntname());
            }else{
                docs.setQymc(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getRegno())){
                docs.setZch(marpripInfoEntity.getRegno());
            }else{
                docs.setZch(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getUniscid())){
                docs.setUniscid(marpripInfoEntity.getUniscid());
            }else{
                docs.setUniscid(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getEnttypename())){
                docs.setQylx(marpripInfoEntity.getEnttypename());
            }else{
                docs.setQylx("公司");
            }
            if (StringUtils.hasText(marpripInfoEntity.getEnttype())){
                docs.setQylxDm(marpripInfoEntity.getEnttype());
            }else{
                docs.setQylxDm("1001");
            }
            if (StringUtils.hasText(marpripInfoEntity.getLerep())){
                docs.setFddbr(marpripInfoEntity.getLerep());
            }else{
                docs.setFddbr(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getEnttra())){
                docs.setZihao(marpripInfoEntity.getEnttra());
            }else{
                docs.setZihao(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getEnttypename())){
                docs.setQyzl(marpripInfoEntity.getEnttypename());
            }else{
                docs.setQyzl("公司");
            }
            if (StringUtils.hasText(marpripInfoEntity.getEnttype())){
                docs.setQyzlDm(marpripInfoEntity.getEnttype());
            }else{
                docs.setQyzlDm("1001");
            }
            if (StringUtils.hasText(marpripInfoEntity.getStatename())){
                docs.setQyzt(marpripInfoEntity.getStatename());
            }else{
                docs.setQyzt(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getZgjg())){
                docs.setZgjg(marpripInfoEntity.getZgjg());
            }else{
                docs.setZgjg(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getIndustryconame())){
                docs.setHy(marpripInfoEntity.getIndustryconame());
            }else{
                docs.setHy(" ");
            }
            if (StringUtils.hasText(marpripInfoEntity.getDom())){
                docs.setJydz(marpripInfoEntity.getDom());
            }else{
                docs.setJydz(" ");
            }
            docs.setDazt("JCTG");
            docs.setCzy("迁移程序");
            docs.setDescription(marpripInfoEntity.getDjjg());
            docs.setCheckDescription(String.valueOf(marpripInfoEntity.getMarprid()));
            docs.setTjrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            docs.setYwmc("设立登记");
            docs.setYwslmc("2000-01-01设立登记");
            docs.setYwid(1);
            docs.setHzrq("2000-01-01 00:00:00");
            docs.setJcr(" ");
            docs.setJcrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            docs.setGdr(" ");
            docs.setGdrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            List<ZlflEntity> zlfls = new ArrayList<ZlflEntity>(1);
            ZlflEntity zlfl = new ZlflEntity();
            zlfl.setFlmc("设立登记");
            zlfl.setDasl(pages);
            zlfl.setDayh(1);
            zlfl.setDescription("华瑞系统迁移档案");
            zlfl.setFlsx(1);
            zlfl.setTlevel("03");
            zlfls.add(zlfl);
            docs.setZlfls(zlfls);
        }
        return docs;
    }

    private Boolean uploadFile(File file, Integer sx, DocsEntity docs){
        Boolean result = false;
        DBObject metaData = new BasicDBObject();
        metaData.put(Constant.FILEVO_DOCID, new ObjectId(docs.getId()));
        metaData.put(Constant.FILEVO_CONTENTTYPE, Constant.FILEVO_IMAGE_TIFF);
        metaData.put(Constant.FILEVO_CZY, "迁移程序");
        metaData.put(Constant.FILEVO_DASX, sx);
        metaData.put(Constant.FILEVO_DAZT, docs.getDazt());
        metaData.put(Constant.FILEVO_DARQ, docs.getGdrq());
        metaData.put(Constant.FILEVO_DACD, file.length());
        metaData.put(Constant.FILEVO_DAMC, file.getName());
        metaData.put(Constant.FILEVO_DESCRIPTION, "华瑞系统迁移档案");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            ObjectId fileId = gridFsTemplate.store(inputStream, file.getName(),metaData);
            result = true;
        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.debug(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    private void uploadFiles(SingleDataResponse sdr, String tybm, String qymc, String zch){
        List<File> fileList = (List<File>)sdr.getData();
        LOG.info("{}--共有 {} 页档案", tybm, fileList.size());
        DocsEntity docs = setEntInfo(tybm, qymc, zch, fileList.size());
        docs = docsRepository.insert(docs);
        Boolean b = true;
        for (int j=0; j<fileList.size(); j++){
            b = uploadFile(fileList.get(j), j+1, docs);
            if (!b){
                LOG.info("{}--第 {} 页--{} 有错误", tybm, j+1, fileList.get(j).getName());
                break;
            }
        }
        if (b){
            if (!debug) {
                saveOracleInfo(tybm, fileList.size(), docs);
            }
            ItemoverEntity itemover = new ItemoverEntity();
            itemover.setState("1");
            itemover.setTybm(tybm);
            itemoverService.save(itemover);
        }else{
            ItemoverEntity itemover = new ItemoverEntity();
            itemover.setState("8");
            itemover.setTybm(tybm);
            itemoverService.save(itemover);
        }
    }

    private void saveOracleInfo(String tybm, Integer pages, DocsEntity docs){
        Long dgdywId = lsDgdywService.generatedKey();

        LsDgdywEntity lsDgdyw = new LsDgdywEntity();
        lsDgdyw.setId(dgdywId);
        lsDgdyw.setZch(docs.getZch());
        lsDgdyw.setUniscid(docs.getUniscid());
        lsDgdyw.setQymc(docs.getQymc());
        lsDgdyw.setFddbr(docs.getFddbr());
        lsDgdyw.setQylx(docs.getQylx());
        lsDgdyw.setQylxdm(docs.getQylxDm());
        lsDgdyw.setHy(docs.getHy());
        lsDgdyw.setJydz(docs.getJydz());
        lsDgdyw.setZgjg(docs.getZgjg());
        lsDgdyw.setJcr(docs.getJcr());
        lsDgdyw.setJcrq(new Date());
        lsDgdyw.setYwsllj(docs.getId());
        lsDgdyw.setQylj(tybm);
        lsDgdyw.setYwlx(docs.getYwmc());
        lsDgdyw.setHzrq(DateUtils.stringToDate(docs.getHzrq(), DateUtils.DATE_TIME_PATTERN));
        lsDgdyw.setDasl(pages);
        if ("CWBDR".equalsIgnoreCase(docs.getDazt())){
            lsDgdyw.setDjjg("0");
            lsDgdyw.setMarprid(Long.parseLong(DateUtils.format(new Date(), "MMddHHmmssSSS")));
        }else{
            lsDgdyw.setDjjg(docs.getDescription());
            lsDgdyw.setMarprid(Long.parseLong(docs.getCheckDescription()));
        }
        lsDgdyw.setSmjh(docs.getDajh());
        lsDgdyw.setSmjzt(docs.getDazt());
        lsDgdyw.setYsmys(pages);
        lsDgdyw.setBz(docs.getBz());
        lsDgdyw.setYxbz(2);
        lsDgdyw.setZihao(docs.getZihao());
        lsDgdyw.setYwmc(docs.getYwmc());
        lsDgdyw.setYwslmc(docs.getYwslmc());
        lsDgdyw.setYwid(docs.getYwid());
        lsDgdyw.setQyjg("ABE");
        lsDgdyw.setBgnr("F");
        lsDgdyw.setModifytime(new Date());
        lsDgdywService.saveEx(lsDgdyw);
//        Long dgdywId = lsDgdyw.getId();
        lsDgdywCxService.insertByDgdyw(dgdywId);
        LsDgdmxEntity lsDgdmx = new LsDgdmxEntity();
        lsDgdmx.setModifytime(new Date());
        lsDgdmx.setDgdywid(dgdywId);
        lsDgdmx.setYmq(1);
        lsDgdmx.setYmz(pages);
        lsDgdmx.setBustype("HuaRei");
        lsDgdmx.setSource("ELE");
        lsDgdmx.setZlmc("档案卷");
        lsDgdmxService.saveEx(lsDgdmx);
            TEamQueryallEntity queryall = new TEamQueryallEntity();
            queryall.setFddbr(lsDgdyw.getFddbr());
            queryall.setMarprid(String.valueOf(lsDgdyw.getMarprid()));
            queryall.setQymc(lsDgdyw.getQymc());
            queryall.setZch(lsDgdyw.getUniscid());
            queryall.setOrgid(lsDgdyw.getDjjg());
            tEamQueryallService.delEx(queryall);
            tEamQueryallService.saveEx(queryall);
            if (lsDgdyw.getUniscid().length() > 5) {
                queryall = new TEamQueryallEntity();
                queryall.setFddbr(lsDgdyw.getFddbr());
                queryall.setMarprid(String.valueOf(lsDgdyw.getMarprid()));
                queryall.setQymc(lsDgdyw.getQymc());
                queryall.setZch(lsDgdyw.getUniscid().substring(lsDgdyw.getUniscid().length() - 5));
                queryall.setOrgid(lsDgdyw.getDjjg());
                tEamQueryallService.saveEx(queryall);
            }
            if (StringUtils.hasText(lsDgdyw.getZch())) {
                queryall.setFddbr(lsDgdyw.getFddbr());
                queryall.setMarprid(String.valueOf(lsDgdyw.getMarprid()));
                queryall.setQymc(lsDgdyw.getQymc());
                queryall.setZch(lsDgdyw.getZch());
                queryall.setOrgid(lsDgdyw.getDjjg());
                tEamQueryallService.saveEx(queryall);
                if (lsDgdyw.getZch().length() > 5) {
                    queryall = new TEamQueryallEntity();
                    queryall.setFddbr(lsDgdyw.getFddbr());
                    queryall.setMarprid(String.valueOf(lsDgdyw.getMarprid()));
                    queryall.setQymc(lsDgdyw.getQymc());
                    queryall.setZch(lsDgdyw.getZch().substring(lsDgdyw.getZch().length() - 5));
                    queryall.setOrgid(lsDgdyw.getDjjg());
                    tEamQueryallService.saveEx(queryall);
                }
            }
    }

    private void move2308001(MoveTablenameEntity moveTablenameEntity){
        List<Item2308001Entity> list = item2308001Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308001Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
//                SingleDataResponse sdr = FileUtils.getNumberFileName("F:\\scanimage\\sy\\07cc421d-344f-42b6-b49b-9bde44e6622e", ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308002(MoveTablenameEntity moveTablenameEntity){
        List<Item2308002Entity> list = item2308002Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308002Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308003(MoveTablenameEntity moveTablenameEntity){
        List<Item2308003Entity> list = item2308003Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308003Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308021(MoveTablenameEntity moveTablenameEntity){
        List<Item2308021Entity> list = item2308021Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308021Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308031(MoveTablenameEntity moveTablenameEntity){
        List<Item2308031Entity> list = item2308031Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308031Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308041(MoveTablenameEntity moveTablenameEntity){
        List<Item2308041Entity> list = item2308041Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308041Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308051(MoveTablenameEntity moveTablenameEntity){
        List<Item2308051Entity> list = item2308051Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308051Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move2308111(MoveTablenameEntity moveTablenameEntity){
        List<Item2308111Entity> list = item2308111Service.notMove();
        LOG.info("");
        LOG.info("{}--表共有 {} 条未迁移数据", moveTablenameEntity.getTablename(), list.size());
        LOG.info("------------------------------------------------------------------");
        if (null!=list && list.size()>0){
            Long i = 1L;
            for (Item2308111Entity item: list){
                StopWatch sw = new StopWatch();
                sw.start("处理时长");
                LOG.info("第 {} 条{}迁移开始", i, item.getTybm());
                SingleDataResponse sdr = FileUtils.getNumberFileName(moveTablenameEntity.getPath() + File.separator + item.getTybm(), ".tif");
                if (sdr.getCode().equals(0)){
                    uploadFiles(sdr, item.getTybm(), item.getField1(), item.getField2());
                }else{
                    ItemoverEntity itemover = new ItemoverEntity();
                    itemover.setState(String.valueOf(sdr.getCode()));
                    itemover.setTybm(item.getTybm());
                    itemoverService.save(itemover);
                }
                sw.stop();
                LOG.info("第 {} 条迁移完毕，{}--{}ms", i, sw.getLastTaskName(), sw.getTotalTimeMillis());
                ++i;
            }
        }
        moveTablenameEntity.setState(1);
        moveTablenameService.saveOrUpdate(moveTablenameEntity);
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }

    private void move(MoveTablenameEntity moveTablenameEntity){
        LOG.info("");
        LOG.info("{}--表数据迁移开始", moveTablenameEntity.getTablename());
        LOG.info("------------------------------------------------------------------");
        switch (moveTablenameEntity.getTablename()){
            case "tab_item_230800_1":
                move2308001(moveTablenameEntity);
                break;
            case "tab_item_230800_2":
                move2308002(moveTablenameEntity);
                break;
            case "tab_item_230800_3":
                move2308003(moveTablenameEntity);
                break;
            case "tab_item_230802_1":
                move2308021(moveTablenameEntity);
                break;
            case "tab_item_230803_1":
                move2308031(moveTablenameEntity);
                break;
            case "tab_item_230804_1":
                move2308041(moveTablenameEntity);
                break;
            case "tab_item_230805_1":
                move2308051(moveTablenameEntity);
                break;
            case "tab_item_230811_1":
                move2308111(moveTablenameEntity);
                break;

        }
        LOG.info("------------------------------------------------------------------");
        LOG.info("{}--表数据迁移完毕", moveTablenameEntity.getTablename());
    }
}
