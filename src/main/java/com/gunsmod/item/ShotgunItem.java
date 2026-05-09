package com.gunsmod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import java.util.List;

/**
 * Shotgun: 2 bullets loaded, fires 6 pellets each shot (6x5 = 30 dmg if all hit),
 * very slow cooldown (20 ticks = 1 sec).
 */
public class ShotgunItem extends GunItem {
    public ShotgunItem(Settings settings) {
        super(2, 5f, 2.5f, 20, 6, settings);
    }

    @Override
    protected float getPitch() { return 0.6f; }

    @Override
    protected void appendGunTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {
        tooltip.add(new LiteralText("§c⚔ Урон: §f5x6 (30 макс)  §7| §e⚡ Скорость: §fОчень медленно"));
        tooltip.add(new LiteralText("§6Разлёт дроби! Эффективен вблизи."));
    }
}
