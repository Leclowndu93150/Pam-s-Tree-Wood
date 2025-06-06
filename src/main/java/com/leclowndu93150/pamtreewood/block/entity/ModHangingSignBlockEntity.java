package com.leclowndu93150.pamtreewood.block.entity;

import com.leclowndu93150.pamtreewood.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntity extends HangingSignBlockEntity {
    public ModHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOD_HANGING_SIGN.get(), pos, state);
    }
}