package dev.tonimatas.mixedpaper;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;

public class MixedPaper implements ModInitializer {
    @Override
    public void onInitialize() {
        LogUtils.getLogger().info("MixedPaper has been started correctly.");
    }
}
