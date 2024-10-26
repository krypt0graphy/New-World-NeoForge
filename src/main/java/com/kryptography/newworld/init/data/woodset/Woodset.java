package com.kryptography.newworld.init.data.woodset;

import com.kryptography.newworld.NewWorld;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class Woodset {
    public static final BlockSetType FIR_SET = BlockSetType.register(new BlockSetType(NewWorld.MOD_ID + ":fir"));
    public static final WoodType FIR_WOOD_TYPE = WoodType.register(new WoodType(NewWorld.MOD_ID + ":fir", FIR_SET));
}
