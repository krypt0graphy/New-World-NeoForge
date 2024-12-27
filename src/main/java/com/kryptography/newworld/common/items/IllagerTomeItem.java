package com.kryptography.newworld.common.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IllagerTomeItem extends Item {
    public IllagerTomeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCraftingRemainder(ItemStack itemStack) {
        return itemStack.copy();
    }
}
