package mc.fossplugins.staffchat.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlaceholderUtil {

    private static boolean PAPI = false;
    public static void setPAPI(boolean newPAPI) {
        PAPI = newPAPI;
    }
    private static FileConfiguration config;
    public static void setConfig(FileConfiguration newConfig) {
        config = newConfig;
    }

    @SuppressWarnings("ReassignedVariable")
    public static String setPlaceholders(Player p, String message) {
        String retval = Objects.requireNonNull(config.getString("msg-format"), "config#getString returned null for 'msg-format'. config was not validated?");
        if (PAPI) {
            retval = PlaceholderAPI.setPlaceholders(p, retval);
        }
        return retval.replace("%name%", p.getName()).replace("%message%", message);
    }

}
