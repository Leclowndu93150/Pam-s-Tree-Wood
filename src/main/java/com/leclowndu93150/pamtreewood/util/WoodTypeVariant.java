package com.leclowndu93150.pamtreewood.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public enum WoodTypeVariant {
    ALMOND("almond"),
    APRICOT("apricot"),
    BANANA("banana"),
    CASHEW("cashew"),
    DATE("date"),
    DRAGONFRUIT("dragonfruit"),
    HAZELNUT("hazelnut"),
    JACKFRUIT("jackfruit"),
    MAPLE("maple"),
    NUTMEG("nutmeg"),
    OLIVE("olive"),
    PASSIONFRUIT("passionfruit"),
    PECAN("pecan"),
    PEPPERCORN("peppercorn"),
    PISTACHIO("pistachio"),
    RED_CHERRY("red_cherry"),
    TAMARIND("tamarind"),
    VANILLABEAN("vanillabean"),
    WALNUT("walnut");

    private final String name;
    private final WoodType woodType;

    WoodTypeVariant(String name) {
        this.name = name;
        this.woodType = WoodType.register(new WoodType(name, BlockSetTypeVariant.valueOf(name.toUpperCase()).getBlockSetType()));
    }

    public String getName() {
        return name;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    public ResourceLocation getTexture(String type) {
        return new ResourceLocation("pamtreewood", "block/" + name + "_" + type);
    }
}
