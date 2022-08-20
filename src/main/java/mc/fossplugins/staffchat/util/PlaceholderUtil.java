package mc.fossplugins.staffchat.util;

import mc.fossplugins.staffchat.StaffChat;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlaceholderUtil {

    private static FileConfiguration config;
    public static void setConfig(FileConfiguration newConfig) {
        config = newConfig;
    }

    public static String setPlaceholders(Player p, String message) {
        String retval = Objects.requireNonNull(config.getString("msg-format"), "config#getString returned null for 'msg-format'. config was not validated?");
        if (StaffChat.getPapi()) {
            retval = PlaceholderAPI.setPlaceholders(p, retval);
        }
        return retval.replace("%name%", p.getName()).replace("%message%", message);
    }

}
