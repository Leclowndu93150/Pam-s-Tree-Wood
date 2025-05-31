package com.leclowndu93150.pamtreewood.datagen;

import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.init.ModItems;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            generateWoodRecipes(consumer, woodType);
        }
    }

    private void generateWoodRecipes(Consumer<FinishedRecipe> consumer, WoodTypeVariant woodType) {
        ItemLike log = ModItems.getLogItem(woodType).get();
        ItemLike strippedLog = ModItems.getStrippedLogItem(woodType).get();
        ItemLike planks = ModItems.getPlankItem(woodType).get();
        String name = woodType.getName();

        // Planks from logs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                .requires(log)
                .unlockedBy("has_" + name + "_log", has(log))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                .requires(strippedLog)
                .unlockedBy("has_stripped_" + name + "_log", has(strippedLog))
                .save(consumer, name + "_planks_from_stripped_log");

        // Slabs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.SLAB_ITEMS.get(woodType).get(), 6)
                .pattern("###")
                .define('#', planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.STAIR_ITEMS.get(woodType).get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Door
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.DOOR_ITEMS.get(woodType).get(), 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Trapdoor
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.TRAPDOOR_ITEMS.get(woodType).get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Fence
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.FENCE_ITEMS.get(woodType).get(), 3)
                .pattern("#S#")
                .pattern("#S#")
                .define('#', planks)
                .define('S', Items.STICK)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Fence Gate
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.FENCE_GATE_ITEMS.get(woodType).get())
                .pattern("S#S")
                .pattern("S#S")
                .define('#', planks)
                .define('S', Items.STICK)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Pressure Plate
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModItems.PRESSURE_PLATE_ITEMS.get(woodType).get())
                .pattern("##")
                .define('#', planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Button
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModItems.BUTTON_ITEMS.get(woodType).get())
                .requires(planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Sign
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.SIGN_ITEMS.get(woodType).get(), 3)
                .pattern("###")
                .pattern("###")
                .pattern(" S ")
                .define('#', planks)
                .define('S', Items.STICK)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Hanging Sign
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.HANGING_SIGN_ITEMS.get(woodType).get(), 6)
                .pattern("C C")
                .pattern("###")
                .pattern("###")
                .define('#', strippedLog)
                .define('C', Items.CHAIN)
                .unlockedBy("has_stripped_" + name + "_log", has(strippedLog))
                .save(consumer);

        // Boat
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, ModItems.BOAT_ITEMS.get(woodType).get())
                .pattern("# #")
                .pattern("###")
                .define('#', planks)
                .unlockedBy("has_" + name + "_planks", has(planks))
                .save(consumer);

        // Chest Boat
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, ModItems.CHEST_BOAT_ITEMS.get(woodType).get())
                .requires(ModItems.BOAT_ITEMS.get(woodType).get())
                .requires(Blocks.CHEST)
                .unlockedBy("has_" + name + "_boat", has(ModItems.BOAT_ITEMS.get(woodType).get()))
                .save(consumer);

        // Stonecutting recipes for efficiency
        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.SLAB_ITEMS.get(woodType).get(), planks, 2);
        stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.STAIR_ITEMS.get(woodType).get(), planks);
    }
}