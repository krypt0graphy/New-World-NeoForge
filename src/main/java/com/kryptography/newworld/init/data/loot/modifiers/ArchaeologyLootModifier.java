package com.kryptography.newworld.init.data.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.function.Supplier;

public class ArchaeologyLootModifier extends LootModifier {
    private Item itemAdded;
    private float chance;

    public static final Supplier<MapCodec<ArchaeologyLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(lm -> lm.itemAdded))
                    .and(Codec.FLOAT.fieldOf("chance_to_replace").forGetter(modifier -> modifier.chance))
                    .apply(instance, ArchaeologyLootModifier::new)));

    public ArchaeologyLootModifier(LootItemCondition[] conditionsIn, Item itemAdded, float chance) {
        super(conditionsIn);
        this.itemAdded = itemAdded;
        this.chance = chance;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for(LootItemCondition condition : this.conditions) {
            if(!condition.test(context)) {
                return generatedLoot;
            }
        }

        if(context.getRandom().nextFloat() < chance) {
            generatedLoot.clear();
            generatedLoot.add(new ItemStack(this.itemAdded));
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
