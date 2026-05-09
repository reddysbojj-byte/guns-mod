package com.gunsmod.entity;

import com.gunsmod.ModEntities;
import com.gunsmod.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class BulletEntity extends ThrownItemEntity {
    private float damage = 5.0f;

    public BulletEntity(EntityType<? extends BulletEntity> type, World world) {
        super(type, world);
    }

    public BulletEntity(World world, LivingEntity owner, float damage) {
        super(ModEntities.BULLET, owner, world);
        this.damage = damage;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BULLET;
    }

    @Override
    protected ItemStack getStack() {
        return new ItemStack(ModItems.BULLET);
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        super.onEntityHit(result);
        if (result.getEntity() instanceof LivingEntity target) {
            target.damage(DamageSource.thrownProjectile(this, this.getOwner()), damage);
        }
        this.remove();
    }
}
