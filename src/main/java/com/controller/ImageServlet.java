package com.controller;

import com.util.QrCodeUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.*;

@WebServlet(name = "ImageServlet", value = "/ImageServlet")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
//        PrintWriter out = response.getWriter();

        //处理
        String content = "https://www.baidu.com";
        try {
            BufferedImage bufferedImage = QrCodeUtils.createImage(content, null, false);
            responseImage(response, bufferedImage);
        } catch (Exception e) {
            // 异常自行处理，应用程序切忌直接打印堆栈日志，难定位
            e.printStackTrace();
        }


        //输出
//        out.print("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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