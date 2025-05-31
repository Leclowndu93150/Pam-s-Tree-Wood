package com.leclowndu93150.pamtreewood;

import com.leclowndu93150.pamtreewood.datagen.DataGenerators;
import com.leclowndu93150.pamtreewood.init.ModBlockEntities;
import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.init.ModBoats;
import com.leclowndu93150.pamtreewood.init.ModCreativeTabs;
import com.leclowndu93150.pamtreewood.init.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PamTreeWood.MODID)
public class PamTreeWood {

    public static final String MODID = "pamtreewood";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PamTreeWood() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBoats.ENTITY_TYPES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::gatherData);
    }

    //TODO: Fences not having an item model (doesn't work)
    //TODO: Buttons having wrong perspective model, i only see the direct face
    //TODO: Can't strip the woods
    //TODO: Neither the signs not the models render (don't fix)

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerators.gatherData(event);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
}