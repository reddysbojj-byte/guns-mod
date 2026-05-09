package com.gunsmod;

import com.gunsmod.entity.BulletEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static EntityType<BulletEntity> BULLET;

    public static void register() {
        BULLET = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(GunsMod.MOD_ID, "bullet"),
            FabricEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC, BulletEntity::new)
                .dimensions(EntityDimensions.fixed(0.15f, 0.15f))
                .trackRangeBlocks(64)
                .trackedUpdateRate(1)
                .build()
        );
    }
}
