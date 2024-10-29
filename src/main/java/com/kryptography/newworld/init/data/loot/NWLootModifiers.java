package com.kryptography.newworld.init.data.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.data.loot.modifiers.AddItemModifier;
import com.kryptography.newworld.init.data.loot.modifiers.ArchaeologyLootModifier;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NWLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NewWorld.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddItemModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", AddItemModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ArchaeologyLootModifier>> ARCHAEOLOGY_LOOT = LOOT_MODIFIERS.register("archaeology_loot", ArchaeologyLootModifier.CODEC);
}
