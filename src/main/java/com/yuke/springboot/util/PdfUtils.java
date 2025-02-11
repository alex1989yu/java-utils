package com.yuke.springboot.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.apache.pdfbox.rendering.ImageType.RGB;

public class PdfUtils {
    /**
     * @param pdfPath
     * @param dpi
     * @param pageLength
     * @throws IOException
     */
    public static void pdfToImages(String pdfPath, int dpi, int pageLength) throws IOException {
        try {
            /*图像合并使用参数*/
            // 定义宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            // 定义高度，后面用于叠加
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            // 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /*根据总页数, 按照50页生成一张长图片的逻辑, 进行拆分*/
            // 每50页转成1张图片
            //int pageLength = 5;
            // 总计循环的次数
            int totalCount = pdDocument.getNumberOfPages() / pageLength + 1;
            for (int m = 0; m < totalCount; m++) {
                for (int i = 0; i < pageLength; i++) {
                    int pageIndex = i + (m * pageLength);
                    if (pageIndex == pdDocument.getNumberOfPages()) {
                        System.out.println("m = " + m);
                        break;
                    }
                    // 96为图片的dpi，dpi越大，则图片越清晰，图片越大，转换耗费的时间也越多
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, dpi, RGB);
                    int imageHeight = image.getHeight();
                    int imageWidth = image.getWidth();
                    if (i == 0) {
                        //计算高度和偏移量
                        //使用第一张图片宽度;
                        width = imageWidth;
                        // 保存每页图片的像素值
                        // 加个判断：如果m次循环后所剩的图片总数小于pageLength，则图片高度按剩余的张数绘制，否则会出现长图片下面全是黑色的情况
                        if ((pdDocument.getNumberOfPages() - m * pageLength) < pageLength) {
                            imageResult = new BufferedImage(width, imageHeight * (pdDocument.getNumberOfPages() - m * pageLength), BufferedImage.TYPE_INT_RGB);
                        } else {
                            imageResult = new BufferedImage(width, imageHeight * pageLength, BufferedImage.TYPE_INT_RGB);
                        }
                    } else {
                        // 将高度不断累加
                        shiftHeight += imageHeight;
                    }
                    singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                    imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
                }
                //System.out.println("m = " + m);
                File outFile = new File(pdfPath.replace(".pdf", "_" + m + ".jpg"));
                //System.out.println(outFile.getName());
                // 写图片
                ImageIO.write(imageResult, "jpg", outFile);
                // 这个很重要，下面会有说明
                shiftHeight = 0;
            }
            pdDocument.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //PdfUtils.pdfToImage("D:\\11.pdf", 100);
    }

}
