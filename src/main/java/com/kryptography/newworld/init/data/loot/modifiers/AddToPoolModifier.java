package com.kryptography.newworld.init.data.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.function.Supplier;

public class AddToPoolModifier extends LootModifier {
    public static final Supplier<Codec<AddToPoolModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).and(
                            inst.group(
                                    ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter((m) -> m.itemAdded.asItem()),
                                    Codec.INT.optionalFieldOf("count", 1).forGetter((m) -> m.amountAdded),
                                    Codec.FLOAT.fieldOf("chance").forGetter((m) -> m.chance),
                                    Codec.BOOL.fieldOf("replace").forGetter((m) -> m.replace)
                            )
                    )
                    .apply(inst, AddToPoolModifier::new)));
    private Item itemAdded;
    private int amountAdded;
    private float chance;
    private boolean replace;

    public AddToPoolModifier(LootItemCondition[] conditionsIn, Item itemAdded, int amountAdded, float chance, boolean replace) {
        super(conditionsIn);
        this.itemAdded = itemAdded;
        this.amountAdded = amountAdded;
        this.chance = chance;
        this.replace = replace;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        if (context.getRandom().nextFloat() < chance) {
            if (replace) {
                generatedLoot.clear();
            }
            generatedLoot.add(new ItemStack(this.itemAdded));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
