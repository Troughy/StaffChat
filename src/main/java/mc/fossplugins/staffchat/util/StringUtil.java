package mc.fossplugins.staffchat.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class StringUtil {

    private static FileConfiguration languageConf;

    public static void setLanguageConf(FileConfiguration newLanguageConf) {
        languageConf = newLanguageConf;
    }

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String translate(String key) {
        return colorize(Objects.requireNonNull(languageConf.getString(key), "languageConf#getString(key) returned null."));
    }

}
