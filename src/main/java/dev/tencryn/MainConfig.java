package dev.tencryn;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class MainConfig {
    public static final BuilderCodec<MainConfig> CODEC = BuilderCodec.builder(MainConfig.class, MainConfig::new)
            .append(new KeyedCodec<>("DisableItemDurability", Codec.BOOLEAN),
                    (config, value) -> config.disableItemDurability = value,
                    (config) -> config.disableItemDurability).add()
            .build();

    private boolean disableItemDurability = true;
    public boolean getDisableItemDurability() {
        return disableItemDurability;
    }
}
