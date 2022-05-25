package io.renren.common.utils;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import io.renren.modules.archives.controller.FileArchives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;

/**
 * @author zhaoxiubin
 * @create 2022-05-21 14:37
 * @desc 图片处理工具
 **/
public class ImageUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ImageUtils.class);

//    public static ByteArrayOutputStream bmp2Jpeg(File file, String outPath) {
//        ByteArrayOutputStream baosResult = null;
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
//            Image img = ImageIO.read(file);
//            BufferedImage tag = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(img.getScaledInstance(img.getWidth(null), img.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
//            // JPEGImageEncoder可适用于其他图片类型的转换
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
//            encoder.encode(tag);
//            baos.close();
//            baosResult = baos;
//            InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//
//        }
//        return baosResult;
//    }
//
//
//    public static ByteArrayOutputStream bmp2Jpeg(File file) {
//        ByteArrayOutputStream baosResult = null;
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
//            Image img = ImageIO.read(file);
//            BufferedImage tag = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(img.getScaledInstance(img.getWidth(null), img.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
//            // JPEGImageEncoder可适用于其他图片类型的转换
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
//            encoder.encode(tag);
//            baos.close();
//            baosResult = baos;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//
//        }
//        return baosResult;
//    }

    public static String bmp2Jpeg(String filePath, String outPath) {
//        try {
//            long start = System.currentTimeMillis();
//            File file = new File(filePath);
//            Image img = ImageIO.read(file);
//            BufferedImage tag = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(img.getScaledInstance(img.getWidth(null), img.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
//            FileOutputStream out = new FileOutputStream(outPath);
//            // JPEGImageEncoder可适用于其他图片类型的转换
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
//            LOG.info("bmp 转 JPEG，共耗时：  " + (System.currentTimeMillis() - start) + " 毫秒");
//            return outPath;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return outPath;
    }
}
