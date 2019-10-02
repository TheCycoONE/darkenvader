package com.sealbaker.darkenvader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileSystem {
    private Path dataPath;

    public FileSystem() throws IOException {
        dataPath = Paths.get(getUserDataDir().toString(), "darkenvader");
        Files.createDirectories(dataPath);
    }

    public Path getDataPath() {
        return dataPath;
    }

    private Path getUserDataDir() {
        if (System.getProperty("os.name").contains("Windows")) {
            if (System.getenv("AppData") != null) {
                return Paths.get(System.getenv("AppData"));
            }
            return Paths.get(System.getProperty("user.home"));
        }
        if (System.getenv("XDG_DATA_HOME") != null) {
            return Paths.get(System.getenv("XDG_DATA_HOME"));
        }
        return Paths.get(System.getProperty("user.home"), ".local", "share");
    }
}
