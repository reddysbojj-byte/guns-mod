package com.gunsmod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import java.util.List;

/**
 * Rifle: 10 bullets, 18 damage, very fast bullet, medium cooldown (8 ticks).
 * High-velocity bullet travels farther without drop.
 */
public class RifleItem extends GunItem {
    public RifleItem(Settings settings) {
        super(10, 18f, 6.0f, 8, 1, settings);
    }

    @Override
    protected float getPitch() { return 1.3f; }

    @Override
    protected void appendGunTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {
        tooltip.add(new LiteralText("§c⚔ Урон: §f18  §7| §e⚡ Пуля: §fСверхбыстро"));
        tooltip.add(new LiteralText("§bДальнобойное оружие снайпера!"));
    }
}
