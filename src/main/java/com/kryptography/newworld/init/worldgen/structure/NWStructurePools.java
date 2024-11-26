package com.kryptography.newworld.init.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.kryptography.newworld.NewWorld;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;


public class NWStructurePools {

    public static final ResourceKey<StructureTemplatePool> BURIED_BUNKER = registerKey("buried_bunker");

    private static final ResourceKey<StructureTemplatePool> EMPTY = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.withDefaultNamespace("empty"));

    public static void bootstrap(BootstrapContext<StructureTemplatePool> bootstrap) {

        HolderGetter<StructureTemplatePool> templateLookup = bootstrap.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> empty = templateLookup.getOrThrow(EMPTY);
        HolderGetter<StructureProcessorList> listLookup = bootstrap.lookup(Registries.PROCESSOR_LIST);
        Holder<StructureProcessorList> bunkerList = listLookup.getOrThrow(NWProcessorsList.BURIED_BUNKER);

        bootstrap.register(
                BURIED_BUNKER,
                new StructureTemplatePool(
                        empty,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single(NewWorld.id("buried_bunker").toString(), bunkerList), 1),
                                Pair.of(StructurePoolElement.single(NewWorld.id("buried_bunker_empty").toString(), bunkerList), 10)
                        ),
                StructureTemplatePool.Projection.RIGID
                )
        );
    }

    public static ResourceKey<StructureTemplatePool> registerKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL,  NewWorld.id(name));
    }

}
