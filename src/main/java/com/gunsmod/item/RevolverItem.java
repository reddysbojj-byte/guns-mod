package com.gunsmod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import java.util.List;

/**
 * Revolver: 6 bullets, 14 damage, slow cooldown (14 ticks).
 */
public class RevolverItem extends GunItem {
    public RevolverItem(Settings settings) {
        super(6, 14f, 4.0f, 14, 1, settings);
    }

    @Override
    protected float getPitch() { return 0.8f; }

    @Override
    protected void appendGunTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {
        tooltip.add(new LiteralText("§c⚔ Урон: §f14  §7| §e⚡ Скорость: §fМедленно"));
    }
}
