package io.renren.modules.archives.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoxiubin
 * @create 2022-04-14 14:05
 * @desc 文件档案迁移控制类
 **/

@Component
public class FileArchives {
    private static final Logger LOG = LoggerFactory.getLogger(FileArchives.class);


    @Autowired
    private MarpripInfoService marpripInfoService;

    @Autowired
    private ItemoverFileService itemoverFileService;

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

    @Value("${jmsda.imageFilePath}")
    private String imageFilePath;

    @Value("${jmsda.debug}")
    private boolean debug;

    private static String curPath = "";

    private static String regNoTemplate = "230800100000000";

    public void run() {
        LOG.info("");
        LOG.info("扫描历史档案迁移开始");
        LOG.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        File file = new File(imageFilePath);
        read(file);
        LOG.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        LOG.info("扫描历史档案迁移结束");
        LOG.info("");
    }

    private boolean read(File file) {
        boolean b = false;
        String path = file.getPath().replace(imageFilePath, "").replace(File.separator, "|");
        ItemoverFileEntity itemoverFileQ = new ItemoverFileEntity();
        itemoverFileQ.setTybm(path);
        ItemoverFileEntity itemoverFile = itemoverFileService.selectByTybm(itemoverFileQ);
        if (null==itemoverFile){
            if (file.exists()){
                if(!file.isDirectory()){
                    return true;
                }else{
                    File[] list = file.listFiles();
                    for(File f : list){
                        b = read(f);
                        if (b){
                            FilenameFilter searchSuffix = new FilenameFilter(){
                                @Override
                                public boolean accept(File dir, String name) {
                                    return name.endsWith(".tif");
                                }
                            };
                            list = new File(f.getParent()).listFiles(searchSuffix);
                            if (null==list || list.length<1){
                                FilenameFilter searchSuffix1 = new FilenameFilter(){
                                    @Override
                                    public boolean accept(File dir, String name) {
                                        return name.endsWith(".jpg");
                                    }
                                };
                                list = new File(f.getParent()).listFiles(searchSuffix1);
                                if (null==list || list.length<1){
                                    ItemoverFileEntity itemoverFileS = new ItemoverFileEntity();
                                    itemoverFileS.setState("1001");
                                    itemoverFileS.setTybm(path);
                                    itemoverFileService.save(itemoverFileS);
                                    break;
                                }
                            }
                            LOG.info("");
                            StopWatch sw = new StopWatch();
                            sw.start("处理时长");
                            String[] paths = path.split("\\|");
                            String[] regNoS1 = paths[paths.length-1].split("-");
                            String[] regNoS2 = regNoS1[0].split("\\(");
                            String[] regNoS3 = regNoS2[0].split("\\（");
                            String regNoSuffix = regNoS3[0].trim();
                            String regNo = "";
                            if (regNoSuffix.length()==13){
                                regNo = regNoSuffix;
                            }else if (regNoSuffix.length()==15){
                                regNo = regNoSuffix;
                            }else{
                                LOG.info("{}--regNoSuffix---{}", path, regNoSuffix);
                                regNo = regNoTemplate.substring(0, 15-regNoSuffix.length()) + regNoSuffix;
                            }
                            LOG.info("{}（{}）--共有 {} 页档案", regNo, path, list.length);
                            DocsEntity docs = setEntInfo(path, regNo, list.length);
                            docs = docsRepository.insert(docs);
                            Boolean bb = true;
                            for (int j=0; j<list.length; j++){
                                bb = uploadFile(list[j], j+1, docs);
                                if (!bb){
                                    LOG.info("{}--第 {} 页--{} 有错误", regNo, j+1, list[j].getName());
                                    break;
                                }
                            }
                            if (b){
                                if (!debug){
                                    saveOracleInfo(path, list.length, docs);
                                }
                                ItemoverFileEntity itemoverFileS = new ItemoverFileEntity();
                                itemoverFileS.setState("1");
                                itemoverFileS.setTybm(path);
                                itemoverFileService.save(itemoverFileS);
                            }else{
                                ItemoverFileEntity itemoverFileS = new ItemoverFileEntity();
                                itemoverFileS.setState("8");
                                itemoverFileS.setTybm(path);
                                itemoverFileService.save(itemoverFileS);
                            }
                            sw.stop();
                            LOG.info("{}--迁移完毕，，{}--{}ms", regNo, sw.getLastTaskName(), sw.getTotalTimeMillis());
                            break;
                        }else{
                            LOG.info("{}---{}", f.getPath(), f.getName());
                        }
                    }
                    if (!b){
                        ItemoverFileEntity itemoverFileS = new ItemoverFileEntity();
                        itemoverFileS.setState("1");
                        itemoverFileS.setTybm(path);
                        itemoverFileService.save(itemoverFileS);
                    }
                    b = false;
                }
            }
        }
        return b;
    }

    private DocsEntity setEntInfo(String path, String regNo, Integer pages){
        DocsEntity docs = new DocsEntity();
        MarpripInfoEntity marpripInfoEntity = null;
        if (!debug){
            marpripInfoEntity = marpripInfoService.getEntInfoByRegno(regNo);
        }
        if (null==marpripInfoEntity){
            docs.setDajh(1);
            docs.setYsmys(pages);
            docs.setBz("扫描历史迁移档案");
            docs.setQymc(" ");
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
            docs.setCzy("扫描历史迁移档案");
            docs.setDescription(" ");
            docs.setCheckDescription(docs.getZch());
            docs.setTjrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            if (path.indexOf("变更")>-1){
                docs.setYwmc("变更登记");
                docs.setYwid(2);
                docs.setYwslmc("2000-01-01变更登记");
            }else if (path.indexOf("注销")>-1){
                docs.setYwmc("注销登记");
                docs.setYwid(4);
                docs.setYwslmc("2000-01-01注销登记");
            }else{
                docs.setYwmc("设立登记");
                docs.setYwid(1);
                docs.setYwslmc("2000-01-01设立登记");
            }
            docs.setHzrq("2000-01-01 00:00:00");
            docs.setJcr(" ");
            docs.setJcrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            docs.setGdr(" ");
            docs.setGdrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            List<ZlflEntity> zlfls = new ArrayList<ZlflEntity>(1);
            ZlflEntity zlfl = new ZlflEntity();
            zlfl.setFlmc(docs.getYwmc());
            zlfl.setDasl(pages);
            zlfl.setDayh(1);
            zlfl.setDescription("扫描历史迁移档案");
            zlfl.setFlsx(1);
            zlfl.setTlevel("03");
            zlfls.add(zlfl);
            docs.setZlfls(zlfls);
        }else{
            docs.setDajh(marpripInfoEntity.getSmjh());
            docs.setYsmys(pages);
            docs.setBz("扫描历史迁移档案");
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
            docs.setCzy("扫描历史迁移档案");
            docs.setDescription(marpripInfoEntity.getDjjg());
            docs.setCheckDescription(String.valueOf(marpripInfoEntity.getMarprid()));
            docs.setTjrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
//            docs.setYwmc("设立登记");
//            docs.setYwslmc("2000-01-01设立登记");
//            docs.setYwid(1);
            if (path.indexOf("变更")>-1){
                docs.setYwmc("变更登记");
                docs.setYwid(2);
                docs.setYwslmc("2000-01-01变更登记");
            }else if (path.indexOf("注销")>-1){
                docs.setYwmc("注销登记");
                docs.setYwid(4);
                docs.setYwslmc("2000-01-01注销登记");
            }else{
                docs.setYwmc("设立登记");
                docs.setYwid(1);
                docs.setYwslmc("2000-01-01设立登记");
            }
            docs.setHzrq("2000-01-01 00:00:00");
            docs.setJcr(" ");
            docs.setJcrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            docs.setGdr(" ");
            docs.setGdrq(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
            List<ZlflEntity> zlfls = new ArrayList<ZlflEntity>(1);
            ZlflEntity zlfl = new ZlflEntity();
            zlfl.setFlmc(docs.getYwmc());
            zlfl.setDasl(pages);
            zlfl.setDayh(1);
            zlfl.setDescription("扫描历史迁移档案");
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
        metaData.put(Constant.FILEVO_DESCRIPTION, "扫描历史迁移档案");
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

    private void saveOracleInfo(String tybm, Integer pages, DocsEntity docs){
        Long dgdywId = lsDgdywService.generatedKey();
        LOG.info("dgdywId---" + dgdywId);
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
        if (lsDgdyw.getUniscid().length()>5){
            queryall = new TEamQueryallEntity();
            queryall.setFddbr(lsDgdyw.getFddbr());
            queryall.setMarprid(String.valueOf(lsDgdyw.getMarprid()));
            queryall.setQymc(lsDgdyw.getQymc());
            queryall.setZch(lsDgdyw.getUniscid().substring(lsDgdyw.getUniscid().length() - 5));
            queryall.setOrgid(lsDgdyw.getDjjg());
            tEamQueryallService.saveEx(queryall);
        }
        if (StringUtils.hasText(lsDgdyw.getZch())){
            queryall.setFddbr(lsDgdyw.getFddbr());
            queryall.setMarprid(String.valueOf(lsDgdyw.getMarprid()));
            queryall.setQymc(lsDgdyw.getQymc());
            queryall.setZch(lsDgdyw.getZch());
            queryall.setOrgid(lsDgdyw.getDjjg());
            tEamQueryallService.saveEx(queryall);
            if (lsDgdyw.getZch().length()>5){
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
}
