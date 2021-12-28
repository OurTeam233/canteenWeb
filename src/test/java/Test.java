import com.util.QrCodeUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package PACKAGE_NAME
 * @className Test
 * @date 2021/12/25 21:30
 * @TODO
 **/
public class Test {
    @org.junit.Test
    public void test() {
        String content = "https://www.baidu.com";
        try {
            BufferedImage bufferedImage = QrCodeUtils.createImage(content, null, false);
//            responseImage(response, bufferedImage);
        } catch (Exception e) {
            // 异常自行处理，应用程序切忌直接打印堆栈日志，难定位
            e.printStackTrace();
        }
    }

    /**
     * 设置 可通过postman 或者浏览器直接浏览
     *
     * @param response      response
     * @param bufferedImage bufferedImage
     * @throws Exception e
     */
    public void responseImage(HttpServletResponse response, BufferedImage bufferedImage) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
        ImageIO.write(bufferedImage, "jpeg", imageOutput);
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        OutputStream outputStream = response.getOutputStream();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
    }

}
