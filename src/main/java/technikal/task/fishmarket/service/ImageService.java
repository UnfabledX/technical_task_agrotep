package technikal.task.fishmarket.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Log4j2
@Service
public class ImageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    @Transactional
    public void deleteImage(String imageFileName) {
        Path imagePath = Paths.get("public/images/" + imageFileName);
        try {
            Files.delete(imagePath);
        } catch (IOException ex) {
            log.error("Exception occurred during file deletion: {}", ex.getMessage());
        }
    }

    @Transactional
    public String saveImage(MultipartFile image) {
        String storageFileName = getFileName(image);
        Path uploadPath = Paths.get(uploadDir);

        try (InputStream inputStream = image.getInputStream()) {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.error("Exception occurred during file creation: {}", ex.getMessage());
        }
        return storageFileName;
    }

    @Transactional(readOnly = true)
    public byte[] getFileContent(String fileName) {
        Path path = Paths.get(uploadDir + fileName);
        try {
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            log.error("Exception occurred during file reading: {}", ex.getMessage());
            return new byte[0];
        }
    }

    private String getFileName(MultipartFile image) {
        Date catchDate = new Date();
        return catchDate.getTime() + "_" + image.getOriginalFilename();
    }
}
