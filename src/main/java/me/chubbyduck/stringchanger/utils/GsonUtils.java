package me.chubbyduck.stringchanger.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

@UtilityClass
public class GsonUtils {

    private final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .create();

    /**
     * This will load a config, and serialize the values that dont exist
     * @param file The file to read and write
     * @param clazz The class for the config
     * @param <T> The config type
     * @return The config
     */
    @SneakyThrows
    public <T> T load(File file, Class<T> clazz) {

        T object;

        if(!file.exists()) {
            object = GSON.fromJson("{}", clazz);
        } else {
            FileReader fileReader = new FileReader(file);

            object = GSON.fromJson(fileReader, clazz);
        }

        try(Writer writer = new FileWriter(file)) {
            GSON.toJson(object, writer);
        }

        FileReader fileReader = new FileReader(file);
        return GSON.fromJson(fileReader, clazz);
    }

}
