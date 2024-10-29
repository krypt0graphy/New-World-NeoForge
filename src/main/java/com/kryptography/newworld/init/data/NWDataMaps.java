package com.kryptography.newworld.init.data;

import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class NWDataMaps extends DataMapProvider {
    protected NWDataMaps(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void gather() {
        var compostables = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        var fuel = this.builder(NeoForgeDataMaps.FURNACE_FUELS);

        compostables.add(NWBlocks.FIR_LEAVES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false);
        compostables.add(NWBlocks.FIR_SAPLING.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false);
    }

    public static void register() {

        registerFlammable(NWBlocks.FIR_PLANKS.get(), 5, 20);
        registerFlammable(NWBlocks.FIR_SLAB.get(), 5, 20);
        registerFlammable(NWBlocks.FIR_STAIRS.get(), 5, 20);
        registerFlammable(NWBlocks.FIR_FENCE.get(), 5, 20);
        registerFlammable(NWBlocks.FIR_FENCE_GATE.get(), 5, 20);
        registerFlammable(NWBlocks.FIR_LEAVES.get(), 30, 60);
        registerFlammable(NWBlocks.FIR_LOG.get(), 5, 5);
        registerFlammable(NWBlocks.FIR_WOOD.get(), 5, 5);
        registerFlammable(NWBlocks.STRIPPED_FIR_LOG.get(), 5, 5);
        registerFlammable(NWBlocks.STRIPPED_FIR_WOOD.get(), 5, 5);


    }

    public static void registerFlammable(Block block, int encouragement, int flammability) {
    FireBlock fire = (FireBlock) Blocks.FIRE;
    fire.setFlammable(block, encouragement, flammability);
    }
}
