package com.yuke.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 压缩与解压缩工具类
 */
@Slf4j
public class BZip2Utils {

    /**
     * 缓冲区大小
     */
    private static final int BUFFER = 1024;

    public static byte[] compress(byte[] data) {
        try (ByteArrayInputStream is = new ByteArrayInputStream(data);
             ByteArrayOutputStream os = new ByteArrayOutputStream();
             BZip2CompressorOutputStream gos = new BZip2CompressorOutputStream(os);) {
            int count;
            byte buffer[] = new byte[BUFFER];
            while ((count = is.read(buffer, 0, BUFFER)) != -1) {
                gos.write(buffer, 0, count);
            }
            data = os.toByteArray();
        } catch (Exception e) {
            log.warn("压缩数据错误", e);
        }
        return data;
    }

    /**
     * 数据解压缩 入口
     *
     * @param data
     * @return
     */
    public static byte[] decompress(byte[] data) {
        try (ByteArrayInputStream is = new ByteArrayInputStream(data);
             ByteArrayOutputStream os = new ByteArrayOutputStream();
             BZip2CompressorInputStream gis = new BZip2CompressorInputStream(is);) {
            int count;
            byte buffer[] = new byte[BUFFER];
            while ((count = gis.read(buffer, 0, BUFFER)) != -1) {
                os.write(buffer, 0, count);
            }
            data = os.toByteArray();
        } catch (IOException e) {
            log.warn("解压缩过程出错", e);
        }
        return data;
    }
}
