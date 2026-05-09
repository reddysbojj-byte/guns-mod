package com.gunsmod;

import com.gunsmod.item.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    // ── Creative tab ──────────────────────────────────────────────────────
    public static final ItemGroup GUNS_GROUP = new ItemGroup("gunsmod_guns") {
        @Override
        public net.minecraft.item.ItemStack createIcon() {
            return new net.minecraft.item.ItemStack(PISTOL);
        }
    };

    private static Item.Settings s() { return new Item.Settings().group(GUNS_GROUP); }

    // ── Materials ─────────────────────────────────────────────────────────
    public static final Item STEEL_INGOT  = reg("steel_ingot",  new Item(s()));
    public static final Item BULLET       = reg("bullet",       new Item(new Item.Settings().group(GUNS_GROUP).maxCount(64)));
    public static final Item BULLET_BOX   = reg("bullet_box",   new Item(new Item.Settings().group(GUNS_GROUP).maxCount(16)));
    public static final Item GUN_BARREL   = reg("gun_barrel",   new Item(s()));
    public static final Item GUN_HANDLE   = reg("gun_handle",   new Item(s()));
    public static final Item GUN_STOCK    = reg("gun_stock",    new Item(s()));

    // ── Guns ──────────────────────────────────────────────────────────────
    public static final Item PISTOL   = reg("pistol",   new PistolItem(s()));
    public static final Item REVOLVER = reg("revolver", new RevolverItem(s()));
    public static final Item RIFLE    = reg("rifle",    new RifleItem(s()));
    public static final Item SHOTGUN  = reg("shotgun",  new ShotgunItem(s()));
    public static final Item SMG      = reg("smg",      new SmgItem(s()));

    private static Item reg(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(GunsMod.MOD_ID, name), item);
    }

    public static void register() {
        GunsMod.LOGGER.info("[GunsMod] Registered all items.");
    }
}
