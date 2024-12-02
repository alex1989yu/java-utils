package com.yuke.springboot.system.controller;

import com.yuke.springboot.util.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;

@RestController
@Slf4j
@RequestMapping("/common")
public class SysCommonController {

    @RequestMapping("/downloadUrl")
    public void downloadUrl(@RequestParam("url") String url, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            log.info("downloadUrl网络文件下载URL:{}", url);
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
}
