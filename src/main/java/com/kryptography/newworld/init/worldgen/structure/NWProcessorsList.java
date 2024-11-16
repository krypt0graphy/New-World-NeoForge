package com.kryptography.newworld.init.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.kryptography.newworld.NewWorld;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import java.util.List;

public class NWProcessorsList {

    public static final ResourceKey<StructureProcessorList> BURIED_BUNKER = registerKey("buried_bunker");


    public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);

        register(context, BURIED_BUNKER,
                ImmutableList.of(new RuleProcessor(List.of(
                        new ProcessorRule(new RandomBlockMatchTest(Blocks.STONE, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.INFESTED_STONE.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(Blocks.DIRT, 0.1f), AlwaysTrueTest.INSTANCE, Blocks.ROOTED_DIRT.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(Blocks.DIRT, 0.1f), AlwaysTrueTest.INSTANCE, Blocks.COARSE_DIRT.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(Blocks.DIRT, 0.05f), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
                        new ProcessorRule(new RandomBlockMatchTest(Blocks.CHAIN, 0.1f), AlwaysTrueTest.INSTANCE, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true)),
                        new ProcessorRule(new RandomBlockMatchTest(Blocks.MOSSY_STONE_BRICK_STAIRS, 0.8f), AlwaysTrueTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState())
                        ))));


    }












    private static void register(BootstrapContext<StructureProcessorList> pContext, ResourceKey<StructureProcessorList> pKey, List<StructureProcessor> pProcessors) {
        pContext.register(pKey, new StructureProcessorList(pProcessors));
    }

    public static ResourceKey<StructureProcessorList> registerKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
