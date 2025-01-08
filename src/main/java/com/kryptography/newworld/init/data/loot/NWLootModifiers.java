package com.kryptography.newworld.init.data.loot;


import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.data.loot.modifiers.AddItemModifier;
import com.kryptography.newworld.init.data.loot.modifiers.AddToPoolModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class NWLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NewWorld.MOD_ID);
    public static final RegistryObject<Codec<AddItemModifier>> ADD_ITEM = LOOT_MODIFIERS.register("add_item", AddItemModifier.CODEC);
    public static final RegistryObject<Codec<AddToPoolModifier>> ADD_TO_POOL = LOOT_MODIFIERS.register("add_to_pool", AddToPoolModifier.CODEC);
}
