package mc.fossplugins.staffchat;

import mc.fossplugins.staffchat.handler.CommandHandler;
import mc.fossplugins.staffchat.listener.ChatEvent;
import mc.fossplugins.staffchat.type.InvalidConfigException;
import mc.fossplugins.staffchat.type.WTFException;
import mc.fossplugins.staffchat.util.ConfigValidator;
import mc.fossplugins.staffchat.util.LoggerUtil;
import mc.fossplugins.staffchat.util.PlaceholderUtil;
import mc.fossplugins.staffchat.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class StaffChat extends JavaPlugin {

    private FileConfiguration langConf;

    @Override
    public void onEnable() {
        if (!initConfigs()) return;
        checkDependencies();
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("staffchat"), "getCommand(\"staffchat\") returned null. perhaps another plugin registered this command?").setExecutor(new CommandHandler(this));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
    }

    private void checkDependencies() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PlaceholderUtil.setPAPI(true);
        }
    }

    public boolean reloadConfigs() {
        reloadConfig();
        if (!validateConfig()) return false;
        langConf = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(getResource("languages" + File.separator + getConfig().getString("lang") + ".yml"), "getResource returned null.")));
        StringUtil.setLanguageConf(langConf);
        PlaceholderUtil.setConfig(getConfig());
        return true;
    }

    private boolean validateConfig() {
        HashMap<String, Object> rulesConfig = new HashMap<>();
        rulesConfig.put("lang", Stream.of("en_US", "hu_HU").collect(Collectors.toCollection(HashSet::new)));
        try {
            ConfigValidator.validateConfig(getConfig(),
                    Stream.of("lang", "prefix", "msg-format").collect(Collectors.toCollection(HashSet::new)),
                    rulesConfig
                 );
            return true;
        } catch (InvalidConfigException | WTFException e) {
            e.printStackTrace();
            LoggerUtil.err("Selected language yaml cannot be validated. Report this issue on Github and provide your configuration files.");
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }
    }

    private boolean initConfigs() {
        saveDefaultConfig();
        if (!validateConfig()) return false;
        langConf = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(getResource("languages" + File.separator + getConfig().getString("lang") + ".yml"), "getResource returned null.")));
        StringUtil.setLanguageConf(langConf);
        PlaceholderUtil.setConfig(getConfig());
        return true;
    }
}
