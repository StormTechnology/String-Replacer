package me.chubbyduck.stringchanger;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class Config {

    private final String baseDirectory = "D:";

    private final List<String> includedDirectories = List.of(
            "<base>\\plugins"
    );

    private final List<String> excludedDirectories = List.of(
            "<base>\\plugins\\CloudCrates"
    );

    private final Map<String, String> replaceable = Collections.singletonMap("%%__NAME__%%", "Chubbyduck1");

    /**
     * This method replaces the replaceable strings
     * @param args The args to replace
     * @return The replaced args
     */
    public String replaceString(String args) {
        for(Map.Entry<String, String> replaceEntry : replaceable.entrySet()) {

            args = args.replace(replaceEntry.getKey(), replaceEntry.getValue());

        }

        return args;
    }

    /**
     * Check if a directory is excluded
     * @param filePath The file path to check
     * @return The {@link Boolean} value of if its excluded
     */
    public boolean isExcluded(String filePath) {
        for(String excluded : excludedDirectories) {

            if(filePath.equals(excluded.replace("<base>", baseDirectory))) {
                return true;
            }

        }

        return false;
    }

}
