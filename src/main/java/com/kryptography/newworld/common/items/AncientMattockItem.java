package com.kryptography.newworld.common.items;

import com.kryptography.newworld.init.data.tags.NWBlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;


public class AncientMattockItem extends DiggerItem {
    private static final ItemAbility MATTOCK_DIG = ItemAbility.get("mattock_dig");
    public static final Set<ItemAbility> DEFAULT_MATTOCK_ABILITIES = Set.of(
            ItemAbilities.AXE_DIG, ItemAbilities.SHOVEL_DIG, ItemAbilities.HOE_DIG, ItemAbilities.PICKAXE_DIG

    );

    public AncientMattockItem(Tier pTier, Properties pProperties) {
        super(pTier, NWBlockTags.MATTOCK_MINEABLE, pProperties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return DEFAULT_MATTOCK_ABILITIES.contains(itemAbility) || itemAbility == MATTOCK_DIG;
    }
}
