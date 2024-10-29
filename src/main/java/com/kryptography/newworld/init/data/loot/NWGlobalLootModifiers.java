package com.kryptography.newworld.init.data.loot;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.loot.modifiers.AddItemModifier;
import com.kryptography.newworld.init.data.loot.modifiers.ArchaeologyLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class NWGlobalLootModifiers extends GlobalLootModifierProvider {
    public NWGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NewWorld.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("mattock_crafting_template_head_ancient_city", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.15F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY.location()).build()
        }, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), 1));
        this.add("mattock_crafting_template_head_stronghold_corridor", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.15F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.STRONGHOLD_CORRIDOR.location()).build()
        }, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), 1));
        this.add("mattock_crafting_template_shaft_desert_pyramid_chest", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.15F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID.location()).build()
        }, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get(), 1));

        this.add("mattock_crafting_template_shaft_trail_ruins_rare",
                new ArchaeologyLootModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE.location()).build()
                }, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get(), 0.077F));
        this.add("mattock_crafting_template_shaft_desert_archaeology",
                new ArchaeologyLootModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY.location()).build()
                }, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get(), 0.11F));

        this.add("illager_tome_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                LootItemRandomChanceCondition.randomChance(0.85F).build(),
                LootTableIdCondition.builder(BuiltInLootTables.WOODLAND_MANSION.location()).build()
        }, NWItems.ILLAGER_TOME.get(), 1));
    }
}
