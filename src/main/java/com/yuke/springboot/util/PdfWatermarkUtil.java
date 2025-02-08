package com.yuke.springboot.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class PdfWatermarkUtil {

    /**
     * 给PDF添加水印仅文字
     * @param inputFile 原文件路径+名称
     * @param outputFile 添加水印后输出文件保存的路径+名称
     * @param waterMarkName 添加水印的内容
     * @param textH 文字水印区域高度
     * @param textW 文字水印区域宽度
     * @param fontAndSize 水印字体大小
     * @param R red 色域
     * @param G green 色域
     * @param B B blue 色域
     */
    public static void PDFAddWatermark(String inputFile,String outputFile, String waterMarkName, int textH, int textW, int fontAndSize, int R,int G, int B) {
        try {
            // 参数默认赋值
            if (textH == 0) {
                textH = 45;
            }
            if (textW == 0) {
                textW = 100;
            }
            if (fontAndSize == 0) {
                fontAndSize = 15;
            }
            if (R == 0) {
                R = 2;
            }
            if (G == 0) {
                G = 152;
            }
            if (B == 0) {
                G = 246;
            }
            // 间隔距离
            int interval = 30;
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
            // 设置文字水印样式
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            // 设置字体颜色
            BaseColor baseColor = new BaseColor(R,G,B);
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.8f);//改透明度
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;
            JLabel label = new JLabel();
            label.setText(waterMarkName);
            PdfContentByte under;
            // 添加多行文字水印
            Rectangle pageRect = null;
            label.setText(waterMarkName);
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                // 在内容下方加水印
                under = stamper.getUnderContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, fontAndSize);
                under.setColorFill(baseColor);
                // 水印文字成30度角倾斜
                for (int height = interval + textH; height < pageRect.getHeight();
                     height = height + textH * 3) {
                    for (int width = interval + textW; width < pageRect.getWidth() + textW;
                         width = width + textW * 2) {
                        under.showTextAligned(Element.ALIGN_LEFT
                                , waterMarkName, width - textW,
                                height - textH, 30);
                        under.setGState(gs);
                    }
                }
                // 添加水印文字
                under.endText();
            }
            stamper.close();
            reader.close();
        } catch (Exception e) {
            log.error("工具类文字水印添加异常"+ e.getMessage());
        }
    }

    /**
     * 给PDF添加水印(文字+图片)
     * @param inputFile 原文件路径+名称
     * @param outputFile 添加水印后输出文件保存的路径+名称
     * @param waterMarkName 添加水印的内容
     * @param img 图片水印
     * @param textH 文字水印区域高度
     * @param textW 文字水印区域宽度
     * @param fontAndSize 水印字体大小
     * @param R red 色域 （0-255）
     * @param G green 色域（0-255）
     * @param B blue 色域（0-255）
     * @param moveH 移动图片的高度 ，文字水印和图片水印的位置关系，通过此参数调节
     * @param moveW 移动图片的宽度，文字水印和图片水印的位置关系，通过此参数调节
     */
    public static void PDFAddWatermarkAndPicture(String inputFile,String outputFile, String waterMarkName, Image img, int textH, int textW, int fontAndSize, int R,int G, int B, float textFillOpacity, float imageFillOpacity, int moveH, int moveW) {
        try {
            // 参数默认赋值
            if (textH == 0) {
                textH = 45;
            }
            if (textW == 0) {
                textW = 100;
            }
            if (fontAndSize == 0) {
                fontAndSize = 15;
            }
            if (R == 0) {
                R = 2;
            }
            if (G == 0) {
                G = 152;
            }
            if (B == 0) {
                G = 246;
            }
            // 间隔距离（参数可调节）
            int interval = 30;
            // 切记这里的参数是文件的路径 ，路径必须是双斜杠的如F:\\test.pdf，不能是F:/test.pdf 或者F:\test.pdf
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
            // 设置文字水印样式
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            // 设置字体颜色
            BaseColor baseColor = new BaseColor(R,G,B);
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(textFillOpacity);//改透明度
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;
            JLabel label = new JLabel();
            label.setText(waterMarkName);
            PdfContentByte under;

            // 可添加多个文字水印
            Rectangle pageRect = null;
            label.setText(waterMarkName);
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                // 在内容下方加水印
                under = stamper.getUnderContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, fontAndSize);
                under.setColorFill(baseColor);
                // 水印文字成30度角倾斜
                for (int height = interval + textH; height < pageRect.getHeight();
                     height = height + textH * 3) {
                    for (int width = interval + textW; width < pageRect.getWidth() + textW;
                         width = width + textW * 2) {
                        under.showTextAligned(Element.ALIGN_LEFT
                                , waterMarkName, width - textW,
                                height - textH, 30);
                        under.setGState(gs);
                    }
                }
                // 添加水印文字
                under.endText();
            }
            gs.setFillOpacity(imageFillOpacity);//改透明度
            if (null != img) {
                // 设置图片水印
                PdfContentByte over;
                for (int i = 1; i < total; i++) {
                    pageRect = reader.getPageSizeWithRotation(i);
                    for (int height = interval + textH; height < pageRect.getHeight();
                         height = height + textH * 3) {
                        for (int width = interval + textW; width < pageRect.getWidth() + textW;
                             width = width + textW * 2) {
                            over = stamper.getOverContent(i);
                            over.setGState(gs);
                            img.setAbsolutePosition(width - textW + moveW,height - textH + moveH);
                            img.setRotationDegrees(30);
                            img.setAlignment(Element.ALIGN_LEFT);
                            over.addImage(img);
                        }
                    }
                }
            }
            stamper.close();
            reader.close();
        } catch (Exception e) {
            log.error("工具类给PDF添加水印(文字加图片)异常"+ e.getMessage());
        }
    }

    /**
     * 可编辑类型pdf转图片类型pdf
     * @param inputFile 可编辑的pdf文件
     * @param outputFile 图片型pdf文件
     * @throws IOException
     */
    public static void ImagesToOnePdf(String inputFile, String outputFile) throws IOException {
        // 将inputStream文件流转成pdf文档里
        PDDocument docfile = PDDocument.load(new File(inputFile));
        // PDFRenderer将pdf转化成图片
        PDFRenderer renderer = new PDFRenderer(docfile);
        // 图片合成后的pdf文档类
        PDDocument doc = new PDDocument();

        PDPage page;
        PDImageXObject pdImage;
        PDPageContentStream contents;
        float w, h;
        int pageCount = docfile.getNumberOfPages();
        for (int i = 0; i < pageCount; i++) {
            // 144 高清图片  72 放大时模糊
            BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
            pdImage = LosslessFactory.createFromImage(doc, image);
            w = 510;
            h = 680;
            page = new PDPage(new PDRectangle(w, h));
            contents = new PDPageContentStream(doc, page);
            contents.drawImage(pdImage, 0, 0, w, h);
            contents.close();
            doc.addPage(page);
        }
        //保存pdf
        doc.save(new FileOutputStream(outputFile));
        //关闭pdf
        doc.close();
    }
}
