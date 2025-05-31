package com.leclowndu93150.pamtreewood.init;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.block.ModCeilingHangingSignBlock;
import com.leclowndu93150.pamtreewood.block.ModStandingSignBlock;
import com.leclowndu93150.pamtreewood.block.ModWallHangingSignBlock;
import com.leclowndu93150.pamtreewood.block.ModWallSignBlock;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PamTreeWood.MODID);

    // Maps to store all wood blocks by type
    public static final Map<WoodTypeVariant, RegistryObject<Block>> LEAVES = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<RotatedPillarBlock>> LOGS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<RotatedPillarBlock>> STRIPPED_LOGS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<Block>> PLANKS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<SlabBlock>> SLABS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<StairBlock>> STAIRS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<DoorBlock>> DOORS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<TrapDoorBlock>> TRAPDOORS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<FenceBlock>> FENCES = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<FenceGateBlock>> FENCE_GATES = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<PressurePlateBlock>> PRESSURE_PLATES = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ButtonBlock>> BUTTONS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ModStandingSignBlock>> SIGNS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ModWallSignBlock>> WALL_SIGNS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ModCeilingHangingSignBlock>> HANGING_SIGNS = new HashMap<>();
    public static final Map<WoodTypeVariant, RegistryObject<ModWallHangingSignBlock>> WALL_HANGING_SIGNS = new HashMap<>();

    static {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            registerWoodSet(woodType);
        }
    }

    private static void registerWoodSet(WoodTypeVariant woodType) {
        String name = woodType.getName();
        WoodType mcWoodType = woodType.getWoodType();
        BlockSetType blockSetType = mcWoodType.setType();

        // Leaves
        LEAVES.put(woodType, BLOCKS.register(name + "_leaves",
                () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))));

        // Logs
        LOGS.put(woodType, BLOCKS.register(name + "_log",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))));

        STRIPPED_LOGS.put(woodType, BLOCKS.register("stripped_" + name + "_log",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG))));

        // Planks
        PLANKS.put(woodType, BLOCKS.register(name + "_planks",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));

        // Slabs and Stairs
        SLABS.put(woodType, BLOCKS.register(name + "_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB))));

        STAIRS.put(woodType, BLOCKS.register(name + "_stairs",
                () -> new StairBlock(() -> PLANKS.get(woodType).get().defaultBlockState(),
                        BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS))));

        // Doors and Trapdoors
        DOORS.put(woodType, BLOCKS.register(name + "_door",
                () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), blockSetType)));

        TRAPDOORS.put(woodType, BLOCKS.register(name + "_trapdoor",
                () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), blockSetType)));

        // Fences
        FENCES.put(woodType, BLOCKS.register(name + "_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE))));

        FENCE_GATES.put(woodType, BLOCKS.register(name + "_fence_gate",
                () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), mcWoodType)));

        // Pressure Plates and Buttons
        PRESSURE_PLATES.put(woodType, BLOCKS.register(name + "_pressure_plate",
                () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                        BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), blockSetType)));

        BUTTONS.put(woodType, BLOCKS.register(name + "_button",
                () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), blockSetType, 30, true)));

//        // Custom Signs
//        SIGNS.put(woodType, BLOCKS.register(name + "_sign",
//                () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), mcWoodType)));
//
//        WALL_SIGNS.put(woodType, BLOCKS.register(name + "_wall_sign",
//                () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), mcWoodType)));
//
//        // Custom Hanging Signs
//        HANGING_SIGNS.put(woodType, BLOCKS.register(name + "_hanging_sign",
//                () -> new ModCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), mcWoodType)));
//
//        WALL_HANGING_SIGNS.put(woodType, BLOCKS.register(name + "_wall_hanging_sign",
//                () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), mcWoodType)));
    }

    public static RegistryObject<Block> getLeaves(WoodTypeVariant woodType) {
        return LEAVES.get(woodType);
    }

    public static RegistryObject<RotatedPillarBlock> getLog(WoodTypeVariant woodType) {
        return LOGS.get(woodType);
    }

    public static RegistryObject<RotatedPillarBlock> getStrippedLog(WoodTypeVariant woodType) {
        return STRIPPED_LOGS.get(woodType);
    }

    public static RegistryObject<Block> getPlanks(WoodTypeVariant woodType) {
        return PLANKS.get(woodType);
    }
}