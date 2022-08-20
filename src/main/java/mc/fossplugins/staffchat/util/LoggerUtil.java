package mc.fossplugins.staffchat.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class LoggerUtil {

    public static void log(String s) {
        Bukkit.getConsoleSender().sendMessage(s);
    }

    public static void err(String s) {
        log(ChatColor.RED + s);
    }

}
