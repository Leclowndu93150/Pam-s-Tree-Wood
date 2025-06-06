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
        woodBlock(ModBlocks.WOOD.get(woodType).get());
        logBlock(ModBlocks.STRIPPED_LOGS.get(woodType).get());
        woodBlock(ModBlocks.STRIPPED_WOOD.get(woodType).get());

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

        // Signs - generate proper models
        generateSignModels(woodType);

        // Hanging Signs - generate proper models  
        generateHangingSignModels(woodType);
    }

    public void logBlock(RotatedPillarBlock block) {
        axisBlock(block, blockTexture(block), extend(blockTexture(block), "_top"));
    }

    public void woodBlock(RotatedPillarBlock block) {
        String blockName = block.getDescriptionId().replace("block.pamtreewood.", "");
        ResourceLocation barkTexture;
        
        if (blockName.startsWith("stripped_")) {
            // For stripped wood, use the stripped log texture on all sides
            String logName = blockName.replace("_wood", "_log");
            barkTexture = modLoc("block/" + logName);
        } else {
            // For regular wood, use the regular log texture on all sides
            String logName = blockName.replace("_wood", "_log");
            barkTexture = modLoc("block/" + logName);
        }
        
        axisBlock(block, barkTexture, barkTexture);
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    private void generateSignModels(WoodTypeVariant woodType) {
        String name = woodType.getName();
        Block standingSign = ModBlocks.SIGNS.get(woodType).get();
        Block wallSign = ModBlocks.WALL_SIGNS.get(woodType).get();
        
        // Generate sign model with particle texture
        ModelFile signModel = models().withExistingParent(name + "_sign", "block/oak_sign")
                .texture("particle", modLoc("block/" + name + "_planks"));
        
        // Apply model to both standing and wall signs
        simpleBlock(standingSign, signModel);
        simpleBlock(wallSign, signModel);
    }

    private void generateHangingSignModels(WoodTypeVariant woodType) {
        String name = woodType.getName();
        Block hangingSign = ModBlocks.HANGING_SIGNS.get(woodType).get();
        Block wallHangingSign = ModBlocks.WALL_HANGING_SIGNS.get(woodType).get();
        
        // Generate hanging sign model with particle texture
        ModelFile hangingSignModel = models().withExistingParent(name + "_hanging_sign", "block/cherry_hanging_sign")
                .texture("particle", modLoc("block/stripped_" + name + "_log"));
        
        // Apply model to both hanging and wall hanging signs
        simpleBlock(hangingSign, hangingSignModel);
        simpleBlock(wallHangingSign, hangingSignModel);
    }
}