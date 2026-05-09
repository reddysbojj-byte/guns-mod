package com.gunsmod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import java.util.List;

/**
 * Pistol: 12 bullets, 7 damage, fast cooldown (5 ticks).
 */
public class PistolItem extends GunItem {
    public PistolItem(Settings settings) {
        super(12, 7f, 3.5f, 5, 1, settings);
    }

    @Override
    protected void appendGunTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {
        tooltip.add(new LiteralText("§c⚔ Урон: §f7  §7| §e⚡ Скорость: §fБыстро"));
    }
}
