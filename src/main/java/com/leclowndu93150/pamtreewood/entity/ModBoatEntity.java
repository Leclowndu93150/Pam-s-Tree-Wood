package com.leclowndu93150.pamtreewood.entity;

import com.leclowndu93150.pamtreewood.init.ModItems;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<String> DATA_ID_WOOD_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.STRING);

    public ModBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public ModBoatEntity(EntityType<? extends Boat> entityType, Level level, WoodTypeVariant woodType) {
        this(entityType, level);
        setModWoodType(woodType);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_WOOD_TYPE, WoodTypeVariant.MAPLE.getName());
    }

    public WoodTypeVariant getModWoodType() {
        try {
            String woodTypeName = this.entityData.get(DATA_ID_WOOD_TYPE);
            return WoodTypeVariant.valueOf(woodTypeName.toUpperCase());
        } catch (Exception e) {
            return WoodTypeVariant.MAPLE;
        }
    }

    public void setModWoodType(WoodTypeVariant woodType) {
        this.entityData.set(DATA_ID_WOOD_TYPE, woodType.getName());
    }

    @Override
    public Item getDropItem() {
        return ModItems.BOAT_ITEMS.get(getModWoodType()).get();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("ModWoodType", getModWoodType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("ModWoodType")) {
            try {
                setModWoodType(WoodTypeVariant.valueOf(tag.getString("ModWoodType").toUpperCase()));
            } catch (IllegalArgumentException e) {
                setModWoodType(WoodTypeVariant.MAPLE);
            }
        }
    }
}