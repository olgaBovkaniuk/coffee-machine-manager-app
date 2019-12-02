package by.pvt.service;

import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.imgscalr.Scalr.crop;
import static org.imgscalr.Scalr.resize;

@Service
public class ImageService {

    public void saveSmallAndLargeImages(File file, String uploadDirPathSmall, String uploadDirPathLarge) throws IOException {

        BufferedImage imageSmall = ImageIO.read(file);

        BufferedImage small = resizeImage(imageSmall, ImageSize.SMALL);

        File smallOutput = new File(uploadDirPathSmall);
        ImageIO.write(small, "jpg", smallOutput);

        BufferedImage imageLarge = ImageIO.read(file);
        BufferedImage large = resizeImage(imageLarge, ImageSize.LARGE);

        File largeOut = new File(uploadDirPathLarge);
        ImageIO.write(large, "jpg", largeOut);

    }

    private BufferedImage resizeImage(BufferedImage image, ImageSize expectedSize) {
        BufferedImage resized = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(image, 0, 0, null);

        int width = image.getWidth();
        int height = image.getHeight();
        if (width <= expectedSize.width && height <= expectedSize.height) {
            return image;
        }

        BufferedImage resizedImage;
        if (image.getWidth() > image.getHeight()) {
            resizedImage = resize(image, Mode.FIT_TO_HEIGHT, expectedSize.width, expectedSize.height);
            resizedImage = crop(resizedImage, (resizedImage.getWidth() - expectedSize.width) / 2, 0, expectedSize.width, expectedSize.height);
        } else {
            resizedImage = resize(image, Mode.FIT_TO_WIDTH, expectedSize.width, expectedSize.height);
            resizedImage = crop(resizedImage, 0, (resizedImage.getHeight() - expectedSize.height) / 2, expectedSize.width, expectedSize.height);
        }
        return resizedImage;
    }
}

