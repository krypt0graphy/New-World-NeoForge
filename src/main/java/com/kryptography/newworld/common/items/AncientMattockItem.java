package com.kryptography.newworld.common.items;

import com.google.common.collect.Sets;
import com.kryptography.newworld.init.data.tags.NWBlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AncientMattockItem extends DiggerItem {
    public static final Set<ItemAbility> DEFAULT_MATTOCK_ABILITIES = Stream.of(
            ItemAbilities.AXE_DIG, ItemAbilities.SHOVEL_DIG, ItemAbilities.HOE_DIG, ItemAbilities.PICKAXE_DIG
    ).collect(Collectors.toCollection(Sets::newIdentityHashSet));

    public AncientMattockItem(Tier pTier, Properties pProperties) {
        super(pTier, NWBlockTags.MATTOCK_MINEABLE, pProperties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return DEFAULT_MATTOCK_ABILITIES.contains(itemAbility);
    }
}
