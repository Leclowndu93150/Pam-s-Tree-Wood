package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class ModBlockLootTables extends BlockLootSubProvider {
        public ModBlockLootTables() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                generateWoodLootTables(woodType);
            }
        }

        private void generateWoodLootTables(WoodTypeVariant woodType) {
            dropSelf(ModBlocks.LEAVES.get(woodType).get());
            dropSelf(ModBlocks.LOGS.get(woodType).get());
            dropSelf(ModBlocks.WOOD.get(woodType).get());
            dropSelf(ModBlocks.STRIPPED_LOGS.get(woodType).get());
            dropSelf(ModBlocks.STRIPPED_WOOD.get(woodType).get());
            dropSelf(ModBlocks.PLANKS.get(woodType).get());
            dropSelf(ModBlocks.STAIRS.get(woodType).get());
            dropSelf(ModBlocks.FENCES.get(woodType).get());
            dropSelf(ModBlocks.FENCE_GATES.get(woodType).get());
            dropSelf(ModBlocks.PRESSURE_PLATES.get(woodType).get());
            dropSelf(ModBlocks.BUTTONS.get(woodType).get());
            dropSelf(ModBlocks.TRAPDOORS.get(woodType).get());
            dropSelf(ModBlocks.SIGNS.get(woodType).get());
            dropSelf(ModBlocks.HANGING_SIGNS.get(woodType).get());

            add(ModBlocks.SLABS.get(woodType).get(), createSlabItemTable(ModBlocks.SLABS.get(woodType).get()));
            add(ModBlocks.DOORS.get(woodType).get(), createDoorTable(ModBlocks.DOORS.get(woodType).get()));

            add(ModBlocks.WALL_SIGNS.get(woodType).get(),
                    createSingleItemTable(ModBlocks.SIGNS.get(woodType).get()));
            add(ModBlocks.WALL_HANGING_SIGNS.get(woodType).get(),
                    createSingleItemTable(ModBlocks.HANGING_SIGNS.get(woodType).get()));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(entry -> (Block) entry.get())::iterator;
        }
    }
}