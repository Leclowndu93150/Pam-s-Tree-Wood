package com.leclowndu93150.pamtreewood.item;

import com.leclowndu93150.pamtreewood.entity.ModBoatEntity;
import com.leclowndu93150.pamtreewood.entity.ModChestBoatEntity;
import com.leclowndu93150.pamtreewood.init.ModBoats;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ModBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final WoodTypeVariant woodType;
    private final boolean hasChest;

    public ModBoatItem(boolean hasChest, WoodTypeVariant woodType, Properties properties) {
        super(properties);
        this.hasChest = hasChest;
        this.woodType = woodType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack);
        }

        Vec3 viewVector = player.getViewVector(1.0F);
        List<Entity> entities = level.getEntities(player,
                player.getBoundingBox().expandTowards(viewVector.scale(5.0)).inflate(1.0),
                ENTITY_PREDICATE);

        if (!entities.isEmpty()) {
            Vec3 eyePosition = player.getEyePosition();
            for (Entity entity : entities) {
                AABB boundingBox = entity.getBoundingBox().inflate(entity.getPickRadius());
                if (boundingBox.contains(eyePosition)) {
                    return InteractionResultHolder.pass(itemStack);
                }
            }
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            Boat boat = getBoat(level, hitResult);
            boat.setYRot(player.getYRot());

            if (!level.noCollision(boat, boat.getBoundingBox())) {
                return InteractionResultHolder.fail(itemStack);
            }

            if (!level.isClientSide) {
                level.addFreshEntity(boat);
                level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }

        return InteractionResultHolder.pass(itemStack);
    }

    private Boat getBoat(Level level, HitResult hitResult) {
        Vec3 location = hitResult.getLocation();
        if (hasChest) {
            ModChestBoatEntity chestBoat = new ModChestBoatEntity(ModBoats.getChestBoatEntity(woodType).get(), level, woodType);
            chestBoat.setPos(location.x, location.y, location.z);
            chestBoat.setModWoodType(woodType);
            return chestBoat;
        } else {
            ModBoatEntity boat = new ModBoatEntity(ModBoats.getBoatEntity(woodType).get(), level, woodType);
            boat.setPos(location.x, location.y, location.z);
            boat.setModWoodType(woodType);
            return boat;
        }
    }
}