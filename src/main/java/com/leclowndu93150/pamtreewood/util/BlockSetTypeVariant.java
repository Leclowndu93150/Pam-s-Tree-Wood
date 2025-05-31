package com.leclowndu93150.pamtreewood.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public enum BlockSetTypeVariant {
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
    private final BlockSetType blockSetType;

    BlockSetTypeVariant(String name) {
        this.name = name;
        this.blockSetType = BlockSetType.register(new BlockSetType(name));
    }

    public String getName() {
        return name;
    }

    public BlockSetType getBlockSetType() {
        return blockSetType;
    }
}