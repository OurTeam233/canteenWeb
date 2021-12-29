package com.util;

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 图片工具类 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.util
 * @className ImageUtil
 * @date 2021/12/28 12:29
 * @TODO
 **/
public class ImageUtil {

    /**
     * 返回图片的base64编码
     *
     * @param bufferedImage bufferedImage
     * @throws Exception e
     */
    public String toImage(BufferedImage bufferedImage) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(byteArrayOutputStream.toByteArray());
    }

    /**
     * <p> 将bufferImage转换为图片 </p>
     *
     * @param bufferedImage
     * @return java.io.File
     * @throws
     * @since 2021/12/28
     */
    public File toImageFile(BufferedImage bufferedImage) {
        File file = null;
        try {
            file = File.createTempFile("temp", ".jpg");
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(new FileOutputStream(file));
            ImageIO.write(bufferedImage, "jpg", imageOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * <p> 将文件上传至图床，并返回路径 </p>
     *
     * @param file 上传的文件
     * @return java.lang.String
     * @since 2021/12/28
     */
    public String toImageUrl(File file) {
        String url = "";
        try {
            HttpUtil httpUtil = HttpUtil.init();
            // 设置参数
            Map<String, File> fileParam = new HashMap<>(2);
            fileParam.put("file", file);
            // 发送请求
            Map<String, String> data = httpUtil.postUploadFile("http://121.43.56.241/upload", fileParam);
            // 解析其中的参数
            String msg = data.get("result");
            JSONObject jsonObject = JSONObject.parseObject(msg);
            return "http://121.43.56.241" + jsonObject.getString("msg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * <p> 将bufferedImage转换为图片url </p>
     *
     * @param bufferedImage 图片缓存
     * @return java.lang.String
     * @since 2021/12/29
     */
    public String bufferedImageToUrl(BufferedImage bufferedImage) {
        File imageFile = toImageFile(bufferedImage);
        return toImageUrl(imageFile);
    }
}
