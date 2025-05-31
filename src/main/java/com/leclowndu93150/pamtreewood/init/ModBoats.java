package com.leclowndu93150.pamtreewood.init;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.entity.ModBoatEntity;
import com.leclowndu93150.pamtreewood.entity.ModChestBoatEntity;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModBoats {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PamTreeWood.MODID);

    public static final Map<WoodTypeVariant, RegistryObject<EntityType<ModBoatEntity>>> BOAT_ENTITIES = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<EntityType<ModChestBoatEntity>>> CHEST_BOAT_ENTITIES = new HashMap<>();

    static {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            String name = woodType.getName();

            BOAT_ENTITIES.put(woodType, ENTITY_TYPES.register(name + "_boat",
                    () -> EntityType.Builder.<ModBoatEntity>of((type, world) -> new ModBoatEntity(type, world, woodType), MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(name + "_boat")));

            CHEST_BOAT_ENTITIES.put(woodType, ENTITY_TYPES.register(name + "_chest_boat",
                    () -> EntityType.Builder.<ModChestBoatEntity>of((type, world) -> new ModChestBoatEntity(type, world, woodType), MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(name + "_chest_boat")));
        }
    }

    public static RegistryObject<EntityType<ModBoatEntity>> getBoatEntity(WoodTypeVariant woodType) {
        return BOAT_ENTITIES.get(woodType);
    }

    public static RegistryObject<EntityType<ModChestBoatEntity>> getChestBoatEntity(WoodTypeVariant woodType) {
        return CHEST_BOAT_ENTITIES.get(woodType);
    }
}