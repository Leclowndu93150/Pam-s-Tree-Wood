package com.leclowndu93150.pamtreewood.init;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.block.entity.ModHangingSignBlockEntity;
import com.leclowndu93150.pamtreewood.block.entity.ModSignBlockEntity;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PamTreeWood.MODID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_sign",
            () -> {
                List<Block> validBlocks = new ArrayList<>();
                for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                    validBlocks.add(ModBlocks.SIGNS.get(woodType).get());
                    validBlocks.add(ModBlocks.WALL_SIGNS.get(woodType).get());
                }
                return BlockEntityType.Builder.of(ModSignBlockEntity::new,
                        validBlocks.toArray(new Block[0])).build(null);
            });

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign",
            () -> {
                List<Block> validBlocks = new ArrayList<>();
                for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                    validBlocks.add(ModBlocks.HANGING_SIGNS.get(woodType).get());
                    validBlocks.add(ModBlocks.WALL_HANGING_SIGNS.get(woodType).get());
                }
                return BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                        validBlocks.toArray(new Block[0])).build(null);
            });
}