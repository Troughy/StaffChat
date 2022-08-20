package mc.fossplugins.staffchat.util;

import mc.fossplugins.staffchat.type.InvalidConfigException;
import mc.fossplugins.staffchat.type.WTFException;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class ConfigValidator {

    private static void validateConfigNoValueCheck(FileConfiguration configuration, Set<String> expectedKeys, boolean extraKeysAllowed) throws InvalidConfigException {
        for (String key : configuration.getKeys(true)) {
            if (!extraKeysAllowed && !expectedKeys.contains(key)) throw new InvalidConfigException("Unexpected key '"+key+"'");
        }
    }

    public static void validateConfig(FileConfiguration configuration, Set<String> expectedKeys, HashMap<String, Object> expectedValues) throws InvalidConfigException, WTFException {
        validateConfig(configuration, expectedKeys, expectedValues, false);
    }

    @SuppressWarnings("unchecked")
    public static void validateConfig(FileConfiguration configuration, Set<String> expectedKeys, HashMap<String, Object> expectedValues, boolean extraKeysAllowed) throws InvalidConfigException, WTFException {
        validateConfigNoValueCheck(configuration, expectedKeys, extraKeysAllowed);
        for (String key : expectedValues.keySet()) {
            if (!expectedValues.containsKey(key)) continue;
            if (expectedValues.get(key) instanceof String) {
                if (!Objects.requireNonNull(configuration.getString(key), "configuration#getString returned null. (Check expectedKeys)").matches(((String) expectedValues.get(key)))) throw new InvalidConfigException("Regex mismatch! Key: "+key+ " value: "+configuration.getString(key)+ " expected regex: "+expectedValues.get(key));
            } else if (expectedValues.get(key) instanceof Set<?>) {
                if (!((Set<String>)expectedValues.get(key)).contains(configuration.getString(key)))
                    throw new InvalidConfigException("Unexpected value '"+configuration.getString(key)+"' (key: '"+key+"')");
            } else throw new WTFException("Expected value is neither instance of String nor Set<?>.");
        }
    }

}
