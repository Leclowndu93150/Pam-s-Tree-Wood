package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PamTreeWood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            generateWoodTags(woodType);
        }
    }

    private void generateWoodTags(WoodTypeVariant woodType) {
        Block leaves = ModBlocks.LEAVES.get(woodType).get();
        Block log = ModBlocks.LOGS.get(woodType).get();
        Block strippedLog = ModBlocks.STRIPPED_LOGS.get(woodType).get();
        Block planks = ModBlocks.PLANKS.get(woodType).get();
        Block slab = ModBlocks.SLABS.get(woodType).get();
        Block stairs = ModBlocks.STAIRS.get(woodType).get();
        Block door = ModBlocks.DOORS.get(woodType).get();
        Block trapdoor = ModBlocks.TRAPDOORS.get(woodType).get();
        Block fence = ModBlocks.FENCES.get(woodType).get();
        Block fenceGate = ModBlocks.FENCE_GATES.get(woodType).get();
        Block pressurePlate = ModBlocks.PRESSURE_PLATES.get(woodType).get();
        Block button = ModBlocks.BUTTONS.get(woodType).get();
        Block sign = ModBlocks.SIGNS.get(woodType).get();
        Block wallSign = ModBlocks.WALL_SIGNS.get(woodType).get();
        Block hangingSign = ModBlocks.HANGING_SIGNS.get(woodType).get();
        Block wallHangingSign = ModBlocks.WALL_HANGING_SIGNS.get(woodType).get();

        // Leaves tags
        tag(BlockTags.LEAVES).add(leaves);
        tag(BlockTags.MINEABLE_WITH_HOE).add(leaves);

        // Log tags
        tag(BlockTags.LOGS).add(log, strippedLog);
        tag(BlockTags.LOGS_THAT_BURN).add(log, strippedLog);
        tag(BlockTags.MINEABLE_WITH_AXE).add(log, strippedLog);

        // Planks tags
        tag(BlockTags.PLANKS).add(planks);
        tag(BlockTags.MINEABLE_WITH_AXE).add(planks);

        // Slabs and stairs
        tag(BlockTags.WOODEN_SLABS).add(slab);
        tag(BlockTags.SLABS).add(slab);
        tag(BlockTags.WOODEN_STAIRS).add(stairs);
        tag(BlockTags.STAIRS).add(stairs);
        tag(BlockTags.MINEABLE_WITH_AXE).add(slab, stairs);

        // Doors and trapdoors
        tag(BlockTags.WOODEN_DOORS).add(door);
        tag(BlockTags.DOORS).add(door);
        tag(BlockTags.WOODEN_TRAPDOORS).add(trapdoor);
        tag(BlockTags.TRAPDOORS).add(trapdoor);
        tag(BlockTags.MINEABLE_WITH_AXE).add(door, trapdoor);

        // Fences
        tag(BlockTags.WOODEN_FENCES).add(fence);
        tag(BlockTags.FENCES).add(fence);
        tag(Tags.Blocks.FENCES).add(fence);
        tag(Tags.Blocks.FENCES_WOODEN).add(fence);
        tag(BlockTags.FENCE_GATES).add(fenceGate);
        tag(Tags.Blocks.FENCE_GATES).add(fenceGate);
        tag(Tags.Blocks.FENCE_GATES_WOODEN).add(fenceGate);
        tag(BlockTags.MINEABLE_WITH_AXE).add(fence, fenceGate);

        // Pressure plates and buttons
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(pressurePlate);
        tag(BlockTags.PRESSURE_PLATES).add(pressurePlate);
        tag(BlockTags.WOODEN_BUTTONS).add(button);
        tag(BlockTags.BUTTONS).add(button);
        tag(BlockTags.MINEABLE_WITH_AXE).add(pressurePlate, button);

        // Signs
        tag(BlockTags.STANDING_SIGNS).add(sign);
        tag(BlockTags.WALL_SIGNS).add(wallSign);
        tag(BlockTags.SIGNS).add(sign, wallSign);
        tag(BlockTags.CEILING_HANGING_SIGNS).add(hangingSign);
        tag(BlockTags.WALL_HANGING_SIGNS).add(wallHangingSign);
        tag(BlockTags.ALL_HANGING_SIGNS).add(hangingSign, wallHangingSign);
        tag(BlockTags.ALL_SIGNS).add(sign, wallSign, hangingSign, wallHangingSign);
        tag(BlockTags.MINEABLE_WITH_AXE).add(sign, wallSign, hangingSign, wallHangingSign);
    }
}