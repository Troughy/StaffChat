package mc.fossplugins.staffchat.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StaffChatUtil {

    public static void sendMessage(Player sender, String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staffchat.use")) {
                String toSend = StringUtil.colorize(PlaceholderUtil.setPlaceholders(sender, message));
                p.sendMessage(toSend);
                LoggerUtil.log(toSend);
            }
        }
    }

}
