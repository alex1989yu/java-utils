package com.yuke.springboot.util;

import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class ImgUtil {
    public static final String BASE64_PNG_PREFIX = "data:image/png;base64,";

    public static final String PNG_FORMAT_NAME = "png";

    public static String imgToBase64(BufferedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, PNG_FORMAT_NAME, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64.Encoder encoder = java.util.Base64.getEncoder();
        String encode = encoder.encodeToString(outputStream.toByteArray());
        return BASE64_PNG_PREFIX + encode;
    }

    public static void base64ToImg(String imgStr, String path) {
        if (!StringUtils.isEmpty(imgStr)) {
            Base64.Decoder decoder = java.util.Base64.getDecoder();
            try {
                byte[] b = decoder.decode(imgStr);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
