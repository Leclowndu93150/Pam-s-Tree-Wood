package com.leclowndu93150.pamtreewood.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class DataGenerators {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        
        ExistingFileHelper lenientHelper = new LenientExistingFileHelper();

        
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, lenientHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, lenientHelper));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput, "en_us"));

        
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(packOutput));

        ModBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(),
                new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));
    }

    private static class LenientExistingFileHelper extends ExistingFileHelper {

        public LenientExistingFileHelper() {
            super(Collections.emptyList(), Collections.emptySet(), false, null, null);
        }

        @Override
        public boolean exists(ResourceLocation loc, IResourceType type) {
            return true;
        }

        @Override
        public boolean exists(ResourceLocation loc, PackType packType, String pathSuffix, String pathPrefix) {
            return true;
        }

        @Override
        public boolean exists(ResourceLocation loc, PackType packType) {
            return true;
        }
    }
}