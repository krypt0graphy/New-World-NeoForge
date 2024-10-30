package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.data.loot.NWBlockLootTables;
import com.kryptography.newworld.init.data.loot.NWGlobalLootModifiers;
import com.kryptography.newworld.init.data.tags.NWBlockTagsProvider;
import com.kryptography.newworld.init.data.tags.NWItemTagsProvider;
import com.kryptography.newworld.init.worldgen.NWBiomeModifiers;
import com.kryptography.newworld.init.worldgen.NWWorldgenData;
import com.kryptography.newworld.init.worldgen.features.NWConfiguredFeatures;
import com.kryptography.newworld.init.worldgen.features.NWPlacedFeatures;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        gen.addProvider(event.includeClient(), new NWItemModels(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(NWBlockLootTables::new, LootContextParamSets.BLOCK)), lookup));
        gen.addProvider(event.includeClient(), new NWBlockStates(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new NWDataMaps(packOutput, lookup));
        gen.addProvider(event.includeServer(), new NWRecipeProvider(packOutput, lookup));
        gen.addProvider(event.includeServer(), new NWGlobalLootModifiers(packOutput, lookup));
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
