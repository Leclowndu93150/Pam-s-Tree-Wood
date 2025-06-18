package com.leclowndu93150.pamtreewood.mixin;

import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import com.pam.pamhc2trees.worldgen.placers.*;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = com.pam.pamhc2trees.worldgen.ConfiguredFeatures.class, remap = false)
public abstract class PamConfiguredFeaturesMixin {

    @ModifyArg(method = "bootstrap(Lnet/minecraft/data/worldgen/BootstapContext;)V", at = @At(value = "INVOKE", target = "Lcom/pam/pamhc2trees/worldgen/ConfiguredFeatures;register(Lnet/minecraft/data/worldgen/BootstapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)V"), index = 3)
    private static FeatureConfiguration pamtreewood_modifyTreeConfiguration(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> pKey, Feature<TreeConfiguration> pFeature, FeatureConfiguration originalConfig) {
        if (originalConfig instanceof TreeConfiguration originalTreeConfig) {
            FoliagePlacer foliagePlacer = originalTreeConfig.foliagePlacer;
            WoodTypeVariant targetWoodType = null;

            
            if (foliagePlacer instanceof AlmondFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.ALMOND;
            } else if (foliagePlacer instanceof ApricotFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.APRICOT;
            } else if (foliagePlacer instanceof BananaFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.BANANA;
            } else if (foliagePlacer instanceof CashewFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.CASHEW;
            } else if (foliagePlacer instanceof DateFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.DATE;
            } else if (foliagePlacer instanceof DragonfruitFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.DRAGONFRUIT;
            } else if (foliagePlacer instanceof HazelnutFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.HAZELNUT;
            } else if (foliagePlacer instanceof JackfruitFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.JACKFRUIT;
            } else if (foliagePlacer instanceof NutmegFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.NUTMEG;
            } else if (foliagePlacer instanceof OliveFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.OLIVE;
            } else if (foliagePlacer instanceof PassionfruitFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.PASSIONFRUIT;
            } else if (foliagePlacer instanceof PecanFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.PECAN;
            } else if (foliagePlacer instanceof PeppercornFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.PEPPERCORN;
            } else if (foliagePlacer instanceof PistachioFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.PISTACHIO;
            } else if (foliagePlacer instanceof CherryFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.RED_CHERRY;
            } else if (foliagePlacer instanceof TamarindFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.TAMARIND;
            } else if (foliagePlacer instanceof VanillabeanFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.VANILLABEAN;
            } else if (foliagePlacer instanceof WalnutFruitBlockFoliagePlacer) {
                targetWoodType = WoodTypeVariant.WALNUT;
            } else if (foliagePlacer instanceof MapleFruitTrunkPlacer) {
                targetWoodType = WoodTypeVariant.MAPLE;
            }

            if (targetWoodType != null && ModBlocks.LOGS.containsKey(targetWoodType)) {
                Block newLogBlock = ModBlocks.LOGS.get(targetWoodType).get();
                BlockStateProvider newTrunkProvider = BlockStateProvider.simple(newLogBlock.defaultBlockState());

                TreeConfiguration.TreeConfigurationBuilder builder = new TreeConfiguration.TreeConfigurationBuilder(
                        newTrunkProvider,
                        originalTreeConfig.trunkPlacer,
                        originalTreeConfig.foliageProvider,
                        originalTreeConfig.foliagePlacer,
                        originalTreeConfig.minimumSize
                );

                if (!originalTreeConfig.decorators.isEmpty()) {
                    builder.decorators(originalTreeConfig.decorators);
                }

                if (originalTreeConfig.ignoreVines) {
                    builder.ignoreVines();
                }
                if (originalTreeConfig.forceDirt) {
                    builder.forceDirt();
                }

                return builder.build();
            }
        }
        return originalConfig;
    }
}