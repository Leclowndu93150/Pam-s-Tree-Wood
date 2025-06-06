package com.leclowndu93150.pamtreewood.block.entity;

import com.leclowndu93150.pamtreewood.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModSignBlockEntity extends SignBlockEntity {
    public ModSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_SIGN.get(), pos, state);
    }
    
    public ModSignBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}