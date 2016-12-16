package functionality;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

/**
 * Created by Arkadiusz666 on 2016-11-15.
 */
public class FileOperator {
    //todo - get the path autmaticaly
    private static String saveFolderPath = "C:\\Users\\Arkadiusz666\\AppData\\Roaming\\DarkSoulsII\\01100001020d14b3";
    //todo - create backup if not present
    private static String backupFolderPath = "C:\\Users\\Arkadiusz666\\AppData\\Roaming\\DarkSoulsII\\01100001020d14b3\\backup";
    private static String saveFilename = "DARKSII0000.sl2";

    public static void saveGame() {
        Path from = Paths.get(saveFolderPath +"/"+saveFilename);
        Path to = Paths.get(backupFolderPath +"/"+saveFilename);
        try {
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadGame() {
        Path from = Paths.get(backupFolderPath +"/"+saveFilename);
        Path to = Paths.get(saveFolderPath +"/"+saveFilename);
        try {
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getGameSaveDate() {
        File file = new File(saveFolderPath +"/"+saveFilename);
        return getFileDate(file);
    }

    public static String getBackupSaveDate() {
        File file = new File(backupFolderPath +"/"+saveFilename);
        return getFileDate(file);
    }

    public static String getFileDate(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return sdf.format(file.lastModified());
    }
}
