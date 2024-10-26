package com.kryptography.newworld.common.items;

import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IllagerTomeItem extends Item {
    public IllagerTomeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(NWItems.ILLAGER_TOME.get());
    }
}
