package com.kryptography.newworld.common.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class TombstoneBlockItem extends BlockItem {
    public TombstoneBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public ItemStack getCraftingRemainder(ItemStack itemStack) {
        return itemStack.copy();
    }
}
