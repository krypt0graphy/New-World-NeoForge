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
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.function.Supplier;

public class AddToPoolModifier extends LootModifier {

    private Item itemAdded;
    private float chance;
    private boolean replace;

    public static final Supplier<MapCodec<AddToPoolModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(lm -> lm.itemAdded))
                    .and(Codec.FLOAT.fieldOf("chance_to_replace").forGetter(modifier -> modifier.chance))
                    .and(Codec.BOOL.fieldOf("replace").forGetter(modifier -> modifier.replace))
                    .apply(instance, AddToPoolModifier::new)));

    public AddToPoolModifier(LootItemCondition[] conditionsIn, Item itemAdded, float chance, boolean replace) {
        super(conditionsIn);
        this.itemAdded = itemAdded;
        this.chance = chance;
        this.replace = replace;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for(LootItemCondition condition : this.conditions) {
            if(!condition.test(context)) {
                return generatedLoot;
            }
        }

        if(context.getRandom().nextFloat() < chance) {
            if (replace) {
                generatedLoot.clear();
            }
            generatedLoot.add(new ItemStack(this.itemAdded));
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
