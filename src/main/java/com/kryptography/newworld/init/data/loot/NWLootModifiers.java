package com.kryptography.newworld.init.data.loot;


import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.data.loot.modifiers.AddItemModifier;
import com.kryptography.newworld.init.data.loot.modifiers.AddToPoolModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NWLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NewWorld.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddItemModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", AddItemModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddToPoolModifier>> ADD_TO_POOL = LOOT_MODIFIERS.register("add_to_pool", AddToPoolModifier.CODEC);
}
