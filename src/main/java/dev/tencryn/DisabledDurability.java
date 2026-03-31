package dev.tencryn;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import dev.tencryn.system.PlayerInventoryChange;

import javax.annotation.Nonnull;

public class DisabledDurability extends JavaPlugin {
    private static DisabledDurability instance;

    private final Config<MainConfig> config = this.withConfig("Config", MainConfig.CODEC);

    public DisabledDurability(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
    }

    protected void setup() {
        this.getEntityStoreRegistry().registerSystem(new PlayerInventoryChange());
    }

    public static DisabledDurability get() {
        return instance;
    }

    public MainConfig getConfig() {
        return this.config.get();
    }
}
