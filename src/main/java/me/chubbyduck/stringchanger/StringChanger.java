package me.chubbyduck.stringchanger;

import lombok.Getter;
import lombok.SneakyThrows;
import me.chubbyduck.stringchanger.utils.GsonUtils;

import java.io.File;

public class StringChanger {

    @Getter
    private final Config config;

    /**
     * Start the program
     * @param args The path for the configuration
     */
    public static void main(String[] args) {
        File file = new File(getBaseDirectory(), "config.json");

        if(args.length > 0 && args[0].endsWith("json")) {
            file = new File(getBaseDirectory(), args[0]);
        }

        new StringChanger(file);
    }

    /**
     * Initialize and start the String Changer
     * @param file The config file
     */
    public StringChanger(File file) {
        this.config = GsonUtils.load(file, Config.class);

        for(String directory : config.getIncludedDirectories()) {

            File directoryFile = new File(directory.replace("<base>", config.getBaseDirectory()));
            new FileProcessor(config, directoryFile);
        }
    }

    /**
     * Get the current directory that the jar is run in
     * @return The file directory
     */
    @SneakyThrows
    public static File getBaseDirectory() {
        return new File(
                new File(".").getCanonicalPath()
        );
    }

}
