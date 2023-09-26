package vn.aptech.powerofspeed.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    private static final String RESOURCE_PATH = "src/main/resources/static";
    private static File fileRoot = new File(RESOURCE_PATH);

    public static String UploadedFile(MultipartFile fromFile, String folderPath) throws IOException {
        Path toPath = Paths.get(fileRoot.getAbsolutePath() + "/" + folderPath);
        String uniqueFileName = null;

        try {
            if (fromFile != null) {

                if (ExistsPath(folderPath) == false) {
                    Files.createDirectory(toPath);
                }

                uniqueFileName = UUID.randomUUID().toString() + "_" + fromFile.getOriginalFilename().trim().replace("[^a-zA-Z0-9-_.]", "");

                try (InputStream inputStream = fromFile.getInputStream()) {
                    Files.copy(inputStream, toPath.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }

        return uniqueFileName;
    }

    public static boolean ExistsPath(String folderPath) {
        Path toPath = Paths.get(fileRoot.getAbsolutePath() + "/" + folderPath);
        return Files.exists(toPath);
    }

    public static boolean DeletedFile(String folderPath, String fileName) throws IOException {
        Path filePath = Paths.get(fileRoot.getAbsolutePath() + "/" + folderPath + "/" + fileName);
        return Files.deleteIfExists(filePath);
    }
}
