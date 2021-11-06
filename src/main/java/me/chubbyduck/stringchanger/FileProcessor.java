package me.chubbyduck.stringchanger;

import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileProcessor {

    private static final List<String> ALLOWED_FILES = List.of(
            "yaml",
            "yml",
            "json",
            "toml",
            "txt"
    );

    /**
     * Process a file or directory
     * @param config The config to use
     * @param file The file to check
     */
    @SneakyThrows
    public FileProcessor(Config config, File file) {
        if(file.isDirectory() && file.listFiles() != null) {

            for(File subFile : Objects.requireNonNull(file.listFiles())) {
                if(config.isExcluded(subFile.getPath())) {
                    continue;
                }

                new FileProcessor(config, subFile);
            }

            return;
        }

        if(!(ALLOWED_FILES.contains(getFileType(file)))) {
            return;
        }

        List<String> lines = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        );

        String line;

        while((line = bufferedReader.readLine()) != null) {
            lines.add(
                    config.replaceString(line)
            );
        }

        write(file, lines);
    }

    /**
     * Write the output to the file
     * @param file The file to output the lines to
     * @param lines The lines to output
     */
    @SneakyThrows
    private void write(File file, List<String> lines) {
        try(Writer writer = new FileWriter(file)) {

            for(int i=0; i < lines.size(); i++) {

                String line = lines.get(i);

                writer.append(line);

                if(i < lines.size() - 1) {
                    writer.append("\n");
                }
            }

            writer.flush();
        }
    }

    /**
     * Get the extension of a file
     * @param file The file to check
     * @return The string type
     */
    public String getFileType(File file) {
        String[] split = file.getName().split("\\.");

        return split[split.length - 1];
    }

}
