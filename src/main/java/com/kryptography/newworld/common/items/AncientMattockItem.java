package com.kryptography.newworld.common.items;

import com.google.common.collect.Sets;
import com.kryptography.newworld.init.data.tags.NWBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AncientMattockItem extends DiggerItem {
    public static final Set<ToolAction> DEFAULT_MATTOCK_ABILITIES = Stream.of(
            ToolActions.AXE_DIG, ToolActions.SHOVEL_DIG, ToolActions.HOE_DIG, ToolActions.PICKAXE_DIG
    ).collect(Collectors.toCollection(Sets::newIdentityHashSet));

    public AncientMattockItem(float p_204108_, float p_204109_, Tier p_204110_, TagKey<Block> p_204111_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, p_204111_, p_204112_);
    }


    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction itemAbility) {
        return DEFAULT_MATTOCK_ABILITIES.contains(itemAbility);
    }
}
