package com.gunsmod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import java.util.List;

/**
 * SMG: 30 bullets, 5 damage, extremely fast cooldown (2 ticks).
 */
public class SmgItem extends GunItem {
    public SmgItem(Settings settings) {
        super(30, 5f, 3.0f, 2, 1, settings);
    }

    @Override
    protected float getPitch() { return 1.6f; }

    @Override
    protected void appendGunTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {
        tooltip.add(new LiteralText("§c⚔ Урон: §f5  §7| §e⚡ Скорость: §fПулемётная!"));
        tooltip.add(new LiteralText("§a30 патронов в магазине!"));
    }
}
