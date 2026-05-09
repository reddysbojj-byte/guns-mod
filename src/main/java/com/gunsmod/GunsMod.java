package com.gunsmod;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GunsMod implements ModInitializer {
    public static final String MOD_ID = "gunsmod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEntities.register();
        ModItems.register();
        LOGGER.info("[GunsMod] Guns loaded! Lock and load.");
    }
}
