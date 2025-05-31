package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PamTreeWood.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            generateWoodSet(woodType);
        }
    }

    private void generateWoodSet(WoodTypeVariant woodType) {
        String name = woodType.getName();

        // Leaves
        simpleBlockWithItem(ModBlocks.LEAVES.get(woodType).get(), cubeAll(ModBlocks.LEAVES.get(woodType).get()));

        // Logs - proper axis handling
        logBlock(ModBlocks.LOGS.get(woodType).get());
        logBlock(ModBlocks.STRIPPED_LOGS.get(woodType).get());

        // Planks
        simpleBlockWithItem(ModBlocks.PLANKS.get(woodType).get(), cubeAll(ModBlocks.PLANKS.get(woodType).get()));

        // Slabs and Stairs
        slabBlock(ModBlocks.SLABS.get(woodType).get(),
                modLoc("block/" + name + "_planks"),
                modLoc("block/" + name + "_planks"));

        stairsBlock(ModBlocks.STAIRS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        // Doors and Trapdoors
        doorBlockWithRenderType(ModBlocks.DOORS.get(woodType).get(),
                modLoc("block/" + name + "_door_bottom"),
                modLoc("block/" + name + "_door_top"), "cutout");

        trapdoorBlockWithRenderType(ModBlocks.TRAPDOORS.get(woodType).get(),
                modLoc("block/" + name + "_trapdoor"), true, "cutout");

        // Fences
        fenceBlock(ModBlocks.FENCES.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        fenceGateBlock(ModBlocks.FENCE_GATES.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        // Pressure Plates and Buttons
        pressurePlateBlock(ModBlocks.PRESSURE_PLATES.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        buttonBlock(ModBlocks.BUTTONS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        // Signs
        signBlock(ModBlocks.SIGNS.get(woodType).get(),
                ModBlocks.WALL_SIGNS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        hangingSignBlock(ModBlocks.HANGING_SIGNS.get(woodType).get(),
                ModBlocks.WALL_HANGING_SIGNS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));
    }

    public void logBlock(RotatedPillarBlock block) {
        axisBlock(block, blockTexture(block), extend(blockTexture(block), "_top"));
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    private void hangingSignBlock(Block hangingSign, Block wallHangingSign, ResourceLocation texture) {
        String signName = hangingSign.getDescriptionId().replace("block." + PamTreeWood.MODID + ".", "");
        ModelFile hangingSignModel = models().sign(signName, texture);
        hangingSignBlock(hangingSign, wallHangingSign, hangingSignModel);
    }

    private void hangingSignBlock(Block hangingSign, Block wallHangingSign, ModelFile model) {
        simpleBlock(hangingSign, model);
        simpleBlock(wallHangingSign, model);
    }
}