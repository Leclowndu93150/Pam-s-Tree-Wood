package com.leclowndu93150.pamtreewood.init;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.item.ModBoatItem;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PamTreeWood.MODID);

    // Maps to store all wood items by type
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> LEAVES_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> LOG_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> WOOD_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> STRIPPED_LOG_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> STRIPPED_WOOD_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> PLANK_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> SLAB_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> STAIR_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> DOOR_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> TRAPDOOR_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> FENCE_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> FENCE_GATE_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> PRESSURE_PLATE_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<BlockItem>> BUTTON_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<SignItem>> SIGN_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<HangingSignItem>> HANGING_SIGN_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ModBoatItem>> BOAT_ITEMS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ModBoatItem>> CHEST_BOAT_ITEMS = new HashMap<>();

    static {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            registerWoodItemSet(woodType);
        }
    }

    private static void registerWoodItemSet(WoodTypeVariant woodType) {
        String name = woodType.getName();

        // Block items
        LEAVES_ITEMS.put(woodType, ITEMS.register(name + "_leaves",
                () -> new BlockItem(ModBlocks.LEAVES.get(woodType).get(), new Item.Properties())));

        LOG_ITEMS.put(woodType, ITEMS.register(name + "_log",
                () -> new BlockItem(ModBlocks.LOGS.get(woodType).get(), new Item.Properties())));

        WOOD_ITEMS.put(woodType, ITEMS.register(name + "_wood",
                () -> new BlockItem(ModBlocks.WOOD.get(woodType).get(), new Item.Properties())));

        STRIPPED_LOG_ITEMS.put(woodType, ITEMS.register("stripped_" + name + "_log",
                () -> new BlockItem(ModBlocks.STRIPPED_LOGS.get(woodType).get(), new Item.Properties())));

        STRIPPED_WOOD_ITEMS.put(woodType, ITEMS.register("stripped_" + name + "_wood",
                () -> new BlockItem(ModBlocks.STRIPPED_WOOD.get(woodType).get(), new Item.Properties())));

        PLANK_ITEMS.put(woodType, ITEMS.register(name + "_planks",
                () -> new BlockItem(ModBlocks.PLANKS.get(woodType).get(), new Item.Properties())));

        SLAB_ITEMS.put(woodType, ITEMS.register(name + "_slab",
                () -> new BlockItem(ModBlocks.SLABS.get(woodType).get(), new Item.Properties())));

        STAIR_ITEMS.put(woodType, ITEMS.register(name + "_stairs",
                () -> new BlockItem(ModBlocks.STAIRS.get(woodType).get(), new Item.Properties())));

        DOOR_ITEMS.put(woodType, ITEMS.register(name + "_door",
                () -> new BlockItem(ModBlocks.DOORS.get(woodType).get(), new Item.Properties())));

        TRAPDOOR_ITEMS.put(woodType, ITEMS.register(name + "_trapdoor",
                () -> new BlockItem(ModBlocks.TRAPDOORS.get(woodType).get(), new Item.Properties())));

        FENCE_ITEMS.put(woodType, ITEMS.register(name + "_fence",
                () -> new BlockItem(ModBlocks.FENCES.get(woodType).get(), new Item.Properties())));

        FENCE_GATE_ITEMS.put(woodType, ITEMS.register(name + "_fence_gate",
                () -> new BlockItem(ModBlocks.FENCE_GATES.get(woodType).get(), new Item.Properties())));

        PRESSURE_PLATE_ITEMS.put(woodType, ITEMS.register(name + "_pressure_plate",
                () -> new BlockItem(ModBlocks.PRESSURE_PLATES.get(woodType).get(), new Item.Properties())));

        BUTTON_ITEMS.put(woodType, ITEMS.register(name + "_button",
                () -> new BlockItem(ModBlocks.BUTTONS.get(woodType).get(), new Item.Properties())));

        // Signs
        SIGN_ITEMS.put(woodType, ITEMS.register(name + "_sign",
                () -> new SignItem(new Item.Properties().stacksTo(16),
                        ModBlocks.SIGNS.get(woodType).get(), ModBlocks.WALL_SIGNS.get(woodType).get())));

        HANGING_SIGN_ITEMS.put(woodType, ITEMS.register(name + "_hanging_sign",
                () -> new HangingSignItem(ModBlocks.HANGING_SIGNS.get(woodType).get(),
                        ModBlocks.WALL_HANGING_SIGNS.get(woodType).get(), new Item.Properties().stacksTo(16))));

        // Boats
        BOAT_ITEMS.put(woodType, ITEMS.register(name + "_boat",
                () -> new ModBoatItem(false, woodType, new Item.Properties().stacksTo(1))));

        CHEST_BOAT_ITEMS.put(woodType, ITEMS.register(name + "_chest_boat",
                () -> new ModBoatItem(true, woodType, new Item.Properties().stacksTo(1))));
    }

    public static RegistryObject<BlockItem> getLeavesItem(WoodTypeVariant woodType) {
        return LEAVES_ITEMS.get(woodType);
    }

    public static RegistryObject<BlockItem> getLogItem(WoodTypeVariant woodType) {
        return LOG_ITEMS.get(woodType);
    }

    public static RegistryObject<BlockItem> getWoodItem(WoodTypeVariant woodType) {
        return WOOD_ITEMS.get(woodType);
    }

    public static RegistryObject<BlockItem> getStrippedLogItem(WoodTypeVariant woodType) {
        return STRIPPED_LOG_ITEMS.get(woodType);
    }

    public static RegistryObject<BlockItem> getStrippedWoodItem(WoodTypeVariant woodType) {
        return STRIPPED_WOOD_ITEMS.get(woodType);
    }

    public static RegistryObject<BlockItem> getPlankItem(WoodTypeVariant woodType) {
        return PLANK_ITEMS.get(woodType);
    }
}