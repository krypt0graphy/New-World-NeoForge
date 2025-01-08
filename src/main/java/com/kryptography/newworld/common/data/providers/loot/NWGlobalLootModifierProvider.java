package com.kryptography.newworld.common.data.providers.loot;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.loot.modifiers.AddItemModifier;
import com.kryptography.newworld.init.data.loot.modifiers.AddToPoolModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;


import java.util.concurrent.CompletableFuture;

public class NWGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public NWGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, NewWorld.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("mattock_crafting_template_head_ancient_city", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.15F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY).build()
        }, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), 1));
        this.add("mattock_crafting_template_head_stronghold_corridor", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.15F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.STRONGHOLD_CORRIDOR).build()
        }, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), 1));
        this.add("mattock_crafting_template_shaft_desert_pyramid_chest", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.15F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID).build()
        }, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get(), 1));

        this.add("mattock_crafting_template_shaft_trail_ruins_rare",
                new AddToPoolModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE).build()
                }, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get(), 1, 0.077F, true));
        this.add("mattock_crafting_template_shaft_desert_archaeology",
                new AddToPoolModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY).build()
                }, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get(), 1, 0.11F, true));

        this.add("illager_tome_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.85F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.WOODLAND_MANSION).build()
        }, NWItems.ILLAGER_TOME.get(), 1));
        this.add("tombstone_ancient_city", new AddToPoolModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY).build()
        }, NWBlocks.TOMBSTONE.get().asItem(), 1, 0.082F, false));
    }
}
