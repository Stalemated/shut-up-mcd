package com.stalemated.mcdloretoggler.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static com.stalemated.mcdloretoggler.MCDLoreToggler.LOGGER;

public class MCDLoreTogglerConfig {
    // Default values
    public static boolean showMCDAFlavorLore = true;
    public static boolean showMCDAEffectLore = true;
    public static boolean showMCDWFlavorLore = true;
    public static boolean showMCDWEffectLore = true;

    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "mcd_lore_toggler.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                if (data != null) {
                    showMCDAFlavorLore = data.showMCDAFlavorLore;
                    showMCDAEffectLore = data.showMCDAEffectLore;
                    showMCDWFlavorLore = data.showMCDWFlavorLore;
                    showMCDWEffectLore = data.showMCDWEffectLore;
                }
            } catch (IOException e) {
                LOGGER.error("Config could not be loaded: ", e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.showMCDAFlavorLore = showMCDAFlavorLore;
        data.showMCDAEffectLore = showMCDAEffectLore;
        data.showMCDWFlavorLore = showMCDWFlavorLore;
        data.showMCDWEffectLore = showMCDWEffectLore;

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            LOGGER.error("Config could not be saved: ", e);
        }
    }

    private static class ConfigData {
        boolean showMCDAFlavorLore = MCDLoreTogglerConfig.showMCDAFlavorLore;
        boolean showMCDAEffectLore = MCDLoreTogglerConfig.showMCDAEffectLore;
        boolean showMCDWFlavorLore = MCDLoreTogglerConfig.showMCDWFlavorLore;
        boolean showMCDWEffectLore = MCDLoreTogglerConfig.showMCDWEffectLore;
    }
}