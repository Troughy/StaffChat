package mc.fossplugins.staffchat.listener;

import mc.fossplugins.staffchat.StaffChat;
import mc.fossplugins.staffchat.util.StaffChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    private final StaffChat staffChat;
    public ChatEvent(StaffChat staffChat) {
        this.staffChat = staffChat;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPermission("staffchat.use")) return;
        String msg = event.getMessage();
        String prefix = staffChat.getConfig().getString("prefix");
        assert prefix != null;
        if (msg.startsWith("\\"+prefix)) {
            event.setMessage(msg.substring(1));
            return;
        }
        if (!msg.startsWith(prefix)) return;
        StaffChatUtil.sendMessage(p, msg.substring(1));
        event.setCancelled(true);
    }
}
