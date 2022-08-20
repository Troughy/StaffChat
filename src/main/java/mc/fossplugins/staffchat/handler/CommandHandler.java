package mc.fossplugins.staffchat.handler;

import mc.fossplugins.staffchat.StaffChat;
import mc.fossplugins.staffchat.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandHandler implements CommandExecutor {

    //Hopefully @NotNull is there in java 8
    private final StaffChat staffChat;
    public CommandHandler(StaffChat staffChat) {
        this.staffChat = staffChat;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staffchat")) {
            if (!sender.hasPermission("staffchat.cmd")) {
                sender.sendMessage(Objects.requireNonNull(StringUtil.translate("no-perm"), "StringUtil#translate(\"no-perm\") returned null."));
                return false;
            }
            if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) {
                //TODO: Add github repo link
                sender.sendMessage(Objects.requireNonNull(StringUtil.translate("cmd-usage"), "Corrupt lang file. Please report this issue on Github"));
                return false;
            }
            sender.sendMessage(staffChat.reloadConfigs() ? StringUtil.translate("reload-success") : StringUtil.translate("reload-err"));
        }
        return false;
    }
}
