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
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.function.Supplier;

public class AddItemModifier extends LootModifier {
    public static final Supplier<Codec<AddItemModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).and(
                            inst.group(
                                    ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter((m) -> m.itemAdded.asItem()),
                                    Codec.INT.optionalFieldOf("count", 1).forGetter((m) -> m.amountAdded)
                            )
                    )
                    .apply(inst, AddItemModifier::new)));



    private Item itemAdded;
    private int amountAdded;

    public AddItemModifier(LootItemCondition[] conditionsIn, Item itemAdded, int amountAdded) {
        super(conditionsIn);
        this.itemAdded = itemAdded;
        this.amountAdded = amountAdded;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(this.itemAdded, this.amountAdded));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
