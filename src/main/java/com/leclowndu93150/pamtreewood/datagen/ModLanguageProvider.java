package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.init.ModBoats;
import com.leclowndu93150.pamtreewood.init.ModItems;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, PamTreeWood.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        // Creative tab
        add("itemGroup.pamtreewood.pam_tree_wood", "Pam's Tree Wood");

        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            generateWoodTranslations(woodType);
        }
    }

    private void generateWoodTranslations(WoodTypeVariant woodType) {
        String name = woodType.getName();
        String displayName = formatDisplayName(name);

        // Blocks
        add(ModBlocks.LEAVES.get(woodType).get(), displayName + " Leaves");
        add(ModBlocks.LOGS.get(woodType).get(), displayName + " Log");
        add(ModBlocks.WOOD.get(woodType).get(), displayName + " Wood");
        add(ModBlocks.STRIPPED_LOGS.get(woodType).get(), "Stripped " + displayName + " Log");
        add(ModBlocks.STRIPPED_WOOD.get(woodType).get(), "Stripped " + displayName + " Wood");
        add(ModBlocks.PLANKS.get(woodType).get(), displayName + " Planks");
        add(ModBlocks.SLABS.get(woodType).get(), displayName + " Slab");
        add(ModBlocks.STAIRS.get(woodType).get(), displayName + " Stairs");
        add(ModBlocks.DOORS.get(woodType).get(), displayName + " Door");
        add(ModBlocks.TRAPDOORS.get(woodType).get(), displayName + " Trapdoor");
        add(ModBlocks.FENCES.get(woodType).get(), displayName + " Fence");
        add(ModBlocks.FENCE_GATES.get(woodType).get(), displayName + " Fence Gate");
        add(ModBlocks.PRESSURE_PLATES.get(woodType).get(), displayName + " Pressure Plate");
        add(ModBlocks.BUTTONS.get(woodType).get(), displayName + " Button");
        add(ModBlocks.SIGNS.get(woodType).get(), displayName + " Sign");
        add(ModBlocks.HANGING_SIGNS.get(woodType).get(), displayName + " Hanging Sign");

        // Items (boats and special items)
        add(ModItems.BOAT_ITEMS.get(woodType).get(), displayName + " Boat");
        add(ModItems.CHEST_BOAT_ITEMS.get(woodType).get(), displayName + " Boat with Chest");
        
        // Entities
        add(ModBoats.BOAT_ENTITIES.get(woodType).get(), displayName + " Boat");
        add(ModBoats.CHEST_BOAT_ENTITIES.get(woodType).get(), displayName + " Boat with Chest");
    }

    private String formatDisplayName(String name) {
        // Convert snake_case to Title Case
        String[] words = name.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }
}