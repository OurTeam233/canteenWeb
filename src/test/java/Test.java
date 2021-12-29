import com.google.zxing.WriterException;
import com.util.ImageUtil;
import com.util.QrCodeUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;

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
//    @org.junit.Test
    public void test() {
        String content = "37";
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = QrCodeUtils.createImage(content, null, false);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageUtil imageUtil = new ImageUtil();
        System.out.println((imageUtil.bufferedImageToUrl(bufferedImage)));
    }
}
