package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.datagens.NWBlockStateProvider;
import com.kryptography.newworld.common.datagens.NWDataMapProvider;
import com.kryptography.newworld.common.datagens.NWItemModelProvider;
import com.kryptography.newworld.common.datagens.NWRecipeProvider;
import com.kryptography.newworld.common.datagens.loot.NWBlockLootProvider;
import com.kryptography.newworld.common.datagens.loot.NWGlobalLootModifierProvider;
import com.kryptography.newworld.common.datagens.tags.NWBiomeTagsProvider;
import com.kryptography.newworld.common.datagens.tags.NWBlockTagsProvider;
import com.kryptography.newworld.common.datagens.tags.NWItemTagsProvider;
import com.kryptography.newworld.init.worldgen.NWWorldgenData;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NewWorld.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NWData {



    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();


        DatapackBuiltinEntriesProvider datapackProvider = new NWWorldgenData(packOutput, lookup);
        event.getGenerator().addProvider(event.includeServer(), datapackProvider);

        BlockTagsProvider blockTagsProvider = new NWBlockTagsProvider(packOutput, lookup,existingFileHelper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeClient(), new NWItemTagsProvider(packOutput, lookup, blockTagsProvider.contentsGetter(), existingFileHelper));
        gen.addProvider(event.includeClient(), new NWItemModelProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(NWBlockLootProvider::new, LootContextParamSets.BLOCK)), lookup));
        gen.addProvider(event.includeClient(), new NWBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new NWDataMapProvider(packOutput, lookup));
        gen.addProvider(event.includeServer(), new NWRecipeProvider(packOutput, lookup));
        gen.addProvider(event.includeServer(), new NWGlobalLootModifierProvider(packOutput, lookup));
        gen.addProvider(event.includeServer(), new NWBiomeTagsProvider(packOutput, datapackProvider.getRegistryProvider(),existingFileHelper));
    }

    private static HolderLookup.Provider constructRegistries(HolderLookup.Provider original, RegistrySetBuilder datapackEntriesBuilder)
    {
        Cloner.Factory clonerFactory = new Cloner.Factory();
        var builderKeys = new HashSet<>(datapackEntriesBuilder.getEntryKeys());
        RegistryDataLoader.WORLDGEN_REGISTRIES.stream().forEach(data -> {
            // Add keys for missing registries
            if (!builderKeys.contains(data.key()))
                datapackEntriesBuilder.add(data.key(), context -> {});

            data.runWithArguments(clonerFactory::addCodec);
        });

        return datapackEntriesBuilder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original, clonerFactory).patches();
    }
}
