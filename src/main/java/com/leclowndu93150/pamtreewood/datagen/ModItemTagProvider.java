package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.init.ModItems;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, PamTreeWood.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            generateWoodItemTags(woodType);
        }
    }

    private void generateWoodItemTags(WoodTypeVariant woodType) {
        Item leaves = ModItems.getLeavesItem(woodType).get();
        Item log = ModItems.getLogItem(woodType).get();
        Item strippedLog = ModItems.getStrippedLogItem(woodType).get();
        Item planks = ModItems.getPlankItem(woodType).get();
        Item slab = ModItems.SLAB_ITEMS.get(woodType).get();
        Item stairs = ModItems.STAIR_ITEMS.get(woodType).get();
        Item door = ModItems.DOOR_ITEMS.get(woodType).get();
        Item trapdoor = ModItems.TRAPDOOR_ITEMS.get(woodType).get();
        Item fence = ModItems.FENCE_ITEMS.get(woodType).get();
        Item fenceGate = ModItems.FENCE_GATE_ITEMS.get(woodType).get();
        Item pressurePlate = ModItems.PRESSURE_PLATE_ITEMS.get(woodType).get();
        Item button = ModItems.BUTTON_ITEMS.get(woodType).get();
        Item sign = ModItems.SIGN_ITEMS.get(woodType).get();
        Item hangingSign = ModItems.HANGING_SIGN_ITEMS.get(woodType).get();
        Item boat = ModItems.BOAT_ITEMS.get(woodType).get();
        Item chestBoat = ModItems.CHEST_BOAT_ITEMS.get(woodType).get();

        // Leaves
        tag(ItemTags.LEAVES).add(leaves);

        // Logs
        tag(ItemTags.LOGS).add(log, strippedLog);
        tag(ItemTags.LOGS_THAT_BURN).add(log, strippedLog);

        // Planks
        tag(ItemTags.PLANKS).add(planks);

        // Slabs and stairs
        tag(ItemTags.WOODEN_SLABS).add(slab);
        tag(ItemTags.SLABS).add(slab);
        tag(ItemTags.WOODEN_STAIRS).add(stairs);
        tag(ItemTags.STAIRS).add(stairs);

        // Doors and trapdoors
        tag(ItemTags.WOODEN_DOORS).add(door);
        tag(ItemTags.DOORS).add(door);
        tag(ItemTags.WOODEN_TRAPDOORS).add(trapdoor);
        tag(ItemTags.TRAPDOORS).add(trapdoor);

        // Fences
        tag(ItemTags.WOODEN_FENCES).add(fence);
        tag(ItemTags.FENCES).add(fence);
        tag(ItemTags.FENCE_GATES).add(fenceGate);

        // Pressure plates and buttons
        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(pressurePlate);
        tag(ItemTags.WOODEN_BUTTONS).add(button);

        // Signs
        tag(ItemTags.SIGNS).add(sign);
        tag(ItemTags.HANGING_SIGNS).add(hangingSign);

        // Boats
        tag(ItemTags.BOATS).add(boat);
        tag(ItemTags.CHEST_BOATS).add(chestBoat);
    }
}