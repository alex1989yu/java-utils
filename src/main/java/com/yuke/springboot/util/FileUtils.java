package com.yuke.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
public class FileUtils {
    @RequestMapping("/downloadUrl")
    public void downloadUrl(@RequestParam("url") String url, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String [] ss = url.split("/");
            String fileName = ss[ss.length-1];
            // 发送请求
            String urlStr = URLDecoder.decode(url, "UTF-8");
            inputStream = new URL(urlStr).openStream();
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            log.error("下载文件失败" + e.getMessage());
            response.setStatus(404);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("下载文件失败" + e.getMessage());
            response.setStatus(404);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static String encodeFilename(String filename, String agent) throws IOException {
        if (agent.contains("Firefox")) { // 火狐浏览器
            filename = "=?UTF-8?B?" + EncodeUtil.encodeBase64(filename.getBytes("utf-8")) + "?=";
            filename = filename.replaceAll("\r\n", "");
        } else { // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        return filename;
    }
}
