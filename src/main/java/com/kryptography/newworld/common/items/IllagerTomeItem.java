package com.kryptography.newworld.common.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IllagerTomeItem extends Item {
    public IllagerTomeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }
}
