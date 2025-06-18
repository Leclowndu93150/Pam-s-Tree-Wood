package com.leclowndu93150.pamtreewood.client;

import com.leclowndu93150.pamtreewood.client.renderer.ModBoatRenderer;
import com.leclowndu93150.pamtreewood.client.renderer.ModChestBoatRenderer;
import com.leclowndu93150.pamtreewood.init.ModBlockEntities;
import com.leclowndu93150.pamtreewood.init.ModBoats;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                Sheets.addWoodType(woodType.getWoodType());
            }
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
            event.registerEntityRenderer(ModBoats.getBoatEntity(woodType).get(),
                    context -> new ModBoatRenderer(context, false));
            event.registerEntityRenderer(ModBoats.getChestBoatEntity(woodType).get(),
                    ModChestBoatRenderer::new);
        }
        
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}