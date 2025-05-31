package com.leclowndu93150.pamtreewood.init;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PamTreeWood.MODID);

    public static final RegistryObject<BlockEntityType<SignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_sign",
            () -> {
                List<net.minecraft.world.level.block.Block> validBlocks = new ArrayList<>();
                for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                    validBlocks.add(ModBlocks.SIGNS.get(woodType).get());
                    validBlocks.add(ModBlocks.WALL_SIGNS.get(woodType).get());
                }
                return BlockEntityType.Builder.of(SignBlockEntity::new,
                        validBlocks.toArray(new net.minecraft.world.level.block.Block[0])).build(null);
            });

    public static final RegistryObject<BlockEntityType<HangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign",
            () -> {
                List<net.minecraft.world.level.block.Block> validBlocks = new ArrayList<>();
                for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                    validBlocks.add(ModBlocks.HANGING_SIGNS.get(woodType).get());
                    validBlocks.add(ModBlocks.WALL_HANGING_SIGNS.get(woodType).get());
                }
                return BlockEntityType.Builder.of(HangingSignBlockEntity::new,
                        validBlocks.toArray(new net.minecraft.world.level.block.Block[0])).build(null);
            });
}