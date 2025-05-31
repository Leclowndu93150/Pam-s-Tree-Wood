package com.leclowndu93150.pamtreewood.client.renderer;

import com.leclowndu93150.pamtreewood.PamTreeWood;
import com.leclowndu93150.pamtreewood.entity.ModChestBoatEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModChestBoatRenderer extends BoatRenderer {

    public ModChestBoatRenderer(EntityRendererProvider.Context context) {
        super(context, true);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (boat instanceof ModChestBoatEntity modChestBoat) {
            Pair<ResourceLocation, ListModel<Boat>> vanillaPair = super.getModelWithLocation(boat);
            ResourceLocation customTexture = new ResourceLocation(PamTreeWood.MODID,
                    "textures/entity/chest_boat/" + modChestBoat.getModWoodType().getName() + ".png");
            return Pair.of(customTexture, vanillaPair.getSecond());
        }
        return super.getModelWithLocation(boat);
    }
}