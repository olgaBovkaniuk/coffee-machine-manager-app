package by.pvt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class FileService {

    @Autowired
    ImageService imageService;

    private static Logger log = Logger.getLogger("FileService");
    private static final String UPLOAD_DIR_PATH = "/Users/mr_nikolasmirnov/projects/uploadDir/";
    private static final String DEFAULT_IMG__DIR_PATH = "/Users/mr_nikolasmirnov/projects/uploadDir/default/default.png";

    public boolean saveFile(MultipartFile file, UUID coffeeMachineId) {
        try {
            Files.createDirectory(Path.of(UPLOAD_DIR_PATH + coffeeMachineId));
            Files.createDirectory(Path.of(UPLOAD_DIR_PATH + coffeeMachineId + "/small/"));
            Files.createDirectory(Path.of(UPLOAD_DIR_PATH + coffeeMachineId + "/large/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream(UPLOAD_DIR_PATH + coffeeMachineId + "/" + coffeeMachineId + ".jpg")) {
            if (file.isEmpty()) return false;

            fos.write(file.getBytes());
            fos.flush();
            log.info("Image saved: " + file.toString());

            String uploadDirPathSmall = UPLOAD_DIR_PATH + coffeeMachineId + "/small/" + coffeeMachineId + ".jpg";
            String uploadDirPathLarge = UPLOAD_DIR_PATH + coffeeMachineId + "/large/" + coffeeMachineId + ".jpg";

            imageService.saveSmallAndLargeImages(getOriginalFile(coffeeMachineId), uploadDirPathSmall, uploadDirPathLarge);

            return true;
        } catch (IOException e) {
            log.severe(e.getMessage());
            return false;
        }
    }

    public File getOriginalFile(UUID coffeeMachineId) {
        String path = UPLOAD_DIR_PATH + coffeeMachineId + "/" + coffeeMachineId + ".jpg";
        if (Files.exists(Path.of(path))) {
            return new File(path);
        }
        return new File(DEFAULT_IMG__DIR_PATH);
    }

    public File getFileBySize(UUID coffeeMachineId, ImageSize size) {
        String path;
        if (size == ImageSize.SMALL) {
            path = UPLOAD_DIR_PATH + coffeeMachineId + "/small/" + coffeeMachineId + ".jpg";
        } else {
            path = UPLOAD_DIR_PATH + coffeeMachineId + "/large/" + coffeeMachineId + ".jpg";
        }
        if (Files.exists(Path.of(path))) {
            return new File(path);
        }
        return new File(DEFAULT_IMG__DIR_PATH);
    }
}
