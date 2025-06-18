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

        simpleBlockWithItem(ModBlocks.LEAVES.get(woodType).get(), cubeAll(ModBlocks.LEAVES.get(woodType).get()));

        logBlock(ModBlocks.LOGS.get(woodType).get());
        woodBlock(ModBlocks.WOOD.get(woodType).get());
        logBlock(ModBlocks.STRIPPED_LOGS.get(woodType).get());
        woodBlock(ModBlocks.STRIPPED_WOOD.get(woodType).get());

        simpleBlockWithItem(ModBlocks.PLANKS.get(woodType).get(), cubeAll(ModBlocks.PLANKS.get(woodType).get()));

        slabBlock(ModBlocks.SLABS.get(woodType).get(),
                modLoc("block/" + name + "_planks"),
                modLoc("block/" + name + "_planks"));

        stairsBlock(ModBlocks.STAIRS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        doorBlockWithRenderType(ModBlocks.DOORS.get(woodType).get(),
                modLoc("block/" + name + "_door_bottom"),
                modLoc("block/" + name + "_door_top"), "cutout");

        trapdoorBlockWithRenderType(ModBlocks.TRAPDOORS.get(woodType).get(),
                modLoc("block/" + name + "_trapdoor"), true, "cutout");

        fenceBlock(ModBlocks.FENCES.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        fenceGateBlock(ModBlocks.FENCE_GATES.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        pressurePlateBlock(ModBlocks.PRESSURE_PLATES.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        buttonBlock(ModBlocks.BUTTONS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));

        generateSignModels(woodType);

  
        generateHangingSignModels(woodType);
    }

    public void logBlock(RotatedPillarBlock block) {
        axisBlock(block, blockTexture(block), extend(blockTexture(block), "_top"));
    }

    public void woodBlock(RotatedPillarBlock block) {
        String blockName = block.getDescriptionId().replace("block.pamtreewood.", "");
        ResourceLocation barkTexture;
        
        if (blockName.startsWith("stripped_")) {
            String logName = blockName.replace("_wood", "_log");
            barkTexture = modLoc("block/" + logName);
        } else {
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
        signBlock(ModBlocks.SIGNS.get(woodType).get(), 
                ModBlocks.WALL_SIGNS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));
    }

    private void generateHangingSignModels(WoodTypeVariant woodType) {
        String name = woodType.getName();
        hangingSignBlock(ModBlocks.HANGING_SIGNS.get(woodType).get(),
                ModBlocks.WALL_HANGING_SIGNS.get(woodType).get(),
                modLoc("block/" + name + "_planks"));
    }
    
    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return block.getDescriptionId().replace("block." + PamTreeWood.MODID + ".", "");
    }
}