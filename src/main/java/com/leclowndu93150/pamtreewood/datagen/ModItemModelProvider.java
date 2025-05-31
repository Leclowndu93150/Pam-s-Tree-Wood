package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.init.ModItems;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PamTreeWood.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            generateWoodItemModels(woodType);
        }
    }

    private void generateWoodItemModels(WoodTypeVariant woodType) {
        String name = woodType.getName();

        // Block items that should use block models
        blockBasedItem(ModBlocks.LEAVES.get(woodType));
        blockBasedItem(ModBlocks.LOGS.get(woodType));
        blockBasedItem(ModBlocks.STRIPPED_LOGS.get(woodType));
        blockBasedItem(ModBlocks.PLANKS.get(woodType));
        blockBasedItem(ModBlocks.SLABS.get(woodType));
        blockBasedItem(ModBlocks.STAIRS.get(woodType));
        blockBasedItem(ModBlocks.PRESSURE_PLATES.get(woodType));

        // Special inventory models
        fenceItem(ModBlocks.FENCES.get(woodType), name);
        fenceGateItem(ModBlocks.FENCE_GATES.get(woodType), name);
        buttonItem(ModBlocks.BUTTONS.get(woodType), name);

        // Special items that need custom models
        simpleItem(ModItems.DOOR_ITEMS.get(woodType));
        trapdoorItem(ModBlocks.TRAPDOORS.get(woodType));

        // Signs (use generated item models)
        simpleItem(ModItems.SIGN_ITEMS.get(woodType));
        simpleItem(ModItems.HANGING_SIGN_ITEMS.get(woodType));

        // Boats (use generated item models)
        simpleItem(ModItems.BOAT_ITEMS.get(woodType));
        simpleItem(ModItems.CHEST_BOAT_ITEMS.get(woodType));
    }

    private ItemModelBuilder simpleItem(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PamTreeWood.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockBasedItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(PamTreeWood.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder trapdoorItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(PamTreeWood.MODID, "block/" + item.getId().getPath() + "_bottom"));
    }

    private ItemModelBuilder fenceItem(RegistryObject<? extends Block> fence, String textureName) {
        return withExistingParent(fence.getId().getPath(),
                new ResourceLocation("minecraft", "block/fence_inventory"))
                .texture("texture", new ResourceLocation(PamTreeWood.MODID, "block/" + textureName + "_planks"));
    }

    private ItemModelBuilder fenceGateItem(RegistryObject<? extends Block> fenceGate, String textureName) {
        return withExistingParent(fenceGate.getId().getPath(),
                new ResourceLocation(PamTreeWood.MODID, "block/" + textureName + "_fence_gate"));
    }

    private ItemModelBuilder buttonItem(RegistryObject<? extends Block> button, String textureName) {
        return withExistingParent(button.getId().getPath(),
                new ResourceLocation("minecraft", "block/button_inventory"))
                .texture("texture", new ResourceLocation(PamTreeWood.MODID, "block/" + textureName + "_planks"));
    }
}