package com.wechat.bitmap;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Java如何调整图片的大小？
 * https://blog.csdn.net/weixin_36279318/article/details/77447824
 * @author wangqx
 *
 */
public class ImageTest {

    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    public static void main(String [] args){

    try{

        BufferedImage originalImage = ImageIO.read(new File("D:\\1.png"));
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizeImageJpg = resizeImage(originalImage, type);
        ImageIO.write(resizeImageJpg, "jpg", new File("E:\\ target.jpg"));

        BufferedImage resizeImagePng = resizeImage(originalImage, type);
        ImageIO.write(resizeImagePng, "png", new File("E:\\target.jpg"));

        BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
        ImageIO.write(resizeImageHintJpg, "jpg", new File("E:\\target.jpg"));

        BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
        ImageIO.write(resizeImageHintPng, "png", new File("E:\\target.jpg"));

    }catch(IOException e){
        System.out.println(e.getMessage());
    }

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    g.dispose();

    return resizedImage;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){

    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    g.dispose();
    g.setComposite(AlphaComposite.Src);

    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_RENDERING,
    RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);

    return resizedImage;
    }
}
