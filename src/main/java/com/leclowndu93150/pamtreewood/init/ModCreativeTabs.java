package com.leclowndu93150.pamtreewood.init;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PamTreeWood.MODID);

    public static final RegistryObject<CreativeModeTab> PAM_TREE_WOOD_TAB = CREATIVE_MODE_TABS.register("pam_tree_wood",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pamtreewood.pam_tree_wood"))
                    .icon(() -> new ItemStack(ModItems.getLogItem(WoodTypeVariant.MAPLE).get()))
                    .displayItems((parameters, output) -> {
                        // Add all wood items to creative tab
                        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                            // Leaves
                            output.accept(ModItems.getLeavesItem(woodType).get());

                            // Logs
                            output.accept(ModItems.getLogItem(woodType).get());
                            output.accept(ModItems.getWoodItem(woodType).get());
                            output.accept(ModItems.getStrippedLogItem(woodType).get());
                            output.accept(ModItems.getStrippedWoodItem(woodType).get());

                            // Planks and derivatives
                            output.accept(ModItems.getPlankItem(woodType).get());
                            output.accept(ModItems.SLAB_ITEMS.get(woodType).get());
                            output.accept(ModItems.STAIR_ITEMS.get(woodType).get());

                            // Doors and trapdoors
                            output.accept(ModItems.DOOR_ITEMS.get(woodType).get());
                            output.accept(ModItems.TRAPDOOR_ITEMS.get(woodType).get());

                            // Fences
                            output.accept(ModItems.FENCE_ITEMS.get(woodType).get());
                            output.accept(ModItems.FENCE_GATE_ITEMS.get(woodType).get());

                            // Redstone components
                            output.accept(ModItems.PRESSURE_PLATE_ITEMS.get(woodType).get());
                            output.accept(ModItems.BUTTON_ITEMS.get(woodType).get());

                            // Signs
                            output.accept(ModItems.SIGN_ITEMS.get(woodType).get());
                            output.accept(ModItems.HANGING_SIGN_ITEMS.get(woodType).get());

                            // Boats
                            output.accept(ModItems.BOAT_ITEMS.get(woodType).get());
                            output.accept(ModItems.CHEST_BOAT_ITEMS.get(woodType).get());
                        }
                    })
                    .build());
}