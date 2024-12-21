package com.kryptography.newworld.integration;

import com.kryptography.newworld.common.blocks.FirTrimmedPlankBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class NMLIntegration {

    public static Supplier<? extends Block> trimmedPlanks() {
        return () -> new FirTrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    }
}
