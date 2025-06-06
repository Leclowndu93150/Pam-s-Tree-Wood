package com.leclowndu93150.pamtreewood;

import com.leclowndu93150.pamtreewood.datagen.DataGenerators;
import com.leclowndu93150.pamtreewood.init.ModBlockEntities;
import com.leclowndu93150.pamtreewood.init.ModBlocks;
import com.leclowndu93150.pamtreewood.init.ModBoats;
import com.leclowndu93150.pamtreewood.init.ModCreativeTabs;
import com.leclowndu93150.pamtreewood.init.ModItems;
import com.leclowndu93150.pamtreewood.util.WoodTypeVariant;
import com.mojang.logging.LogUtils;
import com.pam.pamhc2trees.worldgen.growers.PamSoursopTreeGrower;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(PamTreeWood.MODID)
public class PamTreeWood {

    public static final String MODID = "pamtreewood";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<Block, Block> MOD_STRIPPABLES = new HashMap<>();

    public PamTreeWood() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBoats.ENTITY_TYPES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::commonSetup);
    }

    //TODO: Neither the signs not the models render (don't fix)


    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            for (WoodTypeVariant woodType : WoodTypeVariant.values()) {
                MOD_STRIPPABLES.put(ModBlocks.LOGS.get(woodType).get(), ModBlocks.STRIPPED_LOGS.get(woodType).get());
                MOD_STRIPPABLES.put(ModBlocks.WOOD.get(woodType).get(), ModBlocks.STRIPPED_WOOD.get(woodType).get());
            }
        });
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemStack = event.getItemStack();
        if (!(itemStack.getItem() instanceof AxeItem)) return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Block strippedBlock = MOD_STRIPPABLES.get(state.getBlock());

        if (strippedBlock != null) {
            BlockState newState = strippedBlock.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));

            level.playSound(event.getEntity(), pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(pos, newState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(event.getEntity(), newState));

            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
            }

            itemStack.hurtAndBreak(1, event.getEntity(), (player) -> player.broadcastBreakEvent(event.getHand()));
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerators.gatherData(event);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }



}