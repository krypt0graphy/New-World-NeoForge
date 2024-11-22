package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;


import java.util.HashMap;
import java.util.Map;

public class NWStats {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, NewWorld.MOD_ID);

    private static final Map<ResourceLocation, StatFormatter> FORMATTERS = new HashMap<>();
    public static final DeferredHolder<ResourceLocation, ResourceLocation> TOMBSTONE_ACTIVATION = register("tombstone_activation");
    private static DeferredHolder<ResourceLocation, ResourceLocation> register(String name, StatFormatter formatter) {
        ResourceLocation resourceLocation = NewWorld.id(name);
        DeferredHolder<ResourceLocation, ResourceLocation> holder = STATS.register(name, () -> resourceLocation);
        FORMATTERS.put(resourceLocation, formatter);
        return holder;
    }

    private static DeferredHolder<ResourceLocation, ResourceLocation> register(String name) {
        return register(name, StatFormatter.DEFAULT);
    }

    public static void init() {}

    @ApiStatus.Internal
    public static void registerFormatter() {
        FORMATTERS.forEach(Stats.CUSTOM::get);
    }
}
