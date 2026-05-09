package com.gunsmod.item;

import com.gunsmod.ModItems;
import com.gunsmod.entity.BulletEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Base class for all guns.
 * NBT keys:
 *   "ammo"   – bullets currently loaded
 *   "reloading" – (unused stub for future animation)
 */
public abstract class GunItem extends Item {

    protected final int maxAmmo;
    protected final float damage;
    protected final float speed;       // projectile velocity
    protected final int cooldownTicks;
    protected final int bulletsPerShot; // shotgun > 1

    public GunItem(int maxAmmo, float damage, float speed, int cooldownTicks, int bulletsPerShot, Settings settings) {
        super(settings.maxCount(1));
        this.maxAmmo       = maxAmmo;
        this.damage        = damage;
        this.speed         = speed;
        this.cooldownTicks = cooldownTicks;
        this.bulletsPerShot = bulletsPerShot;
    }

    // ── Right-click = shoot or reload ─────────────────────────────────────
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        int ammo = getAmmo(stack);

        // Reload if empty
        if (ammo <= 0) {
            return reload(world, player, stack);
        }

        // Shoot
        if (!world.isClient) {
            for (int i = 0; i < bulletsPerShot; i++) {
                BulletEntity bullet = new BulletEntity(world, player, damage);
                Vec3d look = player.getRotationVec(1.0f);
                double spread = bulletsPerShot > 1 ? (world.getRandom().nextDouble() - 0.5) * 0.3 : 0;
                bullet.setVelocity(
                    look.x + spread,
                    look.y + spread,
                    look.z + spread,
                    speed, 0.0f
                );
                world.spawnEntity(bullet);
            }
            setAmmo(stack, ammo - 1);
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0f, getPitch());
        }

        player.getItemCooldownManager().set(this, cooldownTicks);
        return TypedActionResult.success(stack, world.isClient());
    }

    // ── Reload ───────────────────────────────────────────────────────────
    protected TypedActionResult<ItemStack> reload(World world, PlayerEntity player, ItemStack stack) {
        // Count bullets in inventory
        int available = 0;
        for (ItemStack inv : player.inventory.main) {
            if (inv.getItem() == ModItems.BULLET) available += inv.getCount();
        }
        if (player.isCreative()) available = maxAmmo;

        if (available == 0) {
            if (world.isClient) player.sendMessage(new LiteralText("§cНет патронов!"), true);
            return TypedActionResult.fail(stack);
        }

        int toLoad = maxAmmo - getAmmo(stack);
        int loaded = 0;

        if (!player.isCreative()) {
            for (ItemStack inv : player.inventory.main) {
                if (inv.getItem() == ModItems.BULLET && loaded < toLoad) {
                    int take = Math.min(inv.getCount(), toLoad - loaded);
                    inv.decrement(take);
                    loaded += take;
                }
            }
        } else {
            loaded = toLoad;
        }

        setAmmo(stack, getAmmo(stack) + loaded);
        if (!world.isClient) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        if (world.isClient) player.sendMessage(new LiteralText("§aПерезаряжено! Патроны: " + getAmmo(stack) + "/" + maxAmmo), true);
        return TypedActionResult.success(stack, world.isClient());
    }

    // ── NBT helpers ───────────────────────────────────────────────────────
    public int getAmmo(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("ammo")) tag.putInt("ammo", maxAmmo);
        return tag.getInt("ammo");
    }

    public void setAmmo(ItemStack stack, int ammo) {
        stack.getOrCreateTag().putInt("ammo", Math.max(0, ammo));
    }

    protected float getPitch() { return 1.0f; }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {
        int ammo = getAmmo(stack);
        tooltip.add(new LiteralText("§6Патроны: §f" + ammo + " / " + maxAmmo));
        tooltip.add(new LiteralText("§7ПКМ — стрелять  |  ПКМ без патронов — перезарядить"));
        appendGunTooltip(stack, world, tooltip, ctx);
    }

    protected void appendGunTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext ctx) {}

    // Guns don't stack, but still show durability bar as ammo indicator
    @Override
    public boolean isItemBarVisible(ItemStack stack) { return true; }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f * getAmmo(stack) / maxAmmo);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        float f = (float) getAmmo(stack) / maxAmmo;
        return f > 0.5f ? 0x00AA00 : f > 0.2f ? 0xFFAA00 : 0xFF2200;
    }
}
